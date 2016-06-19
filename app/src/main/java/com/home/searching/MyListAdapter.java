package com.home.searching;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import entidades.Item;
import io.realm.*;
import io.realm.RealmBaseAdapter;


public class MyListAdapter extends RealmBaseAdapter<Item> implements ListAdapter {

    private static class ViewHolder {
        TextView item;
    }

    public MyListAdapter(Context context, RealmResults<Item> realmResults) {
        super(context, realmResults, false);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.item = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Item item = adapterData.get(position);
        //viewHolder.item.setText(item.getNome());
        return convertView;
    }
}
