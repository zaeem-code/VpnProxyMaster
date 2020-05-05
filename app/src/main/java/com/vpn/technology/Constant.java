package com.vpn.technology;

import android.content.Context;
import android.content.SharedPreferences;

public class Constant {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;




    public static final String UpgradePro = "VPNPrxyMaster";
    private static final String PREF_NAME = "snow-intro-slider";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public Constant(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }




}