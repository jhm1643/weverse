package com.weverse.shop.controller;

import com.weverse.shop.dto.request.CategoryRegistrationRequest;
import com.weverse.shop.dto.response.CategoryResponse;
import com.weverse.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/v1/category")
    public ResponseEntity<Void> registrationCategory(
            @RequestBody CategoryRegistrationRequest request
    ){
        categoryService.registration(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/category/{categoryId}")
    public ResponseEntity<CategoryResponse> findCategory(
            @PathVariable("categoryId") Long categoryId
    ){
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }

    @PutMapping("/v1/category/{categoryId}/active/{active}")
    public ResponseEntity<Void> removeCategory(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("active") Boolean active
    ){
        categoryService.modifyActive(categoryId, active);
        return ResponseEntity.ok().build();
    }
}
