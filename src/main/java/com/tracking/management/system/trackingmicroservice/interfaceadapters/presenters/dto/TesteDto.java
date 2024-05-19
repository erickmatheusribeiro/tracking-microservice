package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TesteDto {
    private List<ShipmentDto> shipmentDto;
}
