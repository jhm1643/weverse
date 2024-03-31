package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsCategoryRegistrationRequest;
import com.weverse.shop.common.dto.response.GoodsCategoryResponse;
import com.weverse.shop.domain.entity.GoodsCategory;
import com.weverse.shop.domain.mapper.GoodsCategoryMapper;
import com.weverse.shop.domain.repository.CategoryRepository;
import com.weverse.shop.domain.repository.GoodsCategoryRepository;
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
                    var goodsCategory = GoodsCategory.registration(request.goodsCategoryName(), category);
                    category.addGoodsCategory(goodsCategory);
                });
    }

    public void remove(Long id){
        goodsCategoryRepository.findById(id)
                .ifPresent(goodsCategoryRepository::delete);
    }

    public void modifyName(Long id, String name){
        goodsCategoryRepository.findById(id)
                .ifPresent(goodsCategory -> goodsCategory.modifyName(name));
    }
}
