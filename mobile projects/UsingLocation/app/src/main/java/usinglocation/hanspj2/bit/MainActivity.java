package usinglocation.hanspj2.bit;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    double latL;
    double lngL;
    Button randGen;
    Button gpsGet;
    TextView lat;
    TextView lng;
    TextView location;
    ProgressDialog pd;
    Boolean isGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria defaultCriteria = new Criteria();
            String providerName = locationManager.getBestProvider(defaultCriteria, false);
            locationManager.requestLocationUpdates(providerName, 400, 1, new CustomLocationListener());
        }
        else
        {
            checkLocationPermission();
        }


        randGen = (Button) findViewById(R.id.buttonGen);
        gpsGet = (Button) findViewById(R.id.gpsButton);
        lat = (TextView) findViewById(R.id.LatRandTextView);
        lng = (TextView) findViewById(R.id.LongRandTextView);
        location = (TextView) findViewById(R.id.locationTextView);

        randGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please wait");
                pd.setCancelable(false);
                pd.show();
                Telport();
            }
        });

        /*gpsGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetGPS();
            }
        });*/
    }
    public void Telport() {

        TextView textView = (TextView) findViewById(R.id.locationTextView);
        textView.setText("");

        int randomNum = new Random().nextInt(90000 - -90000) + -90000;
        double randomDecimalNumb = (randomNum);
        double randomDecimalNumbDecimals = (randomDecimalNumb / 1000);

        String latran = Double.toString(randomDecimalNumbDecimals);

        Random r = new Random();
        int randomNum2 = r.nextInt(180000 - -180000) + -180000;
        double randomDecimalNumb2 = (randomNum2);
        double randomDecimalNumbDecimals2 = (randomDecimalNumb2 / 1000);

        String lngran = Double.toString(randomDecimalNumbDecimals2);

        isGPS = false;
        new AsyncAPIShowRawJson(MainActivity.this, pd, isGPS).execute("http://www.geoplugin.net/extras/location.gp?lat=" + latran + "&long=" + lngran + "&format=json");





    }

    public void GetGPS() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();

            //cationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //Criteria defaultCriteria = new Criteria();
            //String providerName = locationManager.getBestProvider(defaultCriteria, false);
            //locationManager.requestLocationUpdates(providerName, 40000, 1, new CustomLocationListener());

            TextView textView = (TextView) findViewById(R.id.locationTextView);
            textView.setText("");
            Log.d("GetGPS: ", String.valueOf(latL));
            isGPS = true;
            new AsyncAPIShowRawJson(MainActivity.this, pd, isGPS).execute("http://www.geoplugin.net/extras/location.gp?lat=" + latL + "&long=" + lngL + "&format=json");
        }
        else
        {
            checkLocationPermission();
        }




    }

    public class CustomLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {

            latL = location.getLatitude();
            lngL = location.getLongitude();
            GetGPS();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {                                                        //https://stackoverflow.com/questions/41602557/how-to-get-users-location-through-gps-with-target-api-23-and-higher
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );


            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,                                         //https://stackoverflow.com/questions/41602557/how-to-get-users-location-through-gps-with-target-api-23-and-higher
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Criteria defaultCriteria = new Criteria();
                        String providerName = locationManager.getBestProvider(defaultCriteria, false);
                        locationManager.requestLocationUpdates(providerName, 400, 1, new CustomLocationListener());


                    }

                } else {



                }
                return;
            }
        }
    }

}
