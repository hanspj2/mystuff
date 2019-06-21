package datapassing.hanspj2.bit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView textview = (TextView) findViewById(R.id.settingTextView);
        String hexColor = String.format("#%06X", (0xFFFFFF & textview.getCurrentTextColor()));

        Intent returnIntent = new Intent(SettingsActivity.this,MainActivity.class);

        returnIntent.putExtra("settingtextColor",hexColor);

        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}
