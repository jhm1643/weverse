package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "werverse_shop_goods_category")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "goodsCategory", cascade = PERSIST)
    private List<Goods> goodsList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static GoodsCategory registration(String name, Category category){
        return GoodsCategory.builder()
                .name(name)
                .category(category)
                .active(true)
                .build();
    }

    public void modifyActive(boolean active){
        this.active = active;
    }

    public void addGoods(Goods goods){
        this.goodsList.add(goods);
    }
}
