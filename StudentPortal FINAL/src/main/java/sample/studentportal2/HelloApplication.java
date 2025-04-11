package sample.studentportal2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 420, 390);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println("Couldn't load");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}