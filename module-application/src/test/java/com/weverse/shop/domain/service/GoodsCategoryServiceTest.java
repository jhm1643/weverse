package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsCategoryCreateRequest;
import com.weverse.shop.domain.repository.GoodsCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsCategoryServiceTest {

    @Autowired
    GoodsCategoryService goodsCategoryService;

    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;

    @Test
    void 상품_카테고리_이름_수정(){
        long id = 1;
        var name = "test name";
        goodsCategoryService.modifyName(id, name);

        var result = goodsCategoryRepository.findById(id);

        assert result.isPresent();
        assert result.get().getName().equals(name);
    }

    @Test
    void 상품_카테고리_조회(){
        long id = 1;
        var result = goodsCategoryService.findById(id);
        assert result.id() == id;
    }

    @Test
    void 상품_카테고리_삭제(){
        long id = 1;
        goodsCategoryService.remove(id);

        var result = goodsCategoryRepository.findById(id);
        assert result.isPresent() == false;
    }

    @Test
    void 상품_카테고리_생성(){
        var name = "test name";
        goodsCategoryService.registration(
                new GoodsCategoryCreateRequest(name)
        );

        var result = goodsCategoryRepository.findByName(name);
        assert result.isPresent();
        assert result.get().getName().equals(name);
    }
}
