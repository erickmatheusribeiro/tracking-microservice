package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.product;

import com.tracking.management.system.trackingmicroservice.entities.Item;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.ProductsPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItemDto;
import com.tracking.management.system.trackingmicroservice.util.MessageUtil;
import com.tracking.management.system.trackingmicroservice.util.exception.ExternalInterfaceException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductWeb {

    @Autowired
    private ProductWebInterface productWebInterface;

    @Autowired
    private ProductsPresenter presenter;

    public ItemDto getProductBySku(String sku) throws ExternalInterfaceException {
       try{
           return productWebInterface.findProductsBySku(sku);
       } catch (FeignException exception){
           throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_EXTERNAL_SERVICE_PRODUCT", sku), exception);
       } catch (Exception exception) {
           throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_GENERAL_EXCEPTION", "consultar o produto de SKU" + sku), exception);
       }
    }

    public Double getWeightBySku(String sku) throws ExternalInterfaceException {
        Item dto = presenter.convert(getProductBySku(sku));
        return dto.getProductWeight();
    }


}
