package com.example.sanayisepet;

public class DigerID {
    public  static  String otherID;

    public static String getOtherID() {
        return otherID;
    }

    public static void setOtherID(String otherID) {
        DigerID.otherID = otherID;
    }
}

   /* public void tanimla() {
        otherID = DigerID.getOtherID();
        sharedPreferences = this.getSharedPreferences("giris", 0);
        userID = sharedPreferences.getString("uye_id", null);
    }
    String userID, otherID, userTable,otherTable,key;
    SharedPreferences sharedPreferences;*/