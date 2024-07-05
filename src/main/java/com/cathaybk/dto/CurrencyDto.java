package com.cathaybk.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CurrencyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public CurrencyDto() {
    }

    public CurrencyDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;

    private String name;
}
