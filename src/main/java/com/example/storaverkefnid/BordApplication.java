package com.example.storaverkefnid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BordApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BordApplication.class.getResource("Velkominn-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 570);
        stage.setTitle("Slönguspil");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}