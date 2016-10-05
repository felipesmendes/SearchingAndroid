package com.home.searching;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;

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

    private Realm mRealm;
    private TextView itemNomeTxtView;
    Item item;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        }
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
        String[] cordenadas = Constants.mapUrl(item.getSerial()).split(",");


        // Add a marker in Sydney and move the camera
        LatLng mark = new LatLng(Double.parseDouble(cordenadas[0]), Double.parseDouble(cordenadas[1]));
        mMap.addMarker(new MarkerOptions().position(mark).title(item.getNome()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mark));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

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