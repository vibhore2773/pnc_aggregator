package com.pureandcold.aggregator.constants;

import lombok.Getter;

@Getter
public enum FetchProductRequestTypeEnum {
    BEST_SELLER("bestseller"),
    ALL("all");

    private String type;
    FetchProductRequestTypeEnum(String type) {
        this.type = type;
    }
    public String getName() {
        return this.type;
    }
}
