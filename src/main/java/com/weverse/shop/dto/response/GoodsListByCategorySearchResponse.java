package com.weverse.shop.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record GoodsListByCategorySearchResponse(
        List<Category> categories
) {
    public static record Category(
            String categoryName
    ){
        public static record Goods(
                Long id,
                String name,
                Integer stockCount,
                Integer price,
                Integer purchasePossibleCount,
                String salesNotice,
                String descriptionImageUrl,
                String productNoticeInfo,
                Boolean reservationSale,
                Boolean exclusiveSale,
                LocalDateTime deliveryStartDueFromDtm,
                LocalDateTime deliveryStartDueToDtm,
                LocalDateTime createAt,
                LocalDateTime updateAt
        ){}
    }
}
