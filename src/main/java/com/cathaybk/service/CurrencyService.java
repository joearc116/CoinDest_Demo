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

    public CurrencyDto update(String id, CurrencyDto currencyDto) {
        Currency existingCurrency = repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Currency not found with id: " + id));
        existingCurrency.setCode(currencyDto.getCode());
        existingCurrency.setName(currencyDto.getName());
        Currency result = repository.save(existingCurrency);

        return result != null? currencyDto : null;
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

}
