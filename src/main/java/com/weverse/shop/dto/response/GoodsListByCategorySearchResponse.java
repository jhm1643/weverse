package com.weverse.shop.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record GoodsListByCategorySearchResponse(
        List<Category> categories
) {
    public record Category(
            String categoryName,
            List<Goods> goodsList
    ){
        public record Goods(
                Long id,
                String name,
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
        ){}
    }
}
