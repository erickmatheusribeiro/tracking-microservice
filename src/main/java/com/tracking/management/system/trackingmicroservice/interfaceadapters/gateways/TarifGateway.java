package com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.TarifPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.TarifDto;
import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.frameworks.db.TarifRepository;
import org.springdoc.core.service.RequestBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TarifGateway {
    @Autowired
    private TarifRepository repository;

    @Autowired
    private TarifPresenter terifPresenter;
    @Autowired
    private RequestBodyService requestBodyBuilder;

    public Tarif findByUf(String uf){
        return repository.findByUfEquals(uf);
    }

    public ResponseEntity<?> insertTarif(TarifDto dto){
        if(repository.findByUfEquals(dto.getUf().toString()) != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarifa já cadastrada!");
        }
        Tarif entity = repository.save(terifPresenter.mapToEntity(dto));
        return ResponseEntity.ok(terifPresenter.mapToDto(entity));
    }

    public ResponseEntity<?> updateTarif(TarifDto dto){
        Tarif entity = repository.findByUfEquals(dto.getUf().toString());

        if(entity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status inválido!");
        }

        entity.setDeadline(dto.getDeadline());
        entity.setValue(dto.getValue());

        repository.save(entity);

        return ResponseEntity.ok(terifPresenter.mapToDto(entity));
    }

}
