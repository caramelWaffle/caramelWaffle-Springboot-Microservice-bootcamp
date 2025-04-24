package com.wafflebank.card.mapper;

import com.wafflebank.card.entity.CardEntity;
import com.wafflebank.card.model.CardData;

public class CardDataMapper {

    // Convert CardData to CardEntity
    public static CardEntity toEntity(CardData cardData) {
        if (cardData == null) {
            return null;
        }
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardId(cardData.getCardId());
        cardEntity.setMobileNumber(cardData.getMobileNumber());
        cardEntity.setCardNumber(cardData.getCardNumber());
        cardEntity.setCardType(cardData.getCardType());
        cardEntity.setTotalLimit(cardData.getTotalLimit());
        cardEntity.setAmountUsed(cardData.getAmountUsed());
        cardEntity.setAmountAvailable(cardData.getAmountAvailable());
        return cardEntity;
    }

    // Convert CardEntity to CardData
    public static CardData toData(CardEntity cardEntity) {
        if (cardEntity == null) {
            return null;
        }
        return CardData.builder()
                .cardId(cardEntity.getCardId())
                .mobileNumber(cardEntity.getMobileNumber())
                .cardNumber(cardEntity.getCardNumber())
                .cardType(cardEntity.getCardType())
                .totalLimit(cardEntity.getTotalLimit())
                .amountUsed(cardEntity.getAmountUsed())
                .amountAvailable(cardEntity.getAmountAvailable())
                .build();
    }
}