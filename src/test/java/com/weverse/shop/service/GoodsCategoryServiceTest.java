package com.weverse.shop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsCategoryServiceTest {

    @Autowired
    GoodsCategoryService goodsCategoryService;

    @Test
    void test(){
        goodsCategoryService.registration(1L, "test");
    }
}
