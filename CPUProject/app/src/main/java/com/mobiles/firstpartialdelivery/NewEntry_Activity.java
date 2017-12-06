package com.mobiles.firstpartialdelivery;

import android.app.Activity;
import android.app.Dialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewEntry_Activity extends AppCompatActivity {

    EditText header, content;
    CheckBox important, privacy;
    Button save, delete;
    Firebase ref;
    ImageView iv;
    private String lastURI;
    private String imgURI;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int SELECT_IMAGE = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry_);

        header = (EditText) findViewById(R.id.editText);
        content = (EditText) findViewById(R.id.editText2);
        important = (CheckBox) findViewById(R.id.checkBox);
        privacy = (CheckBox) findViewById(R.id.checkBox2);
        save = (Button) findViewById(R.id.button2);
        delete = (Button) findViewById(R.id.button3);
        iv =(ImageView)findViewById(R.id.preview);


        Intent i = getIntent();

        if(i.hasExtra("Note")){
            Note received = (Note)i.getSerializableExtra("Note");

            header.setText(received.getHeader());
            content.setText(received.getContent());
            ref = new Firebase(received.getKey());

            if(received.important){
                important.setChecked(true);
            }

            if(received.privacy){
                privacy.setChecked(true);
            }

            delete.setText("Delete");
            save.setText("Update");
        }


        //Por lo pronto las carpetas estan definidas asi **SPINER
        String[] folders = {"Collegue", "Funny", "Educational", "Music"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                folders);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void noteOK(View v) {
        if(save.getText() == "Save"){
            Log.d("Save", "Button");
            if (!header.getText().toString().isEmpty() && !content.getText().toString().isEmpty()) {
                Intent i = new Intent();

                //i.putExtra("header", header.getText().toString());
                //i.putExtra("content", content.getText().toString());

                Firebase newRef = new Firebase("https://cpu.firebaseio.com/" + getIntent().getStringExtra("usr") + "/posts/");

                Map<String, Object> post1 = new HashMap<String, Object>();
                post1.put("header", header.getText().toString());
                post1.put("content", content.getText().toString());
                post1.put("important", important.isChecked());
                post1.put("privacy", privacy.isChecked());

                Firebase newNoteKey = newRef.push();
                post1.put("key", newNoteKey.toString());
                newNoteKey.setValue(post1);


                setResult(Activity.RESULT_OK, i);
                finish();

            }
        }else{
            Intent i = new Intent();

            Map<String, Object> post1 = new HashMap<String, Object>();
            post1.put("header", header.getText().toString());
            post1.put("content", content.getText().toString());
            post1.put("important", important.isChecked());
            post1.put("privacy", privacy.isChecked());

            ref.setValue(post1);

            setResult(Activity.RESULT_OK, i);
            finish();
        }


    }

    public void shareData(View v) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("Text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,  header.getText().toString()+ " " + content.getText().toString());
        startActivity(Intent.createChooser(shareIntent, "Share"));
    }

    public void deleteNote(View v) {


        if (delete.getText()=="Delete") {
            Intent i = new Intent();

            //i.putExtra("header", header.getText().toString());
            //i.putExtra("content", content.getText().toString());

            ref.removeValue();

            setResult(Activity.RESULT_OK, i);
            finish();

        } else {
            Intent i = new Intent();

            setResult(Activity.RESULT_OK, i);
            finish();
        }
    }


    public void openGallery(View v){

        /*
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(
                "content://media/internal/images/media"));
                */

        Intent intent = new Intent(Intent.ACTION_PICK,  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        intent.setType("image/*");

        //startActivityForResult(intent, SELECT_IMAGE);


        startActivityForResult(
                Intent.createChooser(intent, "Select File"), SELECT_IMAGE);


    }

    public void enlargeImg(View v){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(imgURI), "image/*");
        startActivity(intent);
    }




    public void openCamera(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){

            switch(requestCode){


                case REQUEST_IMAGE_CAPTURE:
                    getImg();
                    Bundle extra = data.getExtras();
                    Bitmap image = (Bitmap)extra.get("data");
                    iv.setImageBitmap(image);
                    break;

                case SELECT_IMAGE:

                    Uri selectedImg = data.getData();
                    imgURI = selectedImg.toString();

                    String[] projection = { MediaStore.MediaColumns.DATA };
                    CursorLoader cursorLoader = new CursorLoader(this,selectedImg, projection, null, null,
                            null);
                    Cursor cursor =cursorLoader.loadInBackground();
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                    cursor.moveToFirst();
                    String selectedImagePath = cursor.getString(column_index);
                    Bitmap bm = BitmapFactory.decodeFile(selectedImagePath);

                    Toast.makeText(this, selectedImagePath, Toast.LENGTH_LONG).show();
                    iv.setImageBitmap(Bitmap.createScaledBitmap(bm, 120, 120, false));
                    break;


                case 2:

                    //LAST URI IS WHAT YOU WANT TO STORE!/
                    Uri imgUri = data.getData();
                    imgURI = imgUri.toString();

                    Bitmap image3 = BitmapFactory.decodeFile(lastURI);
                    iv.setImageBitmap(image3);
                    break;

            }
        }
    }

    public void getImg(){

        File photo = null;
        Intent savePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        try{
            String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String name = "IMAGE_" + time;
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            photo = File.createTempFile(name, ".jpg", directory);
            lastURI = photo.getAbsolutePath();

        }catch(Exception e){
            e.printStackTrace();
        }

        if(photo != null){
            try {
                //THIS IS MEANT TO BE STORED
                savePic.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                startActivityForResult(savePic, 2);
            }catch(Exception e){

                e.printStackTrace();
            }
        }


    }






    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewEntry_ Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.mobiles.firstpartialdelivery/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewEntry_ Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.mobiles.firstpartialdelivery/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}