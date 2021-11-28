package controller;

import P_Controller.PatientController;
import P_Controller.PatientReportController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import module.Patient;
import module.PatientReport;
import views.tm.PatientReportTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PatientReportFormController {
    public JFXTextField txt_NIC;
    public Label r_ID_lbl;
    public Label Date_lbl;
    public JFXTextField txt_Name;
    public JFXTextField txt_Reason;
    public Label p_Id_lbl;
    public TableView reports_tbl;
    public TableColumn mr_Id_col;
    public TableColumn p_ID_col;
    public TableColumn name_col;
    public TableColumn nic_col;
    public TableColumn reason_col;
    public TableColumn date_col;
    public JFXButton addBtn;
    public JFXButton removeBTn;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime ( );
        r_ID_lbl.setText (new PatientReportController ( ).setReportId ( ));
        mr_Id_col.setCellValueFactory (new PropertyValueFactory<> ("MR_Id"));
        p_ID_col.setCellValueFactory (new PropertyValueFactory<> ("p_Id"));
        nic_col.setCellValueFactory (new PropertyValueFactory<> ("p_NIC"));
        name_col.setCellValueFactory (new PropertyValueFactory<> ("p_Name"));
        reason_col.setCellValueFactory (new PropertyValueFactory<> ("reason"));
        date_col.setCellValueFactory (new PropertyValueFactory<> ("date"));
        setPatientReportToTable (new PatientReportController ( ).getAllPatientReport ( ));

    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date ( );
        SimpleDateFormat f = new SimpleDateFormat ("yyyy-MMM-dd");
        Date_lbl.setText (f.format (date));

        // load Time
        /*Timeline time = new Timeline (new KeyFrame (Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now ( );
            lbl_Time.setText (
                    currentTime.getHour ( ) + " : " + currentTime.getMinute ( ) +
                            " : " + currentTime.getSecond ( )
            );
        }),
                new KeyFrame (Duration.seconds (1))
        );
        time.setCycleCount (Animation.INDEFINITE);
        time.play ( );*/
    }

    private void setPatientReportToTable(ArrayList<PatientReport> patients) {
        ObservableList<PatientReportTm> obList = FXCollections.observableArrayList ( );
        patients.forEach (e -> {
            obList.add (
                    new PatientReportTm (e.getMR_Id ( ), e.getP_Id ( ), e.getP_NIC ( ), e.getP_Name ( ), e.getReason ( ), e.getDate ( )));
        });
        reports_tbl.setItems (obList);
    }

    void search_reports() throws SQLException, ClassNotFoundException {
        String nic = txt_NIC.getText ( );
        String mr_Id=r_ID_lbl.getText ();
        Patient p = new PatientController ( ).getPatient (nic);
        PatientReport mr = new PatientReportController ( ).getPatientReport (nic);


            if (p == null) {
                new Alert (Alert.AlertType.ERROR, "No Reports for this Patient " + nic).show ( );
                txt_NIC.clear ( );
            } else {

                //r_ID_lbl.setText (new PatientReportController ( ).setReportId ( ));
                p_Id_lbl.setText (p.getP_Id ( ));
                txt_NIC.setText (p.getP_Nic ( ));
                txt_Name.setText (p.getP_Name ( ));
                if (!(mr == null)) {

                    System.out.println ( r_ID_lbl.getText () );
                r_ID_lbl.setText (mr.getMR_Id ( ));
                txt_Reason.setText (mr.getReason ( ));
                Date_lbl.setText (mr.getDate ( ));


            }
        } {
            /*p_Id_lbl.setText (p.getP_Id ( ));
            txt_NIC.setText (p.getP_Nic ( ));
            txt_Name.setText (p.getP_Name ( ));
            r_ID_lbl.setText (mr.getMR_Id ( ));
            txt_Reason.setText (mr.getReason ( ));
            Date_lbl.setText (mr.getDate ( ));*/
        }

    }

    void clear_Details() throws SQLException, ClassNotFoundException {
        r_ID_lbl.setText (new PatientReportController ( ).setReportId ( ));
        txt_Reason.clear ();
        txt_NIC.clear ();
        txt_Name.clear ();
        loadDateAndTime ();
        p_Id_lbl.setText (null);
    }

    public void serch_Reports_Key_Releised(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            search_reports ( );
        }
    }

    public void removeTableOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        search_reports ( );

        if (new PatientReportController ( ).deletePatientReport (r_ID_lbl.getText ( ))) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clear_Details ( );
        } else {
            clear_Details ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
        setPatientReportToTable (new PatientReportController ().getAllPatientReport ());
    }

    public void addToTableOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (!r_ID_lbl.getText ( ).equals ("") && !p_Id_lbl.getText ( ).equals ("") && !Date_lbl.getText ( ).equals ("") && !txt_NIC.getText ( ).equals ("") && !txt_Reason.getText ( ).equals ("") && !txt_Reason.getText ( ).equals ("")) {

            PatientReport mr = new PatientReport (
                    r_ID_lbl.getText ( ),
                    p_Id_lbl.getText ( ),
                    txt_NIC.getText ( ),
                    txt_Name.getText ( ),
                    txt_Reason.getText ( ),
                    Date_lbl.getText ( )
            );
            if (new PatientReportController ( ).savePatientReport (mr)) {
                clear_Details ( );
                new Alert (Alert.AlertType.CONFIRMATION, "Saved...").showAndWait ( );
            }
        } else {
            new Alert (Alert.AlertType.WARNING, "Please Fill All Field ...").showAndWait ( );
        }
        setPatientReportToTable (new PatientReportController ( ).getAllPatientReport ( ));
    }

}
