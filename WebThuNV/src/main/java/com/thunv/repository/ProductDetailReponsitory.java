package com.thunv.repository;

import com.thunv.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailReponsitory extends JpaRepository<ProductDetailEntity, Long> {
}
