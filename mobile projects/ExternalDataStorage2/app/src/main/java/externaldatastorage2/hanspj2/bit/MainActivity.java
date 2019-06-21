package externaldatastorage2.hanspj2.bit;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView= (ListView) findViewById(R.id.listView1);



            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,LoadStringArray("city"));

            listView.setAdapter(adapter);






    }
    public ArrayList<String> LoadStringArray(String assetFileName)
    {
        ArrayList<String> stringHolder = new ArrayList<String>();

        AssetManager am = getAssets();

        try
        {
            InputStream is = am.open("city");
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);

            String currentString;
            while((currentString = br.readLine()) != null)
            {
                stringHolder.add(currentString);
            }
            br.close();

        }catch (IOException e)
        {

        }
        return stringHolder;
    }

    }

