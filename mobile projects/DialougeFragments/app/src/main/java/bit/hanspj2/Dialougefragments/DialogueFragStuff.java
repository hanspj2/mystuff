package bit.hanspj2.Dialougefragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogueFragStuff extends DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_dialogue, container, false);

        Button autoButton = (Button) v.findViewById(R.id.button2);
        autoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity myActivity = (MainActivity) getActivity();
                myActivity.giveMeMyData(1);
            }
        });
        Button hausButton = (Button) v.findViewById(R.id.button3);
        hausButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity myActivity = (MainActivity) getActivity();
                myActivity.giveMeMyData(2);
            }
        });
        Button schafButton = (Button) v.findViewById(R.id.button4);
        schafButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity myActivity = (MainActivity) getActivity();
                myActivity.giveMeMyData(3);
            }
        });
        Button schlossButton = (Button) v.findViewById(R.id.button5);
        schlossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity myActivity = (MainActivity) getActivity();
                myActivity.giveMeMyData(4);
            }
        });

        return v;
    }

}
