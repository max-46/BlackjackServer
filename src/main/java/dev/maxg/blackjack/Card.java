/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.maxg.blackjack;

import com.google.gson.Gson;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author max
 */
public class Card {
    
    CardType cardType;

    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    public int getValue() {
        switch(cardType) {
            case ACE: 
                return 1;
            case TWO:
                return 2;
            case THREE: 
                return 3;
            case FOUR:
                return 4;
            case FIVE: 
                return 5;
            case SIX:
                return 6;
            case SEVEN: 
                return 7;
            case EIGHT:
                return 8;
            case NINE: 
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                throw new AssertionError("Card type is unknown.");
        }
    }

    public CardType getCardType() {
        return cardType;
    }
    
    public static Deque<Card> getCardDecks(int numOfDecks) {
        List<Card> cardList = new ArrayList<>();
        for (CardType ct : CardType.values()) {
            for (int i = 0; i < 4 * numOfDecks; i++) {
                cardList.add(new Card(ct));
            }
        }
        Collections.shuffle(cardList);
        return new ArrayDeque<>(cardList);
    }

    @Override
    public String toString() {
        return "Card{" + cardType + '}';
    }

    public String toJson() {
        return new Gson().toJson(this.toString());
    }
    
    
    
}
