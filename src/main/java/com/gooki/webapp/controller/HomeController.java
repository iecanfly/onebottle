package com.gooki.webapp.controller;

import com.gooki.model.User;
import com.gooki.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Home Controller
 *
 * @author <a href="mailto:iecanfly@gmail.com">David Lee</a>
 */
@Controller
public class HomeController extends BaseFormController {

    @RequestMapping(value = "/index")
    public String handleIndex(HttpServletRequest request) throws Exception {
        if (request.getRemoteUser() == null)
            return "index";
        return "redirect:/home";
    }

    @RequestMapping(value = "/home")
    public ModelAndView handle(HttpServletRequest request) throws Exception {
        User currentUser = getUserManager().getUserByUsername(request.getRemoteUser());
        if (currentUser != null) {
            if (currentUser.getIsBuyer()) {
                return new ModelAndView("redirect:/buyerHome");
            } else {
                return new ModelAndView("redirect:/sellerHome");
            }
        }

        return new ModelAndView("redirect:/index");
    }

}
