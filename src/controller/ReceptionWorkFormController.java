package controller;

import P_Controller.PatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import module.Patient;
import views.tm.PatientDetailsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ReceptionWorkFormController {


    public AnchorPane reception_Login_Context;
    public Label lbl_Reception_Name;
    public Label lbl_Date;
    public AnchorPane receptionWorkContext;
    public JFXTextField txt_P_Nic;
    public JFXTextField txt_P_Name;
    public JFXTextField txt_P_Contact;
    public JFXTextField txt_P_Address;
    public JFXButton update_Patient_Btn;
    public TableView<PatientDetailsTm> tbl_Patient_Details_View;
    public TableColumn p_Id_col;
    public TableColumn p_Name_Col;
    public TableColumn p_Nic_Col;
    public TableColumn p_Contact_Col;
    public TableColumn p_Address_Col;
    public Label lbl_Time;
    public Label lbl_P_ID;
    public JFXButton delete_Patient_Btn;
    public JFXButton p_Details_Btn;
    public JFXButton p_Registration_Btn;
    public JFXButton p_Admit_Btn;
    public JFXButton doctor_Btn;
    public JFXButton appointment_Btn;
    public JFXButton ward_btn;
    public JFXButton payment_Btn;
    int selectedRow = -1;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime ( );
        p_Details_Btn.getParent ( ).setStyle ("-fx-background-color: green");


        p_Id_col.setCellValueFactory (new PropertyValueFactory<> ("p_Id"));
        p_Name_Col.setCellValueFactory (new PropertyValueFactory<> ("p_Name"));
        p_Nic_Col.setCellValueFactory (new PropertyValueFactory<> ("p_Nic"));
        p_Contact_Col.setCellValueFactory (new PropertyValueFactory<> ("p_Contact"));
        p_Address_Col.setCellValueFactory (new PropertyValueFactory<> ("p_Address"));
        setPatientToTable (new PatientController ( ).getAllPatient ( ));
        tbl_Patient_Details_View.getSelectionModel ( ).selectedIndexProperty ( ).addListener ((observable, oldValue, newValue) -> {
            selectedRow = ( int ) newValue;
        });
    }

    private void setPatientToTable(ArrayList<Patient> patients) {
        ObservableList<PatientDetailsTm> obList = FXCollections.observableArrayList ( );
        patients.forEach (e -> {
            obList.add (
                    new PatientDetailsTm (e.getP_Id ( ), e.getP_Name ( ), e.getP_Nic ( ), e.getP_Contact ( ), e.getP_Address ( )));
        });
        tbl_Patient_Details_View.setItems (obList);
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date ( );
        SimpleDateFormat f = new SimpleDateFormat ("yyyy-MM-dd");
        lbl_Date.setText (f.format (date));

        // load Time
        Timeline time = new Timeline (new KeyFrame (Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now ( );
            lbl_Time.setText (
                    currentTime.getHour ( ) + " : " + currentTime.getMinute ( ) +
                            " : " + currentTime.getSecond ( )
            );
        }),
                new KeyFrame (Duration.seconds (1))
        );
        time.setCycleCount (Animation.INDEFINITE);
        time.play ( );
    }

    public void search_Patient_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ReceptionWorkForm.fxml");
        Parent load = FXMLLoader.load (resource);
        reception_Login_Context.getChildren ( ).clear ( );
        reception_Login_Context.getChildren ( ).add (load);
        tbl_Patient_Details_View.refresh ( );
    }

    void setStyles() {
        if (txt_P_Nic.getText ( ).equals ("")) {
            txt_P_Nic.getParent ( ).setStyle ("-fx-border-color: red");


        } else {
            txt_P_Nic.getParent ( ).setStyle ("-fx-border-color: green");
        }
        if (txt_P_Name.getText ( ).equals ("")) {
            txt_P_Name.getParent ( ).setStyle ("-fx-border-color: green");
        } else {
            txt_P_Name.getParent ( ).setStyle ("-fx-border-color: green");
        }
        if (txt_P_Address.getText ( ).equals ("")) {
            txt_P_Address.getParent ( ).setStyle ("-fx-border-color: red");
        } else {
            txt_P_Address.getParent ( ).setStyle ("-fx-border-color: green");
        }
        if (txt_P_Contact.getText ( ).equals ("")) {
            txt_P_Contact.getParent ( ).setStyle ("-fx-border-color: red");
        } else {
            txt_P_Contact.getParent ( ).setStyle ("-fx-border-color: green");
        }

    }


    public void update_Patient_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (!txt_P_Name.getText ( ).equals ("") && !txt_P_Address.getText ( ).equals ("") && !lbl_P_ID.getText ( ).equals ("") && !txt_P_Contact.getText ( ).equals ("") && !txt_P_Nic.getText ( ).equals ("")) {
            Patient c1 = new Patient (
                    lbl_P_ID.getText ( ), txt_P_Name.getText ( ), txt_P_Nic.getText ( ), txt_P_Contact.getText ( ), txt_P_Address.getText ( )

            );

            if (new PatientController ( ).updatePatient (c1)) {
                new Alert (Alert.AlertType.CONFIRMATION, "Updated..").showAndWait ( );
                update_Patient_Btn.setDisable (false);
                update_Patient_Btn.getParent ().setStyle ("-fx-background-color: green");
                clear_Details ( );
            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
            }
        } else {
            new Alert (Alert.AlertType.WARNING, "Please fill all fields and try again..").show ( );
        }
        update_Patient_Btn.getParent ().setStyle ("-fx-background-color: #2563EB");

    }

    void clear_Details() {
        txt_P_Nic.clear ( );
        lbl_P_ID.setText (null);
        txt_P_Name.clear ( );
        txt_P_Contact.clear ( );
        txt_P_Address.clear ( );
        tbl_Patient_Details_View.refresh ( );
    }

    void search_Patient() throws SQLException, ClassNotFoundException {

        String nic = txt_P_Nic.getText ( );
        Patient p1 = new PatientController ( ).getPatient (nic);

        if (p1 == null) {
            txt_P_Nic.clear ( );
            clear_Details ( );
            new Alert (Alert.AlertType.WARNING, "No Patient for this NIc...").show ( );

        } else {
            lbl_P_ID.setText (p1.getP_Id ( ));
            txt_P_Name.setText (p1.getP_Name ( ));
            txt_P_Contact.setText (String.valueOf (p1.getP_Contact ( )));
            txt_P_Address.setText (p1.getP_Address ( ));
            update_Patient_Btn.setDisable (false);
            delete_Patient_Btn.setDisable (false);
        }
        //search_Patient_Btn.requestFocus();

    }




    /* public Patient getItemForShow(String P_ID) throws SQLException, ClassNotFoundException {
         ResultSet rst = DbConnection.getInstance().getConnection().
                 prepareStatement("SELECT * FROM patient WHERE P_ID='" + P_ID + "'").
                 executeQuery();
         if (rst.next()){
             return new Patient (
                     rst.getString(1),
                     rst.getString(2),
                     rst.getString(3),
                     rst.getString(4),
                     rst.getString(5)
             );
         }else{
             return null;
         }
     }
 */
    public void delete_Patient_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        search_Patient ( );
        if (new PatientController ( ).deleteCustomer (lbl_P_ID.getText ( ))) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clear_Details ( );
            delete_Patient_Btn.getParent ().setStyle ( "-fx-background-color: green");
        } else {
            clear_Details ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
        delete_Patient_Btn.getParent ().setStyle ( "-fx-background-color: #2563EB");
    }

    public void go_To_Search_Btn_Key_Pressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            search_Patient ( );
        }
    }

    public void admit_Patient_Btn(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/AdmitPatientForm.fxml");
        Parent load = FXMLLoader.load (resource);
        receptionWorkContext.getChildren ( ).clear ( );
        receptionWorkContext.getChildren ( ).add (load);
        p_Registration_Btn.getParent ( ).setStyle ("-fx-background-color: skyblue");
        p_Details_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Admit_Btn.getParent ( ).setStyle ("-fx-background-color: green");
        doctor_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        ward_btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        payment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void availableDoctors_Btn(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/AvailableDoctorsForm.fxml");
        Parent load = FXMLLoader.load (resource);
        receptionWorkContext.getChildren ( ).clear ( );
        receptionWorkContext.getChildren ( ).add (load);
        p_Registration_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Details_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Admit_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        doctor_Btn.getParent ( ).setStyle ("-fx-background-color: green");
        appointment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        ward_btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        payment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void sheduleAppoimnet_Btn(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/SheduleAppoimentForm.fxml");
        Parent load = FXMLLoader.load (resource);
        receptionWorkContext.getChildren ( ).clear ( );
        receptionWorkContext.getChildren ( ).add (load);
        p_Registration_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Details_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Admit_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        doctor_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointment_Btn.getParent ( ).setStyle ("-fx-background-color: green");
        ward_btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        payment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void view_Ward_Btn(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ViewWardForm.fxml");
        Parent load = FXMLLoader.load (resource);
        receptionWorkContext.getChildren ( ).clear ( );
        receptionWorkContext.getChildren ( ).add (load);
        p_Registration_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Details_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Admit_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        doctor_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        ward_btn.getParent ( ).setStyle ("-fx-background-color: green");
        payment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    void setAlert() {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle ("Message Here...");

        alert.setHeaderText ("Welcome !, to NEW LIFE HOSPITAL Reception Part.");
        alert.setContentText ("You Will have a great Day !");
        alert.showAndWait ( ).ifPresent (rs -> {
            if (rs == ButtonType.OK) {
                System.out.println ("Pressed OK.");
            }
        });

    }

    public void make_Payment_Btn(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/MakePaymentForm.fxml");
        Parent load = FXMLLoader.load (resource);
        receptionWorkContext.getChildren ( ).clear ( );
        receptionWorkContext.getChildren ( ).add (load);
        p_Registration_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Details_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Admit_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        doctor_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        ward_btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        payment_Btn.getParent ( ).setStyle ("-fx-background-color: green");
    }

    public void isClicked(MouseEvent mouseEvent) {
        p_Registration_Btn.getParent ( ).setStyle ("-fx-background-color: green");
        p_Details_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        p_Admit_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        doctor_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        ward_btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        payment_Btn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void isClickedDelete(MouseEvent mouseEvent) {
//delete_Patient_Btn.getParent ().setStyle ( "-fx-background-color: green");
    }

    public void isClickedUpdate(MouseEvent mouseEvent) {
        /*update_Patient_Btn.getParent ().setStyle ("-fx-background-color: green");*/
    }

    public void save_Patient_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/PatientRegistrationForm.fxml");
        Parent load = FXMLLoader.load (resource);
        receptionWorkContext.getChildren ( ).clear ( );
        receptionWorkContext.getChildren ( ).add (load);

    }






    /*public void delete_Patient_Btn_Key_Released(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
            if (keyEvent.getCode() == KeyCode.D) {
                String nic = txt_P_Nic.getText ( );
                Patient p1 = new PatientController ( ).getPatient (nic);

                if (p1 == null) {
                    txt_P_Nic.clear ( );

                    new Alert (Alert.AlertType.WARNING, "No Patient for this NIc...").showAndWait ( );

                }else {

                }
            }
    }*/
}
