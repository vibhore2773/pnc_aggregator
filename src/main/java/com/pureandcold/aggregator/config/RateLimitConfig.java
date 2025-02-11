package com.pureandcold.aggregator.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.annotation.EnableCaching;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableCaching
public class RateLimitConfig {
    private final Map<String, Bucket> ipBucketMap = new ConcurrentHashMap<>();
    private final Map<String, Bucket> clientBucketMap = new ConcurrentHashMap<>();

    public Bucket resolveBucketForIp(String ip, int limit, int duration) {
        String key = ip + ":" + limit + ":" + duration;
        return ipBucketMap.computeIfAbsent(key, k -> newBucket(limit, duration));
    }

    public Bucket resolveBucketForClient(String clientId, int limit, int duration) {
        String key = clientId + ":" + limit + ":" + duration;
        return clientBucketMap.computeIfAbsent(key, k -> newBucket(limit, duration));
    }

    private Bucket newBucket(int limit, int duration) {
        Bandwidth bandwidth = Bandwidth.classic(limit, Refill.intervally(limit, Duration.ofSeconds(duration)));
        return Bucket.builder().addLimit(bandwidth).build();
    }
} 