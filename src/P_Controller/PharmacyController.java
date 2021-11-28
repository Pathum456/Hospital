package P_Controller;

import db.DbConnection;
import module.Appointment;
import module.Patient;
import module.Pharmacy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PharmacyController {
    public boolean savePharmacy(Pharmacy p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO pharmacy VALUES(?,?,?,?)");
        stm.setObject(1,p.getPh_Id ());
        stm.setObject(2,p.getName ());
        stm.setObject(3,p.getContact ());
        stm.setObject(4,p.getAddress ());


        return stm.executeUpdate()>0;
    }
    public String setPhIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT PH_Id FROM  pharmacy ORDER BY PH_Id DESC LIMIT 1").executeQuery ( );
        if (rst.next ( )) {

            int tempId = Integer.
                    parseInt (rst.getString (1).split ("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "PH00-00" + tempId;
            } else if (tempId < 99) {
                return "PH00-0" + tempId;
            } else {
                return "PH00-" + tempId;
            }

        } else {
            return "PH00-001";
        }
    }
    public boolean deletePharmacy(String id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM pharmacy WHERE PH_Id='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public Pharmacy getPharmacy(String ID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM pharmacy WHERE PH_Id='"+ID+"'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Pharmacy (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );

        } else {
            return null;
        }
    }/*public Pharmacy getAllPharmacy() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM pharmacy ");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Pharmacy (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );

        } else {
            return null;
        }
    }*/
    public ArrayList<Pharmacy> getAllPharmacy() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM pharmacy ");
        ResultSet rst = stm.executeQuery();
        ArrayList<Pharmacy> pharmacies= new ArrayList<>();
        while (rst.next()) {
            pharmacies.add(new Pharmacy (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return pharmacies;
    }
}
