package shoppinglist.hanspj2.bit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import shoppinglist.hanspj2.bit.R;
import shoppinglist.hanspj2.bit.ShoppingArrayAdapter;
import shoppinglist.hanspj2.bit.ShoppingItems;

public class MainActivity extends AppCompatActivity {

    ArrayList<ShoppingItems> shoppingItems;
    final Context context = this;
    ShoppingItems myItem;

    public String getClickedImage() {
        return clickedImage;
    }

    private String clickedImage;
    private AddItemFragment addingItem;
    private DisplayItemFragment displayingItem;
    private Toolbar toolbar;
    private ShoppingArrayAdapterComplex myComplexLayout;
    private ShoppingArrayAdapter mySimpleLayout;
    private boolean complex = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        try {
            LoadArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ShoppingArrayAdapter ShoppingAdapter = new ShoppingArrayAdapter(this, R.layout.custom_shopping_listview_item, shoppingItems);
        ShoppingArrayAdapterComplex ShoppingAdapterComplex = new ShoppingArrayAdapterComplex(this, R.layout.custom_shopping_listview_item_complex, shoppingItems);

        ListView lvShopping = (ListView) findViewById(R.id.listView1);

        myComplexLayout = ShoppingAdapterComplex;
        mySimpleLayout = ShoppingAdapter;

        if (complex)
        {
            lvShopping.setAdapter(myComplexLayout);
        }
        else
        {
            lvShopping.setAdapter(mySimpleLayout);
        }


        lvShopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {

                int i = shoppingItems.indexOf(position);
                myItem = getYourObject(position);
                FragmentManager fm = getSupportFragmentManager();
                displayingItem = new DisplayItemFragment();
                displayingItem.show(fm,"");

            }

        });

        Button addItem = (Button) findViewById(R.id.buttonAdd);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                addingItem = new AddItemFragment();
                addingItem.show(fm,"");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SortArrayListByName();
            BuildShoppingList();
            return true;
        }
        if (id == R.id.action_settings1) {
            SortArrayListByVendor();
            BuildShoppingList();
            return true;
        }

        if (id == R.id.action_settings2) {
            SortArrayListByPrice();
            BuildShoppingList();
            return true;
        }

        if (id == R.id.ChangeView) {
            Log.d("onOptionsItemSelected: ","Got there");
            complex =! complex;
            android.support.v7.view.menu.ActionMenuItemView complexBtn = (android.support.v7.view.menu.ActionMenuItemView) findViewById(R.id.ChangeView);
            if (complex)
            {
                complexBtn.setText("Simple");
            }
            else
            {
                complexBtn.setText("Complex");
            }
            BuildShoppingList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private ShoppingItems getYourObject(int position){
        return shoppingItems.get(position);
    }

    public ShoppingItems getClickedItem(){
        return myItem;
    }

    public int getClickedItemPosition(){
        return shoppingItems.indexOf(getClickedItem());
    }

    public void DeleteItem(){
        displayingItem.dismiss();
        shoppingItems.remove(myItem);
        BuildShoppingList();

        try {
            SaveArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BuildShoppingList(){
        ShoppingArrayAdapter ShoppingAdapter = new ShoppingArrayAdapter(this, R.layout.custom_shopping_listview_item, shoppingItems);
        ShoppingArrayAdapterComplex ShoppingAdapterComplex = new ShoppingArrayAdapterComplex(this, R.layout.custom_shopping_listview_item_complex, shoppingItems);
        ListView lvShopping = (ListView) findViewById(R.id.listView1);

        myComplexLayout = ShoppingAdapterComplex;
        mySimpleLayout = ShoppingAdapter;

        if (complex)
        {
            lvShopping.setAdapter(myComplexLayout);
        }
        else
        {
            lvShopping.setAdapter(mySimpleLayout);
        }

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

    public void giveMeMyData(String name, String price, String location, String vendor, String notes, String photoURI) {
        addingItem.dismiss();
        Resources resourceMachine = getResources();
        Drawable Breedinformation = resourceMachine.getDrawable(R.drawable.ic_launcher_background, null);
        shoppingItems.add(new ShoppingItems(name,price,location,vendor,notes ,photoURI));

        BuildShoppingList();
        try {
            SaveArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void giveMeMyDataDisplay(String name, String price, String location, String vendor, String notes, String photoURI) {
        displayingItem.dismiss();
        Resources resourceMachine = getResources();
        Drawable Breedinformation = resourceMachine.getDrawable(R.drawable.ic_launcher_background, null);
        shoppingItems.set((getClickedItemPosition()),new ShoppingItems(name,price,location,vendor,notes, photoURI));

        BuildShoppingList();
        try {
            SaveArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveArrayList() throws IOException {
        /*String filePath = context.getFilesDir().getPath().toString() + "/arraylist.txt";
        File f = new File(filePath);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(shoppingItems);
        oos.close();*/
        final String TESTSTRING = new String("Hello Android");
        try {
            String filePath = context.getFilesDir().getPath().toString() + "/arraylist.txt";
            File f = new File(filePath);
            Log.v("mythingsave", filePath);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(shoppingItems);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();

        }}
    public void LoadArrayList() throws IOException, ClassNotFoundException {
        String filePath = context.getFilesDir().getPath().toString() + "/arraylist.txt";

        File f = new File(filePath);
        FileInputStream fis = new FileInputStream(f);
        if(f.exists()) {
            Log.v("mythingloadfileexists", f.toString());
        }

        ObjectInputStream ois = new ObjectInputStream(fis);
        shoppingItems = (ArrayList<ShoppingItems>) ois.readObject();
        ois.close();
        Log.v("mythingload", filePath);
    }

    public void SortArrayListByName()
    {
        Collections.sort(shoppingItems, new Comparator<ShoppingItems>() {
            @Override public int compare(ShoppingItems p1, ShoppingItems p2) {
                return p1.name.toLowerCase().compareTo(p2.name.toLowerCase()); // Ascending
            }

        });
    }

    public void SortArrayListByVendor()
    {
        Collections.sort(shoppingItems, new Comparator<ShoppingItems>() {
            @Override public int compare(ShoppingItems p1, ShoppingItems p2) {
                return p1.vendor.toLowerCase().compareTo(p2.vendor.toLowerCase()); // Ascending
            }

        });
    }

    public void SortArrayListByPrice()
    {
        Collections.sort(shoppingItems, new Comparator<ShoppingItems>() {
            @Override public int compare(ShoppingItems p1, ShoppingItems p2) {
                return Integer.valueOf(p2.price) - Integer.valueOf(p1.price);
            }

        });
    }
}
