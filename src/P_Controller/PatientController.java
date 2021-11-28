package P_Controller;

import InterFace.PatientService;
import db.DbConnection;
import module.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientController implements PatientService {

    @Override
    public boolean savePatient(Patient p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO patient VALUES(?,?,?,?,?)");
        stm.setObject(1,p.getP_Id ());
        stm.setObject(2,p.getP_Name ());
        stm.setObject(3,p.getP_Nic ());
        stm.setObject(4,p.getP_Contact ());
        stm.setObject(5,p.getP_Address ());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updatePatient(Patient p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE patient SET P_ID=?, P_Name=?,  P_NIC=?,P_Contact=?, P_Address=?  WHERE P_NIC='"+p.getP_Nic ()+"'");
        stm.setObject(1,p.getP_Id ());
        stm.setObject(2,p.getP_Name ());
        stm.setObject(3,p.getP_Nic ());
        stm.setObject(4,p.getP_Contact ());
        stm.setObject(5,p.getP_Address ());

        return stm.executeUpdate()>0;
    }


    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM patient WHERE P_ID='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Patient getPatient(String P_ID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM patient WHERE P_NIC='"+P_ID+"'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Patient (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );

        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Patient> getAllPatient() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM patient");
        ResultSet rst = stm.executeQuery();
        ArrayList<Patient> patients = new ArrayList<>();
        while (rst.next()) {
            patients.add(new Patient (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return patients;
    }




    public String setPatientIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT P_ID FROM patient ORDER BY P_ID DESC LIMIT 1").executeQuery ( );
        if (rst.next ( )) {

            int tempId = Integer.
                    parseInt (rst.getString (1).split ("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "P00-00" + tempId;
            } else if (tempId < 99) {
                return "P00-0" + tempId;
            } else {
                return "P00-" + tempId;
            }

        } else {
            return "P00-001";
        }
    }
    public ArrayList<String> getPatientIDS() throws SQLException, ClassNotFoundException {
        ArrayList<String> p_IDS = new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM patient");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            p_IDS.add(rst.getString(1));
        }
        return p_IDS;
    }
}
