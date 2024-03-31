package com.weverse.shop.controller;

import com.weverse.shop.common.dto.request.GoodsRegistrationRequest;
import com.weverse.shop.common.dto.response.GoodsResponse;
import com.weverse.shop.domain.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
@Tag(name = "Goods API")
public class GoodsController {

    private final GoodsService goodsService;

    @Operation(summary = "상품 등록")
    @PostMapping("/v1/goods")
    public ResponseEntity<Void> registrationGoods(
            @RequestBody GoodsRegistrationRequest request
    ){
        goodsService.registration(request);
        return ResponseEntity.status(CREATED).build();
    }

    @Operation(summary = "상품 조회")
    @GetMapping("/v1/goods/{goodsId}")
    public ResponseEntity<GoodsResponse> findGoods(
            @Schema(description = "상품 ID")
            @PathVariable("goodsId") Long goodsId
    ){
        return ResponseEntity.ok(goodsService.findGoods(goodsId));
    }

    @Operation(summary = "상품 재고 조회")
    @GetMapping("/v1/goods/{goodsId}/stock-count")
    public ResponseEntity<Integer> findGoodsStockCount(
            @Schema(description = "상품 ID")
            @PathVariable("goodsId") Long goodsId
    ){
        return ResponseEntity.ok(goodsService.findStockCount(goodsId));
    }


    @Operation(summary = "상품 재고 차감")
    @PutMapping("/v1/goods/{goodsId}")
    public ResponseEntity<Void> decreaseGoodsStockCount(
            @Schema(description = "상품 ID")
            @PathVariable("goodsId") Long goodsId
    ){
        goodsService.decreaseStockCount(goodsId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/v1/goods/{goodsId}")
    public ResponseEntity<Void> removeGoods(
            @Schema(description = "상품 ID")
            @PathVariable("goodsId") Long goodsId
    ){
        goodsService.remove(goodsId);
        return ResponseEntity.ok().build();
    }
}
