package com.pureandcold.aggregator.services.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pureandcold.aggregator.constants.FeatureConstants;
import com.pureandcold.aggregator.constants.FetchProductRequestTypeEnum;
import com.pureandcold.aggregator.model.external.responses.FetchProductResponse;
import com.pureandcold.aggregator.model.internal.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.internal.responses.Info;
import com.pureandcold.aggregator.model.internal.responses.InfoResponse;
import com.pureandcold.aggregator.model.internal.responses.Banner;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView;
import com.pureandcold.aggregator.model.internal.responses.FooterResponse;
import com.pureandcold.aggregator.model.internal.responses.HeaderResponse;
import com.pureandcold.aggregator.model.internal.responses.HelpCenter;
import com.pureandcold.aggregator.model.internal.responses.NavigationItem;
import com.pureandcold.aggregator.model.internal.responses.SocialMedia;
import com.pureandcold.aggregator.model.internal.responses.WidgetsResponse;
import com.pureandcold.aggregator.services.adapters.InventoryAdapter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HomePageAndInfoDefaultHandler {

    @Value(FeatureConstants.PNC_LOGO_URL)
    private String pncLogoUrl;

    private InventoryHandler inventoryHandler;

    public HomePageAndInfoDefaultHandler(InventoryHandler inventoryHandler) {
        this.inventoryHandler = inventoryHandler;
    }

    public HeaderResponse getHeaderResponse() {
        return HeaderResponse.builder()
                .logoUrl(pncLogoUrl)
                .navigationItems(Arrays.asList(
                        NavigationItem.builder().name("Home").url("/").build(),
                        NavigationItem.builder().name("About Us").url("/about").build(),
                        NavigationItem.builder().name("Contact Us").url("/contact").build(),
                        NavigationItem.builder().name("Profile").url("/profile").build(),
                        NavigationItem.builder().name("Shop").url("/shop").build(),
                        NavigationItem.builder().name("Cart").url("/cart").build()
                ))
                .build();
    }

    public FooterResponse getFooterResponse() {
        return FooterResponse.builder()
                .helpCenter(HelpCenter.builder().label("Help Center").url("/help").build())
                .socialMediaInfo(Arrays.asList(
                    SocialMedia.builder().name("Facebook").url("fb_url").build(),
                    SocialMedia.builder().name("Instagram").url("insta_url").build(),
                    SocialMedia.builder().name("Twitter").url("x_url").build()
                )).build();
    }

    public WidgetsResponse getWidgetsResponse() {
        return WidgetsResponse.builder()
                .banners(List.of(
                        Banner.builder().altText("banner_olive_oil").imageUrl("https://drive.google.com/file/d/16ykudXaTvFtIsVft6he08CxKy05BCqlk/view?usp=sharing").build(),
                        Banner.builder().altText("banner_all_oils").imageUrl("https://drive.google.com/file/d/1j-LA-owktdHkefL5HzpD9n00zKz3yGP-/view?usp=sharing").build()
                )).build();
    }

    public FetchProductResponseView getProductsByType(FetchProductRequest request) {
        if (!FetchProductRequestTypeEnum.BEST_SELLER.getType().equalsIgnoreCase(request.getType())) {
            return FetchProductResponseView.builder().build();
        }
        return inventoryHandler.getProductsByType(request);
    }

    public InfoResponse getAboutUsInfo() {
        return InfoResponse.builder()
                              .aboutUs(Info.builder()
                                              .titleText("ABOUT US")
                                              .imageUrl(pncLogoUrl)
                                              .bodyTexts(Arrays.asList("Welcome to PnC, where innovation meets excellence in the oil industry.",
                                                      "We are committed to delivering the highest quality oil, leveraging cutting-edge technology to ensure purity, performance, and sustainability.",
                                                      "Our mission is to set a new standard in the oil industry by prioritizing quality, transparency, and environmental responsibility.",
                                                      "Join us as we revolutionize the way the world experiences and benefits from top-tier oil products."))
                                              .build())
                              .build();
    }

    public InfoResponse getContactUsInfo() {
        return InfoResponse.builder()
                              .aboutUs(Info.builder()
                                              .titleText("CONTACT US")
                                              .imageUrl(pncLogoUrl)
                                              .bodyTexts(new ArrayList<>())
                                              .build())
                              .build();
    }
}
