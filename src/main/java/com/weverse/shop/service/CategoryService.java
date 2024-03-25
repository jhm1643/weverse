package com.weverse.shop.service;

import com.weverse.shop.entity.Category;
import com.weverse.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void registration(String name){
        categoryRepository.save(Category.registration(name));
    }
}
