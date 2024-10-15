package com.example.alunosjsoncomlibhttp;

import java.util.List;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Alunos {

    @SerializedName("alunos")
    @Expose
    private List<Aluno> alunos;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Alunos() {
    }

    public Alunos(List<Aluno> alunos) {
        super();
        this.alunos = alunos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

}
