package com.apiautomation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestLogin {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
