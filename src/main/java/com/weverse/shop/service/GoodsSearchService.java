package com.weverse.shop.service;

import com.weverse.shop.dto.request.GoodsSearchRequest;
import com.weverse.shop.dto.response.GoodsListByCategorySearchResponse;
import com.weverse.shop.dto.response.GoodsSearchResponse;
import com.weverse.shop.mapper.GoodsSearchMapper;
import com.weverse.shop.mapper.PaginationMapper;
import com.weverse.shop.repository.CategoryRepository;
import com.weverse.shop.repository.querydsl.GoodsSearchQuerydsl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsSearchService {

    private final CategoryRepository categoryRepository;
    private final GoodsSearchQuerydsl goodsSearchQuerydsl;

    private final GoodsSearchMapper goodsSearchMapper;
    private final PaginationMapper paginationMapper;

    public GoodsListByCategorySearchResponse searchGoodsListByCategory(){
        var categories = categoryRepository.findAll().stream()
                .map(category -> {
                    var goodsList = category.getGoodsCategories().stream()
                            .flatMap(goodsCategory -> goodsCategory.getGoodsList().stream().map(goodsSearchMapper::toGoods))
                            .collect(Collectors.toList());
                    return goodsSearchMapper.toCategory(category.getName(), goodsList);
                }).collect(Collectors.toList());

        return new GoodsListByCategorySearchResponse(categories);
    }

    public GoodsSearchResponse searchGoods(GoodsSearchRequest goodsSearchRequest){
        var goodsSearchDtoSlice = goodsSearchQuerydsl.searchGoods(goodsSearchRequest);
        return new GoodsSearchResponse(
                paginationMapper.toPaginationResponse(goodsSearchDtoSlice),
                goodsSearchMapper.toGoodsSearchResponse(goodsSearchDtoSlice.getContent())
        );
    }
}
