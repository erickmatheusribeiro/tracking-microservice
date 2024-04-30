package com.tracking.management.system.trackingmicroservice.dto;

import com.tracking.management.system.trackingmicroservice.util.enums.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarifDto {
    private States uf;
    private double value;
    private int deadline;
}
