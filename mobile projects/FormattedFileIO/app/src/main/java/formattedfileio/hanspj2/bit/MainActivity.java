package formattedfileio.hanspj2.bit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> formList;
    ArrayList<String> formListDescription;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            formList = new ArrayList<String>();
            formListDescription = new ArrayList<String>();

            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONObject m_jArry = obj.getJSONObject("events");
            JSONArray myTitle = m_jArry.getJSONArray("event");
            String myT = "";
            String myD = "";

            for (int i =0; i<myTitle.length() ; i++) {

                JSONObject obj2 = myTitle.getJSONObject(i);
                myT = obj2.getString("title");
                myD = obj2.getString("description");
                formList.add(myT);
                formListDescription.add(myD);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.listview1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,formList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                String clickedItem = (String) list.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this,
                        formListDescription.get(position) , Toast.LENGTH_LONG).show();


            }

        });


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("dunedin_events_2017.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
