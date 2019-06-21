package bit.hanspj2.Dialougefragments;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

     DialogueFragStuff changeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment dynamicFragment = new ShowImageFragment();
                FragmentManager fm = getSupportFragmentManager();
                changeImage = new DialogueFragStuff();
                changeImage.show(fm,"alpaca");
            }
        });
    }

    public void giveMeMyData(int myImage)
    {

        changeImage.dismiss();

        ImageView ivImage = (ImageView) findViewById(R.id.imageView2);

        switch(myImage)
        {
            case 1:
                ivImage.setImageResource(R.drawable.das_auto);
                break;
            case 2:
                ivImage.setImageResource(R.drawable.das_haus);
                break;
            case 3:
                ivImage.setImageResource(R.drawable.das_schaf);
                break;
            case 4:
                ivImage.setImageResource(R.drawable.das_schloss);
                break;
            default:
                break;

        }



    }
}
