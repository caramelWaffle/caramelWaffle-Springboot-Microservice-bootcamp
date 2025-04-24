package com.wafflebank.card.service;

import com.wafflebank.card.entity.CardEntity;
import com.wafflebank.card.exception.FileExistException;
import com.wafflebank.card.exception.ResourceNotFoundException;
import com.wafflebank.card.mapper.CardDataMapper;
import com.wafflebank.card.model.CardData;
import com.wafflebank.card.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Override
    public void createCard(CardData cardData) {
        if (cardRepository.findByMobileNumber(cardData.getMobileNumber()).isPresent()) {
            throw new FileExistException(
                    String.format("Card already exists (mobile number: %s)", cardData.getMobileNumber())
            );
        } else {
            CardEntity cardEntity = CardDataMapper.toEntity(cardData);
            cardRepository.save(cardEntity);
        }
    }

    @Override
    public CardData findCard(String cardNumber) {
        CardEntity cardEntity = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        return CardDataMapper.toData(cardEntity);
    }

    @Override
    public CardData findCardByMobileNumber(String mobileNumber) {
        CardEntity cardEntity = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        return CardDataMapper.toData(cardEntity);
    }

    @Override
    public boolean updateCard(CardData cardData) {
        cardRepository.findByCardNumber(cardData.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        cardRepository.save(CardDataMapper.toEntity(cardData));
        return true;
    }

    @Override
    public boolean deleteCard(CardData cardData) {
        cardRepository.findById(cardData.getCardId())
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        cardRepository.delete(CardDataMapper.toEntity(cardData));
        return true;
    }

    @Override
    public boolean deleteCardById(int cardId) {
        cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        cardRepository.deleteById(cardId);
        return true;
    }
}
