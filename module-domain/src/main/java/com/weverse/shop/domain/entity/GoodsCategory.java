package com.weverse.shop.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity(name = "WEVERSE_SHOP_GOODS_CATEGORY")
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

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "goodsCategory", cascade = PERSIST)
    @Builder.Default
    private List<Goods> goodsList = new ArrayList<>();

    public static GoodsCategory registration(String name){
        return builder()
                .name(name)
                .build();
    }

    public void modifyName(String name){
        this.name = name;
    }

    public void addGoods(Goods goods){
        this.goodsList.add(goods);
    }
}
