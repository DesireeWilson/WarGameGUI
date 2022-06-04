package com.example.wargamegui.controller;

import com.example.wargamegui.model.Card;
import com.example.wargamegui.model.Deck;
import com.example.wargamegui.model.PlayerHand;
import com.example.wargamegui.view.Display;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class WarGameController implements Initializable {
    @FXML private Label computerCardCounterLabel;
    @FXML private Label playerCardCounterLabel;
    @FXML private Label messageArea;
    @FXML private ImageView computerCardDeckImageView = new ImageView(Card.getImageBack());
    @FXML private ImageView playerCardDeckImageView = new ImageView(Card.getImageBack());
    @FXML private ImageView computerCardInPlayImageView;
    @FXML private ImageView playerCardInPlayImageView;
    @FXML private ImageView playerCardInHandImageView1;
    @FXML private ImageView playerCardInHandImageView2;
    @FXML private ImageView playerCardInHandImageView3;
    @FXML private ImageView playerCardInHandImageView4;
    @FXML private ImageView playerCardInHandImageView5;
    @FXML private Button peakButton;
    @FXML private Button drawCardButton;
    private Display display;
    private boolean continueGame = true;
    private PlayerHand playerHand;
    private PlayerHand computerHand;
    private Deck deck;
    private Stage stage;
    private Scene scene;
    private Parent root;

//    public WarGameController(Display display, Deck deck, PlayerHand playerOne, PlayerHand playerTwo) {
//        this.display = display;
//        this.deck = deck;
//        playerHand = playerOne;
//        computerHand = playerTwo;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.display = new Display();
        this.playerHand = new PlayerHand("PlayerOne");
        this.computerHand = new PlayerHand("Computer");
        this.deck = new Deck();

        deck.shuffleDeck();
        deck.dealHand(playerHand, computerHand, 5);
        playerCardCounterLabel.setText("Cards: " + playerHand.getCards().size());
        computerCardCounterLabel.setText("Cards: " + computerHand.getCards().size());
    }

    @FXML
    public void displayCardsInHand() {
        //playerCardInHandImageView1.setImage(playerHand.getCardsInHand().get(0).getImage());
        playerCardInHandImageView2.setImage(playerHand.getCardsInHand().get(1).getImage());
        playerCardInHandImageView3.setImage(playerHand.getCardsInHand().get(2).getImage());
        playerCardInHandImageView4.setImage(playerHand.getCardsInHand().get(3).getImage());
        playerCardInHandImageView5.setImage(playerHand.getCardsInHand().get(4).getImage());
        messageArea.setText("I see your hand!");
    }

    @FXML
    public void drawCard() {
//        Card playerCardInPlay = playerHand.playCard();
//        Card computerCardInPlay = computerHand.playCard();
//
//        playerCardInPlayImageView.setImage(playerCardInPlay.getImage());
//        computerCardInPlayImageView.setImage(computerCardInPlay.getImage());
//
//        StringBuilder message = new StringBuilder();
//        if(playerCardInPlay.compareTo(computerCardInPlay) == 0) {
//            message.append("Uh-oh! It's a draw!");
//        } else if (playerCardInPlay.compareTo(computerCardInPlay) < 0) {
//            message.append(computerHand.getName()).append(" won that match!");
//        } else {
//            message.append(playerHand.getName()).append(" won that match!");
//        }
//        messageArea.setText(message.toString());
        monitorGameState();
    }

    void monitorGameState() {
        boolean continueCurrentTurn = true;
        while (continueGame && continueCurrentTurn) {
            battle(playerHand, computerHand);
            if(continueGame == false) {
                messageArea.setText(display.gameOverPrompt());
                //need to stop the game though......
                System.out.println("Please do NOT pres \"Draw Card\" button. :( ");
            }
            continueCurrentTurn = false;
        }
        computerCardCounterLabel.setText("Cards: " + computerHand.getCards().size());
        playerCardCounterLabel.setText("Cards: " + playerHand.getCards().size());
    }

    void battle(PlayerHand player1, PlayerHand player2) {
        if(player1.outOfCards()){
            messageArea.setText(display.gameWinnerPrompt(player2.getName()));
            continueGame = false;
        } else if (player2.outOfCards()) {
            messageArea.setText(display.gameWinnerPrompt(player1.getName()));
            continueGame = false;
        } else {
            Card playerCardInPlay = playerHand.playCard();
            Card computerCardInPlay = computerHand.playCard();

            playerCardInPlayImageView.setImage(playerCardInPlay.getImage());
            computerCardInPlayImageView.setImage(computerCardInPlay.getImage());

            if (playerCardInPlay.compareTo(computerCardInPlay) > 0) {
                winnerTakeSpoils(playerHand, computerHand);
            } else if (playerCardInPlay.compareTo(computerCardInPlay) < 0){
                winnerTakeSpoils(computerHand, playerHand);
            } else {
                declareWar(playerHand, computerHand);
            }
        }
    }

    void winnerTakeSpoils(PlayerHand winner, PlayerHand loser) {
        messageArea.setText(display.matchWinnerPrompt(winner.getName()));
        ArrayList<Card> allCardsInPlay = new ArrayList<>();
        allCardsInPlay.addAll(winner.getCardsInPlay());
        allCardsInPlay.addAll(loser.getCardsInPlay());
        winner.receiveCards(allCardsInPlay);
        winner.clearCardsInPlay();
        loser.clearCardsInPlay();
        allCardsInPlay = null;
    }

    void declareWar(PlayerHand player1, PlayerHand player2) {
        if (player1.getCards().size() < 4) {
            messageArea.setText(display.gameWinnerPrompt(player2.getName()));
            continueGame = false;
        } else if (player2.getCards().size() < 4) {
            messageArea.setText(display.gameWinnerPrompt(player1.getName()));
            continueGame = false;
        } else {
            for (int i = 0; i < 4; i++) {
                player1.playCard();
                player2.playCard();
            }
            battle(player1, player2);
        }
    }
}
