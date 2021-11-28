package controller;

import P_Controller.AppointmentController;
import P_Controller.DoctorController;
import P_Controller.PatientController;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import module.Appointment;
import module.Doctor;
import module.Patient;
import views.tm.AppointmentTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageAppointmentFormController {
    public TextField P_NIC_txt;
    public Label P_Id_lbl;
    public TextField P_Name_txt;
    public TextField P_Contact_txt;
    public TextField P_Reason_txt;
    public ComboBox d_ID_cmb;
    public Label d_Name_lbl;
    public Label d_Date_Lbl;
    public JFXButton set_Appointment_Btn;
    public Label d_Time_Lbl;
    public TableView appointment_View_tbl;
    public TableColumn p_ID_col;
    public TableColumn p_Name_col;
    public TableColumn contact_col;
    public TableColumn reason_col;
    public TableColumn d_Id_col;
    public TableColumn d_Name_col;
    public TableColumn date_col;
    public TableColumn time_Col;
    public JFXButton delete_Appointment_Btn;
    public void initialize() throws SQLException, ClassNotFoundException {
        List temp = new DoctorController ( ).getD_ids ( );
        d_ID_cmb.getItems ( ).addAll (temp);
        d_ID_cmb.getSelectionModel ( ).selectedItemProperty ( ).addListener ((observable, oldValue, newValue) -> {
            try {
                setD_Name (( String ) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace ( );
            } catch (ClassNotFoundException e) {
                e.printStackTrace ( );
            }
        });
        p_ID_col.setCellValueFactory (new PropertyValueFactory<> ("P_Id"));
        p_Name_col.setCellValueFactory (new PropertyValueFactory<> ("P_Name"));
        contact_col.setCellValueFactory (new PropertyValueFactory<> ("P_contact"));
        reason_col.setCellValueFactory (new PropertyValueFactory<> ("Reason"));
        d_Id_col.setCellValueFactory (new PropertyValueFactory<> ("D_Id"));
        d_Name_col.setCellValueFactory (new PropertyValueFactory<> ("D_Name"));
        date_col.setCellValueFactory (new PropertyValueFactory<> ("Date"));
        time_Col.setCellValueFactory (new PropertyValueFactory<> ("Time"));
        setAllAppointmentToTable (new AppointmentController ( ).getAllAppointment ( ));

    }
    private void setAllAppointmentToTable(ArrayList<Appointment> appointments) {
        ObservableList<AppointmentTm> obList = FXCollections.observableArrayList ( );
        appointments.forEach (a -> {
            obList.add (
                    new AppointmentTm (a.getP_Id ( ), a.getP_Name ( ), a.getP_Contact ( ), a.getReason ( ), a.getD_Id ( ), a.getD_Name ( ), a.getD_Name ( ), a.getDate ( ), a.getTime ( )));
        });
        appointment_View_tbl.setItems (obList);

    }
    private void setD_Name(String D_Id) throws SQLException, ClassNotFoundException {
        Doctor d1 = new DoctorController ( ).getDoctor (D_Id);
        if (d1 == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            d_Name_lbl.setText (d1.getD_Name ( ));
            d_Time_Lbl.setText (d1.getD_time ( ));
            d_Date_Lbl.setText (d1.getD_days ( ));
        }
    }

    public void search_Patient_On_Key_Reliease(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            search_Patient ( );
        }
    }

    public void set_appoiment_Btn_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!P_Id_lbl.getText ( ).equals ("") && !P_Name_txt.getText ( ).equals ("") && !P_NIC_txt.getText ( ).equals ("") && !P_Contact_txt.getText ( ).equals ("") && !P_Reason_txt.getText ( ).equals ("") && !d_ID_cmb.getValue ( ).equals ("") && !d_Name_lbl.getText ( ).equals ("") && !d_Date_Lbl.getText ( ).equals ("") && !d_Time_Lbl.getText ( ).equals ("")) {

            Appointment a = new Appointment (
                    P_Id_lbl.getText ( ),
                    P_Name_txt.getText ( ),
                    P_NIC_txt.getText ( ),
                    P_Contact_txt.getText ( ),
                    P_Reason_txt.getText ( ),
                    ( String ) d_ID_cmb.getValue ( ),
                    d_Name_lbl.getText ( ),
                    d_Date_Lbl.getText ( ),
                    d_Time_Lbl.getText ( )

            );
            System.out.println (d_ID_cmb.getValue ( ));
            if (new AppointmentController ( ).saveAppointment (a)) {

                new Alert (Alert.AlertType.CONFIRMATION, "Appointment OF " + P_Name_txt.getText ( ) + " Saved..").showAndWait ( );
                clear_Details ( );
                appointment_View_tbl.refresh ( );
                //refreshOnAction(actionEvent);
            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again..").show ( );
            }
        } else {
            System.out.println (d_ID_cmb.getValue ( ));
            new Alert (Alert.AlertType.INFORMATION, "Please fill all fields and try again..").show ( );
        }

        setAllAppointmentToTable (new AppointmentController ( ).getAllAppointment ( ));

    }
    void clear_Details() {
        P_Contact_txt.clear ( );
        P_Id_lbl.setText (null);
        P_Reason_txt.clear ( );
        P_Name_txt.clear ( );
        P_NIC_txt.clear ( );
        d_Name_lbl.setText (null);
    }

    void search_Patient() throws SQLException, ClassNotFoundException {

        String nic = P_NIC_txt.getText ( );
        Patient p1 = new PatientController ( ).getPatient (nic);
Appointment a = new AppointmentController ().getAppointment(nic);
        if (p1 == null) {
            P_NIC_txt.clear ( );
            clear_Details ( );
            new Alert (Alert.AlertType.WARNING, "No Patient for this NIc...Please Saved First...").show ( );

        } else {
            P_Id_lbl.setText (p1.getP_Id ( ));
            P_Name_txt.setText (p1.getP_Name ( ));
            P_Contact_txt.setText (String.valueOf (p1.getP_Contact ( )));
            //txt_P_Address.setText (p1.getP_Address ( ));
        }
        if (a==null){

        }else{
            P_Reason_txt.setText (a.getReason ());
            d_ID_cmb.setValue (a.getD_Id ());
            d_Name_lbl.setText (a.getD_Name ());
            d_Date_Lbl.setText (a.getDate ());
            d_Time_Lbl.setText (a.getTime ());
        }
        //search_Patient_Btn.requestFocus();

    }


    public void delete_Appointment_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        search_Patient();
        if (new AppointmentController ().deleteAppointment (P_Id_lbl.getText ())) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clear_Details ( );
        } else {
            clear_Details ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
    }
}
