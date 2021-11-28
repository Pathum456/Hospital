package controller;

import P_Controller.AdmitPatientController;
import P_Controller.DischargePatientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.AdmitPatient;
import module.DischargePatient;
import views.tm.AdmitPatientDetailsTm;
import views.tm.DischargePatientDetailsTm;
import views.tm.PatientDetailsTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewDischargePatientFormController {
    public TableView tbl_View_Discharge_Patient;
    public TableColumn p_id_col;
    public TableColumn name_col;
    public TableColumn nic_col;
    public TableColumn w_id_col;
    public TableColumn r_id_col;
    public TableColumn M_Or_F_col;
    public TableColumn d_date_col;
    public void initialize() throws SQLException, ClassNotFoundException {
        p_id_col.setCellValueFactory (new PropertyValueFactory<> ("p_ID"));
        name_col.setCellValueFactory (new PropertyValueFactory<> ("p_Name"));
        nic_col.setCellValueFactory (new PropertyValueFactory<> ("p_Nic"));
        w_id_col.setCellValueFactory (new PropertyValueFactory<> ("w_ID"));
        r_id_col.setCellValueFactory (new PropertyValueFactory<> ("r_ID"));
        M_Or_F_col.setCellValueFactory (new PropertyValueFactory<> ("m_or_f"));
        d_date_col.setCellValueFactory (new PropertyValueFactory<> ("discharge_Date"));
        setDischargePatientToTable (new DischargePatientController ().getAllDischargePatient());

    }

    private void setDischargePatientToTable(ArrayList<DischargePatient> allDischargePatient) {
        ObservableList<DischargePatientDetailsTm > obList = FXCollections.observableArrayList();
        allDischargePatient.forEach(e->{
            obList.add(
                    new DischargePatientDetailsTm (e.getP_ID (),e.getP_Name (),e.getP_Nic (),e.getW_ID (),e.getR_ID (),e.getM_or_f (),e.getDischarge_Date ()));
        });
        tbl_View_Discharge_Patient.setItems(obList);
    }

}
