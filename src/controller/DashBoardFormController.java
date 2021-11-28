package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {


    public void initialize() {

    }

    public void continue_on_Action(ActionEvent actionEvent) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader (getClass ( ).getResource ("../views/ContinueForm.fxml"));
        Parent parent = loader.load ( );

        Scene scene = new Scene (parent);
        Stage stage = new Stage ( );
        stage.setScene (scene);
        stage.setTitle ("Admin OR Reception");
        stage.show ( );

    }
}
