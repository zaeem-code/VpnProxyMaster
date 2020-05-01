package com.vpn.technology.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
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


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final String EXTRA_COUNTRY = "country";


    ImageView connect_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));

        connect_btn=findViewById(R.id.connect);


        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        initNavigationView();
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






















    private void initNavigationView(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }









    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();



         if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "" + getApplicationContext().getPackageName();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share App");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }else if (id == R.id.rate_us) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName())));
        }
        else if (id == R.id.about_me) {
            aboutMyApp();

        }

        else if (id == R.id.privacypolicy)
        {


        }

        else if (id == R.id.moreapp)
        {

            Uri uri = Uri.parse("market://search?q=pub:" + "PA Production");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("" + "")));
            }


        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }







    private void aboutMyApp() {

        MaterialDialog.Builder bulder = new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .customView(R.layout.about, true)
                .backgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                .titleColorRes(android.R.color.white)
                .positiveText("MORE APPS")
                .positiveColor(getResources().getColor(android.R.color.white))
                .icon(getResources().getDrawable(R.mipmap.ic_launcher))
                .limitIconToDefaultSize()
                .onPositive((dialog, which) -> {
                    Uri uri = Uri.parse("market://search?q=pub:" + "PA Production");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/search?q=pub:" + "PA Production")));
                    }
                });

        MaterialDialog materialDialog = bulder.build();

        TextView versionCode = (TextView) materialDialog.findViewById(R.id.version_code);
        TextView versionName = (TextView) materialDialog.findViewById(R.id.version_name);
        versionCode.setText(String.valueOf("Version Code : " + BuildConfig.VERSION_CODE));
        versionName.setText(String.valueOf("Version Name : " + BuildConfig.VERSION_NAME));

        materialDialog.show();
    }



    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
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
            AdView mAdMobAdView = (AdView) findViewById(R.id.admob_adview);
            mAdMobAdView.setVisibility(View.GONE);


        }
        else {
            AdView mAdMobAdView = (AdView) findViewById(R.id.admob_adview);
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



