package com.example.easygo.utility;

import com.example.easygo.R;

public class Pictures {

    public static int get(String profilePicture){
        switch (profilePicture) {
            case "ana.jpg":
                return R.drawable.profile1;
            case "mirko.jpg":
                return R.drawable.mirko_ivanic;
            case "dejan.jpg":
                return R.drawable.dejan_stankovic;
            case "sale.jpg":
                return R.drawable.sale_katai;
            case "nemanja.jpg":
                return R.drawable.osman_bukari;
            case "vladan.jpg":
                return R.drawable.vladan_milojevic;
            case "milos.jpg":
                return R.drawable.milos_milojevic;
            default:
                return R.drawable.profile_default;
        }
    }
}
