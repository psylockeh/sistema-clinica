package DAO;

import org.teste.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    private Connection connection;

    public MedicoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarMedico(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico (nome, especialidade, contato) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.executeUpdate();
        }
    }

    public List<Medico> listarMedicos() throws SQLException {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medico";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medico medico = new Medico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("crm")
                );
                medicos.add(medico);
            }
        }
        return medicos;
    }

    public Medico buscarMedicoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM medico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Medico(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("especialidade"),
                            rs.getString("crm")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarMedico(Medico medico) throws SQLException {
        String sql = "UPDATE medico SET nome = ?, especialidade = ?, contato = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.setInt(4, medico.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarMedico(int id) throws SQLException {
        String sql = "DELETE FROM medico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
