package com.example.exemplojsoncomlibhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterAgenda extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Agenda> itens;

    public MyAdapterAgenda(Context context, ArrayList<Agenda> itens) {
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
        Agenda item = itens.get(i);
        view = inflater.inflate(R.layout.list_agenda, null);

        TextView textId = view.findViewById(R.id.agendaid);
        TextView textNome = view.findViewById(R.id.agendanome);
        TextView textTelefone = view.findViewById(R.id.agendatelefone);

        textId.setText(textId.getText().toString() + item.getId());
        textNome.setText(textNome.getText().toString() + item.getNome());
        textTelefone.setText(textTelefone.getText().toString() + item.getTelefone());
        return view;
    }
}
