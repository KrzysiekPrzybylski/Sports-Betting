package com.crud.bets.repository;

import com.crud.bets.domain.Slip;
import com.crud.bets.domain.SlipState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SlipRepository extends JpaRepository<Slip, Long> {

    List<Slip> findAllByState(SlipState state);
}
