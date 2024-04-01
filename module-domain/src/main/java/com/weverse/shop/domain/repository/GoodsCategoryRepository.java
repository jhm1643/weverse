package com.weverse.shop.domain.repository;

import com.weverse.shop.domain.entity.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long> {

    Optional<GoodsCategory> findByName(String name);
}
