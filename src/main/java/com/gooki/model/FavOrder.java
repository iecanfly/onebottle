package com.gooki.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by iecanfly on 5/13/14.
 */
@Entity
@Table(name = "fav_orders")
public class FavOrder {
    private Long id;
    private User seller;
    private User buyer;
    private Item item;
    private Integer numBottles;
    private Long sellerId;
    private Long itemId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @NotNull
    public User getSeller() {
        return seller;
    }

    @OneToOne
    @NotNull
    public User getBuyer() {
        return buyer;
    }

    @ManyToOne
    @NotNull
    public Item getItem() {
        return item;
    }

    @Column(nullable = false)
    @NotNull
    public Integer getNumBottles() {
        return numBottles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setNumBottles(Integer numBottles) {
        this.numBottles = numBottles;
    }

    @Transient
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Transient
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
