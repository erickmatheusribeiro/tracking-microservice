package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters;

import com.tracking.management.system.trackingmicroservice.entities.Item;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPresenter {
    @Autowired
    private ModelMapper modelMapper;

    public ItemDto mapToDto (Item entity){
        return modelMapper.map(entity, ItemDto.class);
    }

    public Item mapToEntity (ItemDto dto){
        return modelMapper.map(dto, Item.class);
    }
}
