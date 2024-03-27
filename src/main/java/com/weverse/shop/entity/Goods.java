package com.weverse.shop.entity;

import com.weverse.shop.dto.GoodsRegistrationRecord;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "weverse_shop_goods")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int stockCount;

    @Column
    private int price;

    @Column
    private String Artist;

    @Column
    private int purchasePossibleCount;

    @Column
    private String salesNotice;

    @Column
    private String descriptionImageUrl;

    @Column
    private String productNoticeInfo;

    @Column
    private Boolean isReservationSale;

    @Column
    private LocalDateTime deliveryStartDueFromDtm;

    @Column
    private LocalDateTime deliveryStartDueToDtm;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "goods_category_id")
    private GoodsCategory goodsCategory;

    public static Goods registration(GoodsRegistrationRecord record, GoodsCategory goodsCategory){
        return Goods.builder()
                .name(record.name())
                .stockCount(record.stockCount())
                .price(record.price())
                .purchasePossibleCount(record.purchasePossibleCount())
                .salesNotice(record.salesNotice())
                .descriptionImageUrl(record.descriptionImageUrl())
                .productNoticeInfo(record.productNoticeInfo())
                .isReservationSale(record.isReservationSale())
                .deliveryStartDueFromDtm(record.deliveryStartDueFromDtm())
                .deliveryStartDueToDtm(record.deliveryStartDueToDtm())
                .goodsCategory(goodsCategory)
                .build();
    }
}
