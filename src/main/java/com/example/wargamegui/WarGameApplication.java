package com.example.wargamegui;

import com.example.wargamegui.controller.WarGameController;
import com.example.wargamegui.model.Deck;
import com.example.wargamegui.model.PlayerHand;
import com.example.wargamegui.view.Display;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class WarGameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WarGameApplication.class.getResource("warGameGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("I Declare War!");
        stage.getIcons().add(new Image("file:src/main/resources/com/example/wargamegui/images/backOfCard.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        Display display = new Display();
//        Deck deck = new Deck();
//        Scanner scanner = new Scanner(System.in);
//        PlayerHand playerOne = new PlayerHand("PlayerOne");
//        PlayerHand playerTwo = new PlayerHand("Computer");
//        WarGameController controller = new WarGameController(display, deck, playerOne, playerTwo);

        Application.launch();
    }
}