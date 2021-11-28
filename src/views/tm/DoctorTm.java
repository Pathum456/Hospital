package views.tm;

public class DoctorTm {
    private String d_Id;
    private String D_Name;
    private String contact;
    private double salary;
    private String days;
    private String time;



    public DoctorTm() {
    }

    public DoctorTm(String d_Id, String d_Name, String contact, double salary, String days, String time) {
        this.d_Id = d_Id;
        D_Name = d_Name;
        this.contact = contact;
        this.salary = salary;
        this.days = days;
        this.time = time;
    }

    public String getD_Id() {
        return d_Id;
    }

    public void setD_Id(String d_Id) {
        this.d_Id = d_Id;
    }

    public String getD_Name() {
        return D_Name;
    }

    public void setD_Name(String d_Name) {
        D_Name = d_Name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DoctorTm{" +
                "d_Id='" + d_Id + '\'' +
                ", D_Name='" + D_Name + '\'' +
                ", contact='" + contact + '\'' +
                ", salary=" + salary +
                ", days='" + days + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
