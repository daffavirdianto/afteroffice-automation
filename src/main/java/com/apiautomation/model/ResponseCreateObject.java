package com.apiautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCreateObject {
    @JsonProperty("id")
    public int id;

    @JsonProperty("name")
    public String name;

    public DataObject data;

    public ResponseCreateObject() {
    }

    public static class DataObject {
        @JsonProperty("year")
        public int year;

        @JsonProperty("price")
        public double price;

        @JsonProperty("cpu_model")
        public String cpu_model;

        @JsonProperty("hard_disk_size")
        public String hard_disk_size;

        @JsonProperty("capacity")
        public String capacity;

        @JsonProperty("screen_size")
        public String screen_size;

        @JsonProperty("color")
        public String color;

        public DataObject() {
        }
    }

}
