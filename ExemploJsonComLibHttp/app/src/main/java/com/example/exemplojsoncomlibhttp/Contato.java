package com.example.exemplojsoncomlibhttp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Contato {

    @SerializedName("agenda")
    @Expose

    private List<Agenda> agenda;
    @SerializedName("adicionais")
    @Expose
    private List<Adicional> adicionals;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Contato() {
    }

    public Contato(List<Agenda> agenda, List<Adicional> adicionals) {
        super();
        this.agenda = agenda;
        this.adicionals = adicionals;
    }

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public List<Adicional> getAdicionais() {
        return adicionals;
    }

    public void setAdicionais(List<Adicional> adicionals) {
        this.adicionals = adicionals;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "agenda=" + agenda +
                ", adicionais=" + adicionals +
                '}';
    }
}
