package com.weverse.shop.entity;

import com.weverse.shop.dto.type.LanguageType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodsNameMultilingual {

    @Id
    private Long goodsId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private LanguageType languageType;

    @Column
    private String name;
}
