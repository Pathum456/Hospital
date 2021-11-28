package controller;

import P_Controller.BedController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.Bed;
import views.tm.BedTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewBedDetailsFormController {
    public TableView tbl_Bed_Details;
    public TableColumn b_Id_col;
    public TableColumn r_id_col;
    public TableColumn b_Charges_col;
    public void initialize() throws SQLException, ClassNotFoundException {

        AddRoomsToTable ( );
    }

    private void AddRoomsToTable() throws SQLException, ClassNotFoundException {
        b_Id_col.setCellValueFactory (new PropertyValueFactory<> ("b_Id"));
        r_id_col.setCellValueFactory (new PropertyValueFactory<> ("r_Id"));
        b_Charges_col.setCellValueFactory (new PropertyValueFactory<> ("b_Charges"));
        setBedDetailsToTable (new BedController ().getAllBeds ());

    }
    private void setBedDetailsToTable(ArrayList<Bed> beds) {
        ObservableList<BedTm> obList = FXCollections.observableArrayList ( );
        beds.forEach (b -> {
            obList.add (
                    new BedTm (b.getB_Id (),b.getR_Id (),b.getB_Charge ()));
        });
        tbl_Bed_Details.setItems (obList);
    }
}
