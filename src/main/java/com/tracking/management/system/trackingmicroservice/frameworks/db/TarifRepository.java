package com.tracking.management.system.trackingmicroservice.frameworks.db;

import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifRepository extends JpaRepository<Tarif, Integer> {
    Tarif findByUfEquals(String uf);
}
