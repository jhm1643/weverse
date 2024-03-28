package com.weverse.shop.entity;

import com.weverse.shop.dto.GoodsRegistrationRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "weverse_shop_goods")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private Integer stockCount;

    @Column
    private Integer price;

    @Column
    private Integer purchasePossibleCount;

    @Column
    private String salesNotice;

    @Column
    private String descriptionImageUrl;

    @Column
    private String productNoticeInfo;

    @Column
    private Boolean reservationSale;

    @Column
    private Boolean exclusiveSale;

    @Column
    private LocalDateTime deliveryStartDueFromDtm;

    @Column
    private LocalDateTime deliveryStartDueToDtm;

    @Column
    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

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
                .reservationSale(record.isReservationSale())
                .exclusiveSale(record.isExclusiveSale())
                .deliveryStartDueFromDtm(record.deliveryStartDueFromDtm())
                .deliveryStartDueToDtm(record.deliveryStartDueToDtm())
                .goodsCategory(goodsCategory)
                .active(true)
                .build();
    }

    public void modifyActive(boolean active){
        this.active = active;
    }
}
