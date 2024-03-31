package com.weverse.shop.domain.repository.querydsl.util;

import com.querydsl.jpa.impl.JPAQuery;
import com.weverse.shop.common.type.PaginationType;
import com.weverse.shop.domain.dto.PaginationRecord;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class PagingUtil {

    public static Slice<?> makeSlice(PaginationRecord paginationRecord, List<?> content, JPAQuery<Long> countQuery) {
        if(PaginationType.SCROLL == paginationRecord.paginationType()) {
            var pageSize = paginationRecord.getPageable().getPageSize();
            var hasNext = false;
            if(content.size() > pageSize) {
                content.remove(pageSize);
                hasNext = true;
            }
            return new SliceImpl<>(content, paginationRecord.getPageable(), hasNext);
        } else {
            return PageableExecutionUtils.getPage(content, paginationRecord.getPageable(), () -> countQuery.fetchOne());
        }
    }
}
