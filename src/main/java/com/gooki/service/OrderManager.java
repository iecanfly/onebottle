package com.gooki.service;

import com.gooki.dao.OrderDAO;
import com.gooki.exception.ExistingOrderNotProcessedException;
import com.gooki.model.Order;
import com.gooki.model.User;
import com.gooki.service.GenericManager;

import java.util.List;

/**
 * Created by iecanfly on 4/4/14.
 */
public interface OrderManager extends GenericManager<Order, Long> {
    void setOrderDAO(OrderDAO orderDAO);
    Order saveOrder(Order order) throws Exception;
    List<Order> getOrders();
    Order getNotDeliveredOrderByBuyer(String buyer);
    List<Order> getNotTakenOrderByBuyer(String buyer);
    List<Order> getTakenOrderByBuyer(String buyer);
    List<Order> getNearbyNotTakenOrder(User seller);
    void deleteOrder(String orderId);
    boolean isSellerMatchesOrder(User currentSeller, Order order);
    List<Order> getTakenOrCanceledOrder(String sellerName);
    List<Order> getHistoryFor(User currentBuyer);
}
