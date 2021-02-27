package com.thunv.service.impl;

import com.thunv.converter.ProductConverter;
import com.thunv.dto.ProductDTO;
import com.thunv.entity.ProductEntity;
import com.thunv.exception.NotFoundException;
import com.thunv.repository.CategoryRepository;
import com.thunv.repository.ProductRepository;
import com.thunv.service.IProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveAll(List<ProductDTO> models) {
        List<ProductEntity> entities = new ArrayList<>();
        entities = productRepository.saveAll(entities);
        for (ProductEntity entity : entities){
            models.add(productConverter.toDTO(entity));
        }
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> list = new ArrayList<>();
        List<ProductEntity> entities = productRepository.findAll();
        for (ProductEntity entity : entities){
            ProductDTO productDTO = productConverter.toDTO(entity);
            list.add(productDTO);
        }
        return list;
    }

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> list = new ArrayList<>();
        List<ProductEntity> entities = productRepository.findAll(pageable).getContent();
        for (ProductEntity entity : entities){
            ProductDTO productDTO = productConverter.toDTO(entity);
            list.add(productDTO);
        }
        return list;
    }

    @Override
    public ProductDTO findById(long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy ID: "+id));
        return productConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO model) {
        ProductEntity entity;
        if (model.getId() != null){
            ProductEntity oldProduct = productRepository.getOne(model.getId());
            entity = productConverter.toEntity(oldProduct, model);
        }else {
            entity = productConverter.toEntity(model);
        }
        entity = productRepository.save(entity);
        model = productConverter.toDTO(entity);
        return model;
    }

    @Override
    @Transactional
    public void delete(long id) {
        ProductEntity entity = productRepository.getOne(id);
        entity.getCategories().forEach(category -> category.getProducts().remove(entity));
        categoryRepository.saveAll(entity.getCategories());
        productRepository.delete(entity);
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids){
            ProductEntity entity = productRepository.getOne(id);
            entity.getCategories().forEach(category -> category.getProducts().remove(entity));
            categoryRepository.saveAll(entity.getCategories());
            productRepository.delete(entity);
        }
    }

    @Override
    public int totalItem() {
        return (int) productRepository.count();
    }
}
