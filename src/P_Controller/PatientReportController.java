package P_Controller;

import db.DbConnection;

import module.Patient;
import module.PatientReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientReportController {
    public boolean savePatientReport(PatientReport p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO medical_report VALUES(?,?,?,?,?,?)");
        stm.setObject(1,p.getMR_Id ());
        stm.setObject(2,p.getP_Id ());
        stm.setObject(3,p.getP_NIC ());
        stm.setObject(4,p.getP_Name ());
        stm.setObject(5,p.getReason ());
        stm.setObject(6,p.getDate ());

        return stm.executeUpdate()>0;
    }
    public boolean deletePatientReport(String MR_Id) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM medical_report WHERE MR_Id='"+MR_Id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public PatientReport getPatientReport(String P_NIc) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM medical_report WHERE P_NIc='"+P_NIc+"'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new PatientReport (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );

        } else {
            return null;
        }
    }
    public ArrayList<PatientReport> getAllPatientReport() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM medical_report");
        ResultSet rst = stm.executeQuery();
        ArrayList<PatientReport> reports = new ArrayList<>();
        while (rst.next()) {
            reports.add(new PatientReport (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return reports;
    }
    public String setReportId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance ( ).getConnection ( ).prepareStatement ("SELECT MR_Id FROM  medical_report ORDER BY MR_Id DESC LIMIT 1").executeQuery ( );
        if (rst.next ( )) {

            int tempId = Integer.
                    parseInt (rst.getString (1).split ("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "MR00-00" + tempId;
            } else if (tempId < 99) {
                return "MR00-0" + tempId;
            } else {
                return "MR00-" + tempId;
            }

        } else {
            return "MR00-001";
        }
    }
}
