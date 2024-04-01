package com.weverse.shop.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 카테고리 생성 요청")
public record GoodsCategoryCreateRequest(
        @Schema(description = "상품 카테고리 명") String goodsCategoryName
) {}
