package com.weverse.shop.common.dto.request;

import com.weverse.shop.common.type.LanguageType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "상품 등록 요청")
public record GoodsRegistrationRequest(
        @Schema(description = "상품 카테고리 ID") Long goodsCategoryId,
        @Schema(description = "상품 명 다국어 목록") List<GoodsNameMultilingual> goodsNames,
        @Schema(description = "재고 수량") Integer stockCount,
        @Schema(description = "가격") Integer price,
        @Schema(description = "구매 가능 수량") Integer purchasePossibleCount,
        @Schema(description = "판매 공지") String salesNotice,
        @Schema(description = "설명 이미지 URL") String descriptionImageUrl,
        @Schema(description = "상품 고지 정보") String productNoticeInfo,
        @Schema(description = "단독 판매 여부") Boolean isExclusiveSale,
        @Schema(description = "예약 판매 여부") Boolean isReservationSale,
        @Schema(description = "배송 시작 예정 일시 from") LocalDateTime deliveryStartDueFromDtm,
        @Schema(description = "배송 시작 예정 일시 to") LocalDateTime deliveryStartDueToDtm
) {
    public record GoodsNameMultilingual(
            @Schema(description = "언어 유형") LanguageType languageType,
            @Schema(description = "상품 명") String goodsName
    ){}
}
