package com.cathaybk.service;

import com.cathaybk.entity.Currency;
import com.cathaybk.repository.CurrencyRepository;
import com.cathaybk.rest.CoinDeskResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CoinDeskService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String coinDeskUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Autowired
    private CurrencyRepository currencyRepository;

    public String getCoinDeskData() {
        return restTemplate.getForObject(coinDeskUrl, String.class);
    }

    public Map<String, Object> getFormatedCoinDeskData() {
        try {
            String response = restTemplate.getForObject(coinDeskUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            CoinDeskResponse coinDeskResponse = mapper.readValue(response, CoinDeskResponse.class);

            Map<String, Object> result = new HashMap<>();
            result.put("updateTime", formatDate(coinDeskResponse.getTime().getUpdatedISO()));

            Map<String, Map<String, String>> currencies = new HashMap<>();
            coinDeskResponse.getBpi().forEach((code, currencyData) -> {
                Map<String, String> data = new HashMap<>();
                data.put("code", currencyData.getCode());
                data.put("rate", currencyData.getRate());
                Currency currency = currencyRepository.findByCode(currencyData.getCode());
                if (currency !=  null) {
                    data.put("name", currency.getName());
                } else {
                    data.put("name", "Unknown");
                }
                currencies.put(code, data);
            });
            result.put("currencies", currencies);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching data from Coindesk API", e);
        }
    }

    private String formatDate(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            ZonedDateTime utcDateTime = ZonedDateTime.parse(dateTimeStr, formatter.withZone(ZoneOffset.UTC));
            ZonedDateTime taipeiDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Taipei"));
            String updateTime = taipeiDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            return updateTime;
        } catch (Exception e) {
            log.info("DateTime format fail: {}", e);
            return dateTimeStr;
        }
    }
}
