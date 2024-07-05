package com.cathaybk.service;

import com.cathaybk.dto.CurrencyDto;
import com.cathaybk.entity.Currency;
import com.cathaybk.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    public Currency findByCode(String code) {
        return repository.findByCode(code);
    }

    public List<CurrencyDto> findAll() {
        List<Currency> list = repository.findAll();
        if (!CollectionUtils.isEmpty(list)) {
            List<CurrencyDto> resultLs = list.stream().map(currency -> {
                CurrencyDto dto = new CurrencyDto(currency.getCode(), currency.getName());
                return dto;
            }).collect(Collectors.toList());

            return resultLs;
        }

        return null;
    }

    public CurrencyDto save(CurrencyDto currencyDto) {
        Currency currency = new Currency(currencyDto.getCode(), currencyDto.getName());
        Currency result = repository.save(currency);
        CurrencyDto resultDto = new CurrencyDto(result.getCode(), result.getName());

        return result != null? resultDto : null;
    }

    public CurrencyDto update(CurrencyDto currencyDto) {
        String code = currencyDto.getCode();
        Currency existingCurrency = repository.findById(code)
                .orElseThrow(() -> new RuntimeException("Currency not found with code: " + code));
        existingCurrency.setName(currencyDto.getName());
        Currency result = repository.save(existingCurrency);

        return currencyDto;
    }

    public void delete(String code) {
        repository.deleteById(code);
    }

}
