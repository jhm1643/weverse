package com.weverse.shop.service;

import com.weverse.shop.dto.response.GoodsListByCategorySearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsSearchService {



    public GoodsListByCategorySearchResponse searchGoodsListByCategory(int pageSize, int pageNo){
        return null;
    }
}
