package com.weverse.shop.controller;

import com.weverse.shop.common.dto.request.GoodsRegistrationToCategoryRequest;
import com.weverse.shop.common.dto.request.GoodsRemoveFromCategoryRequest;
import com.weverse.shop.domain.service.CategoryGoodsMappingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
@Tag(name = "카테고리 상품 매핑 관리 API")
public class CategoryGoodsMappingController {

    private final CategoryGoodsMappingService goodsMappingService;

    @Operation(summary = "카테고리 상품 매핑")
    @PostMapping("/v1/category/goods/mapping")
    public ResponseEntity<Void> registrationGoodsToCategory(
            @Valid @RequestBody GoodsRegistrationToCategoryRequest request
    ){
        goodsMappingService.registrationGoodsToCategory(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "카테고리 상품 매핑 해제")
    @DeleteMapping("/v1/category/goods/mapping")
    public ResponseEntity<Void> removeGoodsFromCategory(
            @Valid @RequestBody GoodsRemoveFromCategoryRequest request
    ){
        goodsMappingService.removeGoodsFromCategory(request);
        return ResponseEntity.ok().build();
    }
}
