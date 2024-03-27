package com.weverse.shop.service;

import com.weverse.shop.dto.request.GoodsRegistrationRequest;
import com.weverse.shop.entity.Goods;
import com.weverse.shop.mapper.GoodsMapper;
import com.weverse.shop.repository.ArtistRepository;
import com.weverse.shop.repository.GoodsCategoryRepository;
import com.weverse.shop.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

    private final ArtistRepository artistRepository;
    private final GoodsCategoryRepository goodsCategoryRepository;
    private final GoodsRepository goodsRepository;

    private final GoodsMapper goodsMapper;

    public void registration(GoodsRegistrationRequest request){
        goodsCategoryRepository.findById(request.goodsCategoryId())
                .ifPresent(goodsCategory -> {
                    var goods = Goods.registration(goodsMapper.toGoodsRegistrationRecord(request), goodsCategory);
                    goodsCategory.addGoods(goods);
                });
    }

    public void remove(Long goodsId){
        goodsRepository.findById(goodsId)
                .ifPresent(goodsRepository::delete);
    }
}
