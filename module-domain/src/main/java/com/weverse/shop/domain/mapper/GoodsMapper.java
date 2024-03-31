package com.weverse.shop.domain.mapper;

import com.weverse.shop.common.dto.request.GoodsRegistrationRequest;
import com.weverse.shop.common.dto.response.GoodsResponse;
import com.weverse.shop.domain.dto.GoodsRegistrationRecord;
import com.weverse.shop.domain.entity.Goods;
import com.weverse.shop.domain.entity.GoodsNameMultilingual;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GoodsMapper {

    GoodsRegistrationRecord toGoodsRegistrationRecord(GoodsRegistrationRequest goodsRegistrationRequest);

    @Mapping(target = "multilingualNames", source = "goods.goodsNames")
    GoodsResponse toGoodsResponse(Goods goods);

    @Mapping(target = "name", source = "goodsNameMultilingual.name")
    @Mapping(target = "languageType", source = "goodsNameMultilingual.id.languageType")
    GoodsResponse.MultilingualName toMultilingualName(GoodsNameMultilingual goodsNameMultilingual);
}
