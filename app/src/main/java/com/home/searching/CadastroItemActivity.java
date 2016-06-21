package com.home.searching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import entidades.Item;
import io.realm.Realm;

public class CadastroItemActivity extends AppCompatActivity {

    private Realm mRealm;
    private EditText nome;
    private Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRealm = Realm.getDefaultInstance();

    }

    public void SalvarItem(View view) {

        //pega dados da tela
        nome = (EditText) findViewById(R.id.CadItem);
        String strnome = nome.getText().toString();

        if (strnome.equals("")) {
            Toast.makeText(this, "Nome do item não pode ser vazio.", Toast.LENGTH_SHORT).show();
            return;
        }

        mySpinner = (Spinner) findViewById(R.id.serial);
        String serial = mySpinner.getSelectedItem().toString();

        //realm

        mRealm.beginTransaction();
        Item newItem = mRealm.createObject(Item.class);

        // geração de ID e salvar
        int nextID = (int) (mRealm.where(Item.class).maximumInt("ID") + 1);
        newItem.setID(nextID);

        newItem.setNome(strnome);
        newItem.setSerial(serial);

        mRealm.commitTransaction();

        //carrega proxima tela e enviar ID do item criado
        Intent i = new Intent(this, CercaActivity.class);
        Bundle b = new Bundle();

        b.putInt("ID", newItem.getID());
        i.putExtras(b);

        startActivity(i);
        finish();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
