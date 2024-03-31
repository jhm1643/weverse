package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsSearchRequest;
import com.weverse.shop.common.dto.request.PaginationRequest;
import com.weverse.shop.common.dto.response.CountByCategoryResponse;
import com.weverse.shop.common.dto.response.GoodsListByCategorySearchResponse;
import com.weverse.shop.common.dto.response.GoodsSearchResponse;
import com.weverse.shop.domain.mapper.GoodsSearchMapper;
import com.weverse.shop.domain.mapper.PaginationMapper;
import com.weverse.shop.domain.repository.CategoryRepository;
import com.weverse.shop.domain.repository.querydsl.GoodsSearchQuerydsl;
import com.weverse.shop.external.client.AgifyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GoodsSearchService {

    private final CategoryRepository categoryRepository;
    private final GoodsSearchQuerydsl goodsSearchQuerydsl;

    private final GoodsSearchMapper goodsSearchMapper;
    private final PaginationMapper paginationMapper;

    private final AgifyClient agifyClient;

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

    public GoodsSearchResponse searchGoods(PaginationRequest paginationRequest, GoodsSearchRequest goodsSearchRequest){
        var paginationRecord = paginationMapper.toPaginationRecord(paginationRequest);
        var goodsSearchDtoSlice = goodsSearchQuerydsl.searchGoods(paginationRecord, goodsSearchRequest);
        return new GoodsSearchResponse(
                paginationMapper.toPaginationResponse(goodsSearchDtoSlice),
                goodsSearchMapper.toGoodsSearchResponse(goodsSearchDtoSlice.getContent())
        );
    }

    @Cacheable(cacheNames = "countByCategory")
    public List<CountByCategoryResponse> countByCategoryResponse(List<String> names){
        return agifyClient.findCountByCategory(names.toArray(new String[names.size()]));
    }
}
