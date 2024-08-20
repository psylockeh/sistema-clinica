package DAO;

import org.teste.ResultadoExame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultadoExameDAO {
    private Connection connection;

    public ResultadoExameDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarResultadoExame(ResultadoExame resultadoExame) throws SQLException {
        String sql = "INSERT INTO resultadoexame (agendamentoId, resultados, observacoes, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, resultadoExame.getAgendamentoId());
            stmt.setString(2, resultadoExame.getResultados());
            stmt.setString(3, resultadoExame.getObservacoes());
            stmt.setString(4, resultadoExame.getData());
            stmt.executeUpdate();
        }
    }

    public List<ResultadoExame> listarResultadosExames() throws SQLException {
        List<ResultadoExame> resultadosExames = new ArrayList<>();
        String sql = "SELECT * FROM resultadoexame";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ResultadoExame resultadoExame = new ResultadoExame(
                        rs.getInt("id"),
                        rs.getInt("agendamentoId"),
                        rs.getString("resultados"),
                        rs.getString("observacoes"),
                        rs.getDate("data")
                );
                resultadosExames.add(resultadoExame);
            }
        }
        return resultadosExames;
    }

    public ResultadoExame buscarResultadoExamePorId(int id) throws SQLException {
        String sql = "SELECT * FROM resultadoexame WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ResultadoExame(
                            rs.getInt("id"),
                            rs.getInt("agendamentoId"),
                            rs.getString("resultados"),
                            rs.getString("observacoes"),
                            rs.getDate("data")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarResultadoExame(ResultadoExame resultadoExame) throws SQLException {
        String sql = "UPDATE resultadoexame SET agendamentoId = ?, resultados = ?, observacoes = ?, data = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, resultadoExame.getAgendamentoId());
            stmt.setString(2, resultadoExame.getResultados());
            stmt.setString(3, resultadoExame.getObservacoes());
            stmt.setString(4, resultadoExame.getData());
            stmt.setInt(5, resultadoExame.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarResultadoExame(int id) throws SQLException {
        String sql = "DELETE FROM resultadoexame WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean isPacienteAtreladoAoResultado(int resultadoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM agendamento a " +
                "JOIN resultadoexame r ON a.id = r.agendamentoId " +
                "WHERE r.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, resultadoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}

