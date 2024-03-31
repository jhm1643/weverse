package com.weverse.shop.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 카테고리 등록 요청")
public record GoodsCategoryRegistrationRequest(
        @Schema(description = "카테고리 ID") Long categoryId,
        @Schema(description = "상품 카테고리 명") String goodsCategoryName
) {}
