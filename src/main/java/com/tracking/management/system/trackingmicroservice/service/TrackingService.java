package com.tracking.management.system.trackingmicroservice.service;

import com.tracking.management.system.trackingmicroservice.dto.DeliveryDto;
import com.tracking.management.system.trackingmicroservice.dto.TarifDto;
import com.tracking.management.system.trackingmicroservice.dto.viacep.ViaCepResponse;
import com.tracking.management.system.trackingmicroservice.model.Delivery;
import com.tracking.management.system.trackingmicroservice.model.Tarif;
import com.tracking.management.system.trackingmicroservice.repository.DeliveryRepository;
import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Random;


@Service
public class TrackingService {
    @Autowired
    private DeliveryRepository repository;

    @Autowired
    private TarifService tarifService;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> findByTrackingCode(String code){
        Delivery delivery = findTrackingCode(code);

        if(delivery == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Código de rastreio não encontrado");
        }

        return ResponseEntity.ok(delivery);
    }

    public TarifDto listCep(String cep){
        ViaCepResponse response = viaCepService.validCep(cep);
        Tarif tarif = tarifService.findByUf(response.getUf());

        return modelMapper.map(tarif, TarifDto.class);
    }

    public Delivery findTrackingCode (String code){
        Delivery delivery = repository.findByTrakingCodeEquals(code);
        return delivery;
    }

    public DeliveryDto insertDelivery(String cep, Integer clienteId){
        Delivery entity = new Delivery();

        Tarif tarif = tarifService.findByUf(listCep(cep).getUf().toString());

        String trackingCode = generateTrackingCode();
        while(findTrackingCode(trackingCode) != null){
            trackingCode = generateTrackingCode();
        }

        entity.setStatus(Status.PENDENTE);
        entity.setTarif(tarif);
        entity.setClienteId(clienteId);
        entity.setDateCreate(LocalDate.now());
        entity.setTrakingCode(trackingCode);
        entity.setDateEnd(LocalDate.now().plusDays(tarif.getDeadline()));

        repository.save(entity);

        return modelMapper.map(entity, DeliveryDto.class);
    }

    public DeliveryDto deleteDelivery(String code){
        Delivery entity = repository.findByTrakingCodeEquals(code);

        entity.setStatus(Status.CANCELADO);
        repository.save(entity);

        return modelMapper.map(entity, DeliveryDto.class);
    }

    public ResponseEntity<?> updateDelivery(String code, Status status){
        Delivery entity = repository.findByTrakingCodeEquals(code);

        entity.setStatus(status);
        repository.save(entity);

        return ResponseEntity.ok(modelMapper.map(entity, DeliveryDto.class));
    }


    public String generateTrackingCode(){
        Random random = new Random();
        long randomNumber = random.nextLong();
        String randomCode = Long.toString(randomNumber).substring(1,10);

        while (randomCode.length() < 10) {
            randomCode = "0" + randomCode;
        }
        return randomCode;
    }
}
