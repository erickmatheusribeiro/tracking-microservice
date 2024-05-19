package com.tracking.management.system.trackingmicroservice.usercase;

import com.tracking.management.system.trackingmicroservice.entities.Item;
import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.product.ProductWeb;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.ItemPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItemDto;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import com.tracking.management.system.trackingmicroservice.util.exception.ExternalInterfaceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CalcShipmentUsercase {
    @Autowired
    private ProductWeb productWeb;

    @Autowired
    private ItemPresenter itemPresenter;

    public Double calcShipment(List<ShipmentDto> dto, Tarif tarif) {


        double valueShipment = dto.stream().mapToDouble(
                shipmentDto ->
                {
                    return (
                            productWeb.getWeightBySku(shipmentDto.getSku())
                                    * shipmentDto.getQuantity()
                    )
                            * tarif.getValuePerGR()
                            + tarif.getValue();
                }
        ).sum();

        return valueShipment;
    }

    public List<Item> findListItens(List<ShipmentDto> dto){
        List<Item> itensDto = new ArrayList<>();

        dto.forEach(item -> {
                itensDto.add(
                        itemPresenter.mapToEntity(productWeb.getProductBySku(item.getSku().toString())));

        });

        return itensDto;

    }
}
