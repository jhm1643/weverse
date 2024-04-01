package com.weverse.shop.domain.entity;

import com.weverse.shop.domain.dto.GoodsCreateRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "WEVERSE_SHOP_GOODS")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    @Builder.Default
    private List<GoodsNameMultilingual> goodsNames = new ArrayList<>();

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
    private Boolean isReservationSale;

    @Column
    private Boolean isExclusiveSale;

    @Column
    private LocalDateTime deliveryStartDueFromDtm;

    @Column
    private LocalDateTime deliveryStartDueToDtm;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryGoodsMapping> categoryGoodsMappings;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "goods_category_id")
    private GoodsCategory goodsCategory;

    public static Goods create(GoodsCreateRecord record, GoodsCategory goodsCategory){
        return builder()
                .stockCount(record.stockCount())
                .price(record.price())
                .purchasePossibleCount(record.purchasePossibleCount())
                .salesNotice(record.salesNotice())
                .descriptionImageUrl(record.descriptionImageUrl())
                .productNoticeInfo(record.productNoticeInfo())
                .isReservationSale(record.isReservationSale())
                .isExclusiveSale(record.isExclusiveSale())
                .deliveryStartDueFromDtm(record.deliveryStartDueFromDtm())
                .deliveryStartDueToDtm(record.deliveryStartDueToDtm())
                .goodsCategory(goodsCategory)
                .build();
    }

    public void mappingGoodsCategory(GoodsCategory goodsCategory){
        this.goodsCategory = goodsCategory;
    }

    public void releaseMappingGoodsCategory(){
        this.goodsCategory = null;
    }

    public void addGoodsNameMultilingual(GoodsNameMultilingual goodsName){
        this.goodsNames.add(goodsName);
    }

    public void decreaseStockCount(){
        if(this.stockCount > 0) {
            this.stockCount--;
        }
    }

    public void addCategoryGoodsMapping(CategoryGoodsMapping categoryGoodsMapping){
        this.categoryGoodsMappings.add(categoryGoodsMapping);
    }
}
