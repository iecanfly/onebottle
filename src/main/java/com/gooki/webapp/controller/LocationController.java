package com.gooki.webapp.controller;

import com.gooki.webapp.util.location.LocationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by iecanfly on 3/10/14.
 */
@Controller
@RequestMapping("/location")
public class LocationController {

    @RequestMapping(value = "/{parentId}", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> handleLocationRequest(@PathVariable String parentId) throws Exception {
        return LocationUtil.getAddressMap(parentId);
    }

}
