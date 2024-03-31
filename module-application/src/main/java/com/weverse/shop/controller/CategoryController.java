package com.weverse.shop.controller;

import com.weverse.shop.common.dto.request.CategoryRegistrationRequest;
import com.weverse.shop.common.dto.response.CategoryResponse;
import com.weverse.shop.domain.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
@Tag(name = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 등록")
    @PostMapping("/v1/category")
    public ResponseEntity<Void> registrationCategory(
            @RequestBody CategoryRegistrationRequest request
    ){
        categoryService.registration(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "카테고리 조회")
    @GetMapping("/v1/category/{categoryId}")
    public ResponseEntity<CategoryResponse> findCategory(
            @Schema(description = "카테고리 ID")
            @PathVariable("categoryId") Long categoryId
    ){
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/v1/category/{categoryId}")
    public ResponseEntity<Void> removeCategory(
            @Schema(description = "카테고리 ID")
            @PathVariable("categoryId") Long categoryId
    ){
        categoryService.removeCategory(categoryId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "카테고리 명 변경")
    @PutMapping("/v1/category/{categoryId}/name/{categoryName}")
    public ResponseEntity<Void> modifyCategoryName(
            @Schema(description = "카테고리 ID")
            @PathVariable("categoryId") Long categoryId,

            @Schema(description = "카테고리 명")
            @PathVariable("categoryName") String categoryName
    ){
        categoryService.modifyName(categoryId, categoryName);
        return ResponseEntity.ok().build();
    }
}
