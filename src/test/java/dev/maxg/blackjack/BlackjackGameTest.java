/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.maxg.blackjack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
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
public class BlackjackGameTest {
    
    public BlackjackGameTest() {
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
     * Test of dealerPlay method, of class BlackjackGame.
     */
    @Test
    public void testDealerPlay() {
        System.out.println("dealerPlay");
        BlackjackGame bg = new BlackjackGame(3);
        bg.dealerPlay();
        assertTrue(bg.getDealer().getTotal() > bg.getPlayer().getTotal());
        bg.dealNewCards(new Card[]{
            new Card(CardType.TWO), new Card(CardType.QUEEN),
            new Card(CardType.FOUR), new Card(CardType.FIVE)});
        assertTrue(bg.getDealer().getTotal() >= bg.getPlayer().getTotal());
    }

    /**
     * Test of dealNewCards method, of class BlackjackGame.
     */
    @Test
    public void testDealNewCards() {
        System.out.println("dealNewCards");
        BlackjackGame bg = new BlackjackGame(2);
        Card[] cards = bg.getPlayer().getCards();
        bg.dealNewCards(new Card[]{
            new Card(CardType.THREE), new Card(CardType.SIX),
            new Card(CardType.NINE), new Card(CardType.QUEEN)});
        assertFalse(Arrays.equals(cards, bg.getPlayer().getCards()));

        assertThrows(AssertionError.class, () -> {
        
            bg.dealNewCards(new Card[]{
                new Card(CardType.THREE), new Card(CardType.SIX),
                new Card(CardType.NINE)});
        
        });
    }

    /**
     * Test of getWinner method, of class BlackjackGame.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        BlackjackGame bg = new BlackjackGame(3);
        Player winner = bg.getWinner();
        assertTrue(winner.getTotal() <= 21);
    }
    
    /**
     * Test of getWinner method, of class BlackjackGame.
     */
    @Test
    public void testGetCards() {
        System.out.println("getCards");
        BlackjackGame bg = new BlackjackGame(3);
        Deque<Card> cards = bg.getCards();
        assertTrue(!cards.isEmpty());
    }
    
    /**
     * Test of testMain method, of class BlackjackGame.
     */
    @Test
    public void testMain() {
        System.out.println("testMain");
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));  
        BlackjackGame.main(new String[]{"test"});
        assertEquals("Player{name=", outputStreamCaptor.toString().trim().substring(0, 12),
                "The winning player name should be displayed.");
        System.setOut(System.out);
    }
}
