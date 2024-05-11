package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters;

import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.TarifDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarifPresenter {
    @Autowired
    private ModelMapper modelMapper;

    public TarifDto mapToDto(Tarif entity){
        return modelMapper.map(entity, TarifDto.class);
    }

    public Tarif mapToEntity(TarifDto dto){
        return modelMapper.map(dto, Tarif.class);
    }
}
