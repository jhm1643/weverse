package com.weverse.shop.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 등록 요청")
public record CategoryRegistrationRequest(
        @Schema(description = "아티스트 ID") Long artistId,
        @Schema(description = "카테고리 명") String categoryName
) {}
