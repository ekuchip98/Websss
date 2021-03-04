package com.thunv.service.impl;

import com.thunv.converter.ProductDetailConverter;
import com.thunv.dto.ProductDTO;
import com.thunv.dto.ProductDetailDTO;
import com.thunv.entity.ProductDetailEntity;
import com.thunv.exception.NotFoundException;
import com.thunv.repository.ProductDetailReponsitory;
import com.thunv.repository.ProductRepository;
import com.thunv.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductDetailService implements IProductDetailService {

    private final ProductDetailReponsitory productDetailReponsitory;
    private final ProductDetailConverter productDetailConverter;

    public ProductDetailService(ProductDetailReponsitory productDetailReponsitory, ProductDetailConverter productDetailConverter) {
        this.productDetailReponsitory = productDetailReponsitory;
        this.productDetailConverter = productDetailConverter;
    }

    @Override
    public List<ProductDetailDTO> findAll() {
        List<ProductDetailDTO> results = new ArrayList<>();
        List<ProductDetailEntity> entities = productDetailReponsitory.findAll();
        for (ProductDetailEntity item : entities){
            ProductDetailDTO productDetailDTO = productDetailConverter.toDTO(item);
            results.add(productDetailDTO);
        }
        return results;
    }

    @Override
    public ProductDetailDTO findById(Long id) {
        ProductDetailEntity productDetailEntity = productDetailReponsitory.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy ID:"+id));
        return productDetailConverter.toDTO(productDetailEntity);
    }

    @Override
    public void delete(Long id) {
        productDetailReponsitory.deleteById(id);
    }

    @Override
    public ProductDetailDTO save(ProductDetailDTO model) {
        ProductDetailEntity entity;
        if(model.getId() != null){
            ProductDetailEntity oldDetail = productDetailReponsitory.getOne(model.getId());
            entity = productDetailConverter.toEntity(oldDetail, model);
        }else {
            entity = productDetailConverter.toEntity(model);
        }

        entity = productDetailReponsitory.save(entity);
        return productDetailConverter.toDTO(entity);
    }
}
