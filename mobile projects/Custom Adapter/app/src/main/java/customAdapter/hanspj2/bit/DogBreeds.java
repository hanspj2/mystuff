package customAdapter.hanspj2.bit;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class DogBreeds {
    String name;
    Drawable image;
    Context context;

    public DogBreeds(String name, Drawable image, Context context) {
        this.name = name;
        this.image = image;
        this.context = context;
    }

    public String toString()
    {
        return (name);
    }



    public Drawable getImage() {
        return image;
    }
}