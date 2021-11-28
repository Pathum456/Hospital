package module;

import java.util.Objects;

public class Room {
    private String r_Id;

    private String w_Id;
    private double r_Charge;

    public Room() {
    }

    public Room(String r_Id, String w_Id, double r_Charge) {
        this.r_Id = r_Id;
        this.w_Id = w_Id;
        this.r_Charge = r_Charge;
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

    public double getR_Charge() {
        return r_Charge;
    }

    public void setR_Charge(double r_Charge) {
        this.r_Charge = r_Charge;
    }

    @Override
    public String toString() {
        return "Room{" +
                "r_Id='" + r_Id + '\'' +
                ", w_Id='" + w_Id + '\'' +
                ", r_Charge=" + r_Charge +
                '}';
    }
}
