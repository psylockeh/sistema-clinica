package org.teste;

public class Exame {
    private int id;
    private String nome;
    private String descricao;
    private String preparacao;
    private String orientacoes;


    public Exame(int id, String nome, String descricao, String preparacao, String orientacoes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preparacao = preparacao;
        this.orientacoes = orientacoes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreparacao() {
        return preparacao;
    }

    public void setPreparacao(String preparacao) {
        this.preparacao = preparacao;
    }

    public String getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(String orientacoes) {
        this.orientacoes = orientacoes;
    }
}

