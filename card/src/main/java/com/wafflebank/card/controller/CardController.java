package com.wafflebank.card.controller;

import com.wafflebank.card.model.CardData;
import com.wafflebank.card.model.network.ResponseData;
import com.wafflebank.card.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CardController", description = "Controller for managing card operations")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CardController {

    private CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@Valid @RequestBody CardData cardData) {
        cardService.createCard(cardData);

        HttpStatus status = HttpStatus.CREATED;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @GetMapping("/find")
    public ResponseEntity<CardData> find(@RequestParam String cardNumber) {
        CardData cardData = cardService.findCard(cardNumber);
        HttpStatus status = cardData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(cardData);
    }

    @GetMapping("/findByMobileNumber")
    public ResponseEntity<CardData> findCardByMobileNumber(@RequestParam String mobileNumber) {
        CardData cardData = cardService.findCardByMobileNumber(mobileNumber);
        HttpStatus status = cardData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(cardData);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData> update(@Valid @RequestBody CardData cardData) {
        boolean isSuccess = cardService.updateCard(cardData);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();
        return ResponseEntity.status(status).body(body);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData> delete(@Valid @RequestBody CardData cardData) {
        boolean isSuccess = cardService.deleteCard(cardData);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();
        return ResponseEntity.status(status).body(body);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponseData> deleteById(@RequestParam int cardId) {
        boolean isSuccess = cardService.deleteCardById(cardId);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();
        return ResponseEntity.status(status).body(body);
    }
}
