package com.wafflebank.card.repository;

import com.wafflebank.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {
    Optional<CardEntity> findByMobileNumber(String mobileNumber);
    Optional<CardEntity> findByCardNumber(String cardNumber);
}
