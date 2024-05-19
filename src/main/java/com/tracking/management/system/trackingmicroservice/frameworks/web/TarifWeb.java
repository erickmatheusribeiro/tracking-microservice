package com.tracking.management.system.trackingmicroservice.frameworks.web;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.TarifDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways.TarifGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tarif")
@RestController
@Tag(name = "Tarif", description = "Métodos para incluir uma tarifa de entrega")
public class TarifWeb {
    @Autowired
    private TarifGateway service;

    @Operation(summary = "Efetuar a inclusão de uma tarifa")
    @PostMapping
    public ResponseEntity<?> insertTarif(@RequestBody TarifDto dto) {
        return service.insertTarif(dto);
    }

    @Operation(summary = "Listar tarifa e prazo de entrega")
    @GetMapping(value="/{cep}")
    public ResponseEntity<TarifDto> validCep(@PathVariable String cep) {
        return ResponseEntity.ok(service.findByCep(cep));
    }

    @Operation(summary = "Calcular valor e prazo de entrega")
    @GetMapping(value = "/shipment/{cep}", consumes = "application/json")
    public ResponseEntity<TarifDto> calculateShipment(@PathVariable(name = "cep") String cep,
                                                      @RequestBody List<ShipmentDto> dto) {
        return ResponseEntity.ok(service.calculateTarif(cep, dto));
    }

    @Operation(summary = "Efetuar alteração da tarifa")
    @PutMapping
    public ResponseEntity<?> updateTarif(@RequestBody TarifDto dto) {
        return service.updateTarif(dto);
    }
}
