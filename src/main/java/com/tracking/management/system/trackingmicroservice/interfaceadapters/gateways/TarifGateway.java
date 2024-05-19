package com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.controller.ViaCepController;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.TarifPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.TarifDto;
import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.frameworks.db.TarifRepository;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.viacep.ViaCepResponse;
import com.tracking.management.system.trackingmicroservice.usercase.CalcShipmentUsercase;
import com.tracking.management.system.trackingmicroservice.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TarifGateway {
    @Autowired
    private TarifRepository repository;

    @Autowired
    private TarifPresenter tarifPresenter;

    @Autowired
    private ViaCepController viaCepService;

    @Autowired
    private CalcShipmentUsercase calcShipmentUsercase;

    public Tarif findByUf(String uf){
        return repository.findByUfEquals(uf)
                .orElseThrow(() -> new NoSuchElementException(MessageUtil.getMessage("TARIFA_NAO_ENCONTRADA", uf)));
    }

    public ResponseEntity<?> insertTarif(TarifDto dto) {
                if (repository.findByUfEquals(dto.getUf().toString()).isPresent()) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarifa já cadastrada!");
        }
        Tarif entity = repository.save(tarifPresenter.mapToEntity(dto));
        return ResponseEntity.ok(tarifPresenter.mapToDto(entity));
    }

    public ResponseEntity<?> updateTarif(TarifDto dto){
        Optional<Tarif> optionalTarif = repository.findByUfEquals(dto.getUf().toString());

        if(optionalTarif.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status inválido!");
        }
        Tarif entity = optionalTarif.get();

        entity.setDeadline(dto.getDeadline());
        entity.setValue(dto.getValue());

        repository.save(entity);

        return ResponseEntity.ok(tarifPresenter.mapToDto(entity));
    }

    public TarifDto findByCep(String cep){
        try {
            ViaCepResponse response = viaCepService.validCep(cep);
            Tarif tarif = findByUf(response.getUf());
            return tarifPresenter.mapToDto(tarif);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado uma tarifa para o CEP " + cep);
        }
    }

    public TarifDto calculateTarif(String cep, List<ShipmentDto> dto){
        Tarif tarif = tarifPresenter.mapToEntity(findByCep(cep));

        Double value = calcShipmentUsercase.calcShipment(dto, tarif);
        tarif.setValue(value);

        return tarifPresenter.mapToDto(tarif);
    }
}
