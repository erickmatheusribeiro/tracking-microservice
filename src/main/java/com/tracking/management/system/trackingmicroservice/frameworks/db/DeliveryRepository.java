package com.tracking.management.system.trackingmicroservice.frameworks.db;

import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Delivery findByTrakingCodeEquals(String trakingCode);
}
