package controller;

import P_Controller.PatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import module.Patient;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class PatientRegistrationFormController {
    public AnchorPane patient_Registration_Context;
    public JFXTextField txt_P_Id;
    public JFXTextField txt_P_Name;
    public JFXTextField txt_P__Nic;
    public JFXTextField txt_P_Contact;
    public JFXTextField txt_P__Address;
    public JFXButton save_Btn;
    public AnchorPane id_Context;
    public AnchorPane nic_Context;
    public AnchorPane name_Context;
    public AnchorPane contact_Context;
    public AnchorPane address_Context;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<> ( );


    Pattern namePattern = Pattern.compile ("^[A-z.[]]]{2,}$");
    Pattern contact = Pattern.compile ("^(07)[0-9][0-9]{7}$");
    Pattern nicPattern = Pattern.compile ("^[0-9]{12}|[0-9]{9}[V,v]$");
    Pattern addressPattern = Pattern.compile ("^[#./0-9a-zA-z,-[]]]{5,}$");

    public void initialize() throws SQLException, ClassNotFoundException {
        txt_P_Id.setText (new PatientController ( ).setPatientIDS ( ));
        //setValidation ( );

        System.out.println (map.get (txt_P__Nic));
    }

    private void setValidation() {
        map.put (txt_P_Name, namePattern);
        map.put (txt_P__Nic, nicPattern);
        map.put (txt_P_Contact, contact);
        map.put (txt_P__Address, addressPattern);
    }

    void setStyles() {
        if (txt_P__Nic.getText ( ).equals ("")) {
            nic_Context.getParent ( ).setStyle ("-fx-border-color: red");


        } else {
            nic_Context.getParent ( ).setStyle ("-fx-border-color: green");
        }
        if (txt_P_Name.getText ( ).equals ("")) {
            name_Context.getParent ( ).setStyle ("-fx-border-color: green");
        } else {
            name_Context.getParent ( ).setStyle ("-fx-border-color: green");
        }
        if (txt_P__Address.getText ( ).equals ("")) {
            address_Context.getParent ( ).setStyle ("-fx-border-color: red");
        } else {
            address_Context.getParent ( ).setStyle ("-fx-border-color: green");
        }
        if (txt_P_Contact.getText ( ).equals ("")) {
            contact_Context.getParent ( ).setStyle ("-fx-border-color: red");
        } else {
            contact_Context.getParent ( ).setStyle ("-fx-border-color: green");
        }
        //id_Context.getParent ().setStyle ("-fx-border-color: green");

    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        txt_P_Id.setText (new PatientController ( ).setPatientIDS ( ));
        txt_P_Name.clear ( );
        txt_P_Contact.clear ( );
        txt_P__Nic.clear ( );
        txt_P__Address.clear ( );
    }


    public void validationKeyRelieased(KeyEvent keyEvent) {
        Object response = validate ( );
        if ((keyEvent.getCode ( ) == KeyCode.ENTER)) {
            if ((response instanceof TextField)) {
                TextField errorText = ( TextField ) response;
                errorText.requestFocus ( );
            }
        }
        /*if ((keyEvent.getCode ( ) == KeyCode.ENTER)) {
            if (!txt_P_Name.getText ( ).equals ("")) {
                txt_P__Nic.requestFocus ( );
                txt_P_Name.getParent ( ).setStyle ("-fx-border-color: Green");
                if (!txt_P__Nic.getText ( ).equals ("")) {
                    txt_P_Contact.requestFocus ( );
                    txt_P__Nic.getParent ( ).setStyle ("-fx-border-color: Green");
                    if (!txt_P_Contact.getText ( ).equals ("")) {
                        txt_P__Address.requestFocus ( );
                        txt_P_Contact.getParent ( ).setStyle ("-fx-border-color: Green");
                        if (!txt_P__Address.getText ( ).equals ("")) {
                            save_Btn.setDisable (false);
                            txt_P__Address.getParent ( ).setStyle ("-fx-border-color: Green");
                            save_Btn.requestFocus ( );
                        } else {
                            save_Btn.setDisable (true);
                            txt_P__Address.getParent ( ).setStyle ("-fx-border-color: red");
                        }
                    } else {
                        txt_P_Contact.getParent ( ).setStyle ("-fx-border-color: red");
                    }
                } else {
                    txt_P__Nic.getParent ( ).setStyle ("-fx-border-color: red");
                }
            } else {
                txt_P_Name.getParent ( ).setStyle ("-fx-border-color: Red");
            }
        }*/

    }

    public void save_Patient_On_Action(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (!save_Btn.isDisabled ( )) {
            if ((keyEvent.getCode ( ) == KeyCode.ENTER)) {
                setStyles ( );
                if (!txt_P_Name.getText ( ).equals ("") && !txt_P__Nic.getText ( ).equals ("") && !txt_P_Contact.getText ( ).equals ("") && !txt_P__Address.getText ( ).equals ("")) {

                    Patient p = new Patient (
                            txt_P_Id.getText ( ),
                            txt_P_Name.getText ( ),
                            txt_P__Nic.getText ( ),
                            txt_P_Contact.getText ( ),
                            txt_P__Address.getText ( )
                    );

                    if (new PatientController ( ).savePatient (p)) {
                        save_Btn.setDisable (true);
                        new Alert (Alert.AlertType.CONFIRMATION, "Saved..").showAndWait ( );
                        txt_P_Id.redo ( );
                        txt_P_Name.clear ( );
                        txt_P__Nic.clear ( );
                        txt_P_Contact.clear ( );
                        txt_P__Address.clear ( );
                        txt_P_Name.requestFocus ( );
                        //refreshOnAction (actionEvent);
                        // save_Btn.getParent ().setStyle ("-fx-background-color: green");
                        //
                    } else {
                        new Alert (Alert.AlertType.WARNING, "Try Again..").show ( );
                    }
                } else {
                    new Alert (Alert.AlertType.INFORMATION, "Please fill all fields and try again..").show ( );
                }
                txt_P_Id.setText (new PatientController ( ).setPatientIDS ( ));

            }
        }
    }

    public Object validate() {
        setValidation ( );
        for (TextField textFieldKey : map.keySet ( )) {
            Pattern pattern = map.get (textFieldKey);
            if (!pattern.matcher (textFieldKey.getText ( )).matches ( )) {
                textFieldKey.getParent ( ).setStyle ("-fx-border-color: red");
                return textFieldKey;

            }
            System.out.println (map.get (textFieldKey));
            textFieldKey.getParent ( ).setStyle ("-fx-border-color: green");
        }
        return true;
    }
}
