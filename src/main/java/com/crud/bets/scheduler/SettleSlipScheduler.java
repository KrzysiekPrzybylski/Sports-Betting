package com.crud.bets.scheduler;

import com.crud.bets.domain.SlipState;
import com.crud.bets.service.SlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SettleSlipScheduler {

    private final SlipService slipService;

    @Autowired
    public SettleSlipScheduler(SlipService slipService) {
        this.slipService = slipService;
    }

    @Scheduled(cron = "0 0 * * * * ")
    public void settleSlips() {
        slipService.getSlipsByState(SlipState.ORDERED);
    }
}
