package com.crud.bets.repository;

import com.crud.bets.domain.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRates, Long> {

    @Query(nativeQuery = true)
    Optional<ExchangeRates> getLastRates();
}
