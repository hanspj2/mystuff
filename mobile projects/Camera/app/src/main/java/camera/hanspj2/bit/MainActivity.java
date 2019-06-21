package camera.hanspj2.bit;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button;
    Uri imageUri;
    String mPhotoFileName;
    File mPhotoFile;
    Uri mPhotoFileUri;
    ImageView imgView1;
    ImageView imgView2;
    ImageView imgView3;
    ImageView imgView4;
    String PATH = "/storage/emulated/0/Pictures/IMG_20190523_132426.jpg";
    Uri myUri = Uri.parse("file:///storage/emulated/0/Pictures/IMG_20190523_142759.jpg");

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        //StrictMode.setVmPolicy(builder.build());

        button = (Button) findViewById(R.id.button);
        imgView1 = (ImageView) findViewById(R.id.imageView);
        imgView2 = (ImageView) findViewById(R.id.imageView2);
        imgView3 = (ImageView) findViewById(R.id.imageView3);
        imgView4 = (ImageView) findViewById(R.id.imageView4);
        /*File imgFile = new File("/storage/emulated/0/Pictures/IMG_20190523_142759.jpg");
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.imageView);

            myImage.setImageBitmap(myBitmap);

        }*/




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                Date currentTime = new Date();
                String timeStamp = timeStampFormat.format(currentTime);

                mPhotoFileName = "IMG_" + timeStamp + ".jpg";

                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "IMG_" +
                        String.valueOf(timeStamp) + ".jpg"));
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, 1);*/

                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mPhotoFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"IMG_" +
                        timeStamp + ".jpg");
                imageUri = Uri.fromFile(mPhotoFile);
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                startActivityForResult(intent, 1);*/


                mPhotoFile = createTimeStampedFile();

                mPhotoFileUri = Uri.fromFile(mPhotoFile);

                Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                imageCaptureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileUri);

                //startActivityForResult(imageCaptureIntent, 1);

                startActivityForResult(imageCaptureIntent,1);


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(this, "Making it to onActivityResult", Toast.LENGTH_SHORT);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                //use imageUri here to access the image
                String realFilePath = mPhotoFile.getPath();
                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);
                imgView1.setImageBitmap(userPhotoBitmap);
                imgView2.setImageBitmap(userPhotoBitmap);
                imgView3.setImageBitmap(userPhotoBitmap);
                imgView4.setImageBitmap(userPhotoBitmap);

                //Bundle extras = data.getExtras();
                //Log.e("URI",imageUri.toString());
                //Bitmap bmp = (Bitmap) extras.get("data");
                //imgView1.setImageURI(myUri);

                /*imgView1.setImageURI(imageUri);
                textview = (TextView) findViewById(R.id.myTextView);
                textview.setText("Good");*/

            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
            }
            Toast.makeText(this, "Result Code bad", Toast.LENGTH_SHORT);

        }
        else

        {

        }


    }

    public File createTimeStampedFile()
    {
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageStorageDirectory = new File(imageRootPath, "CameraDemo1");
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
}
