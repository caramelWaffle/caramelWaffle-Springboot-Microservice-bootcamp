package com.wafflebank.card.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CardData {
    @Schema(description = "Unique identifier for the card", example = "1001")
    @NotNull
    private int cardId;

    @Schema(description = "Mobile phone number of the card", example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;

    @Schema(description = "Number of the card", example = "1001")
    @NotNull
    private String cardNumber;

    @Schema(description = "Type of the card", example = "Disney Credit")
    @NotNull
    private String cardType;

    @Schema(description = "Total limit of the card", example = "Disney Credit")
    @NotNull
    private int totalLimit;

    @Schema(description = "Amount used of the card", example = "Disney Credit")
    @NotNull
    private int amountUsed;

    @Schema(description = "Amount available of the card", example = "Disney Credit")
    @NotNull
    private int amountAvailable;
}
