package controller;

import P_Controller.AdmitPatientController;
import P_Controller.BedController;
import P_Controller.BillController;
import P_Controller.RoomController;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import module.AdmitPatient;
import module.Bed;
import module.Bill;
import module.Room;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class MakePaymentFormController {
    public Label lbl_Date;
    public Label lbl_Time;
    public JFXTextField txt_P_Nic;
    public JFXTextField txt_P_Name;
    public Label lbl_P_ID;
    public TextField d_Charges_tct;
    public TextField r_Charges_txt;
    public TextField b_Charges_txt;
    public TextField amount_txt;
    public Label lbl_Bill_ID;
    public JFXTextField description_Txt;
    public Label w_Id_txt;
    public Label r_Id_Txt;
    public Label b_Id_Txt;
    public TextField m_Charges_Txt;
    public TextField extra_Charges_txt;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime ( );
        lbl_Bill_ID.setText (new BillController ( ).setBillIDS ( ));
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date ( );
        SimpleDateFormat f = new SimpleDateFormat ("yyyy-MM-dd");
        lbl_Date.setText (f.format (date));

        // load Time
        Timeline time = new Timeline (new KeyFrame (Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now ( );
            lbl_Time.setText (
                    currentTime.getHour ( ) + " : " + currentTime.getMinute ( ) +
                            " : " + currentTime.getSecond ( )
            );
        }),
                new KeyFrame (Duration.seconds (1))
        );
        time.setCycleCount (Animation.INDEFINITE);
        time.play ( );
    }

    public void go_To_Search_Btn_Key_Pressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode ( ) == KeyCode.ENTER) {
            search_Patient ( );
        }
    }

    void search_Patient() throws SQLException, ClassNotFoundException {

        String nic = txt_P_Nic.getText ( );
        AdmitPatient p1 = new AdmitPatientController ( ).getAdmitPatient (nic);

        if (p1 == null) {
            txt_P_Nic.clear ( );
            //clear_Details ( );
            new Alert (Alert.AlertType.INFORMATION, "No Patient Admitted for this NIc...").show ( );

        } else {
            lbl_P_ID.setText (p1.getP_ID ( ));
            txt_P_Name.setText (p1.getP_Name ( ));
            b_Id_Txt.setText (p1.getB_ID ( ));
            w_Id_txt.setText (p1.getW_ID ( ));
            r_Id_Txt.setText (p1.getR_ID ( ));
            //txt_P_Contact.setText (String.valueOf (p1.getP_Contact ( )));
            //txt_P_Address.setText (p1.getP_Address ( ));
            setChargesForAdmitPatient ( );
        }
        //search_Patient_Btn.requestFocus();

    }

    void setChargesForAdmitPatient() throws SQLException, ClassNotFoundException {
        String b_Id = b_Id_Txt.getText ( );
        String r_Id = r_Id_Txt.getText ( );
        Room room = new RoomController ( ).getRoom (r_Id);
        Bed bill = new BedController ( ).getBedCharge (b_Id);
        AdmitPatient p = new AdmitPatient ( );
        double r = room.getR_Charge ( );
        double b = bill.getB_Charge ( );
        double m = 00.00;
        double d = 00.00;
        double e =  00.00;
        double a =  00.00;
        if (new AdmitPatientController ( ).admitPatient (p)) {


            d_Charges_tct.setText (String.valueOf (d));
            r_Charges_txt.setText (String.valueOf (r));
            b_Charges_txt.setText (String.valueOf (b));
            m_Charges_Txt.setText (String.valueOf (m));
            extra_Charges_txt.setText (String.valueOf (e));
            amount_txt.setText (String.valueOf (a));

        }

    }

    public void print_BIll_On_Action(ActionEvent actionEvent) {

    }

    public void payed_Bill_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!lbl_Bill_ID.getText ( ).equals ("") && !lbl_P_ID.getText ( ).equals ("") && !txt_P_Name.getText ( ).equals ("") && !w_Id_txt.getText ( ).equals ("") && !r_Id_Txt.getText ( ).equals ("") && !b_Id_Txt.getText ( ).equals ("")) {
            Bill b = new Bill (
                    lbl_Bill_ID.getText ( ),
                    lbl_P_ID.getText ( ),
                    txt_P_Name.getText ( ),
                    w_Id_txt.getText ( ),
                    r_Id_Txt.getText ( ),
                    b_Id_Txt.getText ( ),
                    description_Txt.getText ( ),
                    Double.parseDouble (d_Charges_tct.getText ( )),
                    Double.parseDouble (r_Charges_txt.getText ( )),
                    Double.parseDouble (b_Charges_txt.getText ( )),
                    Double.parseDouble (m_Charges_Txt.getText ( )),
                    Double.parseDouble (extra_Charges_txt.getText ( )),
                    Double.parseDouble (amount_txt.getText ( )),
                    lbl_Date.getText ( )
            );
            if (new BillController ( ).SaveBill (b)) {

                new Alert (Alert.AlertType.CONFIRMATION, "Patient OF " + txt_P_Name.getText ( ) + " Bill Ready..").showAndWait ( );
                //clear_Details ( );


                //refreshOnAction(actionEvent);
            } else {
                new Alert (Alert.AlertType.WARNING, "Try Again..").show ( );
            }
        }else{
            new Alert (Alert.AlertType.WARNING,"Please Fill All Field...").showAndWait ();
        }

    }

    public void addExtraCharges_Key_Release(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String b_Id = b_Id_Txt.getText ( );
        String r_Id = r_Id_Txt.getText ( );
        Room room = new RoomController ( ).getRoom (r_Id);
        Bed bill = new BedController ( ).getBedCharge (b_Id);
        double r = room.getR_Charge ( );
        double b = bill.getB_Charge ( );
        double m = 500.00;
        double d = 1500;
        double e = 00.00;
        e = Double.parseDouble (extra_Charges_txt.getText ( ));
        if (e >= 0.0) {
            if (keyEvent.getCode ( ) == KeyCode.ENTER) {

                if (!(e == 00.00)) {
                    amount_txt.setText (String.valueOf (d + r + b + m + e));
                } else {
                    amount_txt.setText (String.valueOf (d + r + b + m));
                }
            }
        } else {
            System.out.println ("null");
        }
    }
}
