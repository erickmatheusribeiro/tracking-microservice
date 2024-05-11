package com.tracking.management.system.trackingmicroservice.frameworks.db;

import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Delivery findByTrakingCodeEquals(String trakingCode);
}
