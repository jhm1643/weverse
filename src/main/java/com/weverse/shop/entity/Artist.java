package com.weverse.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WEVERSE_SHOP_ARTIST")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Artist {

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

    @OneToMany(mappedBy = "artist", cascade = CascadeType.PERSIST)
    private List<Category> categories  = new ArrayList<>();

    public static Artist registration(String name){
        return Artist.builder()
                .name(name)
                .active(true)
                .build();
    }

    public void modifyActive(boolean active){
        this.active = active;
    }

    public void addCategory(Category category){
        this.categories.add(category);
    }
}
