package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters;

import com.tracking.management.system.trackingmicroservice.entities.Item;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPresenter {

    public Item mapToEntity (ItemDto dto){
        Item entity = new Item();

        entity.setSku(dto.getSku());
        entity.setPackagingDepth(dto.getPackagingDepth());
        entity.setPackagingWidth(dto.getPackagingWidth());
        entity.setPackagingHeight(dto.getPackagingHeight());
        entity.setPackagingWeight(dto.getPackagingWeight());
        entity.setProductDepth(dto.getProductDepth());
        entity.setProductWidth(dto.getProductWidth());
        entity.setProductHeight(dto.getProductHeight());
        entity.setProductWeight(dto.getProductWeight());

        return entity;
    }
}
