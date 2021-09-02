/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.maxg.blackjack;

import java.util.Deque;
import java.util.Scanner;

/**
 *
 * @author max
 */
public class BlackjackGame {

    private final Deque<Card> cards;
    private final Player dealer;
    private final Player player;

    public BlackjackGame(int numOfDecks) {
        cards = Card.getCardDecks(numOfDecks);
        dealer = new Player("Dealer", new Card[]{cards.pop(), cards.pop()});
        player = new Player("Player", new Card[]{cards.pop(), cards.pop()});
    }

    public static void main(String[] args) {
        BlackjackGame bg = new BlackjackGame(3);
        System.out.println(bg.getDealer());
        Scanner scanner = new Scanner(System.in);
        String response;
        Player p = bg.getPlayer();
        while (p.getTotal() <= 21) {
            System.out.println(p);
            System.out.println("Would you like another card? (Y/n)");
            response = (args.length > 0 && args[0].equalsIgnoreCase("test")) ? "N" : scanner.nextLine();
            if (response.equalsIgnoreCase("N")) {
                break;
            }
            p.giveAnotherCard(bg.getCards().pop());
        }
        if (p.playerHasGoneBust()) System.out.println("You have gone bust!");
        bg.dealerPlay();
        System.out.println("The winner is " + bg.getWinner());
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public Deque<Card> getCards() {
        return cards;
    } 

    public void dealerPlay() {
        System.out.println("Dealer playing...");
        System.out.println(player.getTotal());
        while (dealer.getTotal() < player.getTotal() && dealer.getTotal() < 21) {
            dealer.giveAnotherCard(cards.pop());
        }
        System.out.println(dealer);
    }

    public void dealNewCards(Card[] cards) {
        if (cards.length != 4) {
            throw new AssertionError("There should be four cards.");
        }
        int i = 0;
        for (Player p : new Player[]{dealer, player}) {
            p.giveNewCards(new Card[]{cards[i++], cards[i++]});
        }
    }

    public Player getWinner() {
        Player winner = null;
        for (var p : new Player[]{dealer, player}) {
            int pTotal = p.getTotal();
            if (pTotal <= 21 && (winner == null || pTotal > winner.getTotal())) {
                winner = p;
            }
        }
        return winner;
    }

}
