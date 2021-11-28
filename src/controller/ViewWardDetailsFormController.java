package controller;

import P_Controller.WardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.Ward;
import views.tm.WardTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewWardDetailsFormController {

    public TableView ward_tbl;
    public TableColumn w_Id_col;
    public TableColumn w_Name_col;
    public TableColumn w_D_col;
    public TableColumn r_Qty_col;

    public void initialize() throws SQLException, ClassNotFoundException {

        AddWardsToTable ( );
    }

    void AddWardsToTable() throws SQLException, ClassNotFoundException {
        w_Id_col.setCellValueFactory (new PropertyValueFactory<> ("w_Id"));
        w_Name_col.setCellValueFactory (new PropertyValueFactory<> ("w_Name"));
        w_D_col.setCellValueFactory (new PropertyValueFactory<> ("w_D_In_Charge"));
        r_Qty_col.setCellValueFactory (new PropertyValueFactory<> ("r_Qty"));
        setWardDetailsToTable (new WardController ( ).getAllWards ( ));
    }

    private void setWardDetailsToTable(ArrayList<Ward> wards) {
        ObservableList<WardTm> obList = FXCollections.observableArrayList ( );
        wards.forEach (b -> {
            obList.add (
                    new WardTm (b.getW_Id ( ), b.getW_Name ( ), b.getW_D_ID ( ), (b.getW_ROOM_Qty ( ))));
        });
        ward_tbl.setItems (obList);
    }


}
