package P_Controller;

import db.DbConnection;
import module.AdmitPatient;
import module.Bed;
import module.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BedController {
    public List<String> getBed_ids(String b) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM bed where R_Id='"+b+"'").executeQuery();
        List<String> bed_IDS= new ArrayList<> ();
        while (rst.next()){
            bed_IDS.add(
                    rst.getString(1)
            );
        }
        return bed_IDS;
    }public List<String> getBed_id() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM bed ").executeQuery();
        List<String> bed_IDS= new ArrayList<> ();
        while (rst.next()){
            bed_IDS.add(
                    rst.getString(1)
            );
        }
        return bed_IDS;
    }
    public Bed getBed(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM bed WHERE B_Id=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Bed (
                    rst.getString (1),
                    rst.getDouble (2),
                    rst.getString (3)

            );

        } else {
            return null;
        }
    }
    public ArrayList<Bed> getAllBeds() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM bed ");
        ResultSet rst = stm.executeQuery();
        ArrayList<Bed> beds = new ArrayList<>();
        while (rst.next()) {
            beds.add(new Bed (
                    rst.getString(1),
                    rst.getDouble (2),
                    rst.getString (3)
            ));
        }
        return beds;
    }
    public static String setBedIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT B_Id FROM bed ORDER BY B_Id DESC LIMIT 1").executeQuery ( );
        if (rst.next ( )) {

            int tempId = Integer.
                    parseInt (rst.getString (1).split ("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "B00-00" + tempId;
            } else if (tempId < 99) {
                return "B00-0" + tempId;
            } else {
                return "B00-" + tempId;
            }

        } else {
            return "B00-001";
        }
    }
    public Bed getBedCharge(String B_ID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM bed WHERE B_Id='"+B_ID+"'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Bed (
                    rst.getString(1),
                    rst.getDouble (2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }
    public boolean deleteBed(String B_Id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Bed WHERE B_Id='"+B_Id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public boolean saveBed(Bed b) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO bed VALUES(?,?,?)");
        stm.setObject(1,b.getB_Id ());
        stm.setObject(2,b.getB_Charge ());
        stm.setObject(3,b.getR_Id ());

        return stm.executeUpdate()>0;
    }
}
