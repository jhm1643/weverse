package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsCategoryCreateRequest;
import com.weverse.shop.common.dto.response.GoodsCategoryResponse;
import com.weverse.shop.domain.entity.Goods;
import com.weverse.shop.domain.entity.GoodsCategory;
import com.weverse.shop.domain.mapper.GoodsCategoryMapper;
import com.weverse.shop.domain.repository.GoodsCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsCategoryService {

    private final GoodsCategoryRepository goodsCategoryRepository;
    private final GoodsCategoryMapper goodsCategoryMapper;

    public GoodsCategoryResponse findById(Long id){
        return goodsCategoryMapper.toGoodsCategoryResponse(
                goodsCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new)
        );
    }

    public void registration(GoodsCategoryCreateRequest request){
        goodsCategoryRepository.save(GoodsCategory.registration(request.goodsCategoryName()));
    }

    public void remove(Long id){
        goodsCategoryRepository.findById(id)
                .ifPresent(goodsCategory -> {
                    goodsCategoryRepository.delete(goodsCategory);
                    //상품의 상품 카테고리 매핑 해제
                    goodsCategory.getGoodsList().forEach(Goods::releaseMappingGoodsCategory);
                });
    }

    public void modifyName(Long id, String name){
        goodsCategoryRepository.findById(id)
                .ifPresent(goodsCategory -> goodsCategory.modifyName(name));
    }
}
