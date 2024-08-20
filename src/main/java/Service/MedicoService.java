package Service;

import DAO.MedicoDAO;
import org.teste.Medico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MedicoService {
    private MedicoDAO medicoDAO;

    public MedicoService(Connection connection) {
        this.medicoDAO = new MedicoDAO(connection);
    }

    public void adicionarMedico(Medico medico) {
        try {
            medicoDAO.adicionarMedico(medico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medico> listarMedicos() {
        try {
            return medicoDAO.listarMedicos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Medico buscarMedicoPorId(int id) {
        try {
            return medicoDAO.buscarMedicoPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarMedico(Medico medico) {
        try {
            medicoDAO.atualizarMedico(medico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarMedico(int id) {
        try {
            medicoDAO.deletarMedico(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
