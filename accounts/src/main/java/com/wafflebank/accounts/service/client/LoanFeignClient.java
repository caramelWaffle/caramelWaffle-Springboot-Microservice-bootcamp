package com.wafflebank.accounts.service.client;

import com.wafflebank.accounts.model.feign.card.CardData;
import com.wafflebank.accounts.model.feign.loan.LoanData;
import com.wafflebank.accounts.service.client.fallback.LoansFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoanFeignClient {

    @GetMapping("/api/findByMobileNumber")
    public ResponseEntity<LoanData> findByMobileNumber(@RequestParam String mobileNumber);
}
