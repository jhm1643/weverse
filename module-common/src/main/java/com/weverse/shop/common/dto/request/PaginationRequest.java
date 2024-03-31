package com.weverse.shop.common.dto.request;

import com.weverse.shop.common.type.PaginationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "페이지 요청", requiredMode = REQUIRED)
public record PaginationRequest(
        @Schema(description = "페이지 번호")
        @Min(value = 1, message = "페이지 번호의 최소 값은 1입니다.")
        Integer pageNo,

        @Schema(description = "페이지 사이즈")
        @Min(value = 10, message = "페이지 사이즈의 최소 값은 10 입니다.")
        @Max(value = 100, message = "페이지 사이즈의 최대 값은 10 입니다.")
        Integer pageSize,

        @Schema(description = "페이지네이션 유형")
        @NotNull(message = "페이지네이션 유형 값은 필수 입니다.")
        PaginationType paginationType
) {
}
