package com.weverse.shop.dto;

import lombok.Getter;

@Getter
public record GoodsRegistrationDto (
        String name,
        Integer stockCount,
        Integer price,
        Integer purchasePossibleCount,
        String salesNotice,
        String description,
        String productNoticeInfo
){}
