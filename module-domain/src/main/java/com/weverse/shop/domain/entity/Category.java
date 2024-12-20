package com.weverse.shop.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "WEVERSE_SHOP_CATEGORY")
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

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CategoryGoodsMapping> categoryGoodsMappings  = new ArrayList<>();

    public static Category create(String name, Artist artist){
        return builder()
                .name(name)
                .artist(artist)
                .build();
    }

    public void modifyName(String name){
        this.name = name;
    }

    public void addCategoryGoodsMapping(CategoryGoodsMapping categoryGoodsMapping){
        this.categoryGoodsMappings.add(categoryGoodsMapping);
    }
}
