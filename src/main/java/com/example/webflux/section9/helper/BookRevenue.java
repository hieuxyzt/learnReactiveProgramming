package com.example.webflux.section9.helper;

import lombok.ToString;

import java.util.Map;

@ToString
public class BookRevenue {
    private Map<String, Double> revenue;

    public BookRevenue(Map<String, Double> revenue) {
        this.revenue = revenue;
    }
}
