package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracking.management.system.trackingmicroservice.entities.Item;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsPresenter {

    @Autowired
    private ObjectMapper objectMapper;

    public Item convert(ItemDto dto) {
        return new Item(
                dto.getSku(),
                dto.getProductHeight(),
                dto.getProductWidth(),
                dto.getProductDepth(),
                dto.getProductWeight(),
                dto.getPackagingHeight(),
                dto.getPackagingWidth(),
                dto.getPackagingDepth(),
                dto.getPackagingWeight()
        );
    }

    public List<Item> convertToEntity(List<ItemDto> dtos) {
        List<Item> items = new ArrayList<>();

        dtos.forEach(dto -> items.add(convert(dto)));

        return items;
    }

    public ItemDto convert(Item item) {
        return new ItemDto(item);
    }

    public List<ItemDto> convertToDto(List<Item> items) {
        List<ItemDto> dtos = new ArrayList<>();

        items.forEach(item -> dtos.add(convert(item)));

        return dtos;
    }
}
