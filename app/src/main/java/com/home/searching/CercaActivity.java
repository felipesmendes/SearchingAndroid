package com.home.searching;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import Util.Constants;
import entidades.Item;
import io.realm.Realm;

public class CercaActivity extends FragmentActivity implements OnMapReadyCallback {

    private WebView mWebview;
    private Realm mRealm;
    private TextView itemNomeTxtView;
    Item item;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //setando webview
       // mWebview = (WebView) findViewById(R.id.webViewCerca);
       // mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        mRealm = Realm.getDefaultInstance();

        itemNomeTxtView = (TextView) findViewById(R.id.NomeItem);
        //recuperando Item
        Bundle b = getIntent().getExtras();

        //por segurança confere se foram enviados dados
        if (b != null) {

            //recupera item do Realm
            mRealm.beginTransaction();
            item = mRealm.where(Item.class).equalTo("ID", b.getInt("ID")).findFirst();
            mRealm.commitTransaction();

            //carrega os elementos da tela a partir do Item
            itemNomeTxtView.setText(item.getNome());
            String mapHtml = Constants.mapUrl(item.getSerial());
           // mWebview.loadData(mapHtml, "text/html", null);

        }

        final Activity activity = this;

        //em caso de erro no webview
        /*mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //recuperando Item
        Bundle b = getIntent().getExtras();

        //recupera item do Realm
        mRealm.beginTransaction();
        item = mRealm.where(Item.class).equalTo("ID", b.getInt("ID")).findFirst();
        mRealm.commitTransaction();

        //carrega os elementos da tela a partir do Item
        itemNomeTxtView.setText(item.getNome());
        String mapHtml = Constants.mapUrl(item.getSerial());

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-19.919007, -43.938678);
        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void Ok(View view) {
        Intent i = new Intent(this, ListagemActivity.class);
        startActivity(i);
        finish();
    }

    public void DeletarItem(View view) {

        //exclui item do Realm
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