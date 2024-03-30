package com.weverse.shop.mapper;

import com.weverse.shop.dto.GoodsSearchDto;
import com.weverse.shop.dto.response.GoodsListByCategorySearchResponse;
import com.weverse.shop.dto.response.GoodsSearchResponse;
import com.weverse.shop.entity.Goods;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = PaginationMapper.class)
public interface GoodsSearchMapper {

    GoodsListByCategorySearchResponse.Category toCategory(String categoryName, List<GoodsListByCategorySearchResponse.Category.Goods> goodsList);
    GoodsListByCategorySearchResponse.Category.Goods toGoods(Goods goods);
    List<GoodsSearchResponse.Content> toGoodsSearchResponse(List<GoodsSearchDto> dtos);
}
