package com.thunv.service.impl;

import com.thunv.converter.CategoryConverter;
import com.thunv.converter.ProductConverter;
import com.thunv.dto.CategoryDTO;
import com.thunv.dto.ProductDTO;
import com.thunv.entity.CategoryEntity;
import com.thunv.entity.ProductEntity;
import com.thunv.exception.NotFoundException;
import com.thunv.repository.CategoryRepository;
import com.thunv.repository.ProductRepository;
import com.thunv.service.ICategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductConverter productConverter;

    public CategoryService(CategoryRepository categoryRepository, CategoryConverter categoryConverter, ProductRepository productRepository, ProductService productService, ProductConverter productConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
        this.productRepository = productRepository;
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> list = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity categoryEntity : entities){
            CategoryDTO categoryDTO;
            categoryDTO = categoryConverter.toDTO(categoryEntity);
            list.add(categoryDTO);
        }
        return list;
    }

    @Override
    public List<CategoryDTO> findAll(Pageable pageable) {
        List<CategoryDTO> list = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll(pageable).getContent();
        for (CategoryEntity categoryEntity : entities){
            CategoryDTO categoryDTO;
            categoryDTO = categoryConverter.toDTO(categoryEntity);
            list.add(categoryDTO);
        }
        return list;
    }

    @Override
    public CategoryDTO findById(long id) {
        CategoryEntity entity = categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy ID: "+ id));
        return categoryConverter.toDTO(entity);
    }

    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO model) {
        CategoryEntity entity;
        if(model.getId() != null){
            CategoryEntity oldCategory = categoryRepository.findById(model.getId()).orElseThrow(()->new NotFoundException("Không tìm thấy ID: "+model.getId()));
            entity = categoryConverter.toEntity(oldCategory, model);
        }else{
            entity = categoryConverter.toEntity(model);
        }
        entity = categoryRepository.save(entity);
        return categoryConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids){
            CategoryEntity entity = categoryRepository.getOne(id);
            entity.getProducts().forEach(product -> product.getCategories().remove(entity));
            productRepository.saveAll(entity.getProducts());
            categoryRepository.delete(entity);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        List<ProductDTO> models = new ArrayList<>();
        CategoryEntity entity = categoryRepository.getOne(id);
        entity.getProducts().forEach(product -> product.getCategories().remove(entity));
        for (ProductEntity product : entity.getProducts()){
            models.add(productConverter.toDTO(product));
        }
        productService.saveAll(models);
        categoryRepository.delete(entity);
    }
}
