package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsCategoryRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsCategoryServiceTest {

    @Autowired
    GoodsCategoryService goodsCategoryService;

    @Test
    void 상품_카테고리_등록(){
        goodsCategoryService.registration(
                new GoodsCategoryRegistrationRequest(
                        1L,
                        "test입니다."
                )
        );
    }
}
