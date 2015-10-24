package com.gooki.webapp.controller;

import com.gooki.model.Order;
import com.gooki.model.User;
import com.gooki.service.OrderManager;
import com.gooki.webapp.constants.OrderStatus;
import com.gooki.webapp.exception.InvalidTakeOrderException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller to signup new users.
 *
 * @author <a href="mailto:iecanfly@gmail.com">gooki</a>
 */
@Controller
@RequestMapping("/orders")
public class OrderController extends BaseFormController {
    protected final Log log = LogFactory.getLog(getClass());


    @Autowired
    private OrderManager orderManager;

    @RequestMapping(method = RequestMethod.POST, value = "/confirmDelivery/{orderId}")
    public @ResponseBody Map<String, Object> handleConfirmDelivery(HttpServletRequest request, @PathVariable Long orderId) {
        Map<String, Object> map = new HashMap<>();

        try {
            User currentBuyer = getUserByUsername(request);

            Order existingOrder = orderManager.get(orderId);
            if(currentBuyer.getUsername().equals(existingOrder.getBuyer())) {
                existingOrder.setStatus(OrderStatus.COMPLETED.getStatus());
                orderManager.save(existingOrder);
                map.put("message", "success");
                map.put("detail", getText("orders.confirmDelivery.success", request.getLocale()));
            } else {
                log.warn(currentBuyer + " was trying to confirm order delivery belonging to " + existingOrder.getBuyer());
                throw new AuthorizationServiceException("Cannot confirm delivery of another user's order");
            }
        } catch(Exception e) {
            map.put("message", "fail");
            map.put("detail", getText("orders.confirmDelivery.fail", request.getLocale()));
        }

        return map;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/mark/delivered")
    public @ResponseBody Map<String, Object> handleMarkAsDelivered(HttpServletRequest request, @RequestBody Order order) {
        Map<String, Object> map = new HashMap<>();

        try {
            User currentSeller = getUserByUsername(request);

            Order existingOrder = orderManager.get(order.getId());
            if(currentSeller.getUsername().equals(existingOrder.getSeller())) {
                existingOrder.setStatus(OrderStatus.DELIVERED.getStatus());
                orderManager.save(existingOrder);
                map.put("message", "success");
                map.put("detail", getText("orders.markas.delivered.success", request.getLocale()));
            } else {
                log.warn(currentSeller + " was trying to mark as delivered the order belonging to " + existingOrder.getSeller());
                throw new AuthorizationServiceException("Cannot mark as delivered another user's order");
            }
        } catch(Exception e) {
            map.put("message", "fail");
            map.put("detail", getText("orders.markas.delivered.fail", request.getLocale()));
        }

        return map;

    }



    @RequestMapping(method = RequestMethod.POST, value = "/take")
    public @ResponseBody
    Map<String, Object> handleTakeOrder(@RequestBody Order order, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();

        try {
            User currentSeller = getUserByUsername(request);

            Order existingOrder = orderManager.get(order.getId());
            if(orderManager.isSellerMatchesOrder(currentSeller, existingOrder)) {
                existingOrder.setStatus(OrderStatus.TAKEN.getStatus());
                existingOrder.setSeller(currentSeller.getUsername());
                orderManager.save(existingOrder);
                map.put("message", "success");
                map.put("detail", getText("orders.taken.success.header", request.getLocale()));
            } else {
                throw new InvalidTakeOrderException("The user cannot take this order.");
            }
        } catch(Exception e) {
            map.put("message", "fail");
            map.put("detail", getText("orders.taken.fail.header", request.getLocale()));
        }

        return map;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cancel/force/{orderId}")
    public @ResponseBody
    Map<String, Object> handleForceCancelOrder(HttpServletRequest request, @PathVariable Long orderId) throws Exception {
        Map<String, Object> map = new HashMap<>();

        try {
            User currentBuyer = getUserByUsername(request);

            Order existingOrder = orderManager.get(orderId);
            if(currentBuyer.getUsername().equals(existingOrder.getBuyer())) {
                existingOrder.setStatus(OrderStatus.FORCE_CANCELED.getStatus());
                orderManager.save(existingOrder);
                map.put("message", "success");
                map.put("detail", getText("orders.force.cancel.success", request.getLocale()));
            } else {
                log.warn(currentBuyer + " was trying to cancel order belonging to " + existingOrder.getBuyer());
                throw new AuthorizationServiceException("Cannot cancel another user's order");
            }
        } catch(Exception e) {
            map.put("message", "fail");
            map.put("detail", getText("orders.force.cancel.fail", request.getLocale()));
        }

        return map;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> handleSaveOrder(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();

        try {
            User currentUser = getUserByUsername(request);
            if(currentUser == null) {
                throw new UsernameNotFoundException("User not found");
            }

            Order newOrder = new Order();
            newOrder.setBuyer(currentUser.getUsername());
            newOrder.setAddress(currentUser.getAddress());
            newOrder.setStatus(OrderStatus.NOT_TAKEN.getStatus());
            newOrder.setSeller(currentUser.getBuyerFavOrder().getSeller().getUsername());
            newOrder.setNumBottle(currentUser.getBuyerFavOrder().getNumBottles());
            newOrder.setItem(currentUser.getBuyerFavOrder().getItem());
            newOrder.setOrderDateTime(new Date());
            orderManager.saveOrder(newOrder);

            map.put("message", "success");
            map.put("detail", getText("orders.success.header", request.getLocale()));
        } catch(UsernameNotFoundException ue) {
            map.put("message", "fail");
            map.put("detail", getText("errors.user.notfound", request.getLocale()));
        } catch(Exception e) {
            map.put("message", "fail");
            map.put("detail", getText("orders.fail.header", request.getLocale()));
        }

        return map;
    }



    @RequestMapping(method = RequestMethod.POST, value = "/cancel/{orderId}")
    public @ResponseBody Map<String, Object> handleCancelOrder(HttpServletRequest request, @PathVariable Long orderId) throws Exception {
        Map<String, Object> map = new HashMap<>();

        try {
            User currentUser = getUserByUsername(request);
            String currentUserName = currentUser.getUsername();
            String orderBuyerName = orderManager.get(orderId).getBuyer();

            if(!currentUserName.equals(orderBuyerName)) {
                log.warn(currentUserName + " was trying to cancel order belonging to " + orderBuyerName);
                throw new AuthorizationServiceException("Cannot cancel another user's order");
            }

            orderManager.remove(orderId);

            map.put("message", "success");
            map.put("detail", getText("orders.cancel.success.header", request.getLocale()));
        } catch(Exception e) {
            map.put("message", "fail");
            map.put("detail", getText("orders.cancel.fail.header", request.getLocale()));
        }

        return map;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public @ResponseBody Map<String, Object> handleDeleteOrder(HttpServletRequest request, @RequestBody Order order) throws Exception {
        Map<String, Object> map = new HashMap<>();

        try {
            User currentUser = getUserByUsername(request);
            String currentUserName = currentUser.getUsername();
            String orderSellerName = orderManager.get(order.getId()).getSeller();

            if(!currentUserName.equals(orderSellerName)) {
                log.warn(currentUserName + " was trying to delete order taken by " + orderSellerName);
                throw new AuthorizationServiceException("Cannot cancel another order");
            }

            orderManager.remove(order.getId());

            map.put("message", "success");
            map.put("detail", getText("orders.delete.success", request.getLocale()));
        } catch(Exception e) {
            map.put("message", "fail");
            map.put("detail", getText("orders.delete.fail", request.getLocale()));
        }

        return map;
    }


}
