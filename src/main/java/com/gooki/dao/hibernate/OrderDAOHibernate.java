package com.gooki.dao.hibernate;

import com.gooki.dao.OrderDAO;
import com.gooki.dao.SearchException;
import com.gooki.model.Order;
import com.gooki.model.User;
import com.gooki.webapp.constants.OrderStatus;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by iecanfly on 4/4/14.
 */
@Repository("orderDAO")
public class OrderDAOHibernate extends GenericDaoHibernate<Order, Long> implements OrderDAO {
    public OrderDAOHibernate() {
        super(Order.class);
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getAllDistinct() {
        return null;
    }

    @Override
    public List<Order> search(String searchTerm) throws SearchException {
        return null;
    }

    @Override
    public Order get(Long id) {
        return super.get(id);
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public Order save(Order order) {
        getSession().saveOrUpdate(order);
        getSession().flush();
        return order;
    }

    @Override
    public void remove(Order object) {

    }

    @Override
    public void remove(Long id) {
        super.remove(id);
    }

    @Override
    public List<Order> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        return null;
    }

    @Override
    public void reindex() {

    }

    @Override
    public void reindexAll(boolean async) {

    }

    @Override
    public List<Order> findNotTakenOrderByBuyer(String buyer) {
        return getSession().createCriteria(Order.class).add(Restrictions.eq("buyer", buyer)).add(Restrictions.eq("status", OrderStatus.NOT_TAKEN.getStatus())).list();
    }

    @Override
    public Order findNotCompletedOrderByBuyer(String buyer) {
        return (Order) getSession().createCriteria(Order.class).add(Restrictions.eq("buyer", buyer)).
                add(Restrictions.and(
                        Restrictions.ne("status", OrderStatus.CANCELED.getStatus()),
                        Restrictions.ne("status", OrderStatus.FORCE_CANCELED.getStatus()),
                        Restrictions.ne("status", OrderStatus.COMPLETED.getStatus()))).uniqueResult();
    }

    @Override
    public List<Order> findNearbyNotTakenOrder(User seller) {
        return getSession().createCriteria(Order.class).add(Restrictions.eq("seller", seller.getUsername())).add(Restrictions.eq("status", OrderStatus.NOT_TAKEN.getStatus()))
                .add(Restrictions.eq("address.province", seller.getAddress().getProvince()))
                .add(Restrictions.eq("address.city", seller.getAddress().getCity())).list();
    }

    @Override
    public List<Order> findTakenOrCanceledOrDeliveredOrder(String sellerName) {
        return getSession().createCriteria(Order.class).add(
                Restrictions.or(
                        Restrictions.eq("status", OrderStatus.TAKEN.getStatus()),
                        Restrictions.eq("status", OrderStatus.CANCELED.getStatus()),
                        Restrictions.eq("status", OrderStatus.DELIVERED.getStatus()),
                        Restrictions.eq("status", OrderStatus.FORCE_CANCELED.getStatus())))
                .add(Restrictions.eq("seller", sellerName)).list();
    }

    @Override
    public List<Order> findHistoryFor(User currentBuyer) {
        return getSession().createCriteria(Order.class).add(Restrictions.eq("buyer", currentBuyer.getUsername())).addOrder(org.hibernate.criterion.Order.desc("orderDateTime")).list();
    }
}
