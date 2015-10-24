package com.gooki.webapp.controller;

import com.gooki.annotation.SellerOnly;
import com.gooki.model.User;
import com.gooki.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Home Controller
 *
 * @author <a href="mailto:iecanfly@gmail.com">David Lee</a>
 */
@Controller
public class SellerController extends BaseFormController {

    @Autowired
    private OrderManager orderManager;

    @RequestMapping(value = "/sellerHome")
    @SellerOnly
    public ModelAndView handleSellerHome(HttpServletRequest request) throws Exception {
        User currentUser = getUserManager().getUserByUsername(request.getRemoteUser());
        ModelAndView mv;
        mv = new ModelAndView("sellerHome");
        mv.addObject("orderList", orderManager.getNearbyNotTakenOrder(currentUser));
        mv.addObject("takenOrCancelledOrderList", orderManager.getTakenOrCanceledOrder(currentUser.getUsername()));
        return mv;
    }

    @RequestMapping(value = "/ajax/sellerList/{page}")
    public ModelAndView handleSellerHome(@PathVariable int page, HttpServletRequest request) throws Exception {
        User currentUser = getUserManager().getUserByUsername(request.getRemoteUser());
        ModelAndView mv;
        mv = new ModelAndView("sellerList");
        mv.addObject("sellers", getUserManager().getSellersByAddress(currentUser.getAddress(), page));
        return mv;
    }

}
