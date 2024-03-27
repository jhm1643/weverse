package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WEVERSE_SHOP_ARTIST")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Category> categories  = new ArrayList<>();

    public static Artist registration(String name){
        return Artist.builder()
                .name(name)
                .build();
    }

    public void addCategory(Category category){
        this.categories.add(category);
    }
}
