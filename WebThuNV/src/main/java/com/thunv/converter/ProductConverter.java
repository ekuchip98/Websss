package com.thunv.converter;

import com.thunv.dto.ProductDTO;
import com.thunv.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements IConverter<ProductEntity, ProductDTO>{
    @Override
    public ProductEntity toEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setSale(dto.getSale());

        return entity;
    }

    @Override
    public ProductDTO toDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setSale(entity.getSale());

        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    @Override
    public ProductEntity toEntity(ProductEntity entity, ProductDTO dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setSale(dto.getSale());

        return entity;
    }
}
