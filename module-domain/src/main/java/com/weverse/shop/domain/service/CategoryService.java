package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.CategoryRegistrationRequest;
import com.weverse.shop.common.dto.response.CategoryResponse;
import com.weverse.shop.domain.entity.Category;
import com.weverse.shop.domain.mapper.CategoryMapper;
import com.weverse.shop.domain.repository.ArtistRepository;
import com.weverse.shop.domain.repository.CategoryRepository;
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
                                Category.registration(request.categoryName(), artist)
                        ));
    }

    public void removeCategory(Long id){
        categoryRepository.findById(id)
                .ifPresent(categoryRepository::delete);
    }

    public void modifyName(Long id, String name){
        categoryRepository.findById(id)
                .ifPresent(category -> category.modifyName(name));
    }
}
