package com.gooki.service;

import com.gooki.model.Order;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by iecanfly on 4/4/14.
 */
@WebService
@Path("/orders")
public interface OrderService {
    @POST
    Order saveOrder(Order order) throws Exception;

    @GET
    List<Order> getOrders();

    @GET
    Order getOrderByBuyer(String buyer);

    @GET
    List<Order> getNotTakenOrderByBuyer(String buyer);

    @GET
    List<Order> getTakenOrderByBuyer(String buyer);

    @GET
    List<Order> getNearbyNotTakenOrder(String seller);

    @DELETE
    void deleteOrder(String orderId);

}
