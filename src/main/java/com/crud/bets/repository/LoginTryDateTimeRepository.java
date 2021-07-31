package com.crud.bets.repository;

import com.crud.bets.domain.detail.LoginTryDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LoginTryDateTimeRepository extends JpaRepository<LoginTryDateTime, Long> {

    @Override
    LoginTryDateTime save(LoginTryDateTime loginTryDateTime);
}
