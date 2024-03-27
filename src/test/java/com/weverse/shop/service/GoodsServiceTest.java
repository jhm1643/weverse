package com.weverse.shop.service;

import com.weverse.shop.dto.request.GoodsRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Test
    void goodsRegistrationTest(){
        goodsService.registration(new GoodsRegistrationRequest(
                1L,
                "test",
                100,
                1000,
                1,
                "판매 공지!!",
                "test.jpg",
                "{" + "\"test\"" + "}",
                false,
                null,
                null
        ));
    }
}
