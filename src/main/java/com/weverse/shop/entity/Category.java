package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "weverse_shop_category")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<GoodsCategory> goodsCategories  = new ArrayList<>();

    public static Category registration(String name, Artist artist){
        return Category.builder()
                .name(name)
                .artist(artist)
                .build();
    }

    public void addGoodsCategory(GoodsCategory goodsCategory){
        this.goodsCategories.add(goodsCategory);
    }
}
