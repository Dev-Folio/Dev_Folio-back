package com.inhatc.dev_folio.member.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Order {
    @JsonProperty("popularity")
    POPULARITY,
    @JsonProperty("newest")
    NEWEST,
    @JsonProperty("oldest")
    OLDEST;

}
