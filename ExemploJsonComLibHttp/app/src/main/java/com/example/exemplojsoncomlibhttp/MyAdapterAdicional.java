package com.example.exemplojsoncomlibhttp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterAdicional extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Adicional> itens;

    public MyAdapterAdicional(Context context, ArrayList<Adicional> itens) {
        this.inflater = LayoutInflater.from(context);
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Adicional item = itens.get(i);
        view = inflater.inflate(R.layout.list_adicional, null);

        TextView textId = view.findViewById(R.id.adicionalid);
        TextView textEmail = view.findViewById(R.id.adicionalemail);

        textId.setText(textId.getText().toString()+ item.getId());
        textEmail.setText(textEmail.getText().toString() + item.getEmail());

        return view;
    }
}

