package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsSearchRequest;
import com.weverse.shop.common.dto.request.PaginationRequest;
import com.weverse.shop.common.dto.response.GoodsResponse;
import com.weverse.shop.common.dto.response.GoodsSearchResponse;
import com.weverse.shop.domain.mapper.GoodsSearchMapper;
import com.weverse.shop.domain.mapper.PaginationMapper;
import com.weverse.shop.domain.repository.CategoryRepository;
import com.weverse.shop.domain.repository.querydsl.GoodsSearchQuerydsl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
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

    public List<GoodsResponse> searchGoodsByCategoryId(Long categoryId){
        var category = categoryRepository.findById(categoryId).orElseThrow(NoSuchElementException::new);
        return category.getCategoryGoodsMappings().stream()
                .map(categoryGoodsMapping -> goodsSearchMapper.toGoodsResponse(categoryGoodsMapping.getGoods()))
                .collect(Collectors.toList());
    }

    public GoodsSearchResponse searchGoodsByCategoryId(Long categoryId, PaginationRequest paginationRequest, GoodsSearchRequest goodsSearchRequest){
        var paginationRecord = paginationMapper.toPaginationRecord(paginationRequest);
        var goodsSearchDtoSlice = goodsSearchQuerydsl.searchGoodsByCategoryId(categoryId, paginationRecord, goodsSearchRequest);
        return new GoodsSearchResponse(
                paginationMapper.toPaginationResponse(goodsSearchDtoSlice),
                goodsSearchMapper.toGoodsSearchResponse(goodsSearchDtoSlice.getContent())
        );
    }
}
