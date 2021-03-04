package com.thunv.controller;

import com.thunv.controller.output.ProductOutput;
import com.thunv.dto.ProductDTO;
import com.thunv.service.impl.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/products")
public class ProductAPI {

    private final ProductService productService;

    public ProductAPI(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductOutput> showProducts(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit){
        ProductOutput result = new ProductOutput();
        if (page != null && limit != null){
            result.setPage(page);
            Pageable pageable = PageRequest.of(page - 1, limit);
            result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
            result.setListResult(productService.findAll(pageable));
        }else {
            result.setListResult(productService.findAll());
        }
        if(StringUtils.isEmpty(result)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id){
        ProductDTO productDTO = productService.findById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO model){
        ProductDTO productDTO = productService.save(model);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO model, @PathVariable long id){
        model.setId(id);
        ProductDTO productDTO = productService.save(model);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteProducts(@RequestBody long[] ids){
        productService.delete(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
