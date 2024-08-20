package org.teste;

import java.sql.Date;

public class ResultadoExame {
    private int id;
    private int agendamentoId;
    private String resultados;
    private String observacoes;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(int agendamentoId) {
        this.agendamentoId = agendamentoId;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ResultadoExame(int id, int agendamentoId, String resultados, String observacoes, Date data) {
        this.id = id;
        this.agendamentoId = agendamentoId;
        this.resultados = resultados;
        this.observacoes = observacoes;
        this.data = String.valueOf(data);
    }
}
