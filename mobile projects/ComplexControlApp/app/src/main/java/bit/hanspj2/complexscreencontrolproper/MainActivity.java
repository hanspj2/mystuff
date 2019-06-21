package bit.hanspj2.complexscreencontrolproper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.spin_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.instrumentsR);
        //instrumentGroup.setOnCheckedChangeListener(new radioGroupListener());

        Button button = (Button) findViewById(R.id.Confirmation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner spinner = (Spinner) findViewById(R.id.spinner);

                RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.instrumentsR);

                int checked = instrumentGroup.getCheckedRadioButtonId();

                RadioButton chosenButton = (RadioButton) findViewById(checked);
                String chosenInstrument = chosenButton.getText().toString();
                String feedbackString = "You have enroled for " + chosenInstrument + " lessons in " + spinner.getSelectedItem().toString();
                TextView textViewFeedback = (TextView) findViewById(R.id.TextView_Feedback);
                textViewFeedback.setText(feedbackString);


            }
        });


    }
}
