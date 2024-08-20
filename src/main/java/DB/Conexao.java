package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/clinica"; // URL do banco de dados
    private static final String USER = "root"; // Nome de usuário do banco de dados
    private static final String PASSWORD = "debora12"; // Senha do banco de dados

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        testConnection(); // Testar a conexão com o banco de dados
    }

    private static void testConnection() {
        try (Connection conexao = getConnection()) {
            if (conexao != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao estabelecer a conexão.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}

