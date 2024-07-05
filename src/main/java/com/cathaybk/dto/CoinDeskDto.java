package com.cathaybk.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CoinDeskDto {

    private Timestamp updateTime;

    private String code;

    private String name;

    private String rate;

    private String symbol;
}
