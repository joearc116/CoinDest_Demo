package com.cathaybk.rest;

import com.cathaybk.entity.Currency;
import com.cathaybk.service.CoinDeskService;
import com.cathaybk.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/coindesk")
public class CoinDeskController {

    @Autowired
    private CoinDeskService coindeskService;

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public String getCoinDeskData() {
        String data = coindeskService.getCoinDeskData();
        log.info("Original CoinDesk Data: {}", data);

        return data;
    }

    @GetMapping(value = "/transformed")
    public Map<String, Object> getTransformedCoinDeskData() {
        Map<String, Object> data = coindeskService.getTransformedCoinDeskData();
        Map<String, Map<String, String>> currencies = (Map<String, Map<String, String>>) data.get("currencies");
        currencies.forEach((code, details) -> {
            Currency currency = currencyService.findByCode(code);
            if (currency !=  null) {
                details.put("name", currency.getName());
            } else {
                details.put("name", "Unknown");
            }
        });
        log.info("Transformed CoinDesk Data: {}", data);

        return data;
    }
}
