package com.home.searching;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import entidades.Item;

public class CadastroItemActivity extends AppCompatActivity {
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://torrid-inferno-78.firebaseio.com/");


    }

    public void SalvarItem(View view) {
        Intent i = new Intent(this, CercaActivity.class);
        startActivity(i);


        EditText nome = (EditText) findViewById(R.id.CadItem);
        String strnome = nome.getText().toString();
        Spinner mySpinner = (Spinner)findViewById(R.id.serial);
        String serial = mySpinner.getSelectedItem().toString();
        Firebase item = firebase;
        Map<String, String> itens = new HashMap<String, String>();
        itens.put(strnome, serial);
        item.push().setValue(itens);
    }
}
