package controller;

import P_Controller.BedController;
import P_Controller.RoomController;
import P_Controller.WardController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import module.Appointment;
import module.Bed;
import module.Room;
import module.Ward;
import views.tm.AppointmentTm;
import views.tm.WardTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewWardFormController {
    public AnchorPane table_Load_Context;
    public TableView ward_tbl;
    public TableColumn w_Id_col;
    public TableColumn w_Name_col;
    public TableColumn w_D_col;
    public TableColumn r_Qty_col;
    public JFXComboBox w_cmb;
    public JFXTextField w_Name_Txt;
    public JFXTextField w_D_In_Charge_Txt;
    public JFXTextField w_Room_Qty_Txt;
    public JFXComboBox r_cmb;
    public JFXTextField w_Id_Txt;
    public JFXTextField r_Charge_Txt;
    public JFXTextField r_ID_Txt;
    public JFXComboBox b_cmb;
    public JFXTextField b_Charges_Txt;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCMBDetails ( );
        AddWardsToTable();
    }
    void AddWardsToTable() throws SQLException, ClassNotFoundException {
        w_Id_col.setCellValueFactory (new PropertyValueFactory<> ("w_Id"));
        w_Name_col.setCellValueFactory (new PropertyValueFactory<> ("w_Name"));
        w_D_col.setCellValueFactory (new PropertyValueFactory<> ("w_D_In_Charge"));
        r_Qty_col.setCellValueFactory (new  PropertyValueFactory<> ("r_Qty"));
        setWardDetailsToTable(new WardController ().getAllWards());
    }

    private void setWardDetailsToTable(ArrayList<Ward> wards) {
        ObservableList<WardTm> obList = FXCollections.observableArrayList ( );
        wards.forEach (b -> {
            obList.add (
                    new WardTm (  b.getW_Id (),b.getW_Name (),b.getW_D_ID (),(b.getW_ROOM_Qty ())));
        });
        ward_tbl.setItems (obList);
    }

    void setCMBDetails() throws SQLException, ClassNotFoundException {
        List temp = new WardController ( ).getW_ids ( );
        w_cmb.getItems ( ).addAll (temp);
        w_cmb.getSelectionModel ( ).selectedItemProperty ( ).addListener ((observable, oldValue, newValue) -> {
            try {
                setW_details (( String ) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace ( );
            } catch (ClassNotFoundException e) {
                e.printStackTrace ( );
            }
        });
        List room = new RoomController ( ).getRoom_id ( );
        r_cmb.getItems ( ).addAll (room);
        r_cmb.getSelectionModel ( ).selectedItemProperty ( ).addListener ((observable, oldValue, newValue) -> {
            try {
                setR_details (( String ) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace ( );
            } catch (ClassNotFoundException e) {
                e.printStackTrace ( );
            }
        });
        List bed = new BedController ( ).getBed_id ( );
        b_cmb.getItems ( ).addAll (bed);
        b_cmb.getSelectionModel ( ).selectedItemProperty ( ).addListener ((observable, oldValue, newValue) -> {
            try {
                setB_details (( String ) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace ( );
            } catch (ClassNotFoundException e) {
                e.printStackTrace ( );
            }
        });

    }

    private void setW_details(String W_Id) throws SQLException, ClassNotFoundException {
        Ward d1 = new WardController ( ).getWard (W_Id);
        if (d1 == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            w_Name_Txt.setText (d1.getW_Name ( ));
            w_D_In_Charge_Txt.setText (d1.getW_D_ID ( ));
            w_Room_Qty_Txt.setText (String.valueOf ((d1.getW_ROOM_Qty ( ))));
        }

    }

    private void setR_details(String R_Id) throws SQLException, ClassNotFoundException {
        Room r1 = new RoomController ( ).getRoom (R_Id);
        if (r1 == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            r_Charge_Txt.setText (String.valueOf (r1.getR_Charge ( )));
            w_Id_Txt.setText (r1.getW_Id ( ));

        }

    }

    private void setB_details(String R_Id) throws SQLException, ClassNotFoundException {
        Bed b = new BedController ( ).getBed (R_Id);
        if (b == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            r_ID_Txt.setText (b.getR_Id ( ));
            b_Charges_Txt.setText (String.valueOf (b.getB_Charge ( )));

        }

    }


    public void view_Ward_Details_on_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        AddWardsToTable();
        URL resource = getClass ( ).getResource ("../views/ViewWardDetailsForm.fxml");
        Parent load = FXMLLoader.load (resource);
        table_Load_Context.getChildren ( ).clear ( );
        table_Load_Context.getChildren ( ).add (load);
        ward_tbl.refresh ( );
    }

    public void view_Bed_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ViewBedDetailsForm.fxml");
        Parent load = FXMLLoader.load (resource);
        table_Load_Context.getChildren ( ).clear ( );
        table_Load_Context.getChildren ( ).add (load);
    }

    public void view_Room_Details_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ViewRoomDetailsForm.fxml");
        Parent load = FXMLLoader.load (resource);
        table_Load_Context.getChildren ( ).clear ( );
        table_Load_Context.getChildren ( ).add (load);
    }
}
