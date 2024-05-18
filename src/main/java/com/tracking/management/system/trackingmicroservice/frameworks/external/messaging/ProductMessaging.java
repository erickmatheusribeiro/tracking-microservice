package com.tracking.management.system.trackingmicroservice.frameworks.external.messaging;

import com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.client.product.ProductWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMessaging {

    @Autowired
    private ProductWeb productWeb;

//
//    public Optional<Item> listProductBySku(String sku) {
//
//    }
}
