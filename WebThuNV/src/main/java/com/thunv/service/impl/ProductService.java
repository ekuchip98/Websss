package com.thunv.service.impl;

import com.thunv.converter.ProductConverter;
import com.thunv.converter.ProductDetailConverter;
import com.thunv.dto.ProductDTO;
import com.thunv.entity.CategoryEntity;
import com.thunv.entity.ProductEntity;
import com.thunv.entity.TrademarkEntity;
import com.thunv.exception.ConstraintViolationException;
import com.thunv.exception.NotFoundException;
import com.thunv.repository.CategoryRepository;
import com.thunv.repository.ProductDetailReponsitory;
import com.thunv.repository.ProductRepository;
import com.thunv.repository.TrademarkRepository;
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
    private final TrademarkRepository trademarkRepository;
    private final ProductDetailConverter productDetailConverter;
    private final ProductDetailReponsitory productDetailReponsitory;
    private final ProductDetailService productDetailService;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter, CategoryRepository categoryRepository, TrademarkRepository trademarkRepository, ProductDetailConverter productDetailConverter, ProductDetailReponsitory productDetailReponsitory, ProductDetailService productDetailService) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.categoryRepository = categoryRepository;
        this.trademarkRepository = trademarkRepository;
        this.productDetailConverter = productDetailConverter;
        this.productDetailReponsitory = productDetailReponsitory;
        this.productDetailService = productDetailService;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> entities = productRepository.findAll();
        for (ProductEntity item : entities){
            ProductDTO productDTO = productConverter.toDTO(item);
            productDTO.setProductDetail(productDetailService.findById(item.getProductDetail().getId()));
            results.add(productDTO);
        }
        return results;
    }

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> entities = productRepository.findAll(pageable).getContent();
        for (ProductEntity item : entities){
            ProductDTO productDTO = productConverter.toDTO(item);
            productDTO.setProductDetail(productDetailService.findById(item.getProductDetail().getId()));
            results.add(productDTO);
        }
        return results;
    }

    @Override
    public ProductDTO findById(long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy ID: "+id));
        ProductDTO productDTO = productConverter.toDTO(entity);
        productDTO.setTrademarkId(entity.getTrademark().getId());
        return productDTO;
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO model) {
        ProductEntity entity;

        if (model.getId() != null){
            ProductEntity oldProduct = productRepository.getOne(model.getId());
            entity = productConverter.toEntity(oldProduct, model);
            entity.setProductDetail(productDetailConverter.toEntity(oldProduct.getProductDetail(),model.getProductDetail()));
        }else {
            entity = productConverter.toEntity(model);
            entity.setProductDetail(productDetailConverter.toEntity(model.getProductDetail()));
        }

        if(model.getTrademarkId() != null){
            TrademarkEntity trademarkEntity = trademarkRepository.getOne(model.getTrademarkId());
            entity.setTrademark(trademarkEntity);
        }else {
            throw new ConstraintViolationException("a foreign key constraint null");
        }

        ProductEntity finalEntity = entity;
        entity.getCategories().forEach(category -> category.getProducts().remove(finalEntity));
        List<CategoryEntity> categoryEntities = categoryRepository.findAllById(model.getCategoryIds());
        entity.setCategories(categoryEntities);
        categoryEntities.forEach(c-> c.getProducts().add(finalEntity));

        productDetailReponsitory.save(entity.getProductDetail());
        entity = productRepository.save(entity);
        return productConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        ProductEntity entity = productRepository.getOne(id);
        entity.getCategories().forEach(category -> category.getProducts().remove(entity));
        categoryRepository.saveAll(entity.getCategories());
        productDetailService.delete(entity.getProductDetail().getId());
        productRepository.delete(entity);
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids){
            ProductEntity entity = productRepository.getOne(id);
            entity.getCategories().forEach(category -> category.getProducts().remove(entity));
            categoryRepository.saveAll(entity.getCategories());
            productDetailService.delete(entity.getProductDetail().getId());
            productRepository.delete(entity);
        }
    }

    @Override
    public int totalItem() {
        return (int) productRepository.count();
    }
}
