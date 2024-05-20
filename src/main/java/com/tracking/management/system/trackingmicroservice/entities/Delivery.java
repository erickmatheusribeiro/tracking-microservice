package com.tracking.management.system.trackingmicroservice.entities;

import com.tracking.management.system.trackingmicroservice.util.enums.Status;
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

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    private Tarif tarif;

    private double value;

    @Temporal(TemporalType.DATE)
    private LocalDate dateCreate;

    @Temporal(TemporalType.DATE)
    private LocalDate dateEnd;

    private Integer clienteId;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Item> itens;
}
