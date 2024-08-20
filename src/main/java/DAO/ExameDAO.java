package DAO;
import org.teste.Exame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ExameDAO {
    private Connection connection;

    public ExameDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarExame(Exame exame) throws SQLException {
        String sql = "INSERT INTO exames (nome, descricao, preparacao, orientacoes) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, exame.getNome());
            stmt.setString(2, exame.getDescricao());
            stmt.setString(3, exame.getPreparacao());
            stmt.setString(4, exame.getOrientacoes());
            stmt.executeUpdate();
        }
    }

    public List<Exame> listarExames() throws SQLException {
        List<Exame> exames = new ArrayList<>();
        String sql = "SELECT * FROM exames";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Exame exame = new Exame(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("preparacao"),
                        rs.getString("orientacoes")
                );
                exames.add(exame);
            }
        }
        return exames;
    }

    public Exame buscarExamePorId(int id) throws SQLException {
        String sql = "SELECT * FROM exames WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Exame(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getString("preparacao"),
                            rs.getString("orientacoes")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarExame(Exame exame) throws SQLException {
        String sql = "UPDATE exames SET nome = ?, descricao = ?, preparacao = ?, orientacoes = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, exame.getNome());
            stmt.setString(2, exame.getDescricao());
            stmt.setString(3, exame.getPreparacao());
            stmt.setString(4, exame.getOrientacoes());
            stmt.setInt(5, exame.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarExame(int id) throws SQLException {
        String sql = "DELETE FROM exames WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
