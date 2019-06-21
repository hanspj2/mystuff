package externaldatastorage.hanspj2.bit;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor prefEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("DEMO PREFS", MODE_PRIVATE);
        prefEditor = prefs.edit();

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getGreeting(prefs.getString("Language",null)));

        if(prefs.getString("Color", null) != null)
        {
            getColor(prefs.getString("Color", null));
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int checked = instrumentGroup.getCheckedRadioButtonId();
                RadioButton chosenButton = (RadioButton) findViewById(checked);
                String CheckedLang = chosenButton.getText().toString();

                prefEditor.putString("Language",CheckedLang);
                prefEditor.commit();

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(getGreeting(prefs.getString("Language",null)));

                //////////////////////////////////////////////////////////////////////////////
                RadioGroup Group2 = (RadioGroup) findViewById(R.id.buttonColorGroup);
                int checked2 = Group2.getCheckedRadioButtonId();
                RadioButton chosenButton2 = (RadioButton) findViewById(checked2);
                String CheckedColor = chosenButton2.getText().toString();

                prefEditor.putString("Color",CheckedColor);
                prefEditor.commit();

                if(prefs.getString("Color", null) != null)
                {
                    getColor(prefs.getString("Color", null));
                }



            }
        });


    }

    private String getGreeting(String language)
    {
        String greeting = "";


        switch(language)
        {
            case("French"):
                greeting = "Bonjour La Monde";
                break;
            case("German"):
                greeting = "Hello Welt";
                break;
            case("Spanish"):
                greeting = "Hola Mundo";
                break;
            default:
                greeting = "No Language Selected";
                break;
        }

        return greeting;
    }

    private void getColor(String color)
    {
        TextView textView = (TextView) findViewById(R.id.textView);

        switch(color)
        {
            case("Blue"):
                textView.setTextColor(Color.BLUE);
                break;
            case("Red"):
                textView.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }
}
