package com.weverse.shop.domain.dto;

import java.time.LocalDateTime;

public record GoodsRegistrationRecord(
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
){}
