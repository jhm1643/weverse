package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "카테고리 명")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<GoodsCategory> goodsCategories;

    public static Category registration(String name){
        return Category.builder()
                .name(name)
                .build();
    }

    public void addGoodsCategory(GoodsCategory goodsCategory){
        this.goodsCategories.add(goodsCategory);
    }
}
