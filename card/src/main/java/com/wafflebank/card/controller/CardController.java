package com.wafflebank.card.controller;

import com.wafflebank.card.model.CardData;
import com.wafflebank.card.model.build.BuildInfo;
import com.wafflebank.card.model.network.ResponseData;
import com.wafflebank.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CardController", description = "Controller for managing card operations")
@RefreshScope
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
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

    @Operation(
            description = "Get build information of account microservice",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account successfully deleted by customer ID",
                            content = @Content(schema = @Schema(implementation = ResponseData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid customer ID"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/build-info")
    public ResponseEntity<BuildInfo> getBuildInformation() {
        HttpStatus status = HttpStatus.OK;

        BuildInfo body = BuildInfo.builder()
                .version(buildVersion)
                .javaVersion(environment.getProperty("java.version"))
                .build();

        return ResponseEntity.status(status).body(body);
    }
}
