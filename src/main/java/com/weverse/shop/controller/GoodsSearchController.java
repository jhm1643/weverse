package com.weverse.shop.controller;

import com.weverse.shop.dto.request.GoodsSearchRequest;
import com.weverse.shop.dto.response.GoodsListByCategorySearchResponse;
import com.weverse.shop.dto.response.GoodsSearchResponse;
import com.weverse.shop.service.GoodsSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
public class GoodsSearchController {

    private final GoodsSearchService goodsSearchService;

    @GetMapping("/v1/search/goods-list/by-category")
    public ResponseEntity<GoodsListByCategorySearchResponse> searchAllGoodsListByCategory(){
        return ResponseEntity.ok(goodsSearchService.searchGoodsListByCategory());
    }

    @GetMapping("/v1/search/goods")
    public ResponseEntity<GoodsSearchResponse> searchCategory(
            @Valid GoodsSearchRequest goodsSearchRequest
    ){
        return ResponseEntity.ok(goodsSearchService.searchGoods(goodsSearchRequest));
    }
}
