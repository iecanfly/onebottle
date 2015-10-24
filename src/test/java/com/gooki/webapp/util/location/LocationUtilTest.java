package com.gooki.webapp.util.location;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created by iecanfly on 4/15/14.
 */
public class LocationUtilTest {
    protected final Log log = LogFactory.getLog(getClass());

    @Test
    public void testGetCompleteLocationMap() throws Exception {
        //Map test = LocationUtil.getCompleteLocationMap();
    }

    @Test
    public void testGetProvinceNameMap() throws Exception{
        Map m = LocationUtil.getAddressPartMap(LocationUtil.getRawMap(), LocationUtil.ROOT_ELEMENT);
        log.info(m.toString());
    }

    @Test
    public void testGetCityNameMap() throws Exception {
        Map<String, String> m2 = LocationUtil.getAddressPartMap(LocationUtil.getRawMap(), "330000");
        log.info(m2.toString());
    }

    @Test
    public void testGetAreaNameMap() throws Exception {
        Map<String, String> m3 = LocationUtil.getAddressPartMap(LocationUtil.getRawMap(), "330200");
        log.info(m3.toString());
    }

    @Test
    public void getName() throws Exception {
        assertEquals(LocationUtil.getName("330000"), "浙江省");
        assertEquals(LocationUtil.getName("440704"), "江海区");
    }

    @Test
    public void getRawMap() throws Exception {
        log.info(LocationUtil.getRawMap());
    }

    @Test
    public void getCodeForGiveName() throws Exception  {
        assertEquals(LocationUtil.getCodeforGivenName("浙江省"), "330000");
        assertEquals(LocationUtil.getCodeforGivenName("江海区"), "440704");
    }

    @Test
    public void getRawLocationInfoForIP() throws Exception {
        log.info(LocationUtil.getRawLocationInfoForIP("202.198.16.3"));
    }

    @Test
    public void getLocationInfoForIP() throws Exception {
        log.info(LocationUtil.getAddressDetailForIP("202.198.16.3"));
    }

    @Test
    public void getRawGeocoderSearchResponseForlocation() throws Exception {
        log.info(LocationUtil.getRawGeocoderSearchResponseForlocation("29.88525897,121.57900597"));
    }

    @Test
    public void getBaiduAddressComponent() throws Exception {
        assertEquals(LocationUtil.getBaiduAddressComponent("29.88525897,121.57900597").getProvince(), "浙江省");
        assertEquals(LocationUtil.getBaiduAddressComponent("29.88525897,121.57900597").getCity(), "宁波市");
        assertEquals(LocationUtil.getBaiduAddressComponent("29.88525897,121.57900597").getArea(), "江东区");
    }

    @Test
    public void getLocationCodeForIP() throws Exception {
        assertEquals(LocationUtil.getProvinceCodeForIP("202.198.16.3"), "220000");
        assertEquals(LocationUtil.getCityCodeForIP("202.198.16.3"), "220100");
        log.info(LocationUtil.getAreaCodeForIP("202.198.16.3"));
    }
}
