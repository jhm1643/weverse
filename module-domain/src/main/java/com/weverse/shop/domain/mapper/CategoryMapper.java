package com.weverse.shop.domain.mapper;

import com.weverse.shop.common.dto.response.CategoryResponse;
import com.weverse.shop.common.dto.response.CountByCategoryResponse;
import com.weverse.shop.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CategoryMapper {

    @Mapping(target = "count", source = "response.count")
    @Mapping(target = "name", source = "category.name")
    CategoryResponse toCategoryResponse(Category category, CountByCategoryResponse response);
}
