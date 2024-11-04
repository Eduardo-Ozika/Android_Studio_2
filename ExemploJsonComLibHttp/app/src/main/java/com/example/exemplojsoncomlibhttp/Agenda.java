package com.example.exemplojsoncomlibhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Agenda {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("telefone")
    @Expose
    private String telefone;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Agenda() {
    }

    public Agenda(Integer id, String nome, String telefone) {
        super();
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "\nAgenda{" +
                "\nid=" + id +
                "\nnome='" + nome + '\'' +
                "\ntelefone='" + telefone + '\'' +
                "\n}\n";
    }
}
