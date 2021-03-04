package com.thunv.converter;

import com.thunv.dto.ProductDTO;
import com.thunv.entity.CategoryEntity;
import com.thunv.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter implements IConverter<ProductEntity, ProductDTO>{

    private final ProductDetailConverter productDetailConverter;

    public ProductConverter(ProductDetailConverter productDetailConverter) {
        this.productDetailConverter = productDetailConverter;
    }

    @Override
    public ProductEntity toEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setSale(dto.getSale());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public ProductDTO toDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        List<Long> categoryIds = new ArrayList<>();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setSale(entity.getSale());
        dto.setDescription(entity.getDescription());

        dto.setTrademarkId(entity.getTrademark().getId());
        for (CategoryEntity categoryEntity : entity.getCategories()){
            categoryIds.add(categoryEntity.getId());
        }
        dto.setCategoryIds(categoryIds);
        dto.setProductDetail(productDetailConverter.toDTO(entity.getProductDetail()));

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
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
