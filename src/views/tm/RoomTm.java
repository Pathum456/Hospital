package views.tm;

public class RoomTm {
    private String r_Id;
    private String w_Id;
    private double r_Charges;

    public RoomTm() {
    }

    public RoomTm(String r_Id, String w_Id, double r_Charges) {
        this.r_Id = r_Id;
        this.w_Id = w_Id;
        this.r_Charges = r_Charges;
    }

    public String getR_Id() {
        return r_Id;
    }

    public void setR_Id(String r_Id) {
        this.r_Id = r_Id;
    }

    public String getW_Id() {
        return w_Id;
    }

    public void setW_Id(String w_Id) {
        this.w_Id = w_Id;
    }

    public double getR_Charges() {
        return r_Charges;
    }

    public void setR_Charges(double r_Charges) {
        this.r_Charges = r_Charges;
    }

    @Override
    public String toString() {
        return "RoomTm{" +
                "r_Id='" + r_Id + '\'' +
                ", w_Id='" + w_Id + '\'' +
                ", r_Charges=" + r_Charges +
                '}';
    }
}
