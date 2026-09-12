package com.example.cards_microservices.service;

import com.example.accounts_microservices.exceptions.ResourceNotFoundException;
import com.example.cards_microservices.DTOs.CardsDTO;
import com.example.cards_microservices.constant.CardsConstants;
import com.example.cards_microservices.entity.Cards;
import com.example.cards_microservices.exceptions.CardAlreadyExistException;
import com.example.cards_microservices.mapper.CardsMapper;
import com.example.cards_microservices.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsService {

    private CardsRepository cardsRepository;



    public void createCard(String mobileNumber) {
       Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);
       if(card.isPresent()) {
           throw new CardAlreadyExistException("Card already exists for this mobile number"+mobileNumber);
       }
       else {
           cardsRepository.save(createNewCard(mobileNumber));
       }
    }

    public CardsDTO fetchCard(String mobileNumber){
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber",mobileNumber)
        );
        return CardsMapper.mapToCardsDTO(card,new CardsDTO());

    }

    public boolean updateCard(CardsDTO cardsDTO) {
        Cards card = cardsRepository.findByMobileNumber(cardsDTO.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber",cardsDTO.getMobileNumber())
        );
        CardsMapper.mapToCards(cardsDTO,card);
        cardsRepository.save(card);
        return true;

    }
    public boolean deleteCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber",mobileNumber)
        );
        cardsRepository.delete(card);
        return true;
    }
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }


}
