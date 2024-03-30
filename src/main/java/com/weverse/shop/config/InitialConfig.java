package com.weverse.shop.config;

import com.weverse.shop.dto.request.CategoryRegistrationRequest;
import com.weverse.shop.dto.request.GoodsCategoryRegistrationRequest;
import com.weverse.shop.dto.request.GoodsRegistrationRequest;
import com.weverse.shop.repository.ArtistRepository;
import com.weverse.shop.repository.CategoryRepository;
import com.weverse.shop.repository.GoodsCategoryRepository;
import com.weverse.shop.service.ArtistService;
import com.weverse.shop.service.CategoryService;
import com.weverse.shop.service.GoodsCategoryService;
import com.weverse.shop.service.GoodsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class InitialConfig {

    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;
    private final GoodsCategoryRepository goodsCategoryRepository;

    private final ArtistService artistService;
    private final CategoryService categoryService;
    private final GoodsCategoryService goodsCategoryService;
    private final GoodsService goodsService;

    @PostConstruct
    @Transactional
    public void initialDataSetting(){
        for (int i = 1; i <= 3; i++) {
            //아티스트 데이터 셋
            var artistName = "artist" + i;
            artistService.registration(artistName);

            //카테고리 데이터 셋
            artistRepository.findByName(artistName)
                            .ifPresent(artist -> {
                                categoryService.registration(new CategoryRegistrationRequest(artist.getId(), "merch"));
                                categoryService.registration(new CategoryRegistrationRequest(artist.getId(), "album"));
                                categoryService.registration(new CategoryRegistrationRequest(artist.getId(), "book"));
                                categoryService.registration(new CategoryRegistrationRequest(artist.getId(), "membership"));
                                categoryService.registration(new CategoryRegistrationRequest(artist.getId(), "weverse"));
                            });

        }

        //상품 카테고리 데이터 셋
        var categories = categoryRepository.findAll();
        for (int i = 0; i < categories.size(); i++) {
            for (int j = 0; j < 5; j++) {
                goodsCategoryService.registration(
                        new GoodsCategoryRegistrationRequest(
                                categories.get(i).getId(),
                                "goodsCategory" + ((i * 5) + (j + 1))
                        )
                );
            }
        }

        //상품 데이터 셋
        var goodsCategories = goodsCategoryRepository.findAll();
        for (int i = 0; i < goodsCategories.size(); i++) {
            for (int j = 0; j < 20; j++) {
                var goodsRegistrationRequest = new GoodsRegistrationRequest(
                        goodsCategories.get(i).getId(),
                        "goods" + ((i * 20) + (j + 1)),
                        j == 19 ? 0 : 100,
                        1000+i,
                        1,
                        "판매 공지!!",
                        "test.jpg",
                        "{" + "\"test\"" + "}",
                        false,
                        false,
                        null,
                        null
                );
                goodsService.registration(goodsRegistrationRequest);
            }
        }
    }
}
