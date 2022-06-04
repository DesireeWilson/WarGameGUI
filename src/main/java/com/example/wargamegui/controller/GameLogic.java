package com.example.wargamegui.controller;

import com.example.wargamegui.model.Card;
import com.example.wargamegui.model.Deck;
import com.example.wargamegui.model.PlayerHand;
import com.example.wargamegui.view.Display;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GameLogic {
    Display display;
    boolean continueGame = true;

    public GameLogic() { }

    public GameLogic(Display display) {
        this.display = display;
    }

    void start(Deck deck, PlayerHand p1, PlayerHand p2, Scanner scanner) {
        deck.shuffleDeck();
        deck.dealHand(p1, p2, 5);
        monitorGameState(scanner, p1, p2);
    }

    void monitorGameState(Scanner scanner, PlayerHand p1, PlayerHand p2) {
        String userInput = ""; //So this needs to change
        while (continueGame) {
            display.menuPrompt(); //Don't need this anymore
            userInput = scanner.next().toUpperCase(Locale.ROOT); //Or this....
            if(userInput.equals("Q")) {
                display.gameOverPrompt();
                continueGame = false;
            } else if(userInput.equals("L")) {
                display.displayPlayerHand(p1.toString()); //need to update this....DONE
            } else if(userInput.equals("P")) {
                battle(p1, p2);
            }
        }
    }

    void battle(PlayerHand player1, PlayerHand player2) {
        if(player1.outOfCards()){
            display.gameWinnerPrompt(player2.getName());
            continueGame = false;
        } else if (player2.outOfCards()) {
            display.gameWinnerPrompt(player1.getName());
            continueGame = false;
        } else {
            player1.playCard();
            display.displayPlayerCard(player1.getName(), player1.retrieveCardInPlay().toString());
            player2.playCard();
            display.displayPlayerCard(player2.getName(), player2.retrieveCardInPlay().toString());
            if (player1.retrieveCardInPlay().compareTo(player2.retrieveCardInPlay()) > 0) {
                winnerTakeSpoils(player1, player2);
            } else if (player1.retrieveCardInPlay().compareTo(player2.retrieveCardInPlay()) < 0){
                winnerTakeSpoils(player2, player1);
            } else {
                declareWar(player1, player2);
            }
        }
    }

    void declareWar(PlayerHand player1, PlayerHand player2) {
        if (player1.getCards().size() < 4) {
            display.gameWinnerPrompt(player2.getName());
            continueGame = false;
        } else if (player2.getCards().size() < 4) {
            display.gameWinnerPrompt(player1.getName());
            continueGame = false;
        } else {
            for (int i = 0; i < 4; i++) {
                player1.playCard();
                player2.playCard();
            }
            battle(player1, player2);
        }
    }

    void winnerTakeSpoils(PlayerHand winner, PlayerHand loser) {
        display.matchWinnerPrompt(winner.getName());
        ArrayList<Card> allCardsInPlay = new ArrayList<>();
        allCardsInPlay.addAll(winner.getCardsInPlay());
        allCardsInPlay.addAll(loser.getCardsInPlay());
        winner.receiveCards(allCardsInPlay);
        winner.clearCardsInPlay();
        loser.clearCardsInPlay();
        allCardsInPlay = null;
    }

}
