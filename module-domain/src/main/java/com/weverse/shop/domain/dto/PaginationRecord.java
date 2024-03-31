package com.weverse.shop.domain.dto;

import com.weverse.shop.common.type.PaginationType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record PaginationRecord(
        Integer pageNo,
        Integer pageSize,
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
