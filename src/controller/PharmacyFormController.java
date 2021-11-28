package controller;

import P_Controller.PatientController;
import P_Controller.PharmacyController;
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
import module.Pharmacy;
import views.tm.PharmacyTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class PharmacyFormController {
    public JFXTextField search_Id_Txt;
    public Label ph_Id_lbl;
    public TableView view_Pharmacy_Tbl;
    public TableColumn ph_Id_col;
    public TableColumn name_col;
    public TableColumn contact_col;
    public TableColumn address_Col;
    public JFXTextField address_Txt;
    public JFXTextField contact_txt;
    public JFXTextField name_Txt;
    public void initialize() throws SQLException, ClassNotFoundException {

        //List temp =new PharmacyController ().getAllPharmacy();
        ph_Id_col.setCellValueFactory (new PropertyValueFactory<> ("ph_Id"));
        name_col.setCellValueFactory (new PropertyValueFactory<> ("p_Name"));
        contact_col.setCellValueFactory (new PropertyValueFactory<> ("p_Contact"));
        address_Col.setCellValueFactory (new PropertyValueFactory<> ("p_Address"));
        setView_Pharmacy_TblToTable (new PharmacyController ( ).getAllPharmacy ( ));

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
    public void search_Pharmacy_On_Key_Released(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            search_Pharmacy ( );
        }
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
    void clearTXT() {
        search_Id_Txt.clear ( );
        name_Txt.clear ( );
        address_Txt.clear ( );
        contact_txt.clear ( );
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
        setView_Pharmacy_TblToTable (new PharmacyController ( ).getAllPharmacy ( ));
        view_Pharmacy_Tbl.refresh ();
    }
}
