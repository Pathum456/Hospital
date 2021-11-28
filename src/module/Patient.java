package module;

public class Patient {
    private String p_Id;
    private String p_Name;
    private String p_Nic;
    private String p_Contact;
    private String p_Address;

    public Patient() {
    }

    public Patient(String p_Id, String p_Name, String p_Nic, String p_Contact, String p_Address) {
        this.p_Id = p_Id;
        this.p_Name = p_Name;
        this.p_Nic = p_Nic;
        this.p_Contact = p_Contact;
        this.p_Address = p_Address;
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

    public String getP_Nic() {
        return p_Nic;
    }

    public void setP_Nic(String p_Nic) {
        this.p_Nic = p_Nic;
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
        return "Patient{" +
                "p_Id='" + p_Id + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", p_Nic='" + p_Nic + '\'' +
                ", p_Contact='" + p_Contact + '\'' +
                ", p_Address='" + p_Address + '\'' +
                '}';
    }
}
