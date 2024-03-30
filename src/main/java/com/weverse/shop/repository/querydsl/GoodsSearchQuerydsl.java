package com.weverse.shop.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weverse.shop.dto.GoodsSearchDto;
import com.weverse.shop.dto.request.GoodsSearchRequest;
import com.weverse.shop.util.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import static com.weverse.shop.entity.QCategory.category;
import static com.weverse.shop.entity.QGoods.goods;
import static com.weverse.shop.entity.QGoodsCategory.goodsCategory;
import static org.apache.commons.lang3.ObjectUtils.anyNotNull;

@Repository
@RequiredArgsConstructor
public class GoodsSearchQuerydsl {

    private final JPAQueryFactory queryFactory;

    public Slice<GoodsSearchDto> searchGoods(GoodsSearchRequest goodsSearchRequest){
        var paginationRequest = goodsSearchRequest.pagination();
        var content = queryFactory
                .select(
                        Projections.constructor(
                                GoodsSearchDto.class,
                                category.id,
                                category.name,
                                goodsCategory.id,
                                goodsCategory.name,
                                goods.id,
                                goods.name,
                                goods.stockCount,
                                goods.price,
                                goods.purchasePossibleCount,
                                goods.salesNotice,
                                goods.descriptionImageUrl,
                                goods.productNoticeInfo,
                                goods.isReservationSale,
                                goods.isExclusiveSale,
                                goods.deliveryStartDueFromDtm,
                                goods.deliveryStartDueToDtm,
                                goods.createAt,
                                goods.updateAt
                ))
                .from(goods)
                .innerJoin(goodsCategory).on(goodsCategory.eq(goods.goodsCategory))
                .innerJoin(category).on(goodsCategory.category.eq(category))
                .where(goodsConditionProvider(goodsSearchRequest))
                .offset(paginationRequest.getOffset())
                .limit(paginationRequest.getLimit())
                .fetch();

        return (Slice<GoodsSearchDto>) PagingUtil.makeSlice(paginationRequest, content, getCount(goodsSearchRequest));
    }

    private JPAQuery<Long> getCount(GoodsSearchRequest request){
        return queryFactory
                .select(goods.count())
                .from(goods)
                .where(goodsConditionProvider(request));
    }

    public BooleanBuilder goodsConditionProvider(GoodsSearchRequest request){
        var booleanBuilder = new BooleanBuilder();
        if(anyNotNull(request.goodsName())){
            booleanBuilder.and(goods.name.eq(request.goodsName()));
        }
        if(anyNotNull(request.goodsPrice())){
            booleanBuilder.and(goods.price.eq(request.goodsPrice()));
        }
        if(anyNotNull(request.goodsState())){
            booleanBuilder.and(
                    switch (request.goodsState()){
                        case SALE -> goods.stockCount.gt(0);
                        case SOLD_OUT -> goods.stockCount.eq(0);
                    }
            );
        }
        return booleanBuilder;
    }
}
