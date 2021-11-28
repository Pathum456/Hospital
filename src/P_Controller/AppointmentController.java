package P_Controller;

import InterFace.AppointmentService;
import db.DbConnection;
import module.Appointment;
import module.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentController implements AppointmentService {
    @Override
    public boolean saveAppointment(Appointment a) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO patientShedul VALUES (?,?,?,?,?,?,?,?,?)");
        stm.setObject(1,a.getP_Id ());
        stm.setObject(2,a.getP_Name ());
        stm.setObject(3,a.getP_NIC ());
        stm.setObject(4,a.getP_Contact ());
        stm.setObject(5,a.getReason ());
        stm.setObject(6,a.getD_Id ());
        stm.setObject(7,a.getD_Name ());
        stm.setObject(8,a.getDate ());
        stm.setObject(9,a.getTime ());

        System.out.println ( a.getD_Id ());
        System.out.println ( a.getD_Name ());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteAppointment(String a) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM patientShedul WHERE P_ID='"+a+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Appointment> getAllAppointment() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM patientshedul ");
        ResultSet rst = stm.executeQuery();
        ArrayList<Appointment> appointments = new ArrayList<>();
        while (rst.next()) {
            appointments.add(new Appointment (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return appointments;
    }
    public Appointment getAppointment(String P_NIC) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM patientshedul WHERE P_NIC='"+P_NIC+"'");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Appointment (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );

        } else {
            return null;
        }
    }



    @Override
    public boolean updateAppointment(Appointment a) throws SQLException, ClassNotFoundException {
        return false;
    }
}
