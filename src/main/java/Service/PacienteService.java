package Service;

import DAO.PacienteDAO;
import org.teste.Paciente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PacienteService {
    private PacienteDAO pacienteDAO;

    public PacienteService(Connection connection) {
        this.pacienteDAO = new PacienteDAO(connection);
    }

    public void adicionarPaciente(Paciente paciente) {
        try {
            pacienteDAO.adicionarPaciente(paciente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> listarPacientes() {
        try {
            return pacienteDAO.listarPacientes();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Paciente buscarPacientePorId(int id) {
        try {
            return pacienteDAO.buscarPacientePorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarPaciente(Paciente paciente) {
        try {
            pacienteDAO.atualizarPaciente(paciente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarPaciente(int id) {
        try {
            pacienteDAO.deletarPaciente(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

