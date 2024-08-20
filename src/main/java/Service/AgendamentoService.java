package Service;

import DAO.AgendamentoDAO;
import org.teste.Agendamento;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AgendamentoService {
    private final AgendamentoDAO agendamentoDAO;

    public AgendamentoService(Connection connection) {
        this.agendamentoDAO = new AgendamentoDAO(connection);
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        try {
            agendamentoDAO.adicionarAgendamento(agendamento);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Agendamento> listarAgendamentos() {
        try {
            return agendamentoDAO.listarAgendamentos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Agendamento buscarAgendamentoPorId(int id) {
        try {
            return agendamentoDAO.buscarAgendamentoPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarAgendamento(Agendamento agendamento) {
        try {
            agendamentoDAO.atualizarAgendamento(agendamento);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarAgendamento(int id) {
        try {
            agendamentoDAO.deletarAgendamento(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

