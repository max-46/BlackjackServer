/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.maxg.blackjack;

import java.util.Deque;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author max
 */
public class CardTest {
    
    public CardTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getValue method, of class Card.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Card instance = new Card(CardType.ACE);
        int expResult = 1;
        int result = instance.getValue();
        assertEquals(expResult, result, "The card's value should be 1.");
        instance = new Card(CardType.JACK);
        expResult = 10;
        result = instance.getValue();
        assertEquals(expResult, result, "The card's value should be 10.");
    }

    /**
     * Test of getCardType method, of class Card.
     */
    @Test
    public void testGetCardType() {
        System.out.println("getCardType");
        Card instance = new Card(CardType.KING);
        CardType result = instance.getCardType();
        assertEquals(CardType.KING, result, "The card's type should be KING.");
        instance = new Card(CardType.SEVEN);
        result = instance.getCardType();
        assertEquals(CardType.SEVEN, result, "The card's type should be SEVEN.");
        instance = new Card(CardType.NINE);
        result = instance.getCardType();
        assertEquals(CardType.NINE, result, "The card's type should be SEVEN.");
    }

    /**
     * Test of getCardDecks method, of class Card.
     */
    @Test
    public void testGetCardDecks() {
        System.out.println("getCardDecks");
        int numOfDecks = 3;
        Deque<Card> cards = Card.getCardDecks(numOfDecks);
        assertEquals(156, cards.size(), "The should be 156 cards");
        assertTrue(cards instanceof Deque);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Card instance = new Card(CardType.QUEEN);
        String expResult = "Card{QUEEN}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
