package com.example.cards_microservices.mapper;


import com.example.cards_microservices.DTOs.CardsDTO;
import com.example.cards_microservices.entity.Cards;
import org.springframework.stereotype.Component;

@Component
public class CardsMapper{

    public static Cards mapToCards(CardsDTO cardsDTO,Cards cards){
        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        cards.setAvailableAmount(cardsDTO.getAvailableAmount());
        return cards;
    }

    public static CardsDTO mapToCardsDTO(Cards cards,CardsDTO cardsDTO){
        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        cardsDTO.setAmountUsed(cards.getAmountUsed());
        cardsDTO.setAvailableAmount(cards.getAvailableAmount());
        return cardsDTO;

    }

}
