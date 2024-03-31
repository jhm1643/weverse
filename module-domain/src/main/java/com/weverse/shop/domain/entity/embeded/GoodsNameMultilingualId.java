package com.weverse.shop.domain.entity.embeded;

import com.weverse.shop.common.type.LanguageType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@Getter
@Embeddable
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class GoodsNameMultilingualId implements Serializable {

    @Column
    private Long goodsId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private LanguageType languageType;

    public static GoodsNameMultilingualId createId(Long goodsId, LanguageType languageType){
        return builder()
                .goodsId(goodsId)
                .languageType(languageType)
                .build();
    }
}
