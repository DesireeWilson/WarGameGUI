package com.example.wargamegui.controller;

import com.example.wargamegui.model.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Random;

public class WarGameController2 {
    @FXML private Label welcomeText;
    @FXML private ImageView imageViewArea;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void displayCard() {
        Random random = new Random();
        int num = random.nextInt(52);
        Card card = new Card(num);
        System.out.println(card);
        imageViewArea.setImage(card.getImage());
    }
}