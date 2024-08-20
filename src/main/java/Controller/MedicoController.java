package Controller;

import Service.MedicoService;
import org.teste.Medico;

import java.util.List;

public class MedicoController {
    private MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    public void adicionarMedico(Medico medico) {
        medicoService.adicionarMedico(medico);
    }

    public List<Medico> listarMedicos() {
        return medicoService.listarMedicos();
    }

    public Medico buscarMedicoPorId(int id) {
        return medicoService.buscarMedicoPorId(id);
    }

    public void atualizarMedico(Medico medico) {
        medicoService.atualizarMedico(medico);
    }

    public void deletarMedico(int id) {
        medicoService.deletarMedico(id);
    }
}

