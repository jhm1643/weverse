package com.weverse.shop.domain.entity.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Embeddable
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CategoryGoodsMappingId implements Serializable {

    @Column
    private Long categoryId;

    @Column
    private Long goodsId;

    public static CategoryGoodsMappingId createId(Long categoryId, Long goodsId){
        return builder()
                .categoryId(categoryId)
                .goodsId(goodsId)
                .build();
    }
}
