package com.gooki.model;

import com.gooki.webapp.constants.OrderStatus;
import com.gooki.webapp.exception.StatusNotFoundException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by iecanfly on 12/27/13.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private long id;
    private String buyer;
    private Integer numBottle;
    private Address address;
    private String seller;
    private Integer status;
    private Date orderDateTime;
    private Item item;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Integer getNumBottle() {
        return numBottle;
    }

    public void setNumBottle(Integer numBottle) {
        this.numBottle = numBottle;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(nullable = true, length = 50)
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Column(nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(nullable = false, length = 50)
    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    @Column(name = "order_date_time", nullable = false)
    public Date getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Date orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    @Transient
    public OrderStatus getOrderStatus() throws StatusNotFoundException {
        return OrderStatus.find(status);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
