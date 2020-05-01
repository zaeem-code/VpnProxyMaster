package com.vpn.technology.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vpn.technology.R;
import com.vpn.technology.SQLiteHandler;
import com.vpn.technology.adapter.HomeListAdapter;
import com.vpn.technology.model.Server;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.vpn.technology.activity.BaseActivity.dbHelper;

public class ServerListActivity extends AppCompatActivity {
    private List<Server> countryList;
    private ListView listView;
    private HomeListAdapter serverListAdapter;
    private SQLiteHandler db;
    public static final String EXTRA_COUNTRY = "country";
    CircleImageView circleImage;
    TextView city_Name,ip_Address;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);


        circleImage=findViewById(R.id.imageFlag);
        city_Name=findViewById(R.id.textCity);
        ip_Address=findViewById(R.id.textHostName);




        countryList = dbHelper.getUniqueCountries();
        listView = (ListView) findViewById(R.id.homeCountryList);
        String country = getIntent().getStringExtra(MainActivity.EXTRA_COUNTRY);
        final List<Server> serverList = dbHelper.getUniqueCountries();



        serverListAdapter = new HomeListAdapter(this, serverList);
        listView.setAdapter(serverListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onSelectCountry(countryList.get(position));
                SharedPreferences.Editor editor = getSharedPreferences("server", MODE_PRIVATE).edit();
                editor.putString("country",countryList.get(position).getCountryLong() );
                editor.putString("ip",countryList.get(position).getIp() );
                editor.putString("img",countryList.get(position).getCountryShort().toLowerCase());

                editor.apply();



            }
        });







        final Thread thread=new Thread()
        {
            @Override
            public void run() {

                while (! isInterrupted())
                {
                    try {
                        Thread.sleep(2000);

                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {

                                SharedPreferences prefs = getSharedPreferences("server", MODE_PRIVATE);
                                String name_of_country = prefs.getString("country", "");
                                String ip = prefs.getString("ip", "");
                                String code=prefs.getString("img", "");

                                if (name_of_country!=null && ip!=null &&code!=null)
                                {
                                    city_Name.setText(name_of_country);
                                    ip_Address.setText(ip);
                                    if (code.equals("do"))
                                        code = "dom";


                                    ((de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.imageFlag))
                                            .setImageResource(
                                                    getResources().getIdentifier(code,
                                                            "drawable",
                                                            getPackageName()));

                                }


                            }
                        });

                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }


            }
        };
        thread.start();

















    }


    private void onSelectCountry(Server server) {
        Intent intent = new Intent(getApplicationContext(), VPNListActivity.class);
        intent.putExtra(EXTRA_COUNTRY, server.getCountryShort());
        startActivity(intent);
    }

}
