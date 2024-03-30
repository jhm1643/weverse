package com.weverse.shop.util;

import com.querydsl.jpa.impl.JPAQuery;
import com.weverse.shop.dto.request.PaginationRequest;
import com.weverse.shop.dto.type.PaginationType;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class PagingUtil {

    public static Slice<?> makeSlice(PaginationRequest paginationRequest, List<?> content, JPAQuery<Long> countQuery) {
        if(PaginationType.SCROLL == paginationRequest.paginationType()) {
            var pageSize = paginationRequest.getPageable().getPageSize();
            var hasNext = false;
            if(content.size() > pageSize) {
                content.remove(pageSize);
                hasNext = true;
            }
            return new SliceImpl<>(content, paginationRequest.getPageable(), hasNext);
        } else {
            return PageableExecutionUtils.getPage(content, paginationRequest.getPageable(), () -> countQuery.fetchOne());
        }
    }
}
