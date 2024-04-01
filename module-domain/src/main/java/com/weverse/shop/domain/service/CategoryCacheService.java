package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.response.CountByCategoryResponse;
import com.weverse.shop.external.client.AgifyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryCacheService {

    private final AgifyClient agifyClient;

    @Cacheable(cacheNames = "countByCategory")
    public List<CountByCategoryResponse> countByCategoryResponse(List<String> names){
        return agifyClient.findCountByCategory(names.toArray(new String[names.size()]));
    }
}
