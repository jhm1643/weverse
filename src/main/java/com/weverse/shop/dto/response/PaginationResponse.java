package com.weverse.shop.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "페이징 응답 정보")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record PaginationResponse(
        @Schema(description = "현재 페이지 번호")
        Integer currentPage,

        @Schema(description = "총 페이지 수 (only page)")
        Integer totalPages,

        @Schema(description = "총 row 수 (only page)" )
        Long totalElements,

        @Schema(description = "다음 페이지 존재 여부 (only page)")
        Boolean hasNext,

        @Schema(description = "이전 페이지 존재 여부 (only page)")
        Boolean hasPrevious,

        @Schema(description = "마지막 페이지 여부 (only scroll)")
        Boolean isLast
) {
}
