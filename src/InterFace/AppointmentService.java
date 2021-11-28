package InterFace;

import module.Appointment;
import module.Patient;

import java.sql.SQLException;

public interface AppointmentService {
    public boolean saveAppointment(Appointment a) throws SQLException, ClassNotFoundException;
    public boolean deleteAppointment(String a) throws SQLException, ClassNotFoundException;
    public boolean updateAppointment(Appointment a) throws SQLException, ClassNotFoundException;
}
