package com.newsalerts.ef.net_con;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.newsalerts.ef.net_con.util.NetworkReceiver;

public class MainActivity extends AppCompatActivity {
    static TextView textView;
    private NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.text);
        networkReceiver = new NetworkReceiver();

    }

    public static void dialog(boolean status){
        if(status){
            textView.setText("Connected");
            textView.setBackgroundResource(R.color.online);

            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    textView.setVisibility(View.GONE);
                }
            };
            textView.setVisibility(View.VISIBLE);
            handler.postDelayed(delayrunnable, 3000);
        }else {
            textView.setText("No Internet Connection");
            textView.setBackgroundResource(R.color.offline);
            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    textView.setVisibility(View.GONE);
                }
            };
            textView.setVisibility(View.VISIBLE);

//            Toast.makeText(MainActivity.this, "Network Connection is not Available", Toast.LENGTH_LONG).show();
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(networkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterNetworkChanges();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterNetworkChanges();
    }
}
