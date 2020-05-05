package com.vpn.technology.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.net.VpnService;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.multidex.BuildConfig;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.vpn.technology.R;
import com.vpn.technology.model.Server;
import com.vpn.technology.util.PropertiesService;

import static com.vpn.technology.Constant.UpgradePro;


public class MainActivity extends BaseActivity implements View.OnClickListener {



    RelativeLayout settinglyt,conactuslyt,sharelyt,ratuelyt,aboutlyt;
    TextView settingtxt,contacttxt,sharetxt,ratustxt,abouttxt;

    ImageView settingtxtimg,contacttxtimg,sharetxtimg,ratustxtimg,abouttxtimg;
    public static final String EXTRA_COUNTRY = "country";


    ImageView connect_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));

        connect_btn=findViewById(R.id.connect);
//        findViewById(R.id.flag).setOnClickListener(this);
//        findViewById(R.id.lyt).setOnClickListener(this);

//        settingtxtimg=findViewById(R.id.settingimg);
//        settingtxtimg.setOnClickListener(this);

        contacttxtimg=findViewById(R.id.contactusimg);
        contacttxtimg.setOnClickListener(this);

        sharetxtimg=findViewById(R.id.shareimg);
        sharetxtimg.setOnClickListener(this);

        ratustxtimg=findViewById(R.id.rateimg);
        ratustxtimg.setOnClickListener(this);

        abouttxtimg=findViewById(R.id.abtimg);
        abouttxtimg.setOnClickListener(this);


