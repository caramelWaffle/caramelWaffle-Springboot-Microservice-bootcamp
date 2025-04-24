package com.wafflebank.card.service;

import com.wafflebank.card.model.CardData;

public interface CardService {
    void createCard(CardData cardData);
    CardData findCard(String cardNumber);
    CardData findCardByMobileNumber(String mobileNumber);
    boolean updateCard(CardData cardData);
    boolean deleteCard(CardData cardData);
    boolean deleteCardById(int cardId);
}
