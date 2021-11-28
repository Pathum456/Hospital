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

public class AdminLoginFormController {
    public AnchorPane admin_Login_Context;
    public JFXTextField lbl_Admin_User_Name;
    public JFXPasswordField admin_Password_field;

    /*String adminName = lbl_Admin_User_Name.getText ( );
    String adminPassword = admin_Password_field.getText ( );*/
    void setAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");

        alert.setHeaderText("Welcome !, to NEW LIFE HOSPITAL 'Admin' Part.");
        alert.setContentText("You Will have a great Day !");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
                Stage stage = ( Stage ) admin_Login_Context.getScene ( ).getWindow ( );
                stage.close ( );
                FXMLLoader loader = new FXMLLoader (getClass ( ).getResource ("../views/AdminWorkForm.fxml"));
                Parent parent = null;
                try {
                    parent = loader.load ( );
                } catch (IOException e) {
                    e.printStackTrace ( );
                }


                Scene scene = new Scene (parent);
                stage = new Stage ( );
                stage.setScene (scene);
                stage.setTitle ("Welcome to Admin Form");
                stage.show ( );
            }if(rs == ButtonType.CLOSE){

            }
        });

    }
    public void admin_Login_Btn_On_Action(ActionEvent actionEvent) throws IOException {
        setAlert();
            /*Stage stage = ( Stage ) admin_Login_Context.getScene ( ).getWindow ( );
            stage.close ( );
            FXMLLoader loader = new FXMLLoader (getClass ( ).getResource ("../views/AdminWorkForm.fxml"));
            Parent parent = loader.load ( );


            Scene scene = new Scene (parent);
            stage = new Stage ( );
            stage.setScene (scene);
            stage.setTitle ("Welcome to Admin Form");
            stage.show ( );*/
        }


    public void back_From_Admin_On_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = ( Stage ) admin_Login_Context.getScene ( ).getWindow ( );
        stage.close ( );
       /* URL resource = getClass ( ).getResource ("../views/ContinueForm.fxml");
        Parent load = FXMLLoader.load (resource);
        admin_Login_Context.getChildren ( ).clear ( );
        admin_Login_Context.getChildren ( ).add (load);*/
        FXMLLoader loader = new FXMLLoader (getClass ( ).getResource ("../views/ContinueForm.fxml"));
        Parent parent = loader.load ( );
        Scene scene = new Scene (parent);
        stage = new Stage ( );
        stage.setScene (scene);
        stage.setTitle ("Admin OR Reception");
        stage.show ( );

    }
}
