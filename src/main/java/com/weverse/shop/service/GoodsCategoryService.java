package com.weverse.shop.service;

import com.weverse.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodsCategoryService {

    private final CategoryRepository categoryRepository;

    public void registration(Long categoryId, String name){
        categoryRepository.findById(categoryId)
                .ifPresent(category -> category.getGoodsCategories());
    }
}
