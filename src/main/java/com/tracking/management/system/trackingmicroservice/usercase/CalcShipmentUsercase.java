package com.tracking.management.system.trackingmicroservice.usercase;

import com.tracking.management.system.trackingmicroservice.entities.Item;
import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.client.product.ProductWeb;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.ItemPresenter;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ShipmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalcShipmentUsercase {
    @Autowired
    private ProductWeb productWeb;

    @Autowired
    private ItemPresenter itemPresenter;

    public Double calcShipment(List<ShipmentDto> dto, Tarif tarif){
        double valueShipment = dto.stream().mapToDouble(
                shipmentDto ->
                        (
                                productWeb.getWeightBySku(shipmentDto.getSku())
                                * shipmentDto.getQuantity()
                        )
                        * tarif.getValuePerGR()
        ).sum();


        return valueShipment;
    }

    public List<Item> findListItens(List<ShipmentDto> dto){
        List<Item> itensDto = new ArrayList<>();

        dto.forEach(item -> itensDto.add(
                itemPresenter.mapToEntity(
                        productWeb.getProductBySku(item.getSku().toString()
                        ))));

        return itensDto;

    }
}
