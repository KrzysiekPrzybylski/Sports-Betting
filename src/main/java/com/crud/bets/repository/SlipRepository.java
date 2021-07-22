package com.crud.bets.repository;

import com.crud.bets.domain.Slip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SlipRepository extends JpaRepository<Slip, Long> {
}
