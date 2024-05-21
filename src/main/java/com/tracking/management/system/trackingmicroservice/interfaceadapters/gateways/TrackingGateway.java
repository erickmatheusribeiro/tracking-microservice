package com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.frameworks.db.DeliveryRepository;
import com.tracking.management.system.trackingmicroservice.frameworks.external.messaging.logistics.LogisticsMessageProducer;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.TrackingPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.DeliveryDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.message.StatusMessage;
import com.tracking.management.system.trackingmicroservice.usercase.CalcShipmentUsercase;
import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Service
public class TrackingGateway {
    @Autowired
    private DeliveryRepository repository;

    @Autowired
    private TarifGateway tarifService;


    @Autowired
    private TrackingPresenter trackingPresenter;


    @Autowired
    private LogisticsMessageProducer logisticsMessageProducer;

    @Autowired
    private CalcShipmentUsercase calcShipmentUsercase;

    public ResponseEntity<?> findByTrackingCode(String code) {
        Delivery delivery = findTrackingCode(code);

        if (delivery == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Código de rastreio não encontrado");
        }

        return ResponseEntity.ok(delivery);
    }

    public Delivery findTrackingCode(String code) {
        Delivery delivery = repository.findByTrakingCodeEquals(code);
        return delivery;
    }

    public DeliveryDto insertDelivery(String cep, Integer clienteId, List<ShipmentDto> dto) {
        Delivery entity = new Delivery();

        Tarif tarif = tarifService.findByUf(tarifService.findByCep(cep).getUf().toString());

        String trackingCode = generateTrackingCode();

        while (findTrackingCode(trackingCode) != null) {
            trackingCode = generateTrackingCode();
        }

        entity.setStatus(Status.PENDENTE);
        entity.setTarif(tarif);
        entity.setClienteId(clienteId);
        entity.setDateCreate(LocalDate.now());
        entity.setTrakingCode(trackingCode);
        entity.setDateEnd(LocalDate.now().plusDays(tarif.getDeadline()));
//        entity.setValue(calcShipmentUsercase.calcShipment(dto, tarif));
        entity.setItens(calcShipmentUsercase.findListItens(dto));

        repository.save(entity);

        return trackingPresenter.mapToDto(entity);
    }

    public DeliveryDto deleteDelivery(String code) {
        Delivery entity = repository.findByTrakingCodeEquals(code);

        entity.setStatus(Status.CANCELADO);
        repository.save(entity);

        return trackingPresenter.mapToDto(entity);
    }

    public ResponseEntity<?> updateDelivery(String code, Status status) throws JsonProcessingException {
        Delivery entity = repository.findByTrakingCodeEquals(code);

        entity.setStatus(status);

        repository.save(entity);

        StatusMessage statusMessage = new StatusMessage(code, status, entity.getTrakingCode(), "");

        logisticsMessageProducer.sendMessage(statusMessage);

        return ResponseEntity.ok(trackingPresenter.mapToDto(entity));
    }


    public String generateTrackingCode() {
        Random random = new Random();
        long randomNumber = random.nextLong();
        String randomCode = Long.toString(randomNumber).substring(1, 10);

        while (randomCode.length() < 10) {
            randomCode = "0" + randomCode;
        }
        return randomCode;
    }
}
