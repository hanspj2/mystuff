package shoppinglist.hanspj2.bit;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShoppingArrayAdapter extends ArrayAdapter<ShoppingItems> {
    public ShoppingArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container)
    {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());

        View customView = inflater.inflate(R.layout.custom_shopping_listview_item,container,false);

        ImageView itemImageView = (ImageView) customView.findViewById(R.id.lvItemImage);
        TextView itemTextView = (TextView) customView.findViewById(R.id.ItemWords);

        ShoppingItems currentItem = getItem(position);

        if (currentItem.photo != null)
        {
            itemImageView.setImageURI(Uri.parse(currentItem.photo));
        }

        itemTextView.setText(currentItem.name);

        return customView;

    }
}