//        settinglyt=findViewById(R.id.settinglyt); settinglyt.setOnClickListener(this);
//        settingtxt=findViewById(R.id.settingtxt); settingtxt.setOnClickListener(this);

        conactuslyt=findViewById(R.id.contactuslyt); conactuslyt.setOnClickListener(this);
        contacttxt=findViewById(R.id.contactustxt); contacttxt.setOnClickListener(this);

        sharelyt=findViewById(R.id.sharelyt); sharelyt.setOnClickListener(this);
        sharetxt=findViewById(R.id.sharetxt); sharetxt.setOnClickListener(this);

        ratuelyt=findViewById(R.id.ratelyt); ratuelyt.setOnClickListener(this);
        ratustxt=findViewById(R.id.ratetxt); ratustxt.setOnClickListener(this);

        aboutlyt=findViewById(R.id.abtlyt); aboutlyt.setOnClickListener(this);
        abouttxt=findViewById(R.id.abttxt); abouttxt.setOnClickListener(this);

        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        procheck();




        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendTouchButton("homeBtnRandomConnection");
                Server randomServer = getRandomServer();
                if (randomServer != null) {
                    newConnecting(randomServer, true, true);
                } else {
                    String randomError = String.format(getResources().getString(R.string.error_random_country), PropertiesService.getSelectedCountry());
                    Toast.makeText(MainActivity.this, randomError, Toast.LENGTH_LONG).show();
                }

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

    if (BaseActivity.connectedServer==null)
    {
        Toast.makeText(this, "no vpn connect", Toast.LENGTH_SHORT).show();
    }
    else
    {
        Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
    }


    }








    @Override
    protected void onDestroy() {

        super.onDestroy();
    }



    @Override
    protected boolean useHomeButton() {
        return true;
    }








    public void onClick(View v) {

        switch (v.getId())
        {



//            case R.id.settinglyt:
//            case R.id.settingtxt:
//            case R.id.settingimg:
//
//                settingtxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.default_color, null));
//                contacttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
//                sharetxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
//                ratustxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
//                abouttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
//
//
//
//
//                ImageViewCompat.setImageTintList(settingtxtimg, ColorStateList.valueOf((ContextCompat.getColor(getApplicationContext(), R.color.default_color))));
//                ImageViewCompat.setImageTintList(contacttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));
//                ImageViewCompat.setImageTintList(sharetxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));
//                ImageViewCompat.setImageTintList(ratustxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));
//                ImageViewCompat.setImageTintList(abouttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));
//                sendTouchButton("Settings");
////                startActivity(new Intent(this, SettingsActivity.class));
//
//
//                break;



            case R.id.contactuslyt:
            case R.id.contactustxt:
            case R.id.contactusimg:

                settingtxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                contacttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.default_color, null));
                sharetxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                ratustxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                abouttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));



                ImageViewCompat.setImageTintList(settingtxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black)));
                ImageViewCompat.setImageTintList(contacttxtimg, ColorStateList.valueOf((ContextCompat.getColor(getApplicationContext(), R.color.default_color))));
                ImageViewCompat.setImageTintList(sharetxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));
                ImageViewCompat.setImageTintList(ratustxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));
                ImageViewCompat.setImageTintList(abouttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));


                startActivity(new Intent(this,contactus.class));
                break;

            case R.id.sharelyt:
            case R.id.sharetxt:
            case R.id.shareimg:

                shareApp(this);
                settingtxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                contacttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                sharetxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.default_color, null));
                ratustxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                abouttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));






                ImageViewCompat.setImageTintList(settingtxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black)));
                ImageViewCompat.setImageTintList(contacttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black )));
                ImageViewCompat.setImageTintList(sharetxtimg, ColorStateList.valueOf((ContextCompat.getColor(getApplicationContext(), R.color.default_color))));
                ImageViewCompat.setImageTintList(ratustxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));
                ImageViewCompat.setImageTintList(abouttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));

                break;

            case R.id.ratelyt:
            case R.id.ratetxt:
            case R.id.rateimg:


                settingtxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                contacttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                sharetxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                ratustxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.default_color, null));
                abouttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));



                ImageViewCompat.setImageTintList(settingtxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black)));
                ImageViewCompat.setImageTintList(contacttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black )));
                ImageViewCompat.setImageTintList(sharetxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black)));
                ImageViewCompat.setImageTintList(ratustxtimg, ColorStateList.valueOf( (ContextCompat.getColor(getApplicationContext(),R.color.default_color))));
                ImageViewCompat.setImageTintList(abouttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.black)));

                rateApp(getApplicationContext());

                break;
            case R.id.abtlyt:
            case R.id.abttxt:
            case R.id.abtimg:

                settingtxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                contacttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                sharetxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                ratustxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black, null));
                abouttxt.setTextColor(ResourcesCompat.getColor(getResources(),R.color.default_color, null));



                ImageViewCompat.setImageTintList(settingtxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black)));
                ImageViewCompat.setImageTintList(contacttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black )));
                ImageViewCompat.setImageTintList(sharetxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black)));
                ImageViewCompat.setImageTintList(ratustxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.black )));
                ImageViewCompat.setImageTintList(abouttxtimg, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.default_color)));
                startActivity(new Intent(getApplicationContext(),about.class));
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }






    public void shareApp(Context context)
    {


        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "" + getApplicationContext().getPackageName();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void rateApp(Context context)
    {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName())));


    }



















    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }
        });
        toggle.syncState();
    }

    private Toolbar initToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    public void procheck()
    {
        boolean strPref = false;
        SharedPreferences shf = this.getSharedPreferences("config", MODE_PRIVATE);
        strPref = shf.getBoolean(UpgradePro, false);

        if(strPref)
        {
            AdView mAdMobAdView = findViewById(R.id.admob_adview);
            mAdMobAdView.setVisibility(View.GONE);


        }
        else {
            AdView mAdMobAdView = findViewById(R.id.admob_adview);
                AdRequest adRequest = new AdRequest.Builder()
                        .build();
                mAdMobAdView.loadAd(adRequest);

            final InterstitialAd mInterstitial = new InterstitialAd(this);
            mInterstitial.setAdUnitId(getString(R.string.interstitial_ad_unit));
            mInterstitial.loadAd(new AdRequest.Builder().build());
            mInterstitial.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // TODO Auto-generated method stub
                    super.onAdLoaded();
                    if (mInterstitial.isLoaded()) {
                        mInterstitial.show();
                    }
                }
            });



                }

            }




        }



