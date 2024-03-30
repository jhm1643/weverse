package com.weverse.shop.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record GoodsSearchDto(
        Long categoryId,
        String categoryName,
        Long goodsCategoryId,
        String goodsCategoryName,
        Long goodsId,
        String goodsName,
        Integer stockCount,
        Integer price,
        Integer purchasePossibleCount,
        String salesNotice,
        String descriptionImageUrl,
        String productNoticeInfo,
        Boolean isReservationSale,
        Boolean isExclusiveSale,
        LocalDateTime deliveryStartDueFromDtm,
        LocalDateTime deliveryStartDueToDtm,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    @QueryProjection
    public GoodsSearchDto {}
}
