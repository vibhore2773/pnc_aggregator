package com.pureandcold.aggregator.constants;

import java.util.Set;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpConstants {
    
    public static class HeaderConstants {
        public static final String COUNTRY_CODE = "country_code";
        public static final String CLIENT_ID = "client_id";
        public static final String CLIENT_SECRET = "client_secret";
    }
    public static class BaseController {
        public static final String BASE_PATH = "1.0/";
        public static final String HEALTH_CHECK_PATH = "/health";
    }

    public static class DefaultController {
        public static final String BASE_PATH = "1.0/api/home";
        public static final String HEADER_API_PATH = "/header";
        public static final String FOOTER_API_PATH = "/footer";
        public static final String WIDGETS_API_PATH = "/widgets";
    }


    public static final Set <String> UNAUTHENTICATED_API_PATHS = Set.of(BaseController.BASE_PATH.concat(BaseController.HEALTH_CHECK_PATH));
}
