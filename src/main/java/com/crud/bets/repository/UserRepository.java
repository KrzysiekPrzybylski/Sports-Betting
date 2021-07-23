package com.crud.bets.repository;

import com.crud.bets.domain.Slip;
import com.crud.bets.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findBySlipContains(Slip slip);

    Optional<User> findByEmail(String username);

}
