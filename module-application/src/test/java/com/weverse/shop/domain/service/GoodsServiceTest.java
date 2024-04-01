package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsCreateRequest;
import com.weverse.shop.common.type.LanguageType;
import com.weverse.shop.domain.repository.GoodsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsRepository goodsRepository;

    @Test
    void 상품_생성(){
        goodsService.createGoods(new GoodsCreateRequest(
                1L,
                List.of(
                        new GoodsCreateRequest.GoodsNameMultilingual(LanguageType.EN, "영어 상품명 test"),
                        new GoodsCreateRequest.GoodsNameMultilingual(LanguageType.KO, "한국어 상품명 test"),
                        new GoodsCreateRequest.GoodsNameMultilingual(LanguageType.JA, "일본어 상품명 test")
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
