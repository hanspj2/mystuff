package fragments.hanspj2.bit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListFragment extends Fragment {


    public ShowListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_show_list,container,false);
        ListView Animals = (ListView) fragmentView.findViewById(R.id.Instruments);

        String[] animals = {"Frog","Cat","Cow","Dog","Dog","Dog","Dogs"};
        ArrayAdapter<String> animalAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, animals);
        Animals.setAdapter(animalAdapter);

        return fragmentView;
    }

}
