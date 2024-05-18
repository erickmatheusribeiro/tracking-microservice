package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDto {
    @NotBlank
    private String sku;
    @NotNull
    private Integer quantity;
}
