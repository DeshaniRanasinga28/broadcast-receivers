package com.newsalerts.ef.net_con.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.*;
import android.util.Log;
import android.widget.Toast;

import com.newsalerts.ef.net_con.MainActivity;

import org.greenrobot.eventbus.EventBus;

import static com.newsalerts.ef.net_con.MainActivity.dialog;

/**
 * Created by EF on 07-Feb-18.
 */

public class NetworkReceiver extends BroadcastReceiver{
    private static String TAG = NetworkReceiver.class.getSimpleName();

    public NetworkReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (checkInternetConnection(context)) {
            dialog(true);
            Toast.makeText(context , "Connected", Toast.LENGTH_LONG).show();
        } else {
            dialog(false);
            Toast.makeText(context, "Network Connection is not Available", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkInternetConnection(Context context){
        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(netInfo != null && netInfo.isConnected()){
                return true;
            }else{
                return false;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }

}
