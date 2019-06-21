package bit.hanspj2.navigationdunedin;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        String[] pages = {"Activities","Services", "Dining", "Shopping"};
        ArrayAdapter<String> pagesAdapter = new ArrayAdapter<String>(this,R.layout.textthing, pages);

        ListView pagesGroupListView = (ListView) findViewById(R.id.left_drawer);

        pagesGroupListView.setAdapter(pagesAdapter);

        pagesGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                String clickedItem = (String) list.getItemAtPosition(position).toString();
                Intent goToIntent;

                switch(clickedItem){
                    case "Activities":
                        goToIntent = new Intent(MainActivity.this, activities.class);
                        break;
                    case "Services":
                        goToIntent = new Intent(MainActivity.this,services.class);
                        break;
                    case "Dining":
                        goToIntent = new Intent(MainActivity.this, dining.class);
                        break;
                    case "Shopping":
                        goToIntent = new Intent(MainActivity.this,shopping.class);
                        break;
                    default:
                        goToIntent = null;
                }

                if (goToIntent != null)
                {
                    startActivity(goToIntent);
                }


            }

        });
    }
}
