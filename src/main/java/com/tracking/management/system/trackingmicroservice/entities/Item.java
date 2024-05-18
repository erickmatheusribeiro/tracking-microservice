package com.tracking.management.system.trackingmicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "DeliveryItens")
@Table(name = "delivery_itens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
