package P_Controller;

import db.DbConnection;
import module.Room;
import module.Ward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomController {
    public static String setRoomIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT R_Id FROM room ORDER BY R_Id DESC LIMIT 1").executeQuery ( );
        if (rst.next ( )) {

            int tempId = Integer.
                    parseInt (rst.getString (1).split ("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "R00-00" + tempId;
            } else if (tempId < 99) {
                return "R00-0" + tempId;
            } else {
                return "R00-" + tempId;
            }

        } else {
            return "R00-001";
        }
    }

    public boolean saveRoom(Room r) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO room VALUES(?,?,?)");
        stm.setObject(1,r.getR_Id ());
        stm.setObject(2,r.getW_Id ());
        stm.setObject(3,r.getR_Charge ());

        return stm.executeUpdate()>0;
    }

    public List<String> getRoom_ids(String r) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).
                prepareStatement ("select * FROM room WHERE W_Id='" + r + "'").executeQuery ( );
        List<String> r_IDS = new ArrayList<> ( );
        while (rst.next ( )) {
            r_IDS.add (
                    rst.getString (1)
            );
            System.out.println (r_IDS);
        }
        return r_IDS;

    }
    public Room getRoom(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM room WHERE R_Id=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Room (
                    rst.getString (1),
                    rst.getString(2),
                    rst.getDouble (3)

            );

        } else {
            return null;
        }
    }
    public boolean deleteRoom(String R_Id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM room WHERE R_Id='"+R_Id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM room ");
        ResultSet rst = stm.executeQuery();
        ArrayList<Room> rooms = new ArrayList<>();
        while (rst.next()) {
            rooms.add(new Room (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble (3)
            ));
        }
        return rooms;
    }

    public List<String> getRoom_id() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).
                prepareStatement ("select * FROM room ").executeQuery ( );
        List<String> r_IDS = new ArrayList<> ( );
        while (rst.next ( )) {
            r_IDS.add (
                    rst.getString (1)
            );
            System.out.println (r_IDS);
        }
        return r_IDS;

    }


}
