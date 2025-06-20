package com.afteroffice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCreateObject {
    @JsonProperty("name")
    public String name;

    @JsonProperty("data")
    public DataObject data;

    public RequestCreateObject() {
    }

    public RequestCreateObject(String name, DataObject data) {
        this.name = name;
        this.data = data;
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

        public DataObject(int year, double price, String cpu_model, String hard_disk_size, String capacity,
                String screen_size, String color) {
            this.year = year;
            this.price = price;
            this.cpu_model = cpu_model;
            this.hard_disk_size = hard_disk_size;
            this.capacity = capacity;
            this.screen_size = screen_size;
            this.color = color;
        }
    }
}
