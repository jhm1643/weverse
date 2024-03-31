package com.weverse.shop.external.client;

import com.weverse.shop.common.dto.response.CountByCategoryResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface AgifyClient {

    @GetExchange
    List<CountByCategoryResponse> findCountByCategory(@RequestParam(name = "name[]") String ... names);
}
