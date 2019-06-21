package datapassing.hanspj2.bit;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        final TextView textview = (TextView) findViewById(R.id.myText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myintent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivityForResult(myintent,0);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        // Check which request it is that we're responding to
        final TextView textview = (TextView) findViewById(R.id.myText);
        if(requestCode == 0)
            if (resultCode == RESULT_OK) {
                String hexcode = resultIntent.getStringExtra("settingtextColor");
                //Toast.makeText(MainActivity.this, hexcode, Toast.LENGTH_LONG).show(); //Testing result
                textview.setTextColor(Color.parseColor(hexcode));
            }
    }
}

