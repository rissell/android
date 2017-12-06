package com.mobiles.firstpartialdelivery;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {
    private static int NEWACT_CODE =1;
    public EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        user = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
    }

    public void goToMainScreen(View v){
        String userdata =user.getText() + " , " + password.getText();
        Intent intent = new Intent(this, Main_Sreen.class);
        Toast.makeText(getApplicationContext(), userdata, Toast.LENGTH_SHORT).show();
        intent.putExtra("Udata", userdata);
        startActivityForResult(intent, NEWACT_CODE);

    }

    public void sendToRegister(View v){

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, NEWACT_CODE );

    }

    public void login(View v) {
        Firebase ref = new Firebase("https://cpu.firebaseio.com/");
        final LoginActivity esta = this;

        ref.authWithPassword(user.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Intent intent = new Intent(esta, Main_Sreen.class);
                intent.putExtra("usr", authData.getUid());
                startActivityForResult(intent, NEWACT_CODE );
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // Authenticated failed with error firebaseError
            }
        });
    }
}
