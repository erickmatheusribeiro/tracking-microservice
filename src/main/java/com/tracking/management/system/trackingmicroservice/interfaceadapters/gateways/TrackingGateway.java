package com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tracking.management.system.trackingmicroservice.entities.StatusHistory;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.controller.OrderController;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.TrackingPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.DeliveryDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.frameworks.db.DeliveryRepository;
import com.tracking.management.system.trackingmicroservice.usercase.CalcShipmentUsercase;
import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@Service
public class TrackingGateway {
    @Autowired
    private Clock clock;

    @Autowired
    private OrderController orderController;

    @Autowired
    private DeliveryRepository repository;

    @Autowired
    private TarifGateway tarifService;


    @Autowired
    private TrackingPresenter trackingPresenter;


    @Autowired
    private CalcShipmentUsercase calcShipmentUsercase;

    public Delivery findTrackingCode (String code){
        Delivery delivery = repository.findByTrakingCodeEquals(code);
        return delivery;
    }

    public DeliveryDto insertDelivery(String cep, String orderId, List<ShipmentDto> dto){
        Delivery entity = new Delivery();

        Tarif tarif = tarifService.findByUf(tarifService.findByCep(cep).getUf().toString());

        String trackingCode = generateTrackingCode();
        while(findTrackingCode(trackingCode) != null){
            trackingCode = generateTrackingCode();
        }

        entity.setStatus(TrackingStatus.STOCK_SEPARATION);
        entity.setTarif(tarif);
        entity.setOrderId(orderId);
        entity.setDateCreate(LocalDate.now());
        entity.setTrakingCode(trackingCode);
        entity.setDateEnd(LocalDate.now().plusDays(tarif.getDeadline()));
//        entity.setItens(calcShipmentUsercase.findListItens(dto));
        entity.setValue(calcShipmentUsercase.calcShipment(dto,tarif));
        StatusHistory statusHistory = new StatusHistory(TrackingStatus.STOCK_SEPARATION, LocalDateTime.now(clock));
        entity.setStatusHistory(List.of(statusHistory));

        repository.save(entity);

        return trackingPresenter.mapToDto(entity);
    }

    public DeliveryDto deleteDelivery(String code) throws JsonProcessingException {
        Delivery entity = repository.findByOrderIdEquals(code);

        entity.setStatus(TrackingStatus.CANCELED);
        repository.save(entity);

        orderController.updateStatusOrder(entity);

        return trackingPresenter.mapToDto(entity);
    }

    public ResponseEntity<?> updateDelivery(String code, TrackingStatus status) throws JsonProcessingException {
        Delivery entity = repository.findByTrakingCodeEquals(code);

        entity.setStatus(status);
        StatusHistory statusHistory = new StatusHistory(status, LocalDateTime.now(clock));
        entity.setStatusHistory(List.of(statusHistory));
        repository.save(entity);

        orderController.updateStatusOrder(entity);

        return ResponseEntity.ok(trackingPresenter.mapToDto(entity));
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

    public void update(Delivery delivery){
        repository.save(delivery);
    }

    public Delivery findById (Integer id){
        return repository.findById(id).orElse(null);
    }
}
