package com.weverse.shop.common.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 별 상품 갯수 조회")
public record CountByCategoryResponse(
        @Schema(description = "갯수") Long count,
        @Schema(description = "카테고리 명") String name,
        @Schema(description = "age") Long age
) {
}
