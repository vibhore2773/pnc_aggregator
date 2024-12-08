package com.pureandcold.aggregator.constants;

import java.util.HashSet;
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
        public static final String BASE_PATH = "/1.0/";
        public static final String HEALTH_CHECK_PATH = "/health";
    }

    public static class DefaultController {
        public static final String BASE_PATH = "/1.0/api/home";
        public static final String HEADER_API_PATH = "/header";
        public static final String FOOTER_API_PATH = "/footer";
        public static final String WIDGETS_API_PATH = "/widgets";
        public static final String GET_PRODUCTS_PATH = "/products/fetch";
    }

    public static class InventoryController {
        public static final String BASE_PATH = "/1.0/api";
        public static final String GET_PRODUCTS_PATH = "/products/fetch";
    }
    
    public static class InfoController {
        public static final String BASE_PATH = "/1.0/api";
        public static final String ABOUT_US_PATH = "/aboutUs";
        public static final String CONTACT_US_PATH = "/contactUs";
        
    }

    public static class UserOrderController {
        public static final String BASE_PATH = "/1.0/api";
        public static final String USER_REGISTRATION_PATH = "/user/register";
        public static final String VERIFY_OPT_PATH = "/user/verify-otp";
    }

    public static final Set <String> ADMIN_API_PATHS = Set.of(InventoryController.BASE_PATH.concat(InventoryController.GET_PRODUCTS_PATH));

    public static class InventoryService {
        public static final String INVENTORY_BASE_URL = "${inventory_base_url}";
        public static final String FETCH_PRODUCT_API_PATH = "/1.0/api/products/fetch";
    }

    public static class UserOrderService {
        public static final String USER_ORDER_BASE_URL = "${user_order_base_url}";
        public static final String USER_REGISTRATION_API_PATH = "1.0/api/user/register";
        public static final String VERIFY_OTP_API_PATH = "1.0/api/user/verifyOtp";
    }
}
