package com.thunv.controller.api;

import com.thunv.controller.api.output.CategoryOutput;
import com.thunv.dto.CategoryDTO;
import com.thunv.service.ICategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/admin/categories")
@RestController
public class CategoryAPI {

    private final ICategoryService categoryService;

    public CategoryAPI(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryOutput> showCategories(@RequestParam(value = "page", required = false) Integer page,
                                                        @RequestParam(value = "limit", required = false) Integer limit){
        CategoryOutput result = new CategoryOutput();
        if(page!= null && limit != null){
            result.setPage(page);
            Pageable pageable = PageRequest.of(page - 1, limit);
            result.setTotalPage((int) Math.ceil((double) (categoryService.totalItem()) / limit));
            result.setListResult(categoryService.findAll(pageable));
        }else{
            result.setListResult(categoryService.findAll());
        }
        if(StringUtils.isEmpty(result)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable long id) {
        CategoryDTO categoryDTO = categoryService.findById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO model){
        CategoryDTO categoryDTO = categoryService.save(model);
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO model, @PathVariable long id){
        model.setId(id);
        CategoryDTO categoryDTO = categoryService.save(model);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCategories(@RequestBody long[] ids){
        categoryService.delete(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
