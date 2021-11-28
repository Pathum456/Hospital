package controller;

import P_Controller.BedController;
import P_Controller.RoomController;
import P_Controller.WardController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import module.Bed;
import module.Room;
import module.Ward;
import views.tm.WardTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageWardFormController {
    public AnchorPane table_Load_Context;
    public TableView ward_tbl;
    public TableColumn w_Id_col;
    public TableColumn w_Name_col;
    public TableColumn w_D_col;
    public TableColumn r_Qty_col;
    public JFXTextField w_Name_Txt;
    public JFXTextField w_D_In_Charge_Txt;
    public JFXTextField w_Room_Qty_Txt;
    public JFXTextField w_Id_Txt;
    public JFXTextField r_Charge_Txt;
    public JFXTextField r_ID_Txt;
    public JFXTextField b_Charges_Txt;
    public Label w_Id_lbl;
    public Label r_Id_lbl;
    public Label b_Id_lbl;
    public JFXTextField ward_Id_Txt;
    public JFXTextField room_Id_Txt;
    public JFXTextField bed_Id_txt;
    public JFXButton add_Ward_btn;
    public JFXButton add_Room_btn;
    public JFXButton add_Bed_btn;
    public JFXButton delete_Ward_btn;
    public JFXButton delete_Room_btn;
    public JFXButton delete_Bed_btn;

    public void initialize() throws SQLException, ClassNotFoundException {

        AddWardsToTable ( );
        w_Id_lbl.setText (new WardController ( ).setWardId ( ));
        r_Id_lbl.setText (new RoomController ( ).setRoomIDS ( ));
        b_Id_lbl.setText (new BedController ( ).setBedIDS ( ));
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
                    new WardTm (b.getW_Id ( ), b.getW_Name ( ), b.getW_D_ID ( ), b.getW_ROOM_Qty ( )));
        });
        ward_tbl.setItems (obList);
    }

    private void setW_details(String W_Id) throws SQLException, ClassNotFoundException {
        Ward d1 = new WardController ( ).getWard (W_Id);
        if (d1 == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            ward_Id_Txt.clear ( );
            w_Id_lbl.setText (d1.getW_Id ( ));
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
            room_Id_Txt.clear ( );
            r_Id_lbl.setText (r1.getR_Id ( ));
            r_Charge_Txt.setText (String.valueOf (r1.getR_Charge ( )));
            w_Id_Txt.setText (r1.getW_Id ( ));

        }

    }

    private void setB_details(String R_Id) throws SQLException, ClassNotFoundException {
        Bed b = new BedController ( ).getBed (R_Id);
        if (b == null) {
            new Alert (Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            bed_Id_txt.clear ( );
            b_Id_lbl.setText (b.getB_Id ( ));
            r_ID_Txt.setText (b.getR_Id ( ));
            b_Charges_Txt.setText (String.valueOf (b.getB_Charge ( )));

        }

    }

    void clear() {
        w_Name_Txt.clear ( );
        w_D_In_Charge_Txt.clear ( );
        w_Room_Qty_Txt.clear ( );
        w_Id_Txt.clear ( );
        r_Charge_Txt.clear ( );
        room_Id_Txt.clear ( );
        b_Charges_Txt.clear ( );
    }

    public void search_Ward_On_key_Reliesed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            setW_details (ward_Id_Txt.getText ( ));
        }
    }

    public void search_Room_On_key_Reliesed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            setR_details (room_Id_Txt.getText ( ));
        }
    }

    public void search_Bed_On_key_Reliesed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            setB_details (bed_Id_txt.getText ( ));
        }
    }

    public void add_Ward_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!w_Id_lbl.getText ( ).equals ("") && !w_Name_Txt.getText ( ).equals ("") && !w_D_In_Charge_Txt.getText ( ).equals ("") && !w_Room_Qty_Txt.getText ( ).equals ("")) {
            Ward w = new Ward (
                    w_Id_lbl.getText ( ),
                    w_Name_Txt.getText ( ),
                    w_D_In_Charge_Txt.getText ( ),
                    w_Room_Qty_Txt.getText ( )
            );
            if (new WardController ( ).saveWard (w)) {
                clear ( );
                new Alert (Alert.AlertType.CONFIRMATION, "Ward Saved...").showAndWait ( );
            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again...").showAndWait ( );
            }
        } else {
            new Alert (Alert.AlertType.ERROR, "Please Fill ALL field...").showAndWait ( );
        }
        AddWardsToTable ( );
    }

    public void add_Room_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!r_Id_lbl.getText ( ).equals ("") && !w_Id_Txt.getText ( ).equals ("") && !r_Charge_Txt.getText ( ).equals ("")) {
            Room r = new Room (
                    r_Id_lbl.getText ( ),
                    ward_Id_Txt.getText ( ),
                    Double.parseDouble (r_Charge_Txt.getText ( ))
            );

            if (new RoomController ( ).saveRoom (r)) {

                clear ( );
                new Alert (Alert.AlertType.CONFIRMATION, "Ward Saved...").showAndWait ( );
            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again...").showAndWait ( );

            }
        } else {
            new Alert (Alert.AlertType.ERROR, "Please Fill ALL field...").showAndWait ( );
        }
        AddWardsToTable ( );
    }

    public void add_Bed_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!b_Id_lbl.getText ( ).equals ("") && !b_Charges_Txt.getText ( ).equals ("") && !r_ID_Txt.getText ( ).equals ("")) {
            Bed b = new Bed (
                    b_Id_lbl.getText ( ),
                    Double.parseDouble (b_Charges_Txt.getText ( )),
                    r_ID_Txt.getText ( )
            );

            if (new BedController ( ).saveBed (b)) {
                clear ( );
                new Alert (Alert.AlertType.CONFIRMATION, "Saved...").showAndWait ( );

            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again...").showAndWait ( );

            }
        } else {
            new Alert (Alert.AlertType.ERROR, "Please Fill ALL field...").showAndWait ( );
        }
    }

    public void delete_ward_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new WardController ( ).deleteWard (w_Id_lbl.getText ( ))) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clear ( );
        } else {
            clear ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
        AddWardsToTable ( );
    }

    public void delete_Room_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new RoomController ( ).deleteRoom (r_Id_lbl.getText ( ))) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clear ( );
        } else {
            clear ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
        AddWardsToTable ( );
    }

    public void delete_Bed_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new BedController ( ).deleteBed (b_Id_lbl.getText ( ))) {
            new Alert (Alert.AlertType.CONFIRMATION, "Deleted").show ( );
            clear ( );
        } else {
            clear ( );
            new Alert (Alert.AlertType.WARNING, "Try Again").show ( );
        }
        AddWardsToTable ( );
    }

    public void view_Ward_Details_on_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ViewWardDetailsForm.fxml");
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

    public void view_Bed_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass ( ).getResource ("../views/ViewBedDetailsForm.fxml");
        Parent load = FXMLLoader.load (resource);
        table_Load_Context.getChildren ( ).clear ( );
        table_Load_Context.getChildren ( ).add (load);
    }
}
