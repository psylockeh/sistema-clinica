package DAO;



import org.teste.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendamentoDAO {
    private static Connection connection;

    public AgendamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public static List<Agendamento> listarAgendamentosPorPacienteId(int pacienteId) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM agendamento WHERE pacienteId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pacienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Agendamento agendamento = new Agendamento(
                            rs.getInt("id"),
                            rs.getInt("pacienteId"),
                            rs.getInt("exameId"),
                            rs.getDate("data"),
                            rs.getTime("horario"),
                            rs.getInt("medicoId"),
                            rs.getInt("tecnicoId")
                    );
                    agendamentos.add(agendamento);
                }
            }
        }
        return agendamentos;
    }

    public static List<Agendamento> listarAgendamentosPorMedicoId(int medicoId) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM agendamento WHERE medicoId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, medicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Agendamento agendamento = new Agendamento(
                            rs.getInt("id"),
                            rs.getInt("pacienteId"),
                            rs.getInt("exameId"),
                            rs.getDate("data"),
                            rs.getTime("horario"),
                            rs.getInt("medicoId"),
                            rs.getInt("tecnicoId")
                    );
                    agendamentos.add(agendamento);
                }
            }
        }
        return agendamentos;
    }

    public static List<Agendamento> listarAgendamentosPorExameId(int exameId) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM agendamento WHERE exameId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, exameId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Agendamento agendamento = new Agendamento(
                            rs.getInt("id"),
                            rs.getInt("pacienteId"),
                            rs.getInt("exameId"),
                            rs.getDate("data"),
                            rs.getTime("horario"),
                            rs.getInt("medicoId"),
                            rs.getInt("tecnicoId")
                    );
                    agendamentos.add(agendamento);
                }
            }
        }
        return agendamentos;
    }

    public static List<Agendamento> listarAgendamentosPorTecnicoId(int tecnicoId) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM agendamento WHERE tecnicoId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tecnicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Agendamento agendamento = new Agendamento(
                            rs.getInt("id"),
                            rs.getInt("pacienteId"),
                            rs.getInt("exameId"),
                            rs.getDate("data"),
                            rs.getTime("horario"),
                            rs.getInt("medicoId"),
                            rs.getInt("tecnicoId")
                    );
                    agendamentos.add(agendamento);
                }
            }
        }
        return agendamentos;
    }


    public void adicionarAgendamento(Agendamento agendamento) throws SQLException {
        String sql = "INSERT INTO agendamento (pacienteId, exameId, data, horario, medicoId, tecnicoId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getPacienteId());
            stmt.setInt(2, agendamento.getExameId());
            stmt.setDate(3, new java.sql.Date(agendamento.getData().getTime()));
            stmt.setTime(4, new java.sql.Time(agendamento.getHorario().getTime()));
            stmt.setInt(5, agendamento.getMedicoId());
            stmt.setInt(6, agendamento.getTecnicoId());
            stmt.executeUpdate();
        }
    }

    public List<Agendamento> listarAgendamentos() throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM agendamento";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                        rs.getInt("id"),
                        rs.getInt("pacienteId"),
                        rs.getInt("exameId"),
                        rs.getDate("data"),
                        rs.getTime("horario"),
                        rs.getInt("medicoId"),
                        rs.getInt("tecnicoiD")
                );
                agendamentos.add(agendamento);
            }
        }
        return agendamentos;
    }

    public Agendamento buscarAgendamentoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM agendamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Agendamento(
                            rs.getInt("id"),
                            rs.getInt("pacienteId"),
                            rs.getInt("exameId"),
                            rs.getDate("data"),
                            rs.getTime("horario"),
                            rs.getInt("medicoId"),
                            rs.getInt("tecnicoiD")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarAgendamento(Agendamento agendamento) throws SQLException {
        String sql = "UPDATE agendamento SET pacienteId = ?, exameIdd = ?, data = ?, horario = ?, medicoId = ?, tecnicoId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getPacienteId());
            stmt.setInt(2, agendamento.getExameId());
            stmt.setDate(3, new java.sql.Date(agendamento.getData().getTime()));
            stmt.setTime(4, new java.sql.Time(agendamento.getHorario().getTime()));
            stmt.setInt(5, agendamento.getMedicoId());
            stmt.setInt(6, agendamento.getTecnicoId());
            stmt.setInt(7, agendamento.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarAgendamento(int id) throws SQLException {
        String sql = "DELETE FROM agendamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
