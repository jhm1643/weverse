package com.weverse.shop.controller;

import com.weverse.shop.common.dto.request.GoodsSearchRequest;
import com.weverse.shop.common.dto.request.PaginationRequest;
import com.weverse.shop.common.dto.response.CountByCategoryResponse;
import com.weverse.shop.common.dto.response.GoodsListByCategorySearchResponse;
import com.weverse.shop.common.dto.response.GoodsSearchResponse;
import com.weverse.shop.domain.service.GoodsSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
@Tag(name = "Goods Search API")
public class GoodsSearchController {

    private final GoodsSearchService goodsSearchService;

    @Operation(summary = "카테고리 별 상품 목록 조회")
    @GetMapping("/v1/search/goods-list/by-category")
    public ResponseEntity<GoodsListByCategorySearchResponse> searchAllGoodsListByCategory(){
        return ResponseEntity.ok(goodsSearchService.searchGoodsListByCategory());
    }

    @Operation(summary = "상품 검색")
    @GetMapping("/v1/search/goods")
    public ResponseEntity<GoodsSearchResponse> searchCategory(
            @Valid PaginationRequest paginationRequest,
            @Valid GoodsSearchRequest goodsSearchRequest
    ){
        return ResponseEntity.ok(goodsSearchService.searchGoods(paginationRequest, goodsSearchRequest));
    }

    @Operation(summary = "카테고리 갯수 조회")
    @GetMapping("/v1/search/category-count")
    public ResponseEntity<List<CountByCategoryResponse>> countByCategoryResponse(
            @Schema(description = "카테고리 명 목록")
            @RequestParam(name = "names") List<String> names
    ){
        return ResponseEntity.ok(goodsSearchService.countByCategoryResponse(names));
    }
}
