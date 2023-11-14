package com.hx2.desk.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {
    @JsonProperty("USER")
    USER,
    @JsonProperty("ADMIN")
    ADMIN


}
