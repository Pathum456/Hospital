package module;

public class PatientReport {
    private String MR_Id;
    private String p_Id;
    private String p_NIC;
    private String p_Name;
    private String reason;
    private String date;


    public PatientReport() {
    }

    public PatientReport(String MR_Id, String p_Id, String p_NIC, String p_Name, String date, String reason) {
        this.MR_Id = MR_Id;
        this.p_Id = p_Id;
        this.p_NIC = p_NIC;
        this.p_Name = p_Name;
        this.date = date;
        this.reason = reason;
    }

    public String getMR_Id() {
        return MR_Id;
    }

    public void setMR_Id(String MR_Id) {
        this.MR_Id = MR_Id;
    }

    public String getP_Id() {
        return p_Id;
    }

    public void setP_Id(String p_Id) {
        this.p_Id = p_Id;
    }

    public String getP_NIC() {
        return p_NIC;
    }

    public void setP_NIC(String p_NIC) {
        this.p_NIC = p_NIC;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "PatientReportTm{" +
                "MR_Id='" + MR_Id + '\'' +
                ", p_Id='" + p_Id + '\'' +
                ", p_NIC='" + p_NIC + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
