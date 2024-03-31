package com.weverse.shop.domain.mapper;

import com.weverse.shop.common.dto.response.GoodsCategoryResponse;
import com.weverse.shop.domain.entity.GoodsCategory;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GoodsCategoryMapper {

    GoodsCategoryResponse toGoodsCategoryResponse(GoodsCategory goodsCategory);
}
