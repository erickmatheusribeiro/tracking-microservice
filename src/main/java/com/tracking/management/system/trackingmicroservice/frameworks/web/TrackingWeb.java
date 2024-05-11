package com.tracking.management.system.trackingmicroservice.frameworks.web;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.DeliveryDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways.TrackingGateway;
import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/tracking")
@RestController
@Tag(name = "Tracking", description = "Métodos para listar, incluir e alterar o status de uma entrega")
public class TrackingWeb {
    @Autowired
    private TrackingGateway service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Listar uma entrega específica")
    @GetMapping("/{code}")
    public ResponseEntity<DeliveryDto> validDelivery(@PathVariable String code) {
        return ResponseEntity.ok(modelMapper.map(service.findTrackingCode(code), DeliveryDto.class));
    }

    @Operation(summary = "Incluir uma entrega")
    @PostMapping("/{cep}/{id}")
    public ResponseEntity<DeliveryDto> insertDelivery(@PathVariable String cep, @PathVariable Integer id) {
        return ResponseEntity.ok(service.insertDelivery(cep, id));
    }

    @Operation(summary = "Efetuar a alteração do status de uma entrega")
    @PutMapping("/{code}/{status}")
    public ResponseEntity<?> updateStatusDelivery(@PathVariable String code,
                                                  @PathVariable Status status){
        return service.updateDelivery(code, status);
    }

    @Operation(summary = "Cancelar uma entrega")
    @DeleteMapping("/{code}")
    public ResponseEntity<DeliveryDto> deleteDelivery(@PathVariable String code) {
        return ResponseEntity.ok(service.deleteDelivery(code));
    }


}
