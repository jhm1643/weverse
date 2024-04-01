package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsCreateRequest;
import com.weverse.shop.common.dto.response.GoodsResponse;
import com.weverse.shop.domain.entity.Goods;
import com.weverse.shop.domain.entity.GoodsNameMultilingual;
import com.weverse.shop.domain.mapper.GoodsMapper;
import com.weverse.shop.domain.repository.GoodsCategoryRepository;
import com.weverse.shop.domain.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsCategoryRepository goodsCategoryRepository;
    private final GoodsRepository goodsRepository;

    private final GoodsMapper goodsMapper;

    public void createGoods(GoodsCreateRequest request){
        goodsCategoryRepository.findById(request.goodsCategoryId())
                .ifPresent(goodsCategory -> {
                    var goods = Goods.create(goodsMapper.toGoodsCreateRecord(request), goodsCategory);
                    request.goodsNames()
                            .forEach(e -> goods.addGoodsNameMultilingual(GoodsNameMultilingual.create(goods, e.languageType(), e.goodsName())));
                    goodsCategory.addGoods(goods);
                });
    }

    public GoodsResponse findGoods(Long goodsId){
        return goodsMapper.toGoodsResponse(goodsRepository.findById(goodsId)
                .orElseThrow(NoSuchElementException::new));
    }

    public int findStockCount(Long goodsId){
        var goods = goodsRepository.findById(goodsId).orElseThrow(NoSuchElementException::new);
        return goods.getStockCount();
    }

    public void decreaseStockCount(Long goodsId){
        goodsRepository.findById(goodsId)
                .ifPresent(Goods::decreaseStockCount);
    }

    public void remove(Long goodsId){
        goodsRepository.findById(goodsId)
                .ifPresent(goodsRepository::delete);
    }

    public void mappingGoodsCategory(Long goodsCategoryId, Long goodsId){
        goodsRepository.findById(goodsId)
                .ifPresent(goods ->
                        goodsCategoryRepository.findById(goodsCategoryId)
                                .ifPresent(goods::mappingGoodsCategory)
                );
    }
}
