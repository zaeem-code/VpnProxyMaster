package com.vpn.technology.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.vpn.technology.R;
import com.vpn.technology.model.MOREAPPS;


public class about extends AppCompatActivity {
//d


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);
findViewById(R.id.moreapp).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        moreapps();
    }
});

    }
    public void moreapps() {
      new MOREAPPS(getApplicationContext());

    }
}
