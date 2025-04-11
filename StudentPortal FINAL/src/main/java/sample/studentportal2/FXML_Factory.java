package sample.studentportal2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface FXML_Factory {
    public Parent createComponent();
    public  void switchView(ActionEvent event) throws IOException;
}
