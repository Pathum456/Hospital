package module;

import java.util.Objects;

public class Ward {
    private String w_Id;
    private String w_Name;
    private String w_D_ID;
    private String w_ROOM_Qty;

    public Ward() {
    }

    public Ward(String w_Id, String w_Name, String w_D_ID, String w_ROOM_Qty) {
        this.w_Id = w_Id;
        this.w_Name = w_Name;
        this.w_D_ID = w_D_ID;
        this.w_ROOM_Qty = w_ROOM_Qty;
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

    public String getW_D_ID() {
        return w_D_ID;
    }

    public void setW_D_ID(String w_D_ID) {
        this.w_D_ID = w_D_ID;
    }

    public String getW_ROOM_Qty() {
        return w_ROOM_Qty;
    }

    public void setW_ROOM_Qty(String w_ROOM_Qty) {
        this.w_ROOM_Qty = w_ROOM_Qty;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "w_Id='" + w_Id + '\'' +
                ", w_Name='" + w_Name + '\'' +
                ", w_D_ID='" + w_D_ID + '\'' +
                ", w_ROOM_Qty='" + w_ROOM_Qty + '\'' +
                '}';
    }
}
