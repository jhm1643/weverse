package com.weverse.shop.controller;

import com.weverse.shop.dto.request.GoodsCategoryRegistrationRequest;
import com.weverse.shop.dto.response.GoodsCategoryResponse;
import com.weverse.shop.service.GoodsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weverse/shop")
@RequiredArgsConstructor
public class GoodsCategoryController {

    private final GoodsCategoryService goodsCategoryService;

    @PostMapping("/v1/goods-category")
    public ResponseEntity<Void> registrationCategory(
            @RequestBody GoodsCategoryRegistrationRequest request
    ){
        goodsCategoryService.registration(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/goods-category/{goodsCategoryId}")
    public ResponseEntity<GoodsCategoryResponse> findCategory(
            @PathVariable("goodsCategoryId") Long goodsCategoryId
    ){
        return ResponseEntity.ok(goodsCategoryService.findById(goodsCategoryId));
    }

    @PutMapping("/v1/goods-category/{goodsCategoryId}/active/{active}")
    public ResponseEntity<Void> removeCategory(
            @PathVariable("goodsCategoryId") Long goodsCategoryId,
            @PathVariable("active") Boolean active
    ){
        goodsCategoryService.modifyActive(goodsCategoryId, active);
        return ResponseEntity.ok().build();
    }
}
