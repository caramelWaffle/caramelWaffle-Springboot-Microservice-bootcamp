package com.wafflebank.accounts.service.client;

import com.wafflebank.accounts.model.feign.card.CardData;
import com.wafflebank.accounts.service.client.fallback.CardsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "/api/findByMobileNumber", consumes = "application/json")
    ResponseEntity<CardData> findCardByMobileNumber(@RequestParam String mobileNumber);
}
