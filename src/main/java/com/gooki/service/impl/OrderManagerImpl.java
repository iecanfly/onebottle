package com.gooki.service.impl;

import com.gooki.dao.OrderDAO;
import com.gooki.exception.ExistingOrderNotProcessedException;
import com.gooki.model.Order;
import com.gooki.model.User;
import com.gooki.service.OrderManager;
import com.gooki.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by iecanfly on 4/4/14.
 */
@Service("orderManager")
@WebService(serviceName = "OrderService", endpointInterface = "com.gooki.service.OrderService")
public class OrderManagerImpl extends GenericManagerImpl<Order, Long> implements OrderManager, OrderService {
    private OrderDAO orderDAO;

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.dao = orderDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public Order saveOrder(Order order) throws Exception {
        // User cannot place an order if previous order has not been processed by a seller
        List<Order> orderList = getNotTakenOrderByBuyer(order.getBuyer());
        if(orderList.size() != 0) {
            throw new ExistingOrderNotProcessedException();
        }

        return this.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderDAO.getAll();
    }

    @Override
    public Order getOrderByBuyer(String buyer) {
        return null;
    }

    @Override
    public Order getNotDeliveredOrderByBuyer(String buyer) {
        return orderDAO.findNotCompletedOrderByBuyer(buyer);
    }

    @Override
    public List<Order> getNotTakenOrderByBuyer(String buyer) {
        return orderDAO.findNotTakenOrderByBuyer(buyer);
    }

    @Override
    public List<Order> getTakenOrderByBuyer(String buyer) {
        return null;
    }

    @Override
    public List<Order> getNearbyNotTakenOrder(User seller) {
        return orderDAO.findNearbyNotTakenOrder(seller);
    }

    @Override
    public List<Order> getNearbyNotTakenOrder(String seller) {
        return null;
    }


    @Override
    public void deleteOrder(String orderId) {

    }

    @Override
    public boolean isSellerMatchesOrder(User currentSeller, Order order) {
        if(currentSeller != null && order != null) {
            String userProvince = currentSeller.getAddress().getProvince();
            String userCity = currentSeller.getAddress().getCity();
            String orderProvince = order.getAddress().getProvince();
            String orderCity = order.getAddress().getCity();

            return userProvince.equals(orderProvince) && userCity.equals(orderCity);
        } else {
            return false;
        }
    }

    @Override
    public List<Order> getTakenOrCanceledOrder(String sellerName) {
        return orderDAO.findTakenOrCanceledOrDeliveredOrder(sellerName);
    }

    @Override
    public List<Order> getHistoryFor(User currentBuyer) {
        return orderDAO.findHistoryFor(currentBuyer);
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order get(Long id) {
        return orderDAO.get(id);
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public Order save(Order order) {
        orderDAO.save(order);
        return order;
    }

    @Override
    public void remove(Order object) {

    }

    @Override
    public void remove(Long id) {
        orderDAO.remove(id);
    }

    public List<Order> search(String searchTerm) {
        return null;
    }

    @Override
    public void reindex() {

    }

    @Override
    public void reindexAll(boolean async) {

    }
}
