package com.weverse.shop.service;

import com.weverse.shop.dto.request.GoodsCategoryRegistrationRequest;
import com.weverse.shop.dto.response.GoodsCategoryResponse;
import com.weverse.shop.entity.GoodsCategory;
import com.weverse.shop.mapper.GoodsCategoryMapper;
import com.weverse.shop.repository.CategoryRepository;
import com.weverse.shop.repository.GoodsCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsCategoryService {

    private final CategoryRepository categoryRepository;
    private final GoodsCategoryRepository goodsCategoryRepository;

    private final GoodsCategoryMapper goodsCategoryMapper;

    public GoodsCategoryResponse findById(Long id){
        return goodsCategoryMapper.toGoodsCategoryResponse(
                goodsCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new)
        );
    }

    public void registration(GoodsCategoryRegistrationRequest request){
        categoryRepository.findById(request.categoryId())
                .ifPresent(category -> {
                    var goodsCategory = GoodsCategory.registration(request.name(), category);
                    category.addGoodsCategory(goodsCategory);
                });
    }

    public void modifyActive(Long id, Boolean active){
        goodsCategoryRepository.findById(id)
                .ifPresent(goodsCategory -> goodsCategory.modifyActive(active));
    }
}
