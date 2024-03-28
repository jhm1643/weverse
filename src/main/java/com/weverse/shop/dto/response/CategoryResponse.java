package com.weverse.shop.dto.response;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String name,
        Boolean active,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {}
