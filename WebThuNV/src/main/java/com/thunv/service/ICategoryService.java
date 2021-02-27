package com.thunv.service;

import com.thunv.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ICategoryService {
    List<CategoryDTO> findAll();
    List<CategoryDTO> findAll(Pageable pageable);
    CategoryDTO findById(long id);
    int totalItem();
    CategoryDTO save(CategoryDTO model);
    void delete(long[] ids);
    void delete(long id);
}
