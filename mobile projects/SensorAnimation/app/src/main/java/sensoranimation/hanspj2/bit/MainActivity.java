package sensoranimation.hanspj2.bit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView myTextView1;
    private TextView myTextView2;
    private TextView myTextView3;
    private ImageView myImageView;
    private Float ImageX;
    private Float ImageY;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private int Size;
    private int height;
    private int width;
    SensorManager smm;
    List<Sensor> sensor;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImageView = (ImageView)findViewById(R.id.imageView);
        ImageX = myImageView.getX();
        ImageY = myImageView.getY();

        myTextView1 = (TextView) findViewById(R.id.textView2);
        myTextView2 = (TextView) findViewById(R.id.textView3);
        myTextView3 = (TextView) findViewById(R.id.textView4);

        smm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lv = (ListView) findViewById (R.id.listview1);
        sensor = smm.getSensorList(Sensor.TYPE_ALL);
        lv.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,  sensor));



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        SensorActivity sensorActivity = new MainActivity.SensorActivity();
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(sensorActivity, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);



    }



    public class SensorActivity extends Activity implements SensorEventListener {


        protected void onResume() {
            super.onResume();
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {

            float x = event.values[1];
            float y = event.values[0];
            float z = event.values[2];

            myTextView1.setText(String.valueOf(x));
            myTextView2.setText(String.valueOf(y));
            myTextView3.setText(String.valueOf(z));


            myImageView.setY((y * 20) + 600);
            myImageView.setX((x * 20) + 300);



    }
}}


