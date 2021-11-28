package controller;

import P_Controller.RoomController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.Room;
import module.Ward;
import views.tm.RoomTm;
import views.tm.WardTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewRoomDetailsFormController {

    public TableView tbl_Room_Details;
    public TableColumn r_Id_Col;
    public TableColumn W_Id_Col;
    public TableColumn r_Charges_Col;


    public void initialize() throws SQLException, ClassNotFoundException {

        AddRoomsToTable ( );
    }

    private void AddRoomsToTable() throws SQLException, ClassNotFoundException {
        r_Id_Col.setCellValueFactory (new PropertyValueFactory<> ("r_Id"));
        W_Id_Col.setCellValueFactory (new PropertyValueFactory<> ("w_Id"));
        r_Charges_Col.setCellValueFactory (new PropertyValueFactory<> ("r_Charges"));
        setRoomDetailsToTable (new RoomController ().getAllRooms ());

    }
    private void setRoomDetailsToTable(ArrayList<Room> wards) {
        ObservableList<RoomTm> obList = FXCollections.observableArrayList ( );
        wards.forEach (r -> {
            obList.add (
                    new RoomTm (r.getR_Id (),r.getW_Id (),r.getR_Charge ()));
        });
        tbl_Room_Details.setItems (obList);
    }

}
