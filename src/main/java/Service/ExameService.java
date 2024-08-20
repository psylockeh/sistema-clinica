package Service;

import DAO.ExameDAO;
import org.teste.Exame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExameService {
    private ExameDAO exameDAO;

    public ExameService(Connection connection) {
        this.exameDAO = new ExameDAO(connection);
    }

    public void adicionarExame(Exame exame) {
        try {
            exameDAO.adicionarExame(exame);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Exame> listarExames() {
        try {
            return exameDAO.listarExames();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exame buscarExamePorId(int id) {
        try {
            return exameDAO.buscarExamePorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarExame(Exame exame) {
        try {
            exameDAO.atualizarExame(exame);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarExame(int id) {
        try {
            exameDAO.deletarExame(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
