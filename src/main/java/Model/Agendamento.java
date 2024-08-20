package org.teste;

import java.util.Date;

public class Agendamento {
    private int id;
    private int pacienteId;
    private int exameId;
    private Date data;
    private Date horario;
    private int medicoId;
    private int tecnicoId;

    public Agendamento(int id, int pacienteId, int exameId, Date data, Date horario, int medicoId, int tecnicoId) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.exameId = exameId;
        this.data = data;
        this.horario = horario;
        this.medicoId = medicoId;
        this.tecnicoId = tecnicoId;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getExameId() {
        return exameId;
    }

    public void setExameId(int exameId) {
        this.exameId = exameId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(int tecnicoId) {
        this.tecnicoId = tecnicoId;
    }
}


