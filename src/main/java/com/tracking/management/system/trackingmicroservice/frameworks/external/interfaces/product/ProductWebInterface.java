package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.product;


import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-microservice")
public interface ProductWebInterface {

    @GetMapping("/products/sku/{sku}")
    ItemDto findProductsBySku(@PathVariable("sku") String sku);

}
