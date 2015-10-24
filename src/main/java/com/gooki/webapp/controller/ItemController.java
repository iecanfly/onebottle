package com.gooki.webapp.controller;

import com.gooki.model.Item;
import com.gooki.model.User;
import com.gooki.service.UserExistsException;
import com.gooki.service.UserManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * Controller for seller's items
 *
 * @author <a href="mailto:iecanfly@gmail.com">gooki</a>
 */
@Controller
public class ItemController extends BaseFormController {
    protected final Log log = LogFactory.getLog(getClass());


    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/sellerItems")
    public ModelAndView handle(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User currentSeller = getUserByUsername(request);
        mv.setViewName("sellerItems");
        mv.addObject("items", currentSeller.getItems());

        return mv;
    }

    @RequestMapping(value = "/ajax/sellerItemsList/{sellerId}")
    public ModelAndView handleSellerItemsList(@PathVariable Long sellerId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User currentSeller = getUserManager().get(sellerId);
        mv.setViewName("sellerItemsList");
        mv.addObject("items", currentSeller.getItems());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sellerItems")
    public ModelAndView handlePost(@Valid Item item, BindingResult binding, HttpServletRequest request) throws UserExistsException {
        User currentSeller = getUserByUsername(request);
        ModelAndView mv = new ModelAndView();

        if(binding.hasErrors()) {
            mv.setViewName("sellerItems");
            mv.addObject("items", currentSeller.getItems());
            return mv;
        }

        item.setSeller(currentSeller);
        item.setCreateDate(new Date());
        currentSeller.getItems().add(item);
        userManager.saveUser(currentSeller);
        mv.setViewName("redirect:/sellerItems");

        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteItems/{id}")
    public String handleDelete(HttpServletRequest request, @PathVariable Long id) throws UserExistsException {
        User currentSeller = getUserByUsername(request);

        Item toDelete = new Item();
        for(Item item : currentSeller.getItems()) {
            if(item.getId().equals(id)) {
                toDelete = item;

            }
        }
        currentSeller.getItems().remove(toDelete);

        userManager.saveUser(currentSeller);
        return "redirect:/sellerItems";
    }



}
