package com.tracking.management.system.trackingmicroservice.frameworks.db;

import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifRepository extends JpaRepository<Tarif, Integer> {
    Optional<Tarif> findByUfEquals(String uf);
}
