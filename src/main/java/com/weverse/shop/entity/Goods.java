package com.weverse.shop.entity;

import com.weverse.shop.dto.GoodsRegistrationDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "상품 명")
    private String name;

    @Column(columnDefinition = "재고 수량")
    private int stockCount;

    @Column(columnDefinition = "상품 가격")
    private int price;

    @Column(columnDefinition = "구매 가능 수량")
    private int purchasePossibleCount;

    @Column(columnDefinition = "판매 공지")
    private String salesNotice;

    @Column(columnDefinition = "상품 설명")
    private String description;

    @Column(columnDefinition = "상품 고시 정보")
    private String productNoticeInfo;

    @ManyToOne
    private GoodsCategory goodsCategory;

    public static Goods registration(GoodsRegistrationDto dto, GoodsCategory goodsCategory){
        return Goods.builder()
                .name(dto.getName())
                .stockCount(dto.getStockCount())
                .price(dto.getPrice())
                .purchasePossibleCount(dto.getPurchasePossibleCount())
                .salesNotice(dto.getSalesNotice())
                .description(dto.getDescription())
                .productNoticeInfo(dto.getProductNoticeInfo())
                .goodsCategory(goodsCategory)
                .build();
    }
}
