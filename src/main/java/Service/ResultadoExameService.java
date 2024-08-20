package Service;

import DAO.ResultadoExameDAO;
import org.teste.ResultadoExame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ResultadoExameService {
    private ResultadoExameDAO resultadoExameDAO;

    public ResultadoExameService(Connection connection) {
        this.resultadoExameDAO = new ResultadoExameDAO(connection);
    }

    public void adicionarResultadoExame(ResultadoExame resultadoExame) {
        try {
            resultadoExameDAO.adicionarResultadoExame(resultadoExame);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ResultadoExame> listarResultadosExames() {
        try {
            return resultadoExameDAO.listarResultadosExames();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultadoExame buscarResultadoExamePorId(int id) {
        try {
            return resultadoExameDAO.buscarResultadoExamePorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarResultadoExame(ResultadoExame resultadoExame) {
        try {
            resultadoExameDAO.atualizarResultadoExame(resultadoExame);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarResultadoExame(int id) {
        try {
            resultadoExameDAO.deletarResultadoExame(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
