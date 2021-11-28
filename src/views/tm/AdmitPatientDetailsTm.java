package views.tm;

public class AdmitPatientDetailsTm {
    private String p_ID;
    private String p_Name;
    private String p_Nic;
    private String w_ID;
    private String r_ID;
    private String m_or_f;
    private String admit_Date;

    public AdmitPatientDetailsTm() {
    }

    public AdmitPatientDetailsTm(String p_ID, String p_Name, String p_Nic, String w_ID, String r_ID, String m_or_f, String admit_Date) {
        this.p_ID = p_ID;
        this.p_Name = p_Name;
        this.p_Nic = p_Nic;
        this.w_ID = w_ID;
        this.r_ID = r_ID;
        this.m_or_f = m_or_f;
        this.admit_Date = admit_Date;
    }

    public String getP_ID() {
        return p_ID;
    }

    public void setP_ID(String p_ID) {
        this.p_ID = p_ID;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getP_Nic() {
        return p_Nic;
    }

    public void setP_Nic(String p_Nic) {
        this.p_Nic = p_Nic;
    }

    public String getW_ID() {
        return w_ID;
    }

    public void setW_ID(String w_ID) {
        this.w_ID = w_ID;
    }

    public String getR_ID() {
        return r_ID;
    }

    public void setR_ID(String r_ID) {
        this.r_ID = r_ID;
    }

    public String getM_or_f() {
        return m_or_f;
    }

    public void setM_or_f(String m_or_f) {
        this.m_or_f = m_or_f;
    }

    public String getAdmit_Date() {
        return admit_Date;
    }

    public void setAdmit_Date(String admit_Date) {
        this.admit_Date = admit_Date;
    }

    @Override
    public String toString() {
        return "AdmitPatientDetailsTm{" +
                "p_ID='" + p_ID + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", p_Nic='" + p_Nic + '\'' +
                ", w_ID='" + w_ID + '\'' +
                ", r_ID='" + r_ID + '\'' +
                ", m_or_f='" + m_or_f + '\'' +
                ", admit_Date='" + admit_Date + '\'' +
                '}';
    }
}
