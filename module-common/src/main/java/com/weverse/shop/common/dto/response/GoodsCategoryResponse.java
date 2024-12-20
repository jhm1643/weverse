package com.weverse.shop.common.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "삼품 카테고리 조회")
public record GoodsCategoryResponse(
        @Schema(description = "상품 카테고리 ID") Long id,
        @Schema(description = "상품 카테고리 명") String name,
        @Schema(description = "생성 일시") LocalDateTime createAt,
        @Schema(description = "수정 일시") LocalDateTime updateAt
) {}
