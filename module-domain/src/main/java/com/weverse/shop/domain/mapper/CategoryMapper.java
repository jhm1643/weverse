package com.weverse.shop.domain.mapper;

import com.weverse.shop.common.dto.response.CategoryResponse;
import com.weverse.shop.domain.entity.Category;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
}
