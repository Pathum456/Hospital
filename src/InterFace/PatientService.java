package InterFace;

import module.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientService {
    public boolean savePatient(Patient p) throws SQLException, ClassNotFoundException;
    public boolean updatePatient(Patient p) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public Patient  getPatient(String P_ID) throws SQLException, ClassNotFoundException;
    public ArrayList<Patient > getAllPatient() throws SQLException, ClassNotFoundException;

    //boolean deletePatient(String text);
}
