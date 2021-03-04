package com.thunv.service;

import com.thunv.dto.ProductDetailDTO;

import java.util.List;

public interface IProductDetailService {

    List<ProductDetailDTO> findAll();
    ProductDetailDTO findById(Long id);
    void delete (Long id);
    ProductDetailDTO save(ProductDetailDTO model);
}
