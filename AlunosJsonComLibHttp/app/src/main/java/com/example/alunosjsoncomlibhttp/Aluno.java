package com.example.alunosjsoncomlibhttp;

import java.util.List;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Aluno {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("idade")
    @Expose
    private Integer idade;
    @SerializedName("notas")
    @Expose
    private List<Float> notas;
    @SerializedName("frequencia")
    @Expose
    private List<Integer> frequencia;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Aluno() {
    }

    public Aluno(String nome, Integer idade, List<Float> notas, List<Integer> frequencia) {
        super();
        this.nome = nome;
        this.idade = idade;
        this.notas = notas;
        this.frequencia = frequencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Float> getNotas() {
        return notas;
    }

    public void setNotas(List<Float> notas) {
        this.notas = notas;
    }

    public List<Integer> getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(List<Integer> frequencia) {
        this.frequencia = frequencia;
    }

}