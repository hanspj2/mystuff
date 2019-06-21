package bit.hanspj2.FebFridays;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Resources resourceResolver = getResources();
        int datesArray[] = resourceResolver.getIntArray(R.array.FebFridays);

        //System.out.println(Arrays.toString(datesArray));

        TextView txtDisplay = (TextView)findViewById(R.id.txtDisplay);
        txtDisplay.setText("Feburary Fridays are on : " + Arrays.toString(datesArray));

    }
}
