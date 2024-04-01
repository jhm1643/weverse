package com.weverse.shop.domain.service;

import com.weverse.shop.common.dto.request.CategoryCreateRequest;
import com.weverse.shop.common.dto.response.CategoryResponse;
import com.weverse.shop.domain.entity.Category;
import com.weverse.shop.domain.mapper.CategoryMapper;
import com.weverse.shop.domain.repository.ArtistRepository;
import com.weverse.shop.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryCacheService categoryCacheService;
    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> findAllCategory(){
        var categories = categoryRepository.findAll();

        var countByCategoryResponses = categoryCacheService.countByCategoryResponse(
                categories.stream()
                        .map(category -> category.getName())
                        .collect(Collectors.toList())
        );

       return categories.stream()
                .map(category ->
                        categoryMapper.toCategoryResponse(category, countByCategoryResponses.stream()
                                .filter(countByCategoryResponse -> countByCategoryResponse.name().equals(category.getName()))
                                .findAny()
                                .orElse(null)
                        )
                ).collect(Collectors.toList());
    }

    public void createCategory(CategoryCreateRequest request){
        artistRepository.findById(request.artistId())
                        .ifPresent(artist -> artist.addCategory(
                                Category.create(request.categoryName(), artist)
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
