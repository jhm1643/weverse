package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsRegistrationToCategoryRequest;
import com.weverse.shop.common.dto.request.GoodsRemoveFromCategoryRequest;
import com.weverse.shop.domain.entity.CategoryGoodsMapping;
import com.weverse.shop.domain.entity.embeded.CategoryGoodsMappingId;
import com.weverse.shop.domain.repository.CategoryGoodsMappingRepository;
import com.weverse.shop.domain.repository.CategoryRepository;
import com.weverse.shop.domain.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryGoodsMappingService {

    private final CategoryRepository categoryRepository;
    private final GoodsRepository goodsRepository;
    private final CategoryGoodsMappingRepository categoryGoodsMappingRepository;

    public void registrationGoodsToCategory(GoodsRegistrationToCategoryRequest request){
        categoryRepository.findById(request.categoryId())
                .ifPresent(category ->
                    goodsRepository.findById(request.goodsId())
                            .ifPresent(goods -> {
                                var categoryGoodsMapping = CategoryGoodsMapping.goodsRegistrationToCategory(category, goods);
                                category.addCategoryGoodsMapping(categoryGoodsMapping);
                                goods.addCategoryGoodsMapping(categoryGoodsMapping);
                            })
                );
    }

    public void removeGoodsFromCategory(GoodsRemoveFromCategoryRequest request){
        categoryGoodsMappingRepository.findById(CategoryGoodsMappingId.createId(request.categoryId(), request.goodsId()))
                .ifPresent(categoryGoodsMappingRepository::delete);
    }
}
