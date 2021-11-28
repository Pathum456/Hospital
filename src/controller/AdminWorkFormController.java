package controller;

import P_Controller.PatientController;
import P_Controller.PharmacyController;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import module.Pharmacy;
import views.tm.PharmacyTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdminWorkFormController {
    public TableView view_Pharmacy_Tbl;
    public TableColumn ph_Id_col;
    public TableColumn name_col;
    public TableColumn contact_col;
    public TableColumn address_Col;
    public JFXTextField name_Txt;
    public JFXTextField contact_txt;
    public JFXTextField address_Txt;

    public JFXTextField search_Id_Txt;
    public Label date_lbl;
    public Label time_lbl;
    public Label ph_Id_lbl;
    public AnchorPane load_context;
    public JFXButton pharmacyBtn;
    public JFXButton patientBtn;
    public JFXButton systemBtn;
    public JFXButton loginBtn;
    public JFXButton wardBtn;
    public JFXButton appointmentBtn;
    public JFXButton doctorBtn;
    int selectedRow=-1;
    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime ( );
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        //List temp =new PharmacyController ().getAllPharmacy();
        ph_Id_col.setCellValueFactory (new PropertyValueFactory<> ("ph_Id"));
        name_col.setCellValueFactory (new PropertyValueFactory<> ("p_Name"));
        contact_col.setCellValueFactory (new PropertyValueFactory<> ("p_Contact"));
        address_Col.setCellValueFactory (new PropertyValueFactory<> ("p_Address"));
        setView_Pharmacy_TblToTable (new PharmacyController ( ).getAllPharmacy ( ));
view_Pharmacy_Tbl.getSelectionModel ().selectedIndexProperty ().addListener ((observable, oldValue, newValue) -> {
    selectedRow = ( int ) newValue;
});
        System.out.println (selectedRow );
        ph_Id_lbl.setText (new PharmacyController ( ).setPhIds ( ));
    }

    private void setView_Pharmacy_TblToTable(ArrayList<Pharmacy> pharmacies) {
        ObservableList<PharmacyTm> obList = FXCollections.observableArrayList ( );
        pharmacies.forEach (e -> {
            obList.add (
                    new PharmacyTm (e.getPh_Id ( ), e.getName ( ), e.getContact ( ), e.getAddress ( )));
        });
        view_Pharmacy_Tbl.setItems (obList);
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date ( );
        SimpleDateFormat f = new SimpleDateFormat ("yyyy-MM-dd");
        date_lbl.setText (f.format (date));

        // load Time
        Timeline time = new Timeline (new KeyFrame (Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now ( );
            time_lbl.setText (
                    currentTime.getHour ( ) + " : " + currentTime.getMinute ( ) +
                            " : " + currentTime.getSecond ( )
            );
        }),
                new KeyFrame (Duration.seconds (1))
        );
        time.setCycleCount (Animation.INDEFINITE);
        time.play ( );
    }

    void search_Pharmacy() throws SQLException, ClassNotFoundException {
        String id = search_Id_Txt.getText ( );
        Pharmacy ph = new
                PharmacyController ( ).getPharmacy (id);
        if ((ph == null)) {
            search_Id_Txt.clear ( );
            clearTXT ( );
            new Alert (Alert.AlertType.WARNING, "No Pharmacy for this ID..").showAndWait ( );
        } else {
            ph_Id_lbl.setText (ph.getPh_Id ( ));
            name_Txt.setText (ph.getName ( ));
            contact_txt.setText (ph.getContact ( ));
            address_Txt.setText (ph.getAddress ( ));

        }
    }

    void clearTXT() {
        search_Id_Txt.clear ( );
        name_Txt.clear ( );
        address_Txt.clear ( );
        contact_txt.clear ( );
    }

    public void add_Pharmacy_oN_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!ph_Id_lbl.getText ( ).equals ("") && !name_Txt.getText ( ).equals ("") && !contact_txt.getText ( ).equals ("") && !address_Txt.getText ( ).equals ("")) {
            Pharmacy ph = new Pharmacy (
                    ph_Id_lbl.getText ( ),
                    name_Txt.getText ( ),
                    contact_txt.getText ( ),
                    address_Txt.getText ( )
            );
            if (new PharmacyController ( ).savePharmacy (ph)) {
                new Alert (Alert.AlertType.CONFIRMATION, "Saved..").showAndWait ( );
                ph_Id_lbl.setText (new PharmacyController ( ).setPhIds ( ));
                setView_Pharmacy_TblToTable (new PharmacyController ( ).getAllPharmacy ( ));
            } else {
                new Alert (Alert.AlertType.CONFIRMATION, "Try Again..").showAndWait ( );
            }
        } else {

            new Alert (Alert.AlertType.INFORMATION, "Please fill all field..").showAndWait ( );
        }
        clearTXT ( );
        System.out.println (ph_Id_lbl.getText ( ));


    }

    public void delete_Pharmacy_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        search_Pharmacy ( );
        if (new PharmacyController ().deletePharmacy (ph_Id_lbl.getText ( ))) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clearTXT ( );
        } else {
            clearTXT ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
        view_Pharmacy_Tbl.refresh ();
    }

    public void search_Pharmacy_On_Key_Released(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            search_Pharmacy ( );
        }
    }
    void setAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");

        alert.setHeaderText("Welcome !, to NEW LIFE HOSPITAL 'Admin' Part.");
        alert.setContentText("You Will have a great Day !");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });

    }


    public void pharmacy_On_Action(ActionEvent actionEvent) throws IOException {

        URL resource = getClass ( ).getResource ("../views/PharmacyForm.fxml");
        Parent load = FXMLLoader.load (resource);
        load_context.getChildren ( ).clear ( );
        load_context.getChildren ( ).add (load);
        doctorBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointmentBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        wardBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        patientBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        systemBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: green");
        loginBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");


    }

    public void Doctor_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ManageDoctorForm.fxml");
        Parent load = FXMLLoader.load (resource);
        load_context.getChildren ( ).clear ( );
        load_context.getChildren ( ).add (load);
        doctorBtn.getParent ( ).setStyle ("-fx-background-color: green");
        appointmentBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        wardBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        patientBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        systemBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: skyblue");
        loginBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void appointmnet_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ManageAppointmentForm.fxml");
        Parent load = FXMLLoader.load (resource);
        load_context.getChildren ( ).clear ( );
        load_context.getChildren ( ).add (load);
        doctorBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointmentBtn.getParent ( ).setStyle ("-fx-background-color: green");
        wardBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        patientBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        systemBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        loginBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void ward_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ManageWardForm.fxml");
        Parent load = FXMLLoader.load (resource);
        load_context.getChildren ( ).clear ( );
        load_context.getChildren ( ).add (load);
        doctorBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointmentBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        wardBtn.getParent ( ).setStyle ("-fx-background-color: green");
        patientBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        systemBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        loginBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void patient_Report_On_action(ActionEvent actionEvent) throws IOException {

        URL resource = getClass ( ).getResource ("../views/PatientReportForm.fxml");
        Parent load = FXMLLoader.load (resource);
        load_context.getChildren ( ).clear ( );
        load_context.getChildren ( ).add (load);
        doctorBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointmentBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        wardBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        patientBtn.getParent ( ).setStyle ("-fx-background-color: green");
        systemBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        loginBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }

    public void system_Reports_On_Action(ActionEvent actionEvent) throws IOException {
        /*URL resource = getClass ( ).getResource ("../views/SystemReportForm.fxml");
        Parent load = FXMLLoader.load (resource);
        load_context.getChildren ( ).clear ( );
        load_context.getChildren ( ).add (load);*/
        doctorBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointmentBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        wardBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        patientBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        systemBtn.getParent ( ).setStyle ("-fx-background-color: green");
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        loginBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");

    }
    public void getReport(){

    }

    public void login_Details_On_Action(ActionEvent actionEvent) {
        doctorBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        appointmentBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        wardBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        patientBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        systemBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        pharmacyBtn.getParent ( ).setStyle ("-fx-background-color: skyBlue");
        loginBtn.getParent ( ).setStyle ("-fx-background-color: green");

    }


}
