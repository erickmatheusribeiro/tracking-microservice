package com.tracking.management.system.trackingmicroservice.frameworks.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.DeliveryDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways.TrackingGateway;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/tracking")
@RestController
@Tag(name = "Tracking", description = "Métodos para listar, incluir e alterar o status de uma entrega")
public class TrackingWeb {
    @Autowired
    private TrackingGateway service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Listar uma entrega específica")
    @GetMapping
    public ResponseEntity<DeliveryDto> validDelivery(@RequestParam String code) {
        return ResponseEntity.ok(modelMapper.map(service.findTrackingCode(code), DeliveryDto.class));
    }

    @Operation(summary = "Incluir uma entrega")
    @PostMapping
    public ResponseEntity<DeliveryDto> insertDelivery(@RequestParam(name = "cep") String cep,
                                                      @RequestParam(name = "order") Integer id,
                                                      @RequestBody List<ShipmentDto> dto) {
        return ResponseEntity.ok(service.insertDelivery(cep, id, dto));
    }

    @Operation(summary = "Efetuar a alteração do status de uma entrega")
    @PutMapping
    public ResponseEntity<?> updateStatusDelivery(@RequestParam String code,
                                                  @RequestParam Status status) throws JsonProcessingException {
        return service.updateDelivery(code, status);
    }

    @Operation(summary = "Cancelar uma entrega")
    @DeleteMapping
    public ResponseEntity<DeliveryDto> deleteDelivery(@RequestParam String code) {
        return ResponseEntity.ok(service.deleteDelivery(code));
    }
}
