package com.tracking.management.system.trackingmicroservice.service;

import com.tracking.management.system.trackingmicroservice.dto.TarifDto;
import com.tracking.management.system.trackingmicroservice.model.Tarif;
import com.tracking.management.system.trackingmicroservice.repository.TarifRepository;
import org.modelmapper.ModelMapper;
import org.springdoc.core.service.RequestBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TarifService {
    @Autowired
    private TarifRepository repository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RequestBodyService requestBodyBuilder;

    public Tarif findByUf(String uf){
        return repository.findByUfEquals(uf);
    }

    public ResponseEntity<?> insertTarif(TarifDto dto){
        if(repository.findByUfEquals(dto.getUf().toString()) != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarifa já cadastrada!");
        }
        Tarif entity = repository.save(modelMapper.map(dto, Tarif.class));
        return ResponseEntity.ok(modelMapper.map(entity, TarifDto.class));
    }

    public ResponseEntity<?> updateTarif(TarifDto dto){
        Tarif entity = repository.findByUfEquals(dto.getUf().toString());

        if(entity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status inválido!");
        }

        entity.setDeadline(dto.getDeadline());
        entity.setValue(dto.getValue());

        repository.save(entity);

        return ResponseEntity.ok(modelMapper.map(entity, TarifDto.class));
    }

}
