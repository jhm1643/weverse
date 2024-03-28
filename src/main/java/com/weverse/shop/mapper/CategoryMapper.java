package com.weverse.shop.mapper;

import com.weverse.shop.dto.response.CategoryResponse;
import com.weverse.shop.entity.Category;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
}
