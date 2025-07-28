package com.wafflebank.accounts.service.client;

import com.wafflebank.accounts.model.feign.card.CardData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("card")
public interface CardsFeignClient {

    @GetMapping(value = "/api/findByMobileNumber", consumes = "application/json")
    public ResponseEntity<CardData> findCardByMobileNumber(@RequestParam String mobileNumber);
}
