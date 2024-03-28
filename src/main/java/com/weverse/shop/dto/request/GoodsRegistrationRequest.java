package com.weverse.shop.dto.request;

import java.time.LocalDateTime;

public record GoodsRegistrationRequest(
        Long goodsCategoryId,
        String name,
        Integer stockCount,
        Integer price,
        Integer purchasePossibleCount,
        String salesNotice,
        String descriptionImageUrl,
        String productNoticeInfo,
        Boolean isExclusiveSale,
        Boolean isReservationSale,
        LocalDateTime deliveryStartDueFromDtm,
        LocalDateTime deliveryStartDueToDtm
) {}
