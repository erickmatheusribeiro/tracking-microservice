package com.tracking.management.system.trackingmicroservice.repository;

import com.tracking.management.system.trackingmicroservice.model.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifRepository extends JpaRepository<Tarif, Integer> {
    Tarif findByUfEquals(String uf);
}
