package P_Controller;

import db.DbConnection;
import module.AdmitPatient;
import module.DischargePatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DischargePatientController {
    public boolean dischargePatient(DischargePatient a) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO dischargePatient VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,a.getP_ID ());
        stm.setObject(2,a.getP_Nic ());
        stm.setObject(3,a.getP_Name ());
        stm.setObject(4,a.getW_ID ());
        stm.setObject(5,a.getR_ID ());
        stm.setObject(6,a.getM_or_f ());
        stm.setObject(7,a.getDischarge_Date ());

        return stm.executeUpdate()>0;
    }
    public ArrayList<DischargePatient> getAllDischargePatient() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM dischargePatient");
        ResultSet rst = stm.executeQuery();
        ArrayList<DischargePatient> dischargePatients = new ArrayList<>();
        while (rst.next()) {
            dischargePatients.add(new DischargePatient (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return dischargePatients;
    }
}
