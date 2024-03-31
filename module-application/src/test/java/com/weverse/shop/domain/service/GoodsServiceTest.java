package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsRegistrationRequest;
import com.weverse.shop.common.type.LanguageType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Test
    void goodsRegistrationTest(){
        goodsService.registration(new GoodsRegistrationRequest(
                1L,
                List.of(
                        new GoodsRegistrationRequest.GoodsNameMultilingual(LanguageType.EN, "영어 상품명 test"),
                        new GoodsRegistrationRequest.GoodsNameMultilingual(LanguageType.KO, "한국어 상품명 test"),
                        new GoodsRegistrationRequest.GoodsNameMultilingual(LanguageType.JA, "일본어 상품명 test")
                ),
                100,
                1000,
                1,
                "판매 공지!!",
                "test.jpg",
                "{" + "\"test\"" + "}",
                false,
                false,
                null,
                null
        ));
    }
}
