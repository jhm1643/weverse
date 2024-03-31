package com.weverse.shop.common.dto.request;

import com.weverse.shop.common.type.GoodsState;
import com.weverse.shop.common.type.LanguageType;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@Schema(description = "상품 검색 요청")
public record GoodsSearchRequest(

        @Schema(description = "상품 명", requiredMode = NOT_REQUIRED)
        String goodsName,

        @Schema(description = "상품 가격", requiredMode = NOT_REQUIRED)
        Integer goodsPrice,

        @Schema(description = "상품 상태", requiredMode = NOT_REQUIRED)
        GoodsState goodsState,

        @Schema(description = "언어 유형", requiredMode = NOT_REQUIRED)
        LanguageType languageType
) {
        public GoodsSearchRequest{
                languageType = languageType == null ? LanguageType.DEFAULT : languageType;
        }
}
