package com.gooki.webapp.util.location;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by iecanfly on 3/10/14.
 */
public class LocationUtil {
    protected static final String ROOT_ELEMENT = "1";
    protected static final int PARENT_IDX = 1;
    protected static final int NAME_IDX = 0;
    protected static final String API_KEY = "7a47709620e13098e9ac9539b39ddf9a";
    protected static final String BAIDU_LOCATION_API_URI = "http://api.map.baidu.com/location/ip";
    protected static final String BAIDU_GEOCODE_API_URI = "http://api.map.baidu.com/geocoder/v2/";
    public static final String DEFAULT_LOCATION = "29.88525897,121.57900597";

    private static Map<String, List<String>> rawMap;

    public static Map<String, String> getAddressMap(String parentId) throws Exception {
        return getAddressPartMap(getRawMap(), parentId);
    }

    public static Map<String, String> getDefaultProvinceMap() throws Exception {
        return getAddressMap(ROOT_ELEMENT);
    }

    public static Map<String, String> getDefaultCityMap() throws Exception {
        return getAddressMap(getDefaultProvinceMap().entrySet().iterator().next().getKey());
    }

    public static Map<String, String> getDefaultAreaMap() throws Exception {
        return getAddressMap(getDefaultCityMap().entrySet().iterator().next().getKey());
    }


    public static String getName(String id) throws Exception {
        return StringUtils.isEmpty(id) ? "" : getRawMap().get(id).get(NAME_IDX);
    }

    protected static Map<String, String> getAddressPartMap(Map<String, List<String>> map, String parentId) {
        Map<String, String> addressMap = new TreeMap<>();

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            if (values.get(PARENT_IDX).equals(parentId)) {
                addressMap.put(key, values.get(NAME_IDX));
            }
        }

        return addressMap;
    }


    protected synchronized static Map<String, List<String>> getRawMap() throws Exception {
        if (rawMap == null) {
            ObjectMapper mapper = new ObjectMapper();
            rawMap = mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("cityList.json"), Map.class);
        }

        return rawMap;
    }


    public static String getCodeforGivenName(String name) throws Exception {
        for (Map.Entry<String, List<String>> e : getRawMap().entrySet()) {
            if (e.getValue().get(NAME_IDX).equals(name)) {
                return e.getKey();
            }
        }

        return null;
    }

    public static String getProvinceCode(GeocoderSearchResponse.Result.AddressComponent addressComponent) throws Exception {
        return getCodeforGivenName(addressComponent.getProvince());
    }

    public static String getCityCode(GeocoderSearchResponse.Result.AddressComponent addressComponent) throws Exception {
        return getCodeforGivenName(addressComponent.getCity());
    }

    public static String getAreaCode(GeocoderSearchResponse.Result.AddressComponent addressComponent) throws Exception {
        return getCodeforGivenName(addressComponent.getArea());
    }

    public static GeocoderSearchResponse.Result.AddressComponent getBaiduAddressComponent(String location) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return (mapper.readValue(getRawGeocoderSearchResponseForlocation(location), GeocoderSearchResponse.class)).getResult().getAddressComponent();
    }

    protected static String getRawGeocoderSearchResponseForlocation(String location) throws Exception {
        URL url = new URL(BAIDU_GEOCODE_API_URI + "?ak=" + API_KEY + "&output=json&location=" + location);
        return IOUtils.toString(url.openStream());
    }

    public static BaiduLocation getBaiduLocationForIP(String ip) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(getRawLocationInfoForIP(ip), BaiduLocation.class);
    }

    protected static String getRawLocationInfoForIP(String ip) throws Exception {
        URL url = new URL(BAIDU_LOCATION_API_URI + "?ak=" + API_KEY + "&ip=" + ip);
        return IOUtils.toString(url.openStream());
    }

    protected static BaiduLocation.Content.Address_Detail getAddressDetailForIP(String ip) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return ((BaiduLocation) mapper.readValue(getRawLocationInfoForIP(ip), BaiduLocation.class)).getContent().getAddressDetail();
    }

    public static String getProvinceCodeForIP(String ip) throws Exception {
        return getCodeforGivenName(getAddressDetailForIP(ip).getProvince());
    }

    public static String getCityCodeForIP(String ip) throws Exception {
        return getCodeforGivenName(getAddressDetailForIP(ip).getCity());
    }

    public static String getAreaCodeForIP(String ip) throws Exception {
        return getCodeforGivenName(getAddressDetailForIP(ip).getDistrict());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeocoderSearchResponse {

        @JsonProperty("result")
        private Result result;

        public GeocoderSearchResponse() {
        }

        public Result getResult() {
            return result;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Result {
            @JsonProperty("addressComponent")
            private AddressComponent addressComponent;

            public Result() {
            }

            public AddressComponent getAddressComponent() {
                return addressComponent;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class AddressComponent {
                @JsonProperty("province")
                private String province;
                @JsonProperty("city")
                private String city;
                @JsonProperty("district")
                private String area;

                public AddressComponent() {
                }

                public String getProvince() {
                    return province;
                }

                public String getCity() {
                    return city;
                }

                public String getArea() {
                    return area;
                }
            }
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BaiduLocation {
        @JsonProperty("address")
        public String address;
        @JsonProperty("status")
        int status;
        @JsonProperty("content")
        Content content;

        public BaiduLocation() {
        }

        public int getStatus() {
            return status;
        }

        public Content getContent() {
            return content;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        class Content {
            @JsonProperty("address_detail")
            Address_Detail addressDetail;
            @JsonProperty("point")
            Point point;

            public Content() {
            }

            public Address_Detail getAddressDetail() {
                return addressDetail;
            }

            public Point getPoint() {
                return point;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            class Address_Detail {
                @JsonProperty("city")
                String city;
                @JsonProperty("city_code")
                String city_code;
                @JsonProperty("district")
                String district;
                @JsonProperty("province")
                String province;
                @JsonProperty("street")
                String street;
                @JsonProperty("street_number")
                String street_number;

                public Address_Detail() {
                }

                public String getCity() {
                    return city;
                }

                public String getCity_code() {
                    return city_code;
                }

                public String getDistrict() {
                    return district;
                }

                public String getProvince() {
                    return province;
                }

                public String getStreet() {
                    return street;
                }

                public String getStreet_number() {
                    return street_number;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            class Point {
                @JsonProperty("x")
                String x;
                @JsonProperty("y")
                String y;

                public Point() {
                }

                public String getX() {
                    return x;
                }

                public String getY() {
                    return y;
                }
            }
        }

    }

}

