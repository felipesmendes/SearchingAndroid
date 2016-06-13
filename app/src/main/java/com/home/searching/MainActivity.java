package com.home.searching;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.*;
import com.firebase.client.*;

public class MainActivity extends AppCompatActivity {

    private Firebase myFirebaseRef;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://torrid-inferno-78.firebaseio.com/");

        username = (EditText)findViewById(R.id.editTxtLogin);

        myFirebaseRef.child("Item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                username.setText((String) snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }
            @Override public void onCancelled(FirebaseError error) { }
        });
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

        EditText username = (EditText)findViewById(R.id.editTxtLogin);
        // Stores Password
        EditText password = (EditText)findViewById(R.id.editTxtSenha);



        // Validates the User name and Password for admin, admin
        //if (username.getText().equals("admin") && password.getText().equals("admin")) {}

        Intent i = new Intent(MainActivity.this, ListagemActivity.class);
        startActivity(i);
    }
}
