package com.weverse.shop.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "카테고리 상품 등록")
public record GoodsRegistrationToCategoryRequest(
        @Schema(description = "카테고리 ID")
        @NotNull
        Long categoryId,

        @Schema(description = "상품 ID")
        @NotNull
        Long goodsId
) {
}
