package shoppinglist.hanspj2.bit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

public class ShoppingItems implements Serializable {
    String name;
    String price;
    String location;
    String vendor;
    String notes;
    String photo;

    public ShoppingItems(String name,String price,String location,String vendor,String notes, String photo) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.vendor = vendor;
        this.notes = notes;
        this.photo = photo;
    }

    public String toString()
    {
        return (name);
    }


}