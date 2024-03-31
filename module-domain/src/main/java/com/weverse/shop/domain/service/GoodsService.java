package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.GoodsRegistrationRequest;
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

    public void registration(GoodsRegistrationRequest request){
        goodsCategoryRepository.findById(request.goodsCategoryId())
                .ifPresent(goodsCategory -> {
                    var goods = Goods.registration(goodsMapper.toGoodsRegistrationRecord(request), goodsCategory);
                    request.goodsNames()
                            .forEach(e -> goods.addGoodsName(GoodsNameMultilingual.create(goods, e.languageType(), e.goodsName())));
                    goodsCategory.addGoods(goods);
                });
    }

    public GoodsResponse findGoods(Long id){
        return goodsMapper.toGoodsResponse(goodsRepository.findById(id)
                .orElseThrow(NoSuchElementException::new));
    }

    public int findStockCount(Long id){
        var goods = goodsRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return goods.getStockCount();
    }

    public void decreaseStockCount(Long id){
        goodsRepository.findById(id)
                .ifPresent(Goods::decreaseStockCount);
    }

    public void remove(Long id){
        goodsRepository.findById(id)
                .ifPresent(goodsRepository::delete);
    }
}
