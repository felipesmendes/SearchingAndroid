package com.home.searching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import entidades.Item;
import io.realm.Realm;

public class CercaActivity extends AppCompatActivity {

    private WebView mWebview;
    private Realm mRealm;
    private TextView itemNomeTxtView;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mWebview = (WebView) findViewById(R.id.webViewCerca);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        mRealm = Realm.getDefaultInstance();

        itemNomeTxtView = (TextView) findViewById(R.id.NomeItem);
        //recuperando Item
        Bundle b = getIntent().getExtras();

        mRealm.beginTransaction();

        if (b != null) {
            item = mRealm.where(Item.class).equalTo("ID", b.getInt("ID")).findFirst();
            itemNomeTxtView.setText(item.getNome());
        } else {

        }

        final Activity activity = this;

        //em caso de erro no webview
        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebview.loadUrl("https://www.google.com.br/maps");

        mRealm.commitTransaction();
    }

    public void SalvarCerca(View view) {
        //logica para salvar a cerca

        Intent i = new Intent(this, ListagemActivity.class);
        startActivity(i);
        finish();
    }

    public void DeletarItem(View view) {

        mRealm.beginTransaction();
        mRealm.where(Item.class).equalTo("ID", item.getID()).findFirst().removeFromRealm();
        mRealm.commitTransaction();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
