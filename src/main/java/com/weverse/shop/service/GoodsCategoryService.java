package com.weverse.shop.service;

import com.weverse.shop.entity.GoodsCategory;
import com.weverse.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsCategoryService {

    private final CategoryRepository categoryRepository;

    public void registration(Long categoryId, String name){
        categoryRepository.findById(categoryId)
                .ifPresent(category -> {
                    var goodsCategory = GoodsCategory.registration(name, category);
                    category.addGoodsCategory(goodsCategory);
                });
    }
}
