package com.gooki.webapp.controller;

import com.gooki.annotation.BuyerOnly;
import com.gooki.model.FavOrder;
import com.gooki.model.Item;
import com.gooki.model.Order;
import com.gooki.model.User;
import com.gooki.service.GenericManager;
import com.gooki.service.ItemManager;
import com.gooki.service.OrderManager;
import com.gooki.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Home Controller
 *
 * @author <a href="mailto:iecanfly@gmail.com">David Lee</a>
 */
@Controller
public class BuyerController extends BaseFormController {

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private ItemManager itemManager;

    @RequestMapping(value = "/buyerHome")
    @BuyerOnly
    public ModelAndView handleBuyerHome(HttpServletRequest request) throws Exception {
        ModelAndView mv;
        User currentUser = getUserManager().getUserByUsername(request.getRemoteUser());

        // If there is no favourite order item,
        // should direct the user to make the favourite order item first
        FavOrder favOrder = currentUser.getBuyerFavOrder();
        if (favOrder == null) {
            mv = new ModelAndView("redirect:/buyerFavOrder");
            return mv;
        }

        mv = new ModelAndView("buyerHome");
        Order order = orderManager.getNotDeliveredOrderByBuyer(currentUser.getUsername());
        mv.addObject("order", order);
        mv.addObject("favOrder", favOrder);
        return mv;
    }

    @RequestMapping(value = "/buyerFavOrder")
    @BuyerOnly
    public ModelAndView handleBuyerFavOrder(HttpServletRequest request) throws Exception {
        ModelAndView mv;
        User currentUser = getUserManager().getUserByUsername(request.getRemoteUser());

        mv = new ModelAndView("buyerFavOrder");
        mv.addObject("favOrder", currentUser.getBuyerFavOrder());
        mv.addObject("sellers", getUserManager().getSellersByAddress(currentUser.getAddress(), 1));
        return mv;
    }

    @RequestMapping(value = "/buyerFavOrder", method = RequestMethod.POST)
    @BuyerOnly
    public @ResponseBody Map<String, String> handleBuyerFavOrder(@RequestBody FavOrder favOrder, HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();

        User currentUser = getUserManager().getUserByUsername(request.getRemoteUser());
        if (currentUser.getBuyerFavOrder() != null) {
            map.put("message", "fail");
            map.put("detail", getText("buyer.fav.alreadyexists", request.getLocale()));
        } else {
            favOrder.setBuyer(currentUser);
            favOrder.setSeller(getUserManager().get(favOrder.getSellerId()));
            favOrder.setItem(itemManager.get(favOrder.getItemId()));
            currentUser.setBuyerFavOrder(favOrder);
            getUserManager().saveUser(currentUser);
            map.put("message", "success");
            map.put("detail", getText("buyer.fav.success", request.getLocale()));
        }

        return map;
    }

    @RequestMapping(value = "/buyerFavOrder/delete", method = RequestMethod.POST)
    @BuyerOnly
    public @ResponseBody Map<String, String> handleBuyerFavOrderDelete(@RequestParam("id") Long favId, HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();

        User currentUser = getUserManager().getUserByUsername(request.getRemoteUser());
        if (!currentUser.getBuyerFavOrder().getId().equals(favId)) {
            map.put("message", "fail");
            map.put("detail", getText("errors.no.previlege", request.getLocale()));
        } else {
            currentUser.removeBuyerFavOrder();
            getUserManager().saveUser(currentUser);
            map.put("message", "success");
            map.put("detail", getText("buyer.fav.delete.success", request.getLocale()));
        }

        return map;
    }

    @RequestMapping("/buyerOrderHistory")
    public ModelAndView handleOrderHistory(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        User currentBuyer = getUserByUsername(request);
        List<Order> history = orderManager.getHistoryFor(currentBuyer);

        mv.setViewName("buyerHistory");
        mv.addObject("history", history);

        return mv;
    }

}
