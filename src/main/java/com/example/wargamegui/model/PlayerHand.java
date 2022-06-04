package com.example.wargamegui.model;

import java.util.ArrayList;

public class PlayerHand {
    String name;
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Card> cardsInPlay = new ArrayList<>();

    public PlayerHand() { }

    public PlayerHand(String name) {
        this.name = name;
    }

    public void receiveCards(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public void receiveCards(Card card) {
        this.cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card playCard() {
        Card card = this.cards.get(0);
        cardsInPlay.add(card);
        this.cards.remove(card);
        return card;
    }

    public Card retrieveCardInPlay() {
        int lastIndex = this.getCardsInPlay().size() - 1;
        return this.getCardsInPlay().get(lastIndex);
    }

    public void clearCardsInPlay() {
        cardsInPlay.clear();
    }

    public ArrayList<Card> getCardsInPlay() {
        return cardsInPlay;
    }
    public ArrayList<Card> getCardsInHand() {
        return cards;
    }

    public boolean outOfCards() {
        return this.getCards().size() == 0;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(this.getName());
        output.append(" -- Number of cards: ");
        output.append(this.cards.size());
        output.append("\n");
        for (int i = 0; i < this.cards.size() - 1; i++) {
            output.append(" ").append(this.cards.get(i).toString()).append(";");
        }
        output.append(" ").append(this.cards.get(this.cards.size()-1));
        return output.toString();
    }
}

