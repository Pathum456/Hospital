package P_Controller;

import db.DbConnection;

import module.Appointment;
import module.Ward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardController {
    public boolean saveWard(Ward w) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO ward VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,w.getW_Id ());
        stm.setObject(2,w.getW_Name ());
        stm.setObject(3,w.getW_D_ID ());
        stm.setObject(4,w.getW_ROOM_Qty ());

        return stm.executeUpdate()>0;
    }
    public boolean deleteWard(String W_Id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM ward WHERE W_Id='"+W_Id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public Ward getWard(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM ward WHERE W_Id=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Ward (
                    rst.getString (1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString (4)
            );

        } else {
            return null;
        }
    }
    public String setWardId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT  W_Id FROM ward ORDER BY  W_Id DESC LIMIT 1").executeQuery ( );
        if (rst.next ( )) {

            int tempId = Integer.
                    parseInt (rst.getString (1).split ("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "W00-00" + tempId;
            } else if (tempId < 99) {
                return "W00-0" + tempId;
            } else {
                return "W00-" + tempId;
            }

        } else {
            return "W00-001";
        }
    }
    public ArrayList<Ward> getAllWards() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM ward ");
        ResultSet rst = stm.executeQuery();
        ArrayList<Ward> wards = new ArrayList<>();
        while (rst.next()) {
            wards.add(new Ward (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString (4)
            ));
        }
        return wards;
    }
    public List<String> getW_ids() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM ward").executeQuery();
        List<String> w_IDS= new ArrayList<> ();
        while (rst.next()){
            w_IDS.add(
                    rst.getString(1)
            );
        }
        return w_IDS;
    }public static List<String> getW_R_Qty() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM ward").executeQuery();
        List<String> w_R_Qty= new ArrayList<> ();
        while (rst.next()){
            w_R_Qty.add(
                    rst.getString(4)
            );
        }
        return w_R_Qty;
    }
}
