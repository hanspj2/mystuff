package usingwebservicestask2.hanspj2.bit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnHit;
    EditText editText;
    TextView txtJson;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHit = (Button) findViewById(R.id.btnHit);
        editText = (EditText) findViewById(R.id.editText);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new JsonTask().execute("http://www.geoplugin.net/extras/location.gp?lat=-45.8787605&long=170.5027976&format=json");
                new JsonTask().execute("https://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=" + editText.getText() +"&api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=10&format=json");
            }
        });


    }


    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {  //copied code
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {    //copied code


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            ArrayList<String> formList;
            ArrayList<String> formListDescription= new ArrayList<String>();
            ArrayList<String> formListCombined = new ArrayList<String>();;
            formList = new ArrayList<String>();

            int eventnum = 10;

            try {



                JSONObject obj = new JSONObject(result);
                JSONObject m_jArry = obj.getJSONObject("similarartists");
                JSONArray myTitle = m_jArry.getJSONArray("artist");
                String myT = "";
                String myD = "";

                for (int i =0; i<eventnum ; i++) {

                    JSONObject obj2 = myTitle.getJSONObject(i);
                    myT = obj2.getString("name");
                    //myD = obj2.getString("listeners");
                    formList.add(myT);
                    //formListDescription.add(myD);
                    formListCombined.add(myT);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            ListView listView = (ListView) findViewById(R.id.musiclv);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,formListCombined);
            listView.setAdapter(adapter);


            if (pd.isShowing()){
                pd.dismiss();
            }
        }
    }
}