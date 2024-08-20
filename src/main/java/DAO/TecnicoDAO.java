package DAO;

import org.teste.Tecnico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TecnicoDAO {
    private final Connection connection;

    public TecnicoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarTecnico(Tecnico tecnico) throws SQLException {
        String sql = "INSERT INTO tecnico (nome, especialidade, contato) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());
            stmt.setString(3, tecnico.getContato());
            stmt.executeUpdate();
        }
    }

    public List<Tecnico> listarTecnicos() throws SQLException {
        List<Tecnico> tecnicos = new ArrayList<>();
        String sql = "SELECT * FROM tecnico";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Tecnico tecnico = new Tecnico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("contato")
                );
                tecnicos.add(tecnico);
            }
        }
        return tecnicos;
    }

    public Tecnico buscarTecnicoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tecnico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tecnico(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("especialidade"),
                            rs.getString("contato")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarTecnico(Tecnico tecnico) throws SQLException {
        String sql = "UPDATE tecnico SET nome = ?, especialidade = ?, contato = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());
            stmt.setString(3, tecnico.getContato());
            stmt.setInt(4, tecnico.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarTecnico(int id) throws SQLException {
        String sql = "DELETE FROM tecnico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

