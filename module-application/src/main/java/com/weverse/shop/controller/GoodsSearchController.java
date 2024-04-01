package com.weverse.shop.controller;

import com.weverse.shop.common.dto.request.GoodsSearchRequest;
import com.weverse.shop.common.dto.request.PaginationRequest;
import com.weverse.shop.common.dto.response.GoodsResponse;
import com.weverse.shop.common.dto.response.GoodsSearchResponse;
import com.weverse.shop.domain.service.GoodsSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
@Tag(name = "상품 검색 API")
public class GoodsSearchController {

    private final GoodsSearchService goodsSearchService;

    @Operation(summary = "카테고리 별 상품 목록 조회")
    @GetMapping("/v1/search/category/{categoryId}/goods")
    public ResponseEntity<List<GoodsResponse>> searchGoodsByCategory(
            @Schema(description = "카테고리 ID")
            @PathVariable("categoryId") Long categoryId
    ){
        return ResponseEntity.ok(goodsSearchService.searchGoodsByCategoryId(categoryId));
    }

    @Operation(summary = "카테고리 별 상품 목록 검색")
    @GetMapping("/v1/search/category/{categoryId}/goods")
    public ResponseEntity<GoodsSearchResponse> searchCategory(
            @Schema(description = "카테고리 ID")
            @PathVariable("categoryId") Long categoryId,

            @Valid PaginationRequest paginationRequest,
            @Valid GoodsSearchRequest goodsSearchRequest
    ){
        return ResponseEntity.ok(goodsSearchService.searchGoodsByCategoryId(categoryId, paginationRequest, goodsSearchRequest));
    }
}
