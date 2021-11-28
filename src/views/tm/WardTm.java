package views.tm;

import java.sql.PreparedStatement;

public class WardTm {
    private String w_Id;
    private String w_Name;
    private String w_D_In_Charge;
    private String  r_Qty;

    public WardTm() {
    }

    public WardTm(String w_Id, String w_Name, String w_D_In_Charge, String r_Qty) {
        this.w_Id = w_Id;
        this.w_Name = w_Name;
        this.w_D_In_Charge = w_D_In_Charge;
        this.r_Qty = r_Qty;
    }

    public String getW_Id() {
        return w_Id;
    }

    public void setW_Id(String w_Id) {
        this.w_Id = w_Id;
    }

    public String getW_Name() {
        return w_Name;
    }

    public void setW_Name(String w_Name) {
        this.w_Name = w_Name;
    }

    public String getW_D_In_Charge() {
        return w_D_In_Charge;
    }

    public void setW_D_In_Charge(String w_D_In_Charge) {
        this.w_D_In_Charge = w_D_In_Charge;
    }

    public String getR_Qty() {
        return r_Qty;
    }

    public void setR_Qty(String r_Qty) {
        this.r_Qty = r_Qty;
    }

    @Override
    public String toString() {
        return "WardTm{" +
                "w_Id='" + w_Id + '\'' +
                ", w_Name='" + w_Name + '\'' +
                ", w_D_In_Charge='" + w_D_In_Charge + '\'' +
                ", r_Qty='" + r_Qty + '\'' +
                '}';
    }
}
