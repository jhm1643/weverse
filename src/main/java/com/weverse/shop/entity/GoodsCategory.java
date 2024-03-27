package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "werverse_shop_goods_category")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "goodsCategory", cascade = ALL)
    private List<Goods> goodsList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static GoodsCategory registration(String name, Category category){
        return GoodsCategory.builder()
                .name(name)
                .category(category)
                .build();
    }

    public void addGoods(Goods goods){
        this.goodsList.add(goods);
    }
}
