package controller;

import P_Controller.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
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
import module.AdmitPatient;
import module.DischargePatient;
import module.Patient;
import views.tm.AdmitPatientDetailsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdmitPatientFormController {
    public JFXTextField search_Nic_txt;
    public Label p_Id_lbl;
    public JFXTextField set_Name_txt;
    public ComboBox<String> ward_Id_cmb;
    public Label date_lbl;
    public Label time_lbl;
    public JFXComboBox<String> room_Id_cmb;
    public JFXComboBox<String> M_Or_F_cmb;
    public AnchorPane load_d_v_context;
    public JFXToggleButton toggle_Btn;
    public TableView tbl_View_Admit_Patient;
    public TableColumn p_id_col;
    public TableColumn name_col;
    public TableColumn nic_col;
    public TableColumn w_id_col;
    public TableColumn r_id_col;
    public TableColumn M_Or_F_col;
    public TableColumn a_date_col;
    public JFXComboBox bed_Id_cmb;


    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime ( );
        List temp = new WardController ( ).getW_ids ();
        ward_Id_cmb.getItems ( ).addAll (temp);

        setRoom_Id_cmb ( );
        setBed_Id_cmb ( );
        setAdmitPatientToTable ( );
        M_Or_F_cmb.getItems ( ).addAll ("Male", "Female");

    }

    void setAdmitPatientToTable() throws SQLException, ClassNotFoundException {
        p_id_col.setCellValueFactory (new PropertyValueFactory<> ("p_ID"));
        name_col.setCellValueFactory (new PropertyValueFactory<> ("p_Name"));
        nic_col.setCellValueFactory (new PropertyValueFactory<> ("p_Nic"));
        w_id_col.setCellValueFactory (new PropertyValueFactory<> ("w_ID"));
        r_id_col.setCellValueFactory (new PropertyValueFactory<> ("r_ID"));
        M_Or_F_col.setCellValueFactory (new PropertyValueFactory<> ("m_or_f"));
        a_date_col.setCellValueFactory (new PropertyValueFactory<> ("admit_Date"));
        setAdmittedPatientToTable (new AdmitPatientController ( ).getAllAdmittedPatient ( ));

    }

    void setRoom_Id_cmb() {
        ward_Id_cmb.getSelectionModel ( ).selectedItemProperty ( ).addListener ((observable, oldValue, newValue) -> {
            List temp2 = null;
            try {
                temp2 = new RoomController ( ).getRoom_ids (ward_Id_cmb.getSelectionModel ().getSelectedItem () );

                room_Id_cmb.getItems ( ).addAll (temp2);
                //temp2.clear ( );
            } catch (SQLException throwables) {
                throwables.printStackTrace ( );
            } catch (ClassNotFoundException e) {
                e.printStackTrace ( );
            }
        });
    }

    void setBed_Id_cmb() {
        room_Id_cmb.getSelectionModel ( ).selectedItemProperty ( ).addListener ((observable, oldValue, newValue) -> {
            List temp3 = null;
            try {
                temp3 = new BedController ( ).getBed_ids (room_Id_cmb.getSelectionModel ().getSelectedItem () );
            } catch (SQLException throwables) {
                throwables.printStackTrace ( );
            } catch (ClassNotFoundException e) {
                e.printStackTrace ( );
            }
            bed_Id_cmb.getItems ( ).addAll (temp3);

        });
    }

    private void setAdmittedPatientToTable(ArrayList<AdmitPatient> admittedPatients) {
        ObservableList<AdmitPatientDetailsTm> obList = FXCollections.observableArrayList ( );
        admittedPatients.forEach (e -> {
            obList.add (
                    new AdmitPatientDetailsTm (e.getP_ID ( ), e.getP_Name ( ), e.getP_Nic ( ), e.getW_ID ( ), e.getR_ID ( ), e.getM_or_f ( ), e.getAdmit_Date ( )));
        });
        tbl_View_Admit_Patient.setItems (obList);
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

    public void admit_Patient_Btn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nic = search_Nic_txt.getText ( );

        AdmitPatient c = new AdmitPatientController ( ).getAdmitPatient (nic);

        if ((c == null)) {
            if (!p_Id_lbl.getText ( ).equals ("") && !search_Nic_txt.getText ( ).equals ("") && !search_Nic_txt.getText ( ).equals ("") && !
                    ward_Id_cmb.getValue ( ).equals ("") && !room_Id_cmb.getValue ( ).equals ("") && !M_Or_F_cmb.getValue ( ).equals ("") && !date_lbl.getText ( ).equals ("")) {
                AdmitPatient a = new AdmitPatient (
                        p_Id_lbl.getText ( ),
                        search_Nic_txt.getText ( ),
                        set_Name_txt.getText ( ),
                        ward_Id_cmb.getValue ( ),
                        room_Id_cmb.getValue ( ),
                        ( String ) bed_Id_cmb.getValue ( ),
                        M_Or_F_cmb.getValue ( ),
                        date_lbl.getText ( )
                );

                if (new AdmitPatientController ( ).admitPatient (a)) {

                    new Alert (Alert.AlertType.CONFIRMATION, "Patient OF " + set_Name_txt.getText ( ) + " Admitted..").showAndWait ( );
                    clear_Details ( );
                    setAdmittedPatientToTable (new AdmitPatientController ( ).getAllAdmittedPatient ( ));

                    //refreshOnAction(actionEvent);
                } else {
                    new Alert (Alert.AlertType.WARNING, "Try Again..").show ( );
                }
            } else {

                new Alert (Alert.AlertType.INFORMATION, "Please fill all fields and try again..").show ( );

            }
        } else {
            new Alert (Alert.AlertType.WARNING, "Patient Already Admitted").showAndWait ( );
        }


    }


    void clear_Details() {
        p_Id_lbl.setText (null);
        set_Name_txt.clear ( );
        set_Name_txt.clear ( );
        ward_Id_cmb.setValue (null);
        room_Id_cmb.setValue (null);
        M_Or_F_cmb.setValue (null);
        search_Nic_txt.clear ( );
    }


    void search_Patient() throws SQLException, ClassNotFoundException {

        String nic = search_Nic_txt.getText ( );
        Patient p1 = new PatientController ( ).getPatient (nic);
        AdmitPatient a = new AdmitPatientController ( ).getAdmitPatient (nic);
        if (a == null) {
        } else {

            ward_Id_cmb.setValue (a.getW_ID ( ));
            M_Or_F_cmb.setValue (a.getM_or_f ( ));
            room_Id_cmb.setValue (a.getR_ID ( ));
            bed_Id_cmb.setValue (a.getB_ID ( ));
        }
        if (p1 == null) {

            search_Nic_txt.clear ( );
            //clear_Details ( );
            new Alert (Alert.AlertType.WARNING, "No Patient for this NIc...Please Saved First...").show ( );


        } else {
            p_Id_lbl.setText (p1.getP_Id ( ));
            search_Nic_txt.setText (p1.getP_Nic ( ));
            set_Name_txt.setText (p1.getP_Name ( ));

        }

        //txt_P_Address.setText (p1.getP_Address ( ));

        //search_Patient_Btn.requestFocus();

    }


    public void disCharge_Patient_Btn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!p_Id_lbl.getText ( ).equals ("") && !search_Nic_txt.getText ( ).equals ("") && !search_Nic_txt.getText ( ).equals ("") && !
                ward_Id_cmb.getValue ( ).equals ("") && !room_Id_cmb.getValue ( ).equals ("") && !M_Or_F_cmb.getValue ( ).equals ("") && !date_lbl.getText ( ).equals ("")) {
            DischargePatient a = new DischargePatient (
                    p_Id_lbl.getText ( ),
                    search_Nic_txt.getText ( ),
                    set_Name_txt.getText ( ),
                    ward_Id_cmb.getValue ( ),
                    room_Id_cmb.getValue ( ),
                    M_Or_F_cmb.getValue ( ),
                    date_lbl.getText ( )


            );
            if (new AdmitPatientController ( ).deleteAdmitPatient (p_Id_lbl.getText ( )) && new DischargePatientController ( ).dischargePatient (a)) {

                new Alert (Alert.AlertType.CONFIRMATION, "Patient OF " + set_Name_txt.getText ( ) + " Discharge..").showAndWait ( );
                clear_Details ( );
                tbl_View_Admit_Patient.refresh ( );

                //refreshOnAction(actionEvent);
            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again..").show ( );
            }
        } else {

            new Alert (Alert.AlertType.INFORMATION, "Please fill all fields and try again..").show ( );

        }


    }

    public void search_Patient_On_Key_Reliease(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            search_Patient ( );
        }
    }

    boolean settooglebtn() {
        toggle_Btn.isSelected ( );
        return true;
    }

    public void view_Discharge_patient_On_action(ActionEvent actionEvent) throws IOException {

        if (toggle_Btn.isSelected ( )) {


            URL resource = getClass ( ).getResource ("../views/ViewDischargePatientForm.fxml");
            Parent load = FXMLLoader.load (resource);
            load_d_v_context.getChildren ( ).clear ( );
            load_d_v_context.getChildren ( ).add (load);
        } else {
            URL resource = getClass ( ).getResource ("../views/ViewAdmitPatientForm.fxml");
            Parent load = FXMLLoader.load (resource);
            load_d_v_context.getChildren ( ).clear ( );
            load_d_v_context.getChildren ( ).add (load);
            toggle_Btn.setDisable (false);
        }
    }

}
