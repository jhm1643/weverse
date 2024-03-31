package com.weverse.shop.domain.service;

import com.weverse.shop.domain.entity.Artist;
import com.weverse.shop.domain.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public void registration(String name){
        artistRepository.save(Artist.registration(name));
    }
}
