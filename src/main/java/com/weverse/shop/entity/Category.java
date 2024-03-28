package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "weverse_shop_category")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Category {

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<GoodsCategory> goodsCategories  = new ArrayList<>();

    public static Category registration(String name, Artist artist){
        return Category.builder()
                .name(name)
                .artist(artist)
                .active(true)
                .build();
    }

    public void modifyActive(boolean active){
        this.active = active;
    }

    public void addGoodsCategory(GoodsCategory goodsCategory){
        this.goodsCategories.add(goodsCategory);
    }
}
