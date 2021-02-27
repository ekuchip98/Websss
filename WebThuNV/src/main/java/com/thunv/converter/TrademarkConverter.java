package com.thunv.converter;

import com.thunv.dto.TrademarkDTO;
import com.thunv.entity.TrademarkEntity;
import org.springframework.stereotype.Component;

@Component
public class TrademarkConverter implements IConverter<TrademarkEntity, TrademarkDTO> {
    @Override
    public TrademarkEntity toEntity(TrademarkDTO dto) {
        TrademarkEntity entity = new TrademarkEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setLogo(dto.getLogo());
        entity.setTitle(dto.getTitle());

        return entity;
    }

    @Override
    public TrademarkDTO toDTO(TrademarkEntity entity) {
        TrademarkDTO dto = new TrademarkDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setLogo(entity.getLogo());
        dto.setTitle(entity.getTitle());

        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    @Override
    public TrademarkEntity toEntity(TrademarkEntity entity, TrademarkDTO dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setLogo(dto.getLogo());
        entity.setTitle(dto.getTitle());

        return entity;
    }
}
