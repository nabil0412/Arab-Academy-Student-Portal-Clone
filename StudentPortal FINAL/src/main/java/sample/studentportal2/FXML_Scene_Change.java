package sample.studentportal2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXML_Scene_Change implements FXML_Factory {

    private String fxmlPath = "";
    public FXML_Scene_Change(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    @Override
    public Parent createComponent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            System.out.println("Loaded successfully " + fxmlPath);
            return loader.load();

        } catch (IOException e) {
            System.out.println("Failed to load FXML component: " + fxmlPath);
            throw new RuntimeException("Failed to load FXML: " + fxmlPath);
        }
    }

    @Override
    public  void switchView(ActionEvent event) throws IOException {

        FXML_Scene_Change fact= new FXML_Scene_Change(fxmlPath);
        Parent root =  fact.createComponent();
        System.out.println("Created component successfully ");

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        currentStage.close();
        stage.show();
    }




}
