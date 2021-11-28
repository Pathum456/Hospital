package module;



public class Doctor {
    private String d_Id;
    private String d_Name;
    private String d_Contact;
    private double d_Salary;
    private String d_days;
    private String d_time;

    public Doctor( ) {
    }

    public Doctor(String d_Id, String d_Name, String d_Contact, double d_Salary, String d_days, String d_time) {
        this.d_Id = d_Id;
        this.d_Name = d_Name;
        this.d_Contact = d_Contact;
        this.d_Salary = d_Salary;
        this.d_days = d_days;
        this.d_time = d_time;
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

    public String getD_Contact() {
        return d_Contact;
    }

    public void setD_Contact(String d_Contact) {
        this.d_Contact = d_Contact;
    }

    public double getD_Salary() {
        return d_Salary;
    }

    public void setD_Salary(double d_Salary) {
        this.d_Salary = d_Salary;
    }

    public String getD_days() {
        return d_days;
    }

    public void setD_days(String d_days) {
        this.d_days = d_days;
    }

    public String getD_time() {
        return d_time;
    }

    public void setD_time(String d_time) {
        this.d_time = d_time;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "d_Id='" + d_Id + '\'' +
                ", d_Name='" + d_Name + '\'' +
                ", d_Contact='" + d_Contact + '\'' +
                ", d_Salary=" + d_Salary +
                ", d_days='" + d_days + '\'' +
                ", d_time='" + d_time + '\'' +
                '}';
    }
}
