package com.gooki.model;

import java.util.List;

/**
 * Created by iecanfly on 2/1/14.
 */
public class Province {
    private String name;
    private List<String> cityList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }
}
