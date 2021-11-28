package controller;

import P_Controller.DoctorController;
import P_Controller.PatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import module.Doctor;
import views.tm.DoctorTm;
import views.tm.PharmacyTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageDoctorFormController {
    public JFXComboBox search_d_ID_cmb;
    public JFXTextField d_Name_Txt;
    public JFXTextField d_Contact_txt;
    public JFXTextField days_txt;
    public JFXTextField time_txt;
    public Label d_ID_TXt;
    public TableView view_Doctor_tbl;
    public TableColumn d_ID_Col;
    public TableColumn d_name_Col;
    public TableColumn d_Contact_col;
    public TableColumn d_Days_col;
    public TableColumn d_Time_Col;
    public JFXButton add_Btn;
    public JFXButton delete_Btn;
    public JFXTextField salary_txt;
    public TableColumn d_Salary_col;

    public AnchorPane delete_context;
    public AnchorPane add_context;

    public void initialize() throws SQLException, ClassNotFoundException {
        List temp = new DoctorController ( ).getD_ids ( );
        search_d_ID_cmb.getItems ( ).addAll (temp);
        search_d_ID_cmb.getSelectionModel ( ).selectedItemProperty ( ).addListener ((observable, oldValue, newValue) -> {
            try {
                setD_Name (( String ) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace ( );
            } catch (ClassNotFoundException e) {
                e.printStackTrace ( );
            }
        });
        d_ID_TXt.setText (new DoctorController ( ).setDoctorIDS ( ));
        d_ID_Col.setCellValueFactory (new PropertyValueFactory<> ("d_Id"));
        d_name_Col.setCellValueFactory (new PropertyValueFactory<> ("d_Name"));
        d_Contact_col.setCellValueFactory (new PropertyValueFactory<> ("Contact"));
        d_Salary_col.setCellValueFactory (new PropertyValueFactory<> ("Salary"));
        d_Time_Col.setCellValueFactory (new PropertyValueFactory<> ("time"));
        d_Days_col.setCellValueFactory (new PropertyValueFactory<> ("days"));
        setAvailableDoctor (new DoctorController ( ).getAllDoctors ( ));
        //view_Doctor_tbl.refresh ();


    }

    private void setAvailableDoctor(ArrayList<Doctor> doctors) {

        ObservableList<DoctorTm> obList = FXCollections.observableArrayList ( );
        doctors.forEach (e -> {
            obList.add (
                    new DoctorTm (e.getD_Id ( ), e.getD_Name ( ), e.getD_Contact ( ), e.getD_Salary ( ), e.getD_days ( ), e.getD_time ( )));
        });
        view_Doctor_tbl.setItems (obList);
    }

    private void setD_Name(String D_Id) throws SQLException, ClassNotFoundException {
        Doctor d1 = new DoctorController ( ).getDoctor (D_Id);
        if (d1 == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");

        } else {

            d_Name_Txt.setText (d1.getD_Name ( ));
            time_txt.setText (d1.getD_time ( ));
            days_txt.setText (d1.getD_days ( ));
            time_txt.setText (d1.getD_time ( ));
            d_Contact_txt.setText (d1.getD_Contact ( ));
            salary_txt.setText (String.valueOf (d1.getD_Salary ( )));
        }
    }

    public void save_doctor_on_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!days_txt.getText ( ).equals ("") && !d_Name_Txt.getText ( ).equals ("") && !d_Contact_txt.getText ( ).equals ("") && !salary_txt.getText ( ).equals ("") && !days_txt.getText ( ).equals ("") && !time_txt.getText ( ).equals ("")) {
            Doctor d = new Doctor (
                    d_ID_TXt.getText ( ),
                    d_Name_Txt.getText ( ),
                    d_Contact_txt.getText ( ),
                    Double.parseDouble (salary_txt.getText ( )),
                    days_txt.getText ( ),
                    time_txt.getText ( )
            );
            if (new DoctorController ( ).saveDoctor (d)) {

                new Alert (Alert.AlertType.CONFIRMATION, "Saved..").showAndWait ( );

                // save_Btn.getParent ().setStyle ("-fx-background-color: green");
                //
            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again..").show ( );
            }
        } else {
            new Alert (Alert.AlertType.WARNING, "Please fill all field...").showAndWait ( );
        }

    }
    void clear_Details ( ) throws SQLException, ClassNotFoundException {
        search_d_ID_cmb.setValue (null);
        d_ID_TXt.setText (new DoctorController ().setDoctorIDS ());
        d_Name_Txt.clear ();
        d_Contact_txt.clear ();
        salary_txt.clear ();
        days_txt.clear ();
        time_txt.clear ();
    }


    public void delete_Doctor_on_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        System.out.println ( String.valueOf (search_d_ID_cmb.getValue ()));
        if (new DoctorController ().deleteDoctor (String.valueOf (search_d_ID_cmb.getValue ()))) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clear_Details ( );
        } else {
            clear_Details ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
        setAvailableDoctor (new DoctorController ( ).getAllDoctors ( ));
    }
}
