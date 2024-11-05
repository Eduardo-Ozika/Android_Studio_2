package com.example.alunosjsoncomlibhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterAluno extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Aluno> itens;

    public MyAdapterAluno(Context context, ArrayList<Aluno> itens) {
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
        Aluno item = itens.get(i);
        view = inflater.inflate(R.layout.list_aluno, null);

        TextView textId = view.findViewById(R.id.alunonota);
        TextView textNome = view.findViewById(R.id.alunonome);

        textId.setText(textId.getText().toString() + item.getNotas());
        textNome.setText(textNome.getText().toString() + item.getNome());
        return view;
    }
}
