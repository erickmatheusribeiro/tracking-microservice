package com.tracking.management.system.trackingmicroservice.model;

import com.tracking.management.system.trackingmicroservice.util.enums.States;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 2)
    private String uf;
    private double value;
    private int deadline;
}
