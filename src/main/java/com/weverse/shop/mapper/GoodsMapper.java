package com.weverse.shop.mapper;

import com.weverse.shop.dto.GoodsRegistrationRecord;
import com.weverse.shop.dto.request.GoodsRegistrationRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GoodsMapper {

    GoodsRegistrationRecord toGoodsRegistrationRecord(GoodsRegistrationRequest goodsRegistrationRequest);
}
