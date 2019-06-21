package usinglocation.hanspj2.bit;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class AsyncAPIShowRawJson extends AsyncTask<String,String,String> {

    MainActivity activity;
    ProgressDialog pd;
    Boolean isGPS;

    public AsyncAPIShowRawJson(MainActivity activity, ProgressDialog pd, Boolean isGPS)
    {
        this.activity = activity;
        this.pd = pd;
        this.isGPS = isGPS;
    }

    //@Override
    protected String doInBackground(Void... voids) {
        return null;
    }

    protected void onPreExecute() {            //copied code
        super.onPreExecute();

    }

    protected String doInBackground(String... params) {     //copied code


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            Log.d(String.valueOf(url), "URL: ");
            Log.d(String.valueOf(params[0]), "Params: ");


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            Log.d(String.valueOf(buffer), "Params: ");

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

        String myT = "";
        String myLat = "";
        String myLong = "";

        int eventnum = 10;


        try {

            String test = result;
            char first = test.charAt(0);

            if(!(first == '['))
            {
                JSONObject obj = new JSONObject(result);
                myT = obj.getString("geoplugin_place");
                myLat = obj.getString("geoplugin_latitude");
                myLong = obj.getString("geoplugin_longitude");
            }
            else
            {
                myT = "";
                myLat = "";
                myLong = "";

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (myT.equals("") && isGPS == false) {
            activity.Telport();
        }

        if (myT.equals("") && isGPS == true)
        {
            pd.dismiss();
            Toast myToast = Toast.makeText(activity,
                    "No city nearby", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            myToast.show();
        }

        if (pd.isShowing() && !myT.equals("")){
            new AsyncAPIShowRawJsonFlickr(activity, pd).execute("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" + "f3871c6c29e19163bd6fd8765e089d26" + "&tags=" +  myT + "&format=json&nojsoncallback=1");
        }

        TextView textView = (TextView) activity.findViewById(R.id.locationTextView);
        TextView longView = (TextView) activity.findViewById(R.id.LongRandTextView);
        TextView latView = (TextView) activity.findViewById(R.id.LatRandTextView);

        textView.setText(myT);
        longView.setText(myLong);
        latView.setText(myLat);
        longView.setText(String.valueOf(myLong));
        latView.setText(String.valueOf(myLat));
    }

    ////////////////////////////////////////Flickr API Call///////////////////////////////////////////////////

    public class AsyncAPIShowRawJsonFlickr extends AsyncTask<String,String,String> {

        MainActivity activity;
        ProgressDialog pd;

        public AsyncAPIShowRawJsonFlickr(MainActivity activity, ProgressDialog pd)
        {
            this.activity = activity;
            this.pd = pd;
        }

        //@Override
        protected String doInBackground(Void... voids) {
            return null;
        }

        protected void onPreExecute() {            //copied code
            super.onPreExecute();

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

            String farmID = "";
            String serverID = "";
            String photoID = "";
            String secret = "";
            String size = "n";

            int eventnum = 10;


            try {

                String test = result;
                char first = test.charAt(0);

                JSONObject obj = new JSONObject(result);
                JSONObject m_jArry = obj.getJSONObject("photos");
                JSONArray myTitle = m_jArry.getJSONArray("photo");

                if(myTitle.length() > 0)
                {
                    JSONObject obj2 = myTitle.getJSONObject(0);

                    farmID = obj2.getString("farm");
                    serverID = obj2.getString("server");
                    photoID = obj2.getString("id");
                    secret = obj2.getString("secret");

                }
                else
                {
                    Toast myToast = Toast.makeText(activity,
                            "No Image Found", Toast.LENGTH_SHORT);
                    myToast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    myToast.show();
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

            //ListView listView = (ListView) activity.findViewById(R.id.musiclv);
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,formListCombined);
            //listView.setAdapter(adapter);


            Log.d(farmID, "Flickr Values");
            Log.d(serverID, "Flickr Values");
            Log.d(photoID, "Flickr Values");
            Log.d(secret, "Flickr Values");
            Log.d(size, "Flickr Values");
            Log.d(secret, "Flickr Values");


            new DownloadImageTask((ImageView) activity.findViewById(R.id.imageView))
                    .execute("https://farm" + farmID + ".staticflickr.com/" + serverID + "/"+photoID+"_" + secret + "_" + size + ".jpg");


        }
    }

    ///////////////////////////////////////Downloading Image/////////////////////////////////////////

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
        }
    }
}

