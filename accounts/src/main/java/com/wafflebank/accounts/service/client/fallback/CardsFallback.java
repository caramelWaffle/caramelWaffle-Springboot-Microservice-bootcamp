package com.wafflebank.accounts.service.client.fallback;

import com.wafflebank.accounts.model.feign.card.CardData;
import com.wafflebank.accounts.model.feign.loan.LoanData;
import com.wafflebank.accounts.service.client.CardsFeignClient;
import com.wafflebank.accounts.service.client.LoanFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardData> findCardByMobileNumber(String mobileNumber) {
        return ResponseEntity.ofNullable(null);
    }
}
