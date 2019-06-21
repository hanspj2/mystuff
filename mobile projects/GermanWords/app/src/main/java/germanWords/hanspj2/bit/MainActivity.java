package germanWords.hanspj2.bit;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static int CorrectCount;
    private static int AnswerCount;
    private final static int maxQuestionCount = 10;
    private int myRand;
    ArrayList<Integer> alreadyAskedQuestions = new ArrayList<Integer>();
    private final int[] imagesArray = new int[]{
            R.drawable.der_apfel,
            R.drawable.das_auto,
            R.drawable.der_baum,
            R.drawable.die_ente,
            R.drawable.das_haus,
            R.drawable.die_hexe,
            R.drawable.die_kuh,
            R.drawable.die_milch,
            R.drawable.das_schaf,
            R.drawable.das_schloss,
            R.drawable.der_stuhl
    };
    private final int[] answersArray = new int[]{
            1,
            3,
            1,
            2,
            3,
            2,
            2,
            2,
            3,
            3,
            1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CorrectCount = 0;

        nextQuestion();

        Button button = (Button) findViewById(R.id.Confirmation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tally;
                RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int checked = instrumentGroup.getCheckedRadioButtonId();
                RadioButton chosenButton = (RadioButton) findViewById(checked);

                if ((Integer.parseInt(chosenButton.getTag().toString())) == answersArray[myRand])
                {
                    Toast.makeText(MainActivity.this,"correct",Toast.LENGTH_SHORT).show();
                    countCorrectAnswer();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
                }
                alreadyAskedQuestions.add(myRand);
                countAnswer();
                nextQuestion();

            }
        });

    }
    public void countCorrectAnswer(){
        CorrectCount++;
    }
    public void countAnswer(){
        AnswerCount++;
    }

    public void nextQuestion(){
        if (AnswerCount <= 10) {

            Random r = new Random();
            ImageView imageview = (ImageView) findViewById(R.id.imageView2);

            myRand = r.nextInt(12 - 1);
            imageview.setImageResource(imagesArray[myRand]);

            // Get a question that is not already asked
            while (alreadyAskedQuestions.contains(myRand)) {
                myRand = r.nextInt(12 - 1);
                imageview.setImageResource(imagesArray[myRand]);
            }

        }
        else
        {
            Toast.makeText(MainActivity.this,"Completed quiz with " + CorrectCount + " correct answers",Toast.LENGTH_SHORT).show();
            Button button = (Button) findViewById(R.id.Confirmation);
            button.setEnabled(false);
        }


    }
}
