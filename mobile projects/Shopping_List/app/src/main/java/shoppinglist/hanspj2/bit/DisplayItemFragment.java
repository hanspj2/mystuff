package shoppinglist.hanspj2.bit;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayItemFragment extends DialogFragment {

    EditText name;
    EditText price;
    EditText location;
    EditText vendor;
    EditText notes;
    ImageView imageview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_display_item, container, false);

        MainActivity myActivity = (MainActivity) getActivity();
        final ShoppingItems myItem = myActivity.getClickedItem();

        name = (EditText) v.findViewById(R.id.editTextDisplayName);
        price = (EditText) v.findViewById(R.id.editTextDisplayPrice);
        location = (EditText) v.findViewById(R.id.editTextDisplayLocation);
        vendor = (EditText) v.findViewById(R.id.editTextDisplayVendor);
        notes = (EditText) v.findViewById(R.id.editTextDisplayNotes);
        imageview = (ImageView) v.findViewById(R.id.imageView);
        name.setText(myItem.name);
        price.setText(myItem.price);
        location.setText(myItem.location);
        vendor.setText(myItem.vendor);
        notes.setText(myItem.notes);
        if(myItem.photo != null)
        {
            imageview.setImageURI(Uri.parse(myItem.photo));
        }
        else
        {

        }

        Button deleteItem = (Button) v.findViewById(R.id.button2);
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity myActivity = (MainActivity) getActivity();
                myActivity.DeleteItem();
            }
        });

        Button saveItem = (Button) v.findViewById(R.id.button3);
        saveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity myActivity = (MainActivity) getActivity();

                myActivity.giveMeMyDataDisplay(
                        name.getText().toString(),
                        price.getText().toString(),
                        location.getText().toString(),
                        vendor.getText().toString(),
                        notes.getText().toString(),
                        myItem.photo);

            }
        });

        return v;
    }

}
