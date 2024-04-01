package com.weverse.shop.domain.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weverse.shop.common.dto.request.GoodsSearchRequest;
import com.weverse.shop.domain.dto.GoodsSearchRecord;
import com.weverse.shop.domain.dto.PaginationRecord;
import com.weverse.shop.domain.repository.querydsl.util.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import static com.weverse.shop.domain.entity.QCategory.category;
import static com.weverse.shop.domain.entity.QCategoryGoodsMapping.categoryGoodsMapping;
import static com.weverse.shop.domain.entity.QGoods.goods;
import static com.weverse.shop.domain.entity.QGoodsCategory.goodsCategory;
import static com.weverse.shop.domain.entity.QGoodsNameMultilingual.goodsNameMultilingual;
import static org.apache.commons.lang3.ObjectUtils.anyNotNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Repository
@RequiredArgsConstructor
public class GoodsSearchQuerydsl {

    private final JPAQueryFactory queryFactory;

    public Slice<GoodsSearchRecord> searchGoodsByCategoryId(Long categoryId, PaginationRecord paginationRecord, GoodsSearchRequest goodsSearchRequest){
        var content = queryFactory
                .select(
                        Projections.constructor(
                                GoodsSearchRecord.class,
                                category.id,
                                category.name,
                                goodsCategory.id,
                                goodsCategory.name,
                                goods.id,
                                goodsNameMultilingual.name,
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
                .from(categoryGoodsMapping)
                .innerJoin(category).on(category.eq(categoryGoodsMapping.category))
                .innerJoin(goods).on(goods.eq(categoryGoodsMapping.goods))
                .innerJoin(goodsNameMultilingual).on(goodsNameMultilingual.id.goodsId.eq(goods.id)
                        .and(goodsNameMultilingual.id.languageType.eq(goodsSearchRequest.languageType())))
                .leftJoin(goodsCategory).on(goodsCategory.eq(goods.goodsCategory))
                .where(category.id.eq(categoryId)
                        .and(goodsConditionProvider(goodsSearchRequest))
                )
                .offset(paginationRecord.getOffset())
                .limit(paginationRecord.getLimit())
                .fetch();

        return (Slice<GoodsSearchRecord>) PagingUtil.makeSlice(paginationRecord, content, getCount(goodsSearchRequest));
    }

    private JPAQuery<Long> getCount(GoodsSearchRequest request){
        return queryFactory
                .select(goods.count())
                .from(goods)
                .where(goodsConditionProvider(request));
    }

    public BooleanBuilder goodsConditionProvider(GoodsSearchRequest request){
        var booleanBuilder = new BooleanBuilder();

        if(isNotEmpty(request.goodsName())){
            booleanBuilder.and(goodsNameMultilingual.name.eq(request.goodsName()));
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
