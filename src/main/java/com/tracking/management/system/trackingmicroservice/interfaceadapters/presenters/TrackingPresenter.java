package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters;

import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.DeliveryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackingPresenter {
    @Autowired
    private ModelMapper modelMapper;

    public Delivery mapToEntity(DeliveryDto dto){
        return modelMapper.map(dto, Delivery.class);
    }

    public DeliveryDto mapToDto(Delivery entity){
        return modelMapper.map(entity, DeliveryDto.class);
    }
}
