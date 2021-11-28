package controller;

import P_Controller.DoctorController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import module.Doctor;
import views.tm.DoctorTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvailableDoctorsFormController {

    public TableView view_Doctor_tbl;
    public TableColumn d_ID_Col;
    public TableColumn d_name_Col;
    public TableColumn d_Contact_col;
    public TableColumn d_Days_col;
    public TableColumn d_Time_Col;

    public JFXComboBox search_d_ID_cmb;
    public JFXTextField d_Name_Txt;
    public JFXTextField d_Contact_txt;
    public JFXTextField days_txt;
    public JFXTextField time_txt;



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
        d_ID_Col.setCellValueFactory (new PropertyValueFactory<> ("d_Id"));
        d_name_Col.setCellValueFactory (new PropertyValueFactory<> ("d_Name"));
        d_Contact_col.setCellValueFactory (new PropertyValueFactory<> ("Contact"));
        d_Time_Col.setCellValueFactory (new PropertyValueFactory<> ("time"));
        d_Days_col.setCellValueFactory (new PropertyValueFactory<> ("days"));
        setAvailableDoctor(new DoctorController ().getAllDoctors ());
        //view_Doctor_tbl.refresh ();
        System.out.println (new DoctorController ().getAllDoctors () );

    }


    private void setD_Name(String D_Id) throws SQLException, ClassNotFoundException {
        Doctor d1 = new DoctorController ( ).getDoctor (D_Id);
        if (d1 == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");

        } else {

            d_Name_Txt.setText (d1.getD_Name ( ));
            time_txt.setText (d1.getD_time ( ));
            days_txt.setText (d1.getD_days ( ));
            time_txt.setText (d1.getD_time ());
            d_Contact_txt.setText (d1.getD_Contact ());
        }
    }
    private void setAvailableDoctor(ArrayList<Doctor> doctors) {

        ObservableList<DoctorTm> obList = FXCollections.observableArrayList ( );
        doctors.forEach (a -> {
            obList.add (
                    new DoctorTm (a.getD_Id (),a.getD_Name (),a.getD_Contact (),a.getD_Salary (),a.getD_days (),a.getD_time ()));
        });
        view_Doctor_tbl.setItems (obList);
    }
    public void search_Doctor_KeyRelease(KeyEvent keyEvent) {
    }
}
