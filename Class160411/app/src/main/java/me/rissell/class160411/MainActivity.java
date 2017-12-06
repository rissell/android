package me.rissell.class160411;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final static int TAKE_PICTURE = 0;
    private final static int SAVE_PICTURE = 1;
    private final static int TAKE_VIDEO = 2;

    ImageView image;
    private String lastPictureURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.imageView);
    }

    public void takePicture(View v){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, TAKE_PICTURE);
        }


    }

    public void savePicture(View v){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            //request
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);

            return;
        }


        Intent savePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photo = null;

        try{

            String time = new SimpleDateFormat("yyyMMdd-HHmmss").format(new Date());
            String name = "IMAGE_" + time;
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            photo = File.createTempFile(name, ".jpg", directory);
            lastPictureURI = photo.getAbsolutePath();

        } catch (Exception e){
            Log.d("Taking picture ", e.toString());
        }

        if(photo != null){

            savePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, lastPictureURI);
            startActivityForResult(savePicIntent, SAVE_PICTURE);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode == SAVE_PICTURE){

                Log.d("ONACTIVITYRESULT", "SAVEPICTURE");
                Bitmap bitmap = BitmapFactory.decodeFile(lastPictureURI);
                image.setImageBitmap(bitmap);

            }
        }

    }


}
