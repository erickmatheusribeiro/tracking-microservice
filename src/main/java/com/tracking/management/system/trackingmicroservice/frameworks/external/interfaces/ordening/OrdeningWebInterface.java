package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.ordening;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ordering-microservice")
public interface OrdeningWebInterface {

    @PutMapping("/api/v1/order/status/ON_CARRIAGE/{id}")
    void updateStatusOnCarrier(@PathVariable Integer id,
                               @RequestParam String trackingNumber,
                               @RequestParam String urlTracking);

    @PutMapping("/api/v1/order/status/ON_CARRIAGE/{id}")
    void updateStatusDelivered(@PathVariable Integer id);

    @PutMapping
    void updateToCancel(@RequestParam Integer orderId);
}
