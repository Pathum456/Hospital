package P_Controller;

import db.DbConnection;
import module.AdmitPatient;
import module.Doctor;
import module.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdmitPatientController {
    public boolean admitPatient(AdmitPatient a) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO admitPatient VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,a.getP_ID ());
        stm.setObject(2,a.getP_Nic ());
        stm.setObject(3,a.getP_Name ());
        stm.setObject(4,a.getW_ID ());
        stm.setObject(5,a.getR_ID ());
        stm.setObject(6,a.getB_ID ());
        stm.setObject(7,a.getM_or_f ());
        stm.setObject(8,a.getAdmit_Date ());

        return stm.executeUpdate()>0;
    }
    public ArrayList<AdmitPatient> getAllAdmittedPatient() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM admitpatient");
        ResultSet rst = stm.executeQuery();
        ArrayList<AdmitPatient> admittedPatients = new ArrayList<>();
        while (rst.next()) {
            admittedPatients.add(new AdmitPatient (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }
        return admittedPatients;
    }
    public AdmitPatient getAdmitPatient(String P_ID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM admitpatient WHERE P_NIC='"+P_ID+"'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new AdmitPatient (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            );

        } else {
            return null;
        }
    }
    public boolean deleteAdmitPatient(String id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM admitpatient WHERE P_ID='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

}
