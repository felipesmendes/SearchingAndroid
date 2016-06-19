package com.home.searching;

import android.os.Bundle;
import Util.Constants;
import Util.User;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.*;
import com.firebase.client.*;
import io.realm.Realm;

import com.firebase.*;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Firebase FirebaseRef;
    private Realm mRealm;
    EditText email;
    EditText password;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (EditText)findViewById(R.id.editTxtLogin);
        password = (EditText)findViewById(R.id.editTxtSenha);

        //Firebase
        Firebase.setAndroidContext(this);
        FirebaseRef = new Firebase(Constants.FIREBASE_URL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Login(View view) {



        String emailStr = email.getText().toString().trim();
        String passStr = password.getText().toString().trim();

        if (emailStr.isEmpty() || passStr.isEmpty()) {
            Toast.makeText(this, "Email ou Senha vazios.", Toast.LENGTH_LONG).show();
        } else {

            final String emailAddress = emailStr;


            FirebaseRef.authWithPassword(emailAddress, passStr, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {

                    user = new User(authData.getUid(), emailAddress);
                    Intent i = new Intent(MainActivity.this, ListagemActivity.class);
                    startActivity(i);

                }
                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    Toast.makeText(MainActivity.this, "Problema de autenticação." +
                            "Email ou senha errados.", Toast.LENGTH_LONG).show();
                }
            });
        }

        // Validates the User name and Password for admin, admin
        //if (username.getText().equals("admin") && password.getText().equals("admin")) {}


    }
}
