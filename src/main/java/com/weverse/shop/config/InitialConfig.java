package com.weverse.shop.config;

import com.weverse.shop.service.CategoryService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialConfig {

    private final CategoryService categoryService;

    @PostConstruct
    public void initialDataSetting(){
        //카테고리 데이터 셋
        categoryService.registration("merch");
        categoryService.registration("album");
        categoryService.registration("book");
        categoryService.registration("membership");
        categoryService.registration("weverse");

        //카
    }
}
