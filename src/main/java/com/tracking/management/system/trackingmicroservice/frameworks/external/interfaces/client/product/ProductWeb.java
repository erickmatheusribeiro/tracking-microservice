package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.client.product;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItensDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductWeb {

    @Autowired
    private ProductWebInterface productWebInterface;

//    public Optional<Item> findProductBySku(String sku) throws ExternalInterfaceException{
//        try{
//            JsonNode product = productWebInterface.findProductsBySku(sku);
//
//            return Optional.of(new Item(
//                    null,
//                    sku,
//                    product.get("productHeight").asDouble(),
//                    product.get("productWidth").asDouble(),
//                    product.get("productDepth").asDouble(),
//                    product.get("productWeight").asDouble(),
//                    product.get("packagingHeight").asDouble(),
//                    product.get("packagingWidth").asDouble(),
//                    product.get("packagingDepth").asDouble(),
//                    product.get("packagingWeight").asDouble()
//            ));
//        } catch (FeignException exception){
//            if(exception.status() == 404){
//                return Optional.empty();
//            }
//            throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_EXTERNAL_SERVICE_PRODUCT", sku), exception);
//        } catch (Exception exception){
//            throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_GENERAL_EXCEPTION", "consulta do produto " + sku), exception);
//        }
//    }

    public ItensDto getProductBySku(String sku) {
        return productWebInterface.findProductsBySku(sku);
    }

    public Double getWeightBySku(String sku) {
        ItensDto dto = getProductBySku(sku);
        return dto.getPackagingWeight();
    }


}
