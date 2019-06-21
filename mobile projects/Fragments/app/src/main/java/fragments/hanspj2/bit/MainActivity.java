package fragments.hanspj2.bit;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment dynamicFragment = new ShowImageFragment();
                Fragment someFragment = new ShowListFragment();
                FragmentManager fm = getSupportFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                {
                    ft.replace(R.id.fragment_container2, someFragment);
                }
                else
                {
                    ft.replace(R.id.fragment_container, someFragment);
                }

                ft.commit();
            }
    });

        Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new ShowImageFragment();
                FragmentManager fm = getSupportFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, someFragment);
                ft.commit();
            }
        });
    }
}
