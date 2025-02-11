package com.pureandcold.aggregator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    boolean authenticatedOnly() default false;
    int limit() default 100;
    int duration() default 60; // in seconds
} 