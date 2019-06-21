package shoppinglist.hanspj2.bit;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends DialogFragment {

    EditText name;
    EditText price;
    EditText location;
    EditText vendor;
    EditText notes;
    Button takePhoto;
    ImageView imageview;
    Uri imageUri;
    String mPhotoFileName;
    File mPhotoFile;
    Uri mPhotoFileUri = null;
    private String mPhotoFileUriPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_item, container, false);

        Button addItem = (Button) v.findViewById(R.id.button5);

        name = (EditText) v.findViewById(R.id.editTextName);
        price = (EditText) v.findViewById(R.id.editText2);
        location = (EditText) v.findViewById(R.id.editText3);
        vendor = (EditText) v.findViewById(R.id.editText4);
        notes = (EditText) v.findViewById(R.id.editText6);
        imageview = (ImageView) v.findViewById(R.id.imageView2);
        takePhoto = (Button) v.findViewById(R.id.button4);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity myActivity = (MainActivity) getActivity();
                if(mPhotoFileUri != null)
                {
                    mPhotoFileUriPass = mPhotoFileUri.toString();
                }
                else
                {

                }
                if (!name.getText().toString().equals(""))
                {
                    myActivity.giveMeMyData(
                            name.getText().toString(),
                            price.getText().toString(),
                            location.getText().toString(),
                            vendor.getText().toString(),
                            notes.getText().toString(),
                            mPhotoFileUriPass
                    );
                }
                else
                {
                    Toast.makeText(getView().getContext(), "Please name an item", Toast.LENGTH_SHORT).show();
                }

            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                Date currentTime = new Date();
                String timeStamp = timeStampFormat.format(currentTime);

                mPhotoFileName = "IMG_" + timeStamp + ".jpg";
                mPhotoFile = createTimeStampedFile();
                mPhotoFileUri = Uri.fromFile(mPhotoFile);
                Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageCaptureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileUri);
                //startActivityForResult(imageCaptureIntent, 1);
                startActivityForResult(imageCaptureIntent,1);

            }
        });


        return v;
    }
    public File createTimeStampedFile()
    {
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageStorageDirectory = new File(imageRootPath, "ShoppingListImages");
        if(!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs();
        }

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);

        return photoFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                //use imageUri here to access the image
                String realFilePath = mPhotoFile.getPath();
                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);
                imageview.setImageBitmap(userPhotoBitmap);

                //Bundle extras = data.getExtras();
                //Log.e("URI",imageUri.toString());
                //Bitmap bmp = (Bitmap) extras.get("data");
                //imgView1.setImageURI(myUri);

                /*imgView1.setImageURI(imageUri);
                textview = (TextView) findViewById(R.id.myTextView);
                textview.setText("Good");*/

            }
            else if (resultCode == RESULT_CANCELED) {

            }


        }
        else

        {

        }

}}
