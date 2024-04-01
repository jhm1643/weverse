package com.weverse.shop.domain.entity;

import com.weverse.shop.domain.entity.embeded.CategoryGoodsMappingId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WEVERSE_SHOP_CATEGORY_GOODS_MAPPING")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryGoodsMapping {

    @Id
    private CategoryGoodsMappingId id;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @MapsId("goodsId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    public static CategoryGoodsMapping goodsRegistrationToCategory(Category category, Goods goods){
        return CategoryGoodsMapping.builder()
                .id(CategoryGoodsMappingId.createId(category.getId(), goods.getId()))
                .category(category)
                .goods(goods)
                .build();
    }
}
