/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.maxg.blackjack;

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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of giveNewCards method, of class Player.
     */
    @Test
    public void testGiveNewCards() {
        System.out.println("giveNewCards");
        Card[] cards = new Card[]{new Card(CardType.ACE), new Card(CardType.JACK)};
        Player player = new Player("Player", cards);
        assertArrayEquals(cards, player.getCards(), "Cards should be ACE and JACK.");
        Card[] newCards = new Card[]{new Card(CardType.TWO), new Card(CardType.KING)};
        player.giveNewCards(newCards);
        assertArrayEquals(newCards, player.getCards(), "Cards should be TWO and KING.");
    }

    /**
     * Test of giveAnotherCard method, of class Player.
     */
    @Test
    public void testGiveAnotherCard() {
        System.out.println("giveAnotherCard");
        Card card = new Card(CardType.ACE);
        Player player = new Player("Player1", 
                new Card[]{new Card(CardType.THREE), new Card(CardType.EIGHT)});
        player.giveAnotherCard(card);
        assertEquals(3, player.getCards().length);
    }

    /**
     * Test of getTotal method, of class Player.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        Player player = new Player("Player 1", 
                new Card[]{new Card(CardType.THREE), new Card(CardType.KING)});
        int expResult = 13;
        int result = player.getTotal();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playerHasGoneBust method, of class Player.
     */
    @Test
    public void testPlayerHasGoneBust() {
        System.out.println("playerHasGoneBust");
        Player p = new BlackjackGame(1).getPlayer();
        assertTrue(!p.playerHasGoneBust());
        for (int i = 0; i < 3; i++) {
            p.giveAnotherCard(new Card(CardType.KING));
        }
        assertTrue(p.playerHasGoneBust());
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Player player = new Player("Player", 
                new Card[]{new Card(CardType.FOUR), new Card(CardType.EIGHT)});
        String expResult = "Player{name=Player, cards=[Card{FOUR}, Card{EIGHT}], total=12}";
        String result = player.toString();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player player = new Player("Player X", 
                new Card[]{new Card(CardType.THREE), new Card(CardType.KING)});
        assertEquals("Player X", player.getName());
    }
    
}
