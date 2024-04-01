package com.weverse.shop.domain.config;

import com.weverse.shop.common.dto.request.CategoryCreateRequest;
import com.weverse.shop.common.dto.request.GoodsCategoryCreateRequest;
import com.weverse.shop.common.dto.request.GoodsCreateRequest;
import com.weverse.shop.common.type.LanguageType;
import com.weverse.shop.domain.repository.ArtistRepository;
import com.weverse.shop.domain.repository.GoodsCategoryRepository;
import com.weverse.shop.domain.service.ArtistService;
import com.weverse.shop.domain.service.CategoryService;
import com.weverse.shop.domain.service.GoodsCategoryService;
import com.weverse.shop.domain.service.GoodsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InitialConfig {

    private final ArtistRepository artistRepository;
    private final GoodsCategoryRepository goodsCategoryRepository;

    private final ArtistService artistService;
    private final CategoryService categoryService;
    private final GoodsCategoryService goodsCategoryService;
    private final GoodsService goodsService;

    @PostConstruct
    @Transactional
    public void initialDataSetting(){
        //아티스트 데이터 셋
        var artistName = "bts";
        artistService.registration(artistName);

        //카테고리 데이터 셋
        artistRepository.findByName(artistName)
                .ifPresent(artist -> {
                    categoryService.createCategory(new CategoryCreateRequest(artist.getId(), "merch"));
                    categoryService.createCategory(new CategoryCreateRequest(artist.getId(), "album"));
                    categoryService.createCategory(new CategoryCreateRequest(artist.getId(), "book"));
                    categoryService.createCategory(new CategoryCreateRequest(artist.getId(), "membership"));
                    categoryService.createCategory(new CategoryCreateRequest(artist.getId(), "weverse"));
                });

        //상품 카테고리 데이터 셋
        goodsCategoryService.registration(
                new GoodsCategoryCreateRequest("goodsCategory")
        );

        //상품 데이터 셋
        goodsCategoryRepository.findAll()
                .forEach(goodsCategory -> {
                    for (int i = 1; i <= 20; i++) {
                        var goodsCreateRequest = new GoodsCreateRequest(
                                goodsCategory.getId(),
                                List.of(
                                        new GoodsCreateRequest.GoodsNameMultilingual(LanguageType.DEFAULT, "기본 상품명" + i),
                                        new GoodsCreateRequest.GoodsNameMultilingual(LanguageType.EN, "영어 상품명" + i),
                                        new GoodsCreateRequest.GoodsNameMultilingual(LanguageType.KO, "한국어 상품명" + i),
                                        new GoodsCreateRequest.GoodsNameMultilingual(LanguageType.JA, "일본어 상품명" + i)
                                ),
                                i == 1 ? 0 : 1, //첫번 째 상품은 품절로 등록
                                1000 + i,
                                1,
                                "판매 공지!!",
                                "test.jpg",
                                "{" + "\"test\"" + "}",
                                false,
                                false,
                                null,
                                null
                        );
                        goodsService.createGoods(goodsCreateRequest);
                    }
                });

        //
    }
}
