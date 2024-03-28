package com.weverse.shop.controller;

import com.weverse.shop.dto.response.GoodsListByCategorySearchResponse;
import com.weverse.shop.service.GoodsSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
public class GoodsSearchController {

    private final GoodsSearchService goodsSearchService;

    @GetMapping("/v1/search/goods-list/by-category")
    public ResponseEntity<GoodsListByCategorySearchResponse> searchCategory(
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
    ){
        return ResponseEntity.ok(goodsSearchService.searchGoodsListByCategory(pageSize, pageNo));
    }
}
