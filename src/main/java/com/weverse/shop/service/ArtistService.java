package com.weverse.shop.service;

import com.weverse.shop.entity.Artist;
import com.weverse.shop.repository.ArtistRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtistService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ArtistRepository artistRepository;

    public void registration(String name){
        artistRepository.save(Artist.registration(name));
    }

    public void detachTest(){
        var artist = artistRepository.findById(1L).orElseThrow(NoSuchElementException::new);
        entityManager.detach(artist);
        artist.idReset();
        artistRepository.save(artist);
    }
}
