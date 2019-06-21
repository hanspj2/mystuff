package customAdapter.hanspj2.bit;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialougeFragment extends DialogFragment {


    private Drawable myimage;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dialouge, container, false);

        ImageView image = (ImageView) v.findViewById(R.id.imageView);
        DogList myActivity = (DogList) getActivity();

        int iconresID = getResources().getIdentifier(myActivity.getClickedImage().toLowerCase(),"drawable", myActivity.getPackageName());

        image.setImageResource(iconresID);

        return v;
    }

}
