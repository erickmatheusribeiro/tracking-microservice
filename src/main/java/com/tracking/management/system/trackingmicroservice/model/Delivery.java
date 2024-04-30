package com.tracking.management.system.trackingmicroservice.model;

import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String trakingCode;
    private Status status;
    @JoinColumn
    @ManyToOne
    private Tarif tarif;
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreate;
    @Temporal(TemporalType.DATE)
    private LocalDate dateEnd;
    private Integer clienteId;
}
