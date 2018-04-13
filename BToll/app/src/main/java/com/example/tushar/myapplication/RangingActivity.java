package com.example.tushar.myapplication;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class RangingActivity extends AppCompatActivity implements BeaconConsumer {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
    FrameLayout frameLayout;
    RadioGroup radio;
    int k1=0,k2=0,k3=0,k4=0;
   // Spinner spinner;
    public static boolean flag=true;



    //Webview
    String url="http://120.63.229.222/npticketing/";
    //Webview view = ()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranging);
        frameLayout=(FrameLayout)findViewById(R.id.framelayout);

        beaconManager.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
    }

    @Override
    public void onBeaconServiceConnect() {

        beaconManager.setRangeNotifier(new RangeNotifier() {
           @Override
           public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
              if (beacons.size() > 0) {

                  String current=" ";
                 //EditText editText = (EditText)RangingActivity.this.findViewById(R.id.rangingText);
                 Beacon firstBeacon = beacons.iterator().next();
                  Log.e(TAG,firstBeacon.toString());
                  String x =firstBeacon.getId1().toString();
                  Double d=firstBeacon.getDistance();
                 // x=x.substring(24);
                 Log.e("Ranging","The first beacon " + firstBeacon.getId1().toString() + " is about " + firstBeacon.getDistance() + " meters away.");

                  if(x.equals("00112233-4455-6677-8899-aabbccddeeff") && k1!=1 && d< 5.0)
                  {

                  }

                  else if(x.equals("00112233-4455-6677-8899-aabbccddeeee") && k2!=1 && d< 5.0)
                  {
                      Log.e("Sure",x.toString());

                  }

                  else if(x.equals("00112233-4455-6677-8899-aabbccddeeaa") && k3!=1 && d< 5.0){

                  }

              }
           }

        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {   }
    }

    private void logToDisplay(final String line) {
        runOnUiThread(new Runnable() {
            public void run() {
                EditText editText = (EditText)RangingActivity.this.findViewById(R.id.rangingText);
                editText.append(line+"\n");
            }
        });
    }

}
