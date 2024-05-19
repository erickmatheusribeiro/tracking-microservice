package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.product;

import com.tracking.management.system.trackingmicroservice.entities.Item;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.ProductsPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItemDto;
import com.tracking.management.system.trackingmicroservice.util.exception.ExternalInterfaceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductWeb {

    @Autowired
    private ProductWebInterface productWebInterface;

    @Autowired
    private ProductsPresenter presenter;

//    public Item getProductBySku(String sku) throws ExternalInterfaceException {
//        try{
//            ItemDto product = productWebInterface.findProductsBySku(sku);
//
//            return new Item(
//                    sku,
//                    product.getProductHeight(),
//                    product.getProductWidth(),
//                    product.getProductDepth(),
//                    product.getProductWeight(),
//                    product.getPackagingHeight(),
//                    product.getPackagingWidth(),
//                    product.getPackagingDepth(),
//                    product.getPackagingWeight()
//            );
//        } catch (FeignException exception){
//            if(exception.status() == 404){
//                throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_EXTERNAL_SERVICE_PRODUCT", sku), exception);
//            }
//            throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_EXTERNAL_SERVICE_PRODUCT", sku), exception);
//        } catch (Exception exception){
//            throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_GENERAL_EXCEPTION", "consulta do produto " + sku), exception);
//        }
//    }

    public ItemDto getProductBySku(String sku) {
        return productWebInterface.findProductsBySku(sku);
    }

    public Double getWeightBySku(String sku) throws ExternalInterfaceException {
        Item dto = presenter.convert(getProductBySku(sku));
        return dto.getProductWeight();
    }


}
