package com.crud.bets.repository;

import com.crud.bets.domain.detail.CategoryWatchingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryWatchingDetailsRepository extends JpaRepository<CategoryWatchingDetails, Long> {

    @Override
    CategoryWatchingDetails save (CategoryWatchingDetails categoryWatchingDetails);
}
