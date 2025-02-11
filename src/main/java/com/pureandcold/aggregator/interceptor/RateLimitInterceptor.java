package com.pureandcold.aggregator.interceptor;

import com.pureandcold.aggregator.annotation.RateLimit;
import com.pureandcold.aggregator.config.RateLimitConfig;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimitConfig rateLimitConfig;

    public RateLimitInterceptor(RateLimitConfig rateLimitConfig) {
        this.rateLimitConfig = rateLimitConfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RateLimit rateLimit = handlerMethod.getMethodAnnotation(RateLimit.class);

        if (rateLimit == null) {
            return true;
        }

        String clientId = request.getHeader("X-Client-ID");
        String ip = request.getRemoteAddr();

        // For non-authenticated endpoints, only apply IP-based rate limiting
        if (!rateLimit.authenticatedOnly()) {
            Bucket ipBucket = rateLimitConfig.resolveBucketForIp(ip, rateLimit.limit(), rateLimit.duration());
            if (!ipBucket.tryConsume(1)) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setHeader("X-Rate-Limit-Retry-After-Seconds", 
                    String.valueOf(rateLimit.duration()));
                return false;
            }
            return true;
        }

        // For authenticated endpoints, apply both IP and client-based rate limiting
        if (clientId != null) {
            Bucket ipBucket = rateLimitConfig.resolveBucketForIp(ip, rateLimit.limit(), rateLimit.duration());
            if (!ipBucket.tryConsume(1)) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setHeader("X-Rate-Limit-Retry-After-Seconds", 
                    String.valueOf(rateLimit.duration()));
                return false;
            }

            Bucket clientBucket = rateLimitConfig.resolveBucketForClient(clientId, rateLimit.limit(), rateLimit.duration());
            if (!clientBucket.tryConsume(1)) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setHeader("X-Rate-Limit-Retry-After-Seconds", 
                    String.valueOf(rateLimit.duration()));
                return false;
            }
        }

        return true;
    }
} 