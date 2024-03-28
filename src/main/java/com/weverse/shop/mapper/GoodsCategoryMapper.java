package com.weverse.shop.mapper;

import com.weverse.shop.dto.response.GoodsCategoryResponse;
import com.weverse.shop.entity.GoodsCategory;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GoodsCategoryMapper {

    GoodsCategoryResponse toGoodsCategoryResponse(GoodsCategory goodsCategory);
}
