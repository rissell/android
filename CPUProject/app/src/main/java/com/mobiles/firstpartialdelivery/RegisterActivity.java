package com.mobiles.firstpartialdelivery;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static int NEWACT_CODE =1;
    EditText usr, name, mail, passw, cpassw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);
        usr = (EditText)findViewById(R.id.editText3);
        name = (EditText)findViewById(R.id.editText4);
        mail = (EditText)findViewById(R.id.editText5);
        passw = (EditText)findViewById(R.id.editText6);
        cpassw = (EditText)findViewById(R.id.editText7);
    }

    public void sendToLogin(View v){
        final RegisterActivity esta = this;

        Firebase ref = new Firebase("https://cpu.firebaseio.com/");
        ref.createUser(mail.getText().toString(), passw.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                Firebase refUser = new Firebase("https://cpu.firebaseio.com/"+ result.get("uid"));
                Map<String, Object> userPost = new HashMap<String, Object>();
                userPost.put("name", name.getText().toString());
                userPost.put("usr", usr.getText().toString());
                userPost.put("email", mail.getText().toString());

                refUser.setValue(userPost);

                Map<String, Object> post1 = new HashMap<String, Object>();
                post1.put("header", "Titulo de Ejemplo");
                post1.put("content", "Contenido de Ejemplo");
                post1.put("important", false);
                post1.put("privacy", true);

                Firebase newNoteKey = refUser.child("posts").push();
                post1.put("key", newNoteKey.toString());
                newNoteKey.setValue(post1);

                Intent intent = new Intent(esta, Main_Sreen.class);
                intent.putExtra("usr", result.get("uid").toString());
                startActivityForResult(intent, NEWACT_CODE);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });

    }
}
