package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ContinueFormController {
    public AnchorPane reception_Context;
    public AnchorPane admin_Context;
    public JFXButton admin_Btn;
    public JFXButton reception_Btn;

    public void continue_As_Admin_On_Action(ActionEvent actionEvent) throws IOException {
        reception_Btn.setDisable (true);

        URL resource = getClass ( ).getResource ("../views/AdminLoginForm.fxml");
        Parent load = FXMLLoader.load (resource);
        admin_Context.getChildren ( ).clear ( );
        admin_Context.getChildren ( ).add (load);
    }




    public void continue_As_Reception_On_Action(ActionEvent actionEvent) throws IOException {
        admin_Btn.setDisable (true);

        System.out.println (reception_Btn.isArmed ( ));
        URL resource = getClass ( ).getResource ("../views/ReceptionLoginForm.fxml");
        Parent load = FXMLLoader.load (resource);
        reception_Context.getChildren ( ).clear ( );
        reception_Context.getChildren ( ).add (load);

    }
}
