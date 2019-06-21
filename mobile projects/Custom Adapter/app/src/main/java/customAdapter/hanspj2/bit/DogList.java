package customAdapter.hanspj2.bit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DogList extends AppCompatActivity {

    DogBreeds[] DogBreedArray;
    final Context context = this;

    public String getClickedImage() {
        return clickedImage;
    }

    private String clickedImage;
    DialougeFragment changeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);

        initialiseDataArray();

        DogArrayAdapter DogAdapter = new DogArrayAdapter(this,R.layout.custom_listview_item, DogBreedArray);

        ListView lvDogs = (ListView) findViewById(R.id.dogList);

        lvDogs.setAdapter(DogAdapter);

        lvDogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {

                clickedImage = DogBreedArray[position].name;
                //String name = context.getResources().getResourceEntryName(clickedImage);

                //Toast.makeText(context, String.valueOf(position),
                //        Toast.LENGTH_LONG).show();

                FragmentManager fm = getSupportFragmentManager();
                changeImage = new DialougeFragment();
                changeImage.show(fm,"");







            }

        });



    }

    private void initialiseDataArray()
    {
        Resources resourceMachine = getResources();

        Drawable Airdale = resourceMachine.getDrawable(R.drawable.airedale, null);
        Drawable Bedlington = resourceMachine.getDrawable(R.drawable.bedlington, null);
        Drawable Bull = resourceMachine.getDrawable(R.drawable.bull, null);
        Drawable Cairn = resourceMachine.getDrawable(R.drawable.cairn, null);
        Drawable Irish = resourceMachine.getDrawable(R.drawable.irish, null);
        Drawable Lakeland = resourceMachine.getDrawable(R.drawable.lakeland, null);
        Drawable Norfolk = resourceMachine.getDrawable(R.drawable.norfolk, null);
        Drawable Russell = resourceMachine.getDrawable(R.drawable.russell, null);
        Drawable Staffordshire = resourceMachine.getDrawable(R.drawable.staffordshire, null);
        Drawable Breedinformation = resourceMachine.getDrawable(R.drawable.breedinformation, null);

        DogBreedArray = new DogBreeds[8];
        DogBreedArray[0] = new DogBreeds("airdale", Airdale,this);
        DogBreedArray[1] = new DogBreeds("bedlington", Bedlington,this);
        DogBreedArray[2] = new DogBreeds("bull", Bull,this);
        DogBreedArray[3] = new DogBreeds("cairn", Cairn,this);
        DogBreedArray[4] = new DogBreeds("irish", Irish,this);
        DogBreedArray[5] = new DogBreeds("norfolk", Norfolk,this);
        DogBreedArray[6] = new DogBreeds("russell", Russell,this);
        DogBreedArray[7] = new DogBreeds("staffordshire", Staffordshire,this);
    }

    public void confirm() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title

        alertDialogBuilder.setTitle("You pic test");
        //alertDialogBuilder.setIcon(R.drawable.clickedImage);

        // set dialog message
        alertDialogBuilder
                .setMessage("Is it?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity

                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();



        // show it
        alertDialog.show();


    }
}
