package com.afteroffice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseUpdateObject {
@JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;
    
    private DataObject data;

    @Data
    public static class DataObject {
        @JsonProperty("year")
        private int year;

        @JsonProperty("price")
        private double price;

        @JsonProperty("CPU model")
        private String cpu_model;

        @JsonProperty("Hard disk size")
        private String hard_disk_size;

        @JsonProperty("capacity")
        private String capacity;

        @JsonProperty("screen_size")
        private String screen_size;

        @JsonProperty("color")
        private String color;
    }
}
