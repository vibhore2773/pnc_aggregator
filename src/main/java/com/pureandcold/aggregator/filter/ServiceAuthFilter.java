package com.pureandcold.aggregator.filter;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.pureandcold.aggregator.constants.HttpConstants;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceAuthFilter implements Filter{
    
    @Value("${client_id}")
    private String clientId;

    @Value("${client_secret}")
    private String clientSecret;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String uri = httpServletRequest.getRequestURI();
        boolean skipAuth = StringUtils.hasLength(uri) && HttpConstants.UNAUTHENTICATED_API_PATHS.contains(uri);
        if (skipAuth) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            String countryCode = httpServletRequest.getHeader(HttpConstants.HeaderConstants.COUNTRY_CODE);
            MDC.put(HttpConstants.HeaderConstants.COUNTRY_CODE, countryCode);
            String requestClientId = httpServletRequest.getHeader(HttpConstants.HeaderConstants.CLIENT_ID);
            String requestClientSecret = httpServletRequest.getHeader(HttpConstants.HeaderConstants.CLIENT_SECRET);
            if (!HttpConstants.ADMIN_API_PATHS.contains(uri)) {
                if (clientId.equals(requestClientId) && clientSecret.equals(requestClientSecret)) {
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                } else {
                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }
}
