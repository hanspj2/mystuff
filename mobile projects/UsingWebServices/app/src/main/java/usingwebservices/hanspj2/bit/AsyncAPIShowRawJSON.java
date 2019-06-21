package usingwebservices.hanspj2.bit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AsyncAPIShowRawJSON extends AsyncTask<String,String,String> {

    MainActivity activity;
    ProgressDialog pd;

    public AsyncAPIShowRawJSON(MainActivity activity)
    {
        this.activity = activity;
    }

    //@Override
    protected String doInBackground(Void... voids) {
        return null;
    }

    protected void onPreExecute() {            //copied code
        super.onPreExecute();

        pd = new ProgressDialog(activity);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    protected String doInBackground(String... params) {     //copied code


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
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

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
            JSONObject m_jArry = obj.getJSONObject("artists");
            JSONArray myTitle = m_jArry.getJSONArray("artist");
            String myT = "";
            String myD = "";

            for (int i =0; i<eventnum ; i++) {

                JSONObject obj2 = myTitle.getJSONObject(i);
                myT = obj2.getString("name");
                myD = obj2.getString("listeners");
                formList.add(myT);
                formListDescription.add(myD);
                formListCombined.add(myT + ", Listeners: " + myD);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) activity.findViewById(R.id.musiclv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,formListCombined);
        listView.setAdapter(adapter);


        if (pd.isShowing()){
            pd.dismiss();
        }
    }
}

