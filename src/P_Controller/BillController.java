package P_Controller;

import db.DbConnection;
import module.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillController {
    public boolean SaveBill(Bill b) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance ( ).getConnection ( );
        String query = "INSERT INTO bill VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement (query);
        stm.setObject (1, b.getB_ID ( ));
        stm.setObject (2, b.getP_ID ( ));
        stm.setObject (3, b.getP_Name ( ));
        stm.setObject (4, b.getW_Id ( ));
        stm.setObject (5, b.getR_Id ( ));
        stm.setObject (6, b.getBed_Id ( ));
        stm.setObject (7, b.getDescription ( ));
        stm.setObject (8, b.getD_Charge ( ));
        stm.setObject (9, b.getR_Charge ( ));
        stm.setObject (10, b.getBed_Charge ( ));
        stm.setObject (11, b.getM_Charges ( ));
        stm.setObject (12, b.getE_Charges ( ));
        stm.setObject (13, b.getAmount ( ));
        stm.setObject (14, b.getDate ( ));

        return stm.executeUpdate ( ) > 0;
    }

    public String setBillIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT B_Id FROM bill ORDER BY B_Id DESC LIMIT 1").executeQuery ( );
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

}
