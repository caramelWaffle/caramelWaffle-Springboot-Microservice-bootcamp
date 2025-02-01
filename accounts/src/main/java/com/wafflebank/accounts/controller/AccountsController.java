package com.wafflebank.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("say-hello")
    public String sayHello() {
        return "Hello From the outer side";
    }
}
