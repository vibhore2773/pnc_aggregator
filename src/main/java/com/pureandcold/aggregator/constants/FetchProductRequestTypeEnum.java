package com.pureandcold.aggregator.constants;

import lombok.Getter;

@Getter
public enum FetchProductRequestTypeEnum {
    BEST_SELLER("bestseller"),
    ALL("all");

    private String name;
    FetchProductRequestTypeEnum(String name) {
        this.name = name();
    }
    public String getName() {
        return this.name;
    }
}
