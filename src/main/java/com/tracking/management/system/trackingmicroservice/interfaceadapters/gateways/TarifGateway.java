package com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.controller.ViaCepController;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.TarifPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.TarifDto;
import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.frameworks.db.TarifRepository;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.viacep.ViaCepResponse;
import com.tracking.management.system.trackingmicroservice.usercase.CalcShipmentUsercase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.findByUfEquals(uf);
    }

    public ResponseEntity<?> insertTarif(TarifDto dto){
        if(repository.findByUfEquals(dto.getUf().toString()) != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarifa já cadastrada!");
        }
        Tarif entity = repository.save(tarifPresenter.mapToEntity(dto));
        return ResponseEntity.ok(tarifPresenter.mapToDto(entity));
    }

    public ResponseEntity<?> updateTarif(TarifDto dto){
        Tarif entity = repository.findByUfEquals(dto.getUf().toString());

        if(entity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status inválido!");
        }

        entity.setDeadline(dto.getDeadline());
        entity.setValue(dto.getValue());

        repository.save(entity);

        return ResponseEntity.ok(tarifPresenter.mapToDto(entity));
    }

    public TarifDto findByCep(String cep){
        ViaCepResponse response = viaCepService.validCep(cep);
        Tarif tarif = findByUf(response.getUf());

        return tarifPresenter.mapToDto(tarif);
    }

    public TarifDto calculateTarif(String cep, List<ShipmentDto> dto){
        Tarif tarif = tarifPresenter.mapToEntity(findByCep(cep));

        tarif.setValue(calcShipmentUsercase.calcShipment(dto, tarif));

        return tarifPresenter.mapToDto(tarif);
    }
}
