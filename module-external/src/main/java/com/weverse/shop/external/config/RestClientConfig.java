package com.weverse.shop.external.config;

import com.weverse.shop.external.client.AgifyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static java.time.Duration.ofSeconds;
import static org.springframework.boot.web.client.ClientHttpRequestFactorySettings.DEFAULTS;

@Configuration
public class RestClientConfig {

    @Value("${external.api.agify.url}")
    private String agifyBaseUrl;

    @Bean
    public AgifyClient agifyClient(){
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(RestClient.builder()
                                .baseUrl(agifyBaseUrl)
                                .requestFactory(ClientHttpRequestFactories.get(DEFAULTS
                                        .withConnectTimeout(ofSeconds(3))
                                        .withReadTimeout(ofSeconds(1))))
                                .build())
                ).build()
                .createClient(AgifyClient.class);
    }
}
