package com.weverse.shop.controller;

import com.weverse.shop.common.dto.request.GoodsCategoryRegistrationRequest;
import com.weverse.shop.common.dto.response.GoodsCategoryResponse;
import com.weverse.shop.domain.service.GoodsCategoryService;
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
@Tag(name = "Goods Category API")
public class GoodsCategoryController {

    private final GoodsCategoryService goodsCategoryService;

    @Operation(summary = "상품 카테고리 등록")
    @PostMapping("/v1/goods-category")
    public ResponseEntity<Void> registrationGoodsCategory(
            @RequestBody GoodsCategoryRegistrationRequest request
    ){
        goodsCategoryService.registration(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "상품 카테고리 조회")
    @GetMapping("/v1/goods-category/{goodsCategoryId}")
    public ResponseEntity<GoodsCategoryResponse> findGoodsCategory(
            @Schema(description = "상품 카테고리 ID")
            @PathVariable("goodsCategoryId") Long goodsCategoryId
    ){
        return ResponseEntity.ok(goodsCategoryService.findById(goodsCategoryId));
    }

    @Operation(summary = "상품 카테고리 삭제")
    @DeleteMapping("/v1/goods-category/{goodsCategoryId}")
    public ResponseEntity<Void> removeGoodsCategory(
            @Schema(description = "상품 카테고리 ID")
            @PathVariable("goodsCategoryId") Long goodsCategoryId
    ){
        goodsCategoryService.remove(goodsCategoryId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상품 카테고리 명 변경")
    @PutMapping("/v1/goods-category/{goodsCategoryId}/name/{goodsCategoryName}")
    public ResponseEntity<Void> modifyGoodsCategoryName(
            @Schema(description = "상품 카테고리 ID")
            @PathVariable("goodsCategoryId") Long goodsCategoryId,

            @Schema(description = "상품 카테고리 명")
            @PathVariable("goodsCategoryName") String goodsCategoryName
    ){
        goodsCategoryService.modifyName(goodsCategoryId, goodsCategoryName);
        return ResponseEntity.ok().build();
    }
}
