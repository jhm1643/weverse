package com.weverse.shop.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AgifyClient {

    private final RestTemplate template;
}
