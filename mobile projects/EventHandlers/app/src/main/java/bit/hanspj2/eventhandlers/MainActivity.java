package bit.hanspj2.eventhandlers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent)
            {
                if (keyCode == KeyEvent.KEYCODE_0)
                {
                    Toast.makeText(MainActivity.this, "Dont Type \"0\"", Toast.LENGTH_LONG).show();
                }

                if (keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    if (editText.getText().length() > 7)
                    {
                        Toast.makeText(MainActivity.this, "Welcome " + editText.getText(), Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Username must be atleast 8 characters long", Toast.LENGTH_LONG).show();
                    }
                }


                return(false);

            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(MainActivity.this, "Short click", Toast.LENGTH_LONG).show();
                }
            });


        button.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                Toast.makeText(MainActivity.this, "long click", Toast.LENGTH_LONG).show();
                return(true);
            }
        });
        }
    }

