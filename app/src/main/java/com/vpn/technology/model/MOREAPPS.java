package com.vpn.technology.model;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MOREAPPS {
    Context context;

    public MOREAPPS(Context context) {
        this.context = context;


        Uri uri = Uri.parse("market://search?q=pub:" + "PA Production");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
           context. startActivity(goToMarket);
        } catch (
                ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("" + "")));
        }
    }


}
