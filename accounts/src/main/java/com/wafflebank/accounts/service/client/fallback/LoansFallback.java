package com.wafflebank.accounts.service.client.fallback;

import com.wafflebank.accounts.model.feign.loan.LoanData;
import com.wafflebank.accounts.service.client.LoanFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoanFeignClient {
    @Override
    public ResponseEntity<LoanData> findByMobileNumber(String mobileNumber) {
        return ResponseEntity.ofNullable(null);
    }
}
