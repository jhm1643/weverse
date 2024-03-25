package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "상품 카테고리 명")
    private String name;

    @OneToMany(mappedBy = "goodsCategory", cascade = CascadeType.REMOVE)
    private List<Goods> goodsList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;

    public static GoodsCategory registration(String name, Category category){
        return GoodsCategory.builder()
                .name(name)
                .category(category)
                .build();
    }
}
