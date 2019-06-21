package customAdapter.hanspj2.bit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DogArrayAdapter extends ArrayAdapter<DogBreeds> {
    public DogArrayAdapter(Context context, int resource, DogBreeds[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container)
    {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());

        View customView = inflater.inflate(R.layout.custom_listview_item,container,false);

        ImageView itemImageView = (ImageView) customView.findViewById(R.id.lvItemImage);
        TextView itemTextView = (TextView) customView.findViewById(R.id.ItemWords);

        DogBreeds currentItem = getItem(position);

        itemImageView.setImageDrawable(currentItem.image);
        itemTextView.setText(currentItem.name);

        return customView;

    }
}
