package com.weverse.shop.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GoodsQuerydsl {

    private final JPAQueryFactory queryFactory;
}
