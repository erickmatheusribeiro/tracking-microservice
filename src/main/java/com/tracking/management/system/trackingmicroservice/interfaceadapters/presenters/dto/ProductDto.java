package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String sku;
    private double productHeight;
    private double productWidth;
    private double productDepth;
    private double productWeight;
    private double packagingHeight;
    private double packagingWidth;
    private double packagingDepth;
    private double packagingWeight;
}
