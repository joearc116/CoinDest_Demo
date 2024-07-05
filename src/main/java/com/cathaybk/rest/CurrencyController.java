package com.cathaybk.rest;

import com.cathaybk.dto.CurrencyDto;
import com.cathaybk.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @GetMapping
    public List<CurrencyDto> getAll() {
        return service.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CurrencyDto currencyDto) {
        log.info("Request CurrencyDto: {}", currencyDto);
        CurrencyDto result = service.save(currencyDto);
        log.info("Request CurrencyDto: {}", currencyDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CurrencyDto currencyDto) {
        log.info("Request Id: {}, CurrencyDto: {}", id, currencyDto);
        CurrencyDto result = service.update(id, currencyDto);
        log.info("Update  result: {} ", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        log.info("Delete Currency Id: {}", id);
        service.delete(id);
    }
}
