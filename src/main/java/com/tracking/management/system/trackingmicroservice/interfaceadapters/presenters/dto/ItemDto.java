package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tracking.management.system.trackingmicroservice.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {
    private String sku;
    private double productHeight;
    private double productWidth;
    private double productDepth;
    private double productWeight;
    private double packagingHeight;
    private double packagingWidth;
    private double packagingDepth;
    private double packagingWeight;


    public ItemDto(Item item) {

        this.sku = item.getSku();
        this.productHeight = item.getProductHeight();
        this.productWidth = item.getProductWidth();
        this.productDepth = item.getProductDepth();
        this.productWeight = item.getProductWeight();
        this.packagingHeight = item.getPackagingHeight();
        this.packagingWidth = item.getPackagingWidth();
        this.packagingDepth = item.getPackagingDepth();
        this.packagingWeight = item.getPackagingWeight();
    }
}
