package com.weverse.shop.controller;

import com.weverse.shop.dto.request.GoodsRegistrationRequest;
import com.weverse.shop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping("/v1/goods")
    public ResponseEntity<Void> registrationGoods(
            @RequestBody GoodsRegistrationRequest request
    ){
        goodsService.registration(request);
        return ResponseEntity.status(CREATED).build();
    }

    @DeleteMapping("/v1/goods/{goodsId}")
    public ResponseEntity<Void> removeGoods(
            @PathVariable("goodsId") Long goodsId
    ){
        goodsService.remove(goodsId);
        return ResponseEntity.ok().build();
    }
}
