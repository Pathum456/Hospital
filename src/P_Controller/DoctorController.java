package P_Controller;

import db.DbConnection;
import module.DischargePatient;
import module.Doctor;
import module.Patient;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    public boolean saveDoctor(Doctor d) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance ( ).getConnection ( );
        String query = "INSERT INTO doctor VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement (query);
        stm.setObject (1, d.getD_Id ( ));
        stm.setObject (2, d.getD_Name ( ));
        stm.setObject (3, d.getD_Contact ( ));
        stm.setObject (4, d.getD_Salary ( ));
        stm.setObject (5, d.getD_days ( ));
        stm.setObject (6, d.getD_time ( ));
        return stm.executeUpdate ( ) > 0;
    }

    public List<String> getD_ids() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).
                prepareStatement ("SELECT * FROM doctor").executeQuery ( );
        List<String> d_IDS = new ArrayList<> ( );
        while (rst.next ( )) {
            d_IDS.add (
                    rst.getString (1)
            );
        }
        return d_IDS;
    }

    public boolean deleteDoctor(String D_Id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM doctor WHERE D_Id='"+D_Id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public Doctor getDoctor(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance ( )
                .getConnection ( ).prepareStatement ("SELECT * FROM Doctor WHERE D_Id=?");
        stm.setObject (1, id);

        ResultSet rst = stm.executeQuery ( );
        if (rst.next ( )) {
            return new Doctor (
                    rst.getString (1),
                    rst.getString (2),
                    rst.getString (3),
                    rst.getDouble (4),
                    rst.getString (5),
                    rst.getString (6)
            );

        } else {
            return null;
        }
    }


    public ArrayList<Doctor> getAllDoctors() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT * FROM doctor");
        ResultSet rst = stm.executeQuery ( );
        ArrayList<Doctor> doctors = new ArrayList<> ( );
        while (rst.next ( )) {
            doctors.add (new Doctor (
                    rst.getString (1),
                    rst.getString (2),
                    rst.getString (3),
                    rst.getDouble (4),
                    rst.getString (5),
                    rst.getString (6)
            ));
        }
        return doctors;
    }

    public String setDoctorIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT D_Id FROM doctor ORDER BY D_Id DESC LIMIT 1").executeQuery ( );
        if (rst.next ( )) {

            int tempId = Integer.
                    parseInt (rst.getString (1).split ("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "D00-00" + tempId;
            } else if (tempId < 99) {
                return "D00-0" + tempId;
            } else {
                return "D00-" + tempId;
            }

        } else {
            return "D00-001";
        }
    }
}
