package com.weverse.shop.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {

    COUNT_BY_CATEGORY("countByCategory", 12, 10000)
    ;

    private final String cacheName;
    private final int expiredAfterWrite;
    private final int maxSize;
}
