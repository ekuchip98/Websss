package com.sapo.edu.converter;

import com.sapo.edu.dto.CategoryDTO;
import com.sapo.edu.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements IConverter<CategoryEntity, CategoryDTO> {
    @Override
    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryCode(dto.getCategoryCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }

    @Override
    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setCategoryCode(entity.getCategoryCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    @Override
    public CategoryEntity toEntity(CategoryEntity entity, CategoryDTO dto) {
        entity.setCategoryCode(dto.getCategoryCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }
}
