package views.tm;

public class PharmacyTm {
    private String ph_Id;
    private String p_Name;
    private String p_Contact;
    private String p_Address;

    public PharmacyTm() {
    }

    public PharmacyTm(String ph_Id, String p_Name, String p_Contact, String p_Address) {
        this.ph_Id = ph_Id;
        this.p_Name = p_Name;
        this.p_Contact = p_Contact;
        this.p_Address = p_Address;
    }

    public String getPh_Id() {
        return ph_Id;
    }

    public void setPh_Id(String ph_Id) {
        this.ph_Id = ph_Id;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getP_Contact() {
        return p_Contact;
    }

    public void setP_Contact(String p_Contact) {
        this.p_Contact = p_Contact;
    }

    public String getP_Address() {
        return p_Address;
    }

    public void setP_Address(String p_Address) {
        this.p_Address = p_Address;
    }

    @Override
    public String toString() {
        return "PharmacyTm{" +
                "ph_Id='" + ph_Id + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", p_Contact='" + p_Contact + '\'' +
                ", p_Address='" + p_Address + '\'' +
                '}';
    }
}
