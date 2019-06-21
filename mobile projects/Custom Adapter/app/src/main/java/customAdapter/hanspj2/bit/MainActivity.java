package customAdapter.hanspj2.bit;

import android.content.Intent;
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

        String[] pages = {"Dogs","Dogs", "Dogs", "Dogs"};
        ArrayAdapter<String> pagesAdapter = new ArrayAdapter<String>(this,R.layout.list_item, pages);

        ListView pagesGroupListView = (ListView) findViewById(R.id.myList);

        pagesGroupListView.setAdapter(pagesAdapter);

        pagesGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                String clickedItem = (String) list.getItemAtPosition(position).toString();
                Intent goToIntent;

                switch(clickedItem){
                    case "Dogs":
                        goToIntent = new Intent(MainActivity.this, DogList.class);
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
