package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import DAO.TecnicoDAO;
import org.teste.Tecnico;


public class TecnicoService {
    private TecnicoDAO tecnicoDAO;

    public TecnicoService(Connection connection) {
        this.tecnicoDAO = new TecnicoDAO(connection);
    }

    public void adicionarTecnico(Tecnico tecnico) {
        try {
            tecnicoDAO.adicionarTecnico(tecnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tecnico> listarTecnicos() {
        try {
            return tecnicoDAO.listarTecnicos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Tecnico buscarTecnicoPorId(int id) {
        try {
            return tecnicoDAO.buscarTecnicoPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarTecnico(Tecnico tecnico) {
        try {
            tecnicoDAO.atualizarTecnico(tecnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarTecnico(int id) {
        try {
            tecnicoDAO.deletarTecnico(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

