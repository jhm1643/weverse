package com.weverse.shop.service;

import com.weverse.shop.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArtistServiceTest {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtistService artistService;

    @Test
    void detachTest(){
        var beforeCount = artistRepository.count();
        artistService.detachTest();

        var afterCount = artistRepository.count();
        assert afterCount > beforeCount;
    }
}
