package com.example.wargamegui.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>(52);
    private ArrayList<Integer> uniqueNumbers = new ArrayList<>();

    public Deck() {createDeck();}

    void createDeck() {
        Random random = new Random();
        while (cards.size() != 52) {
            int num = random.nextInt(52);
            if (!uniqueNumbers.contains(num)) {
                uniqueNumbers.add(num);
                //System.out.println(num);
                cards.add(new Card(num));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffleDeck() {
        //determine size of array
        int limit = this.cards.size();
        //create random numbers and new cards array lists
        ArrayList<Integer> randomNumbers = new ArrayList<>(limit);
        ArrayList<Card> newCards = new ArrayList<>(limit);
        //create an array of random numbers, each unique
        Random random = new Random();
        while (randomNumbers.size() != limit) {
            int num = random.nextInt(limit);
            if (!randomNumbers.contains(num)) {
                randomNumbers.add(num);
            }
        }
        //add the cards from original deck to the new cards array list
        for (Integer randomNumber : randomNumbers) {
            newCards.add(this.cards.get(randomNumber));
        }
        //assign new cards array list into the card field
        this.cards = newCards;
        //delete the new cards array list
        newCards = null;
    }

    public void dealHand(PlayerHand player1Hand, PlayerHand player2Hand, int numOfCards) {
        while (numOfCards > 0) {
            numOfCards--;
            player1Hand.receiveCards(cards.get(0));
            cards.remove(0);
            player2Hand.receiveCards(cards.get(0));
            cards.remove(0);
        }
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(x -> x.toString())
                .collect(Collectors.joining("\n"));
    }
}

