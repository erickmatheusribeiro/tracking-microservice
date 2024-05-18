package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.client.product;


import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItensDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-ms")
public interface ProductWebInterface {

    @GetMapping("/products/sku/{sku}")
    ItensDto findProductsBySku(@PathVariable("sku") String sku);

}
