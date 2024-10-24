package com.pureandcold.aggregator.services.handlers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pureandcold.aggregator.model.internal.responses.FooterResponse;
import com.pureandcold.aggregator.model.internal.responses.HeaderResponse;
import com.pureandcold.aggregator.model.internal.responses.HelpCenter;
import com.pureandcold.aggregator.model.internal.responses.NavigationItem;
import com.pureandcold.aggregator.model.internal.responses.SocialMedia;
import com.pureandcold.constants.FeatureConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HomePageDefaultHandler {

    @Value(FeatureConstants.PNC_LOGO_URL)
    private String pncLogoUrl;

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
}
