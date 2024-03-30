package com.weverse.shop.dto.request;

import com.weverse.shop.dto.type.PaginationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Schema(description = "페이지 요청")
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
    public Pageable getPageable(){
        return PageRequest.of(pageNo - 1, pageSize);
    }

    public long getOffset(){
        return getPageable().getOffset();
    }

    public int getLimit(){
        //slice의 경우 다음 스크롤에 값이 있는지 확인하기 위해 limit에 +1을 한다.
        return PaginationType.SCROLL == paginationType ? getPageable().getPageSize() + 1 : getPageable().getPageSize();
    }
}
