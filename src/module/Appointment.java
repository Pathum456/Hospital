package module;

import javafx.scene.control.SingleSelectionModel;

public class Appointment {
    private String p_Id;
    private String p_Name;
    private String P_NIC;
    private String P_Contact;
    private String reason;
    private String d_Id;
    private String d_Name;
    private String date;
    private String time;

    public Appointment(String text, String p_name_txtText, String p_nic_txtText, String p_contact_txtText, String p_reason_txtText, String d_name_lblText, SingleSelectionModel selectionModel, String d_date_lblText, String d_time_lblText) {
    }

    public Appointment(String p_Id, String p_Name, String p_NIC, String p_Contact, String reason, String d_Id, String d_Name, String date, String time) {
        this.p_Id = p_Id;
        this.p_Name = p_Name;
        P_NIC = p_NIC;
        P_Contact = p_Contact;
        this.reason = reason;
        this.d_Id = d_Id;
        this.d_Name = d_Name;
        this.date = date;
        this.time = time;
    }

    public String getP_Id() {
        return p_Id;
    }

    public void setP_Id(String p_Id) {
        this.p_Id = p_Id;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getP_NIC() {
        return P_NIC;
    }

    public void setP_NIC(String p_NIC) {
        P_NIC = p_NIC;
    }

    public String getP_Contact() {
        return P_Contact;
    }

    public void setP_Contact(String p_Contact) {
        P_Contact = p_Contact;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getD_Id() {
        return d_Id;
    }

    public void setD_Id(String d_Id) {
        this.d_Id = d_Id;
    }

    public String getD_Name() {
        return d_Name;
    }

    public void setD_Name(String d_Name) {
        this.d_Name = d_Name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "p_Id='" + p_Id + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", P_NIC='" + P_NIC + '\'' +
                ", P_Contact='" + P_Contact + '\'' +
                ", reason='" + reason + '\'' +
                ", d_Id='" + d_Id + '\'' +
                ", d_Name='" + d_Name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
