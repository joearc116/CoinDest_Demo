package com.cathaybk.rest;

import lombok.Data;

import java.util.Map;

@Data
public class CoinDeskResponse {

    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, CurrencyData> bpi;

    @Data
    public static class Time {
        private String updated;
        private String updatedISO;
        private String updateduk;
    }

    @Data
    public static class CurrencyData {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private double rate_float;
    }
}
