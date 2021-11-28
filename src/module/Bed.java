package module;

public class Bed {
    private String b_Id;

    private String r_Id;
    private double b_Charge;
    public Bed() {
    }

    public Bed(String b_Id, double b_Charge, String r_Id) {
        this.b_Id = b_Id;
        this.b_Charge = b_Charge;
        this.r_Id = r_Id;
    }

    public String getB_Id() {
        return b_Id;
    }

    public void setB_Id(String b_Id) {
        this.b_Id = b_Id;
    }

    public double getB_Charge() {
        return b_Charge;
    }

    public void setB_Charge(double b_Charge) {
        this.b_Charge = b_Charge;
    }

    public String getR_Id() {
        return r_Id;
    }

    public void setR_Id(String r_Id) {
        this.r_Id = r_Id;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "b_Id='" + b_Id + '\'' +
                ", b_Charge=" + b_Charge +
                ", r_Id='" + r_Id + '\'' +
                '}';
    }
}
