package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarifDto {
    private String uf;
    private double value;
    private double valuePerGr;
    private int deadline;
}
