package com.tracking.management.system.trackingmicroservice.frameworks.web;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.TarifDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways.TarifGateway;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways.TrackingGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tarif")
@RestController
@Tag(name = "Tarif", description = "Métodos para incluir uma tarifa de entrega")
public class TarifWeb {
    @Autowired
    private TarifGateway service;

    @Autowired
    private TrackingGateway trackingService;

    @Operation(summary = "Efetuar a inclusão de uma tarifa")
    @PostMapping
    public ResponseEntity<?> insertTarif(@RequestBody TarifDto dto) {
        return service.insertTarif(dto);
    }

    @Operation(summary = "Listar tarifa e prazo de entrega")
    @GetMapping("/{cep}")
    public ResponseEntity<TarifDto> validCep(@PathVariable String cep) {
        return ResponseEntity.ok(trackingService.listCep(cep));
    }

    @Operation(summary = "Efetuar alteração da tarifa")
    @PutMapping
    public ResponseEntity<?> updateTarif(@RequestBody TarifDto dto) {
        return service.updateTarif(dto);
    }
}
