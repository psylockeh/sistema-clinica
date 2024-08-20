package Controller;

import Service.AgendamentoService;
import org.teste.Agendamento;


import java.util.List;

public class AgendamentoController {
    private AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        agendamentoService.adicionarAgendamento(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoService.listarAgendamentos();
    }

    public Agendamento buscarAgendamentoPorId(int id) {
        return agendamentoService.buscarAgendamentoPorId(id);
    }

    public void atualizarAgendamento(Agendamento agendamento) {
        agendamentoService.atualizarAgendamento(agendamento);
    }

    public void deletarAgendamento(int id) {
        agendamentoService.deletarAgendamento(id);
    }
}

