package com.thunv.service;

import com.thunv.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();
    List<ProductDTO> findAll(Pageable pageable);
    ProductDTO findById(long id);
    ProductDTO save(ProductDTO model);
    void delete(long id);
    void delete(long[] ids);
    int totalItem();
}
