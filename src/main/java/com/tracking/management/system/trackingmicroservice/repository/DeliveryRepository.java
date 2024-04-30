package com.tracking.management.system.trackingmicroservice.repository;

import com.tracking.management.system.trackingmicroservice.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Delivery findByTrakingCodeEquals(String trakingCode);
}
