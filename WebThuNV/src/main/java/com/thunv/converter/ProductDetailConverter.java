package com.thunv.converter;

import com.thunv.dto.ProductDetailDTO;
import com.thunv.entity.ProductDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailConverter implements IConverter<ProductDetailEntity, ProductDetailDTO> {
    @Override
    public ProductDetailEntity toEntity(ProductDetailDTO dto) {
        ProductDetailEntity entity = new ProductDetailEntity();
        entity.setSize(dto.getSize());
        entity.setPrice(dto.getPrice());
        entity.setPriceOld(dto.getPriceOld());
        entity.setEvaluate(dto.getEvaluate());
        entity.setQuantity(dto.getQuantity());
        entity.setView(dto.getView());
        entity.setFavorite(dto.getFavorite());
        entity.setAuthor(dto.getAuthor());
        entity.setWeight(dto.getWeight());
        entity.setManufacture(dto.getManufacture());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public ProductDetailDTO toDTO(ProductDetailEntity entity) {
        ProductDetailDTO dto = new ProductDetailDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setSize(entity.getSize());
        dto.setPrice(entity.getPrice());
        dto.setPriceOld(entity.getPriceOld());
        dto.setEvaluate(entity.getEvaluate());
        dto.setQuantity(entity.getQuantity());
        dto.setView(entity.getView());
        dto.setFavorite(entity.getFavorite());
        dto.setAuthor(entity.getAuthor());
        dto.setWeight(entity.getWeight());
        dto.setManufacture(entity.getManufacture());
        dto.setStatus(entity.getStatus());

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }

    @Override
    public ProductDetailEntity toEntity(ProductDetailEntity entity, ProductDetailDTO dto) {
        entity.setSize(dto.getSize());
        entity.setPrice(dto.getPrice());
        entity.setPriceOld(dto.getPriceOld());
        entity.setEvaluate(dto.getEvaluate());
        entity.setQuantity(dto.getQuantity());
        entity.setView(dto.getView());
        entity.setFavorite(dto.getFavorite());
        entity.setAuthor(dto.getAuthor());
        entity.setWeight(dto.getWeight());
        entity.setManufacture(dto.getManufacture());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
