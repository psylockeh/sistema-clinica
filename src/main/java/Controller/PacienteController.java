package Controller;

import Service.PacienteService;
import org.teste.Paciente;

import java.util.List;

public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public void adicionarPaciente(Paciente paciente) {
        pacienteService.adicionarPaciente(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    public Paciente buscarPacientePorId(int id) {
        return pacienteService.buscarPacientePorId(id);
    }

    public void atualizarPaciente(Paciente paciente) {
        pacienteService.atualizarPaciente(paciente);
    }

    public void deletarPaciente(int id) {
        pacienteService.deletarPaciente(id);
    }
}

