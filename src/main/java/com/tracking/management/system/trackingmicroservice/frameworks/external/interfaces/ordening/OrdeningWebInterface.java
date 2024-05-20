package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.ordening;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ordering-microservice")
public interface OrdeningWebInterface {

    @PutMapping(value="/api/v1/order/status/")
    void updateStatusOrderCanceled(@RequestParam Integer orderId);

    @PutMapping(value="/api/v1/order/status/ON_CARRIAGE/{id}")
    void updateStatusOrderOnCarrier(@PathVariable Integer id,
                                    @RequestParam String trackingNumber,
                                    @RequestParam String urlTracking);

    @PutMapping(value="/api/v1/order/status/DELIVERED/{id}")
    void updateStatusOrderDelivered(@PathVariable Integer orderId);

}
