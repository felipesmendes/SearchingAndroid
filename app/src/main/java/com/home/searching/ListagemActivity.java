package com.home.searching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import entidades.Item;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListagemActivity extends AppCompatActivity {

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRealm = Realm.getDefaultInstance();

        //recupear itens do Realm e os carrega no ListView
        RealmResults<Item> itens = mRealm.where(Item.class).findAll();
        ArrayList<String> itensFormatados = new ArrayList<>();

        //carregando uma lista com nomes formatados para exibição na lista
        for (Item item : itens) {
            itensFormatados.add(Item.nomeFormatado(item));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itensFormatados);
        ListView listView = (ListView) findViewById(R.id.Listagem);

        listView.setAdapter(adapter);

        final ListView listViewListener = listView;

        //Captura clique em item do listview
        //Invoca a tela CercaActivity com o item clicado
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String itemValue = (String) listViewListener.getItemAtPosition(position);
                String itemId = itemValue.substring(4, itemValue.indexOf("Item") - 1);

                Intent intent = new Intent(ListagemActivity.this, CercaActivity.class);
                Bundle b = new Bundle();

                b.putInt("ID", Integer.parseInt(itemId));
                intent.putExtras(b);

                startActivity(intent);
                finish();

            }

        });
    }

    public void addItem(View view) {
        Intent i = new Intent(ListagemActivity.this, CadastroItemActivity.class);
        startActivity(i);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
