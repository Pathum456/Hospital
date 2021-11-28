package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionLoginFormController {
    public AnchorPane reception_Login_Context;
    public JFXTextField lbl_Reception_User_Name;
    public JFXPasswordField reception_Password_field;


    public void reception_Login_Btn_On_Action(ActionEvent actionEvent) throws IOException {
        setAlert();
        Stage stage = ( Stage ) reception_Login_Context.getScene ( ).getWindow ( );
        stage.close ( );
        FXMLLoader loader = new FXMLLoader (getClass ( ).getResource ("../views/ReceptionWorkForm.fxml"));
        Parent parent = loader.load ( );
        Scene scene = new Scene (parent);
        stage = new Stage ( );
        stage.setScene (scene);
        stage.setTitle ("Welcome to Reception Form");
        //stage.setMaximized (true);
        System.out.println (stage.getMaxHeight ( ) + stage.getMaxWidth ( ));
        stage.show ( );

    }
    void setAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");

        alert.setHeaderText("Welcome !, to NEW LIFE HOSPITAL Reception Part.");
        alert.setContentText("Today , You Will have a great Day !");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });

    }

    public void back_From_Reception_On_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = ( Stage ) reception_Login_Context.getScene ( ).getWindow ( );
        stage.close ( );

        FXMLLoader loader = new FXMLLoader (getClass ( ).getResource ("../views/ContinueForm.fxml"));
        Parent parent = loader.load ( );
        Scene scene = new Scene (parent);
        stage = new Stage ( );
        stage.setScene (scene);
        stage.setTitle ("Admin OR Reception");
        stage.show ( );

    }


}
