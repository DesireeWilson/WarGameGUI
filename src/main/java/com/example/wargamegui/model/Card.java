package com.example.wargamegui.model;

import javafx.scene.image.Image;

public class Card implements Comparable<Card> {
    static final Image imageBack = new Image("file:src/images/backOfCard.png");
    Suit suit;
    Rank rank;
    Image image;

    Card() {};
    public Card(int num) {
        suit = assignSuit(num);
        rank = assignRank(num);
        image = assignImage(num);
    }

    public static Image getImageBack() {
        return imageBack;
    }

    Suit assignSuit(int num) {
        return (num <= 12 && num >= 0 ) ? Suit.CLUB
                : (num <= 25 && num >= 13) ? Suit.DIAMOND
                : (num <= 38 && num >= 26) ? Suit.HEART
                : Suit.SPADE;
    }

    // I don't like this "assignRank" code:
    Rank assignRank(int num) {
        final int value = (num % 13);
        switch (value) {
            case 0:
                return Rank.TWO;
            case 1:
                return Rank.THREE;
            case 2:
                return Rank.FOUR;
            case 3:
                return Rank.FIVE;
            case 4:
                return Rank.SIX;
            case 5:
                return Rank.SEVEN;
            case 6:
                return Rank.EIGHT;
            case 7:
                return Rank.NINE;
            case 8:
                return Rank.TEN;
            case 9:
                return Rank.JACK;
            case 10:
                return Rank.QUEEN;
            case 11:
                return Rank.KING;
            case 12:
                return Rank.ACE;
        };
        return Rank.TWO;
    }

    Image assignImage(int num) {
        Suit suit = assignSuit(num);
        Rank rank = assignRank(num);
        String fileName = rank.name() + "_of_" + suit.toString() + ".png";
        System.out.println("src/main/resources/com/example/wargamegui/images/"+fileName);
        return new Image("file:"+"src/main/resources/com/example/wargamegui/images/"+fileName);
    }

    public Image getImage() {
        System.out.println(image.getUrl());
        return image;
    }

    @Override
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(this.rank.ordinal(), otherCard.rank.ordinal());
    }
}

