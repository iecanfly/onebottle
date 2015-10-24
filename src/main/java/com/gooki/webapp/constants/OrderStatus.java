package com.gooki.webapp.constants;

import com.gooki.webapp.exception.StatusNotFoundException;

/**
 * Created by iecanfly on 4/4/14.
 */
public enum OrderStatus {
    NOT_TAKEN(1, "orders.status.not.taken", "primary"),
    TAKEN(2, "orders.status.taken", "primary"),
    DELIVERED(3, "orders.status.delivered", "info"),
    COMPLETED(4, "orders.status.completed", "success"),
    CANCELED(5, "orders.status.canceled", "warning"),
    FORCE_CANCELED(6, "orders.status.force.canceled", "danger");

    private int status;
    private String key;
    private String type; // used for determining which class to use(ie, default, warn, danger, etc)

    OrderStatus(int status, String key, String type) {
        this.status = status;
        this.key = key;
        this.type = type;
    }

    public int getStatus() {
        return this.status;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public static OrderStatus find(Integer status) throws StatusNotFoundException {
        for(OrderStatus orderStatus : OrderStatus.values()) {
            if(orderStatus.getStatus() == status) {
                return orderStatus;
            }
        }

        throw new StatusNotFoundException(status + " is not a valid status");
    }
}
