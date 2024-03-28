package com.weverse.shop.dto.response;

import java.time.LocalDateTime;

public record GoodsCategoryResponse(
        Long id,
        String name,
        Boolean active,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {}
