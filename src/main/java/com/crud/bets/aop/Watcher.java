package com.crud.bets.aop;

import com.crud.bets.domain.Slip;
import com.crud.bets.domain.SlipState;
import com.crud.bets.domain.detail.CategoryWatchingDetails;
import com.crud.bets.domain.detail.LoginTryDateTime;
import com.crud.bets.domain.detail.SLipOrderDetails;
import com.crud.bets.domain.detail.SlipSettleDetails;
import com.crud.bets.repository.CategoryWatchingDetailsRepository;
import com.crud.bets.repository.LoginTryDateTimeRepository;
import com.crud.bets.repository.SlipOrderDetailsRepository;
import com.crud.bets.repository.SlipSettleDetailsRepository;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class Watcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);
    private final LoginTryDateTimeRepository loginTryDateTimeRepository;
    private final CategoryWatchingDetailsRepository categoryWatchingDetailsRepository;
    private final SlipOrderDetailsRepository slipOrderDetailsRepository;
    private final SlipSettleDetailsRepository slipSettleDetailsRepository;

    @Autowired
    public Watcher(LoginTryDateTimeRepository loginTryDateTimeRepository, CategoryWatchingDetailsRepository categoryWatchingDetailsRepository, SlipOrderDetailsRepository slipOrderDetailsRepository, SlipSettleDetailsRepository slipSettleDetailsRepository) {
        this.loginTryDateTimeRepository = loginTryDateTimeRepository;
        this.categoryWatchingDetailsRepository = categoryWatchingDetailsRepository;
        this.slipOrderDetailsRepository = slipOrderDetailsRepository;
        this.slipSettleDetailsRepository = slipSettleDetailsRepository;
    }

    @Before("execution(* com.crud.bets.controller.UserController.getUsersDetail(..))")
    public void saveLoginDataTime() {
        LOGGER.info("Some is trying to login.");
        loginTryDateTimeRepository.save(new LoginTryDateTime());
    }
    @Before("execution(* com.crud.bets.controller.CategoryController.getCategory(..))"
            + "&& args(categoryId)")

    public void saveCategory(long categoryId) {
        LOGGER.info("Some is watching category" + categoryId);
        categoryWatchingDetailsRepository.save(new CategoryWatchingDetails(categoryId));

    }
    @AfterReturning(pointcut ="execution(* com.crud.bets.fasade.OrderSlipFacade.orderSlip(..))",
        returning = "retVal")
    public void saveSlipOrderDetails(Object retVal) {
        Slip cartSlip = (Slip) retVal;
        LOGGER.info(cartSlip.getSlipId() + "has been ordered.");
        slipOrderDetailsRepository.save(
                SLipOrderDetails.builder()
                        .odds(cartSlip.getTotalOdds())
                        .orderDateTime(LocalDateTime.now())
                        .slip(cartSlip)
                        .stake(cartSlip.getStake())
                        .build()
        );
    }
    @AfterReturning("execution(* com.crud.bets.service.SlipService.settleSlip(..))"+
                    "&& args(objectSlip)")
    public  void saveSlipSettleDetails(Object objectSlip) {
        Slip slip = (Slip) objectSlip;
        if (slip.getState().equals(SlipState.LOST)) {
            slipSettleDetailsRepository.save(
                    SlipSettleDetails.builder()
                            .dateTime(LocalDateTime.now())
                            .odds(slip.getTotalOdds())
                            .slip(slip)
                            .stake(slip.getStake())
                            .winning(false)
                            .build()
            );
            LOGGER.info("Slip" + slip.getSlipId() + "has lost.");
        } else if (slip.getState().equals(SlipState.WINNING)) {
            slipSettleDetailsRepository.save(
                    SlipSettleDetails.builder()
                            .dateTime(LocalDateTime.now())
                            .odds(slip.getTotalOdds())
                            .slip(slip)
                            .stake(slip.getStake())
                            .winning(true)
                            .build()
            );
            LOGGER.info("Slip" + slip.getSlipId() + "has won.");
        }
    }
}
