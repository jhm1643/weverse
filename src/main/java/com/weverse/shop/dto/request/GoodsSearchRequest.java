package com.weverse.shop.dto.request;

import com.weverse.shop.dto.type.GoodsState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "상품 검색 요청")
public record GoodsSearchRequest(
        @Schema(description = "페이징 요청", requiredMode = REQUIRED)
        @NotNull(message = "페이지 요청 값은 필수 입니다.")
        @Valid
        PaginationRequest pagination,

        @Schema(description = "상품 명", requiredMode = NOT_REQUIRED)
        String goodsName,

        @Schema(description = "상품 가격", requiredMode = NOT_REQUIRED)
        @NotNull(message = "페이지 요청 값은 필수 입니다.")
        Integer goodsPrice,

        @Schema(description = "상품 상태", requiredMode = NOT_REQUIRED)
        GoodsState goodsState
) {
}
