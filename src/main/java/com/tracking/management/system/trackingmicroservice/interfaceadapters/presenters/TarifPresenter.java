package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters;

import com.tracking.management.system.trackingmicroservice.entities.Tarif;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.TarifDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarifPresenter {

    public TarifDto mapToDto(Tarif entity){
        TarifDto dto = new TarifDto();

        dto.setUf(entity.getUf());
        dto.setValue(entity.getValue());
        dto.setValuePerGr(entity.getValuePerGR());
        dto.setDeadline(entity.getDeadline());

        return dto;
    }

    public Tarif mapToEntity(TarifDto dto){
        Tarif entity = new Tarif();

        entity.setUf(dto.getUf());
        entity.setValue(dto.getValue());
        entity.setValuePerGR(dto.getValuePerGr());
        entity.setDeadline(dto.getDeadline());

        return entity;
    }
}
