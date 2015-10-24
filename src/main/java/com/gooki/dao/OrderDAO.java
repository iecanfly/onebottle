package com.gooki.dao;

import com.gooki.model.Order;
import com.gooki.dao.GenericDao;
import com.gooki.dao.hibernate.GenericDaoHibernate;
import com.gooki.model.User;

import java.util.List;

/**
 * Created by iecanfly on 4/4/14.
 */
public interface OrderDAO extends GenericDao<Order, Long> {
    List<Order> findNotTakenOrderByBuyer(String buyer);
    Order findNotCompletedOrderByBuyer(String buyer);
    List<Order> findNearbyNotTakenOrder(User seller);
    List<Order> findTakenOrCanceledOrDeliveredOrder(String sellerName);
    List<Order> findHistoryFor(User currentBuyer);
}
