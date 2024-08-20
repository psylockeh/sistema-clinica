package org.teste;

import java.sql.Date;

public class Paciente {
    private int id;
    private String nome;
    private String cpf;
    private String dataNascimento;

    public Paciente(int id, String nome, String cpf, Date dataNascimento, String contato) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = String.valueOf(dataNascimento);
        this.contato = contato;
    }

    private String contato;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
