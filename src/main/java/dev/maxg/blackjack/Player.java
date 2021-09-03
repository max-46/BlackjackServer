/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.maxg.blackjack;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author max
 */
public class Player {

    String name;
    List<Card> cards;

    public Player(String name, Card[] cards) {
        this.name = name;
        this.cards = new ArrayList<>(Arrays.asList(cards));
    }

    public String getName() {
        return name;
    }

    public Card[] getCards() {
        return cards.toArray(new Card[cards.size()]);
    }

    public void giveNewCards(Card[] cards) {
        this.cards = new ArrayList<>(Arrays.asList(cards));
    }

    public void giveAnotherCard(Card card) {
        cards.add(card);
    }

    public int getTotal() {
        int numOfAces = cards.stream()
                .filter(c -> c.getCardType() == CardType.ACE)
                .mapToInt(c -> 1)
                .sum();
        int total = cards.stream().mapToInt(Card::getValue).sum();
        if (numOfAces == 0) return total;
        return total <= 11 ? total + 10 : total;
    }
    
    public boolean hasGoneBust() {
        return getTotal() > 21;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", cards=" + cards + ", total=" 
                + getTotal() + '}';
    }

    public String toJson() {
        return new Gson().toJson(this.toString());
    }

}
