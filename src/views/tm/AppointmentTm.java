package views.tm;

public class AppointmentTm {
    private String P_Id;
    private String P_Name;
    private String P_contact;
    private String Reason;
    private String D_Id;
    private String D_Name;
    private String Date;
    private String Time;

    public AppointmentTm() {
    }

    public AppointmentTm(String p_Id, String p_Name, String p_contact, String reason, String d_Id, String d_Name, String date, String time, String aTime) {
        P_Id = p_Id;
        P_Name = p_Name;
        P_contact = p_contact;
        Reason = reason;
        D_Id = d_Id;
        D_Name = d_Name;
        Date = date;
        Time = time;
    }

    public String getP_Id() {
        return P_Id;
    }

    public void setP_Id(String p_Id) {
        P_Id = p_Id;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }

    public String getP_contact() {
        return P_contact;
    }

    public void setP_contact(String p_contact) {
        P_contact = p_contact;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getD_Id() {
        return D_Id;
    }

    public void setD_Id(String d_Id) {
        D_Id = d_Id;
    }

    public String getD_Name() {
        return D_Name;
    }

    public void setD_Name(String d_Name) {
        D_Name = d_Name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "AppointmentTm{" +
                "P_Id='" + P_Id + '\'' +
                ", P_Name='" + P_Name + '\'' +
                ", P_contact='" + P_contact + '\'' +
                ", Reason='" + Reason + '\'' +
                ", D_Id='" + D_Id + '\'' +
                ", D_Name='" + D_Name + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
