package module;

import java.sql.DataTruncation;
import java.sql.Date;

public class Bill {
    private String B_ID;
    private String p_ID;
    private String p_Name;
    private String w_Id;
    private String r_Id;
    private String bed_Id;
    private String description;
    private double d_Charge;
    private double r_Charge;
    private double bed_Charge;
    private double m_Charges;
    private double e_Charges;
    private double amount;
    private String date;
    public Bill() {
    }

    public Bill(String b_ID, String p_ID, String p_Name, String w_Id, String r_Id, String bed_Id, String description, double d_Charge, double r_Charge, double bed_Charge, double m_Charges, double e_Charges, double amount, String date) {
        B_ID = b_ID;
        this.p_ID = p_ID;
        this.p_Name = p_Name;
        this.w_Id = w_Id;
        this.r_Id = r_Id;
        this.bed_Id = bed_Id;
        this.description = description;
        this.d_Charge = d_Charge;
        this.r_Charge = r_Charge;
        this.bed_Charge = bed_Charge;
        this.m_Charges = m_Charges;
        this.e_Charges = e_Charges;
        this.amount = amount;
        this.date = date;
    }

    public String getB_ID() {
        return B_ID;
    }

    public void setB_ID(String b_ID) {
        B_ID = b_ID;
    }

    public String getP_ID() {
        return p_ID;
    }

    public void setP_ID(String p_ID) {
        this.p_ID = p_ID;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getW_Id() {
        return w_Id;
    }

    public void setW_Id(String w_Id) {
        this.w_Id = w_Id;
    }

    public String getR_Id() {
        return r_Id;
    }

    public void setR_Id(String r_Id) {
        this.r_Id = r_Id;
    }

    public String getBed_Id() {
        return bed_Id;
    }

    public void setBed_Id(String bed_Id) {
        this.bed_Id = bed_Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getD_Charge() {
        return d_Charge;
    }

    public void setD_Charge(double d_Charge) {
        this.d_Charge = d_Charge;
    }

    public double getR_Charge() {
        return r_Charge;
    }

    public void setR_Charge(double r_Charge) {
        this.r_Charge = r_Charge;
    }

    public double getBed_Charge() {
        return bed_Charge;
    }

    public void setBed_Charge(double bed_Charge) {
        this.bed_Charge = bed_Charge;
    }

    public double getM_Charges() {
        return m_Charges;
    }

    public void setM_Charges(double m_Charges) {
        this.m_Charges = m_Charges;
    }

    public double getE_Charges() {
        return e_Charges;
    }

    public void setE_Charges(double e_Charges) {
        this.e_Charges = e_Charges;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "B_ID='" + B_ID + '\'' +
                ", p_ID='" + p_ID + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", w_Id='" + w_Id + '\'' +
                ", r_Id='" + r_Id + '\'' +
                ", bed_Id='" + bed_Id + '\'' +
                ", description='" + description + '\'' +
                ", d_Charge=" + d_Charge +
                ", r_Charge=" + r_Charge +
                ", bed_Charge=" + bed_Charge +
                ", m_Charges=" + m_Charges +
                ", e_Charges=" + e_Charges +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
