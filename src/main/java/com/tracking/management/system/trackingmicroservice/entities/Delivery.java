package com.tracking.management.system.trackingmicroservice.entities;

import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String trakingCode;
    private TrackingStatus status;
    @ManyToOne
    private Tarif tarif;
    private double value;
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreate;
    @Temporal(TemporalType.DATE)
    private LocalDate dateEnd;
    private String orderId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL})
    private List<StatusHistory> statusHistory;
}
