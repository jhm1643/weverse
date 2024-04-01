package com.weverse.shop.domain.entity;

import com.weverse.shop.common.type.LanguageType;
import com.weverse.shop.domain.entity.embeded.GoodsNameMultilingualId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WEVERSE_SHOP_GOODS_NAME_MULTILINGUAL")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodsNameMultilingual {

    @EmbeddedId
    private GoodsNameMultilingualId id;

    @Column
    private String name;

    @MapsId("goodsId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    public static GoodsNameMultilingual create(Goods goods, LanguageType languageType, String name){
        return builder()
                .id(GoodsNameMultilingualId.createId(goods.getId(), languageType))
                .name(name)
                .goods(goods)
                .build();
    }
}
