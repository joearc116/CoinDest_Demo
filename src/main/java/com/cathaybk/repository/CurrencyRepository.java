package com.cathaybk.repository;

import com.cathaybk.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query(value = "select * from CURRENCY where CODE = :code ", nativeQuery = true)
    Currency findByCode(String code);
}
