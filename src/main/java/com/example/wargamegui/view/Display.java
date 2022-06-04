package com.example.wargamegui.view;

public class Display {
    //messages to MessageArea
    public void menuPrompt() {
        System.out.println("Press [P] key to play a card\n" +
                "Press [L] key to see your cards\n" +
                "Press [Q] key to quit game");
    }

    public String gameWinnerPrompt(String playerName) {
        return playerName + " wins the game!!!!!";
    }

    public String matchWinnerPrompt(String playerName) {
        return playerName + " won that match!";
    }

    public String displayPlayerCard(String playerName, String card) {
        return playerName + ": " + card;
    }

    public String gameOverPrompt() {
        return "Game Over. Thanks for playing! :) ";
    }

    public String displayPlayerHand(String playerHand) {
        return playerHand;
    }



}
