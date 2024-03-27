package com.weverse.shop.service;

import com.weverse.shop.dto.request.CategoryRegistrationRequest;
import com.weverse.shop.entity.Category;
import com.weverse.shop.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final ArtistRepository artistRepository;

    public void registration(CategoryRegistrationRequest request){
        artistRepository.findById(request.artistId())
                        .ifPresent(artist -> artist.addCategory(
                                Category.registration(request.categoryName(), artist)
                        ));
    }
}
