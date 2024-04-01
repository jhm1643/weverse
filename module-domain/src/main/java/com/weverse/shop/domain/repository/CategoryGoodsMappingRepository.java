package com.weverse.shop.domain.repository;

import com.weverse.shop.domain.entity.CategoryGoodsMapping;
import com.weverse.shop.domain.entity.embeded.CategoryGoodsMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryGoodsMappingRepository extends JpaRepository<CategoryGoodsMapping, CategoryGoodsMappingId> {
}
