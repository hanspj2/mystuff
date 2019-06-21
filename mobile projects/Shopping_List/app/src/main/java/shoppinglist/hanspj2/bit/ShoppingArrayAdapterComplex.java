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

public class ShoppingArrayAdapterComplex extends ArrayAdapter<ShoppingItems> {
    public ShoppingArrayAdapterComplex(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container)
    {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());

        View customView = inflater.inflate(R.layout.custom_shopping_listview_item_complex,container,false);

        ImageView itemImageView = (ImageView) customView.findViewById(R.id.lvItemImageComplex);
        TextView itemTextView = (TextView) customView.findViewById(R.id.ItemName);
        TextView itemPriceView = (TextView) customView.findViewById(R.id.ItemPrice);
        TextView ItemlocationView = (TextView) customView.findViewById(R.id.ItemLocation);

        ShoppingItems currentItem = getItem(position);

        if (currentItem.photo != null)
        {
            itemImageView.setImageURI(Uri.parse(currentItem.photo));
        }

        itemTextView.setText(currentItem.name);
        itemPriceView.setText(currentItem.price);
        ItemlocationView.setText(currentItem.location);

        return customView;

    }
}
