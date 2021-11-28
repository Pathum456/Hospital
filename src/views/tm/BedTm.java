package views.tm;

public class BedTm {
    private String b_Id;
    private String r_Id;
    private double b_Charges;
    public BedTm() {
    }
    public BedTm(String b_Id, String r_Id, double b_Charges) {
        this.b_Id = b_Id;
        this.r_Id = r_Id;
        this.b_Charges = b_Charges;
    }

    public String getB_Id() {
        return b_Id;
    }

    public void setB_Id(String b_Id) {
        this.b_Id = b_Id;
    }

    public String getR_Id() {
        return r_Id;
    }

    public void setR_Id(String r_Id) {
        this.r_Id = r_Id;
    }

    public double getB_Charges() {
        return b_Charges;
    }

    public void setB_Charges(double b_Charges) {
        this.b_Charges = b_Charges;
    }

    @Override
    public String toString() {
        return "BedTm{" +
                "b_Id='" + b_Id + '\'' +
                ", r_Id='" + r_Id + '\'' +
                ", b_Charges=" + b_Charges +
                '}';
    }
}
