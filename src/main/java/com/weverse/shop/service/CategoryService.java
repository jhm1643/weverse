package com.weverse.shop.service;

import com.weverse.shop.dto.request.CategoryRegistrationRequest;
import com.weverse.shop.dto.response.CategoryResponse;
import com.weverse.shop.entity.Category;
import com.weverse.shop.mapper.CategoryMapper;
import com.weverse.shop.repository.ArtistRepository;
import com.weverse.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryResponse findById(Long id){
        return categoryMapper.toCategoryResponse(
                categoryRepository.findById(id).orElseThrow(NoSuchElementException::new)
        );
    }

    public void registration(CategoryRegistrationRequest request){
        artistRepository.findById(request.artistId())
                        .ifPresent(artist -> artist.addCategory(
                                Category.registration(request.name(), artist)
                        ));
    }

    public void modifyActive(Long id, Boolean active){
        categoryRepository.findById(id)
                .ifPresent(category -> category.modifyActive(active));
    }
}
