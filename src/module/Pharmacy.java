package module;

public class Pharmacy {
    private String ph_Id;
    private String name;
    private String contact;
    private String address;


    public Pharmacy() {
    }

    public Pharmacy(String ph_Id, String name, String contact, String address) {
        this.ph_Id = ph_Id;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public String getPh_Id() {
        return ph_Id;
    }

    public void setPh_Id(String ph_Id) {
        this.ph_Id = ph_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "ph_Id='" + ph_Id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
