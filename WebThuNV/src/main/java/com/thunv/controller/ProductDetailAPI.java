package com.thunv.controller;

import com.thunv.controller.output.ProductDetailOutput;

import com.thunv.dto.ProductDetailDTO;
import com.thunv.service.impl.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/test")
public class ProductDetailAPI {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<ProductDetailOutput> showProducts(){
        ProductDetailOutput result = new ProductDetailOutput();

        result.setListResult(productDetailService.findAll());

        if(StringUtils.isEmpty(result)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDetailDTO> createProductD(@RequestBody ProductDetailDTO model){
        ProductDetailDTO productDetailDTO = productDetailService.save(model);
        return new ResponseEntity<>(productDetailDTO, HttpStatus.CREATED);
    }
}
