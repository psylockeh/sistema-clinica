package Controller;

import Service.TecnicoService;
import org.teste.Tecnico;


import java.util.List;

public class TecnicoController {
    private TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    public void adicionarTecnico(Tecnico tecnico) {
        tecnicoService.adicionarTecnico(tecnico);
    }

    public List<Tecnico> listarTecnicos() {
        return tecnicoService.listarTecnicos();
    }

    public Tecnico buscarTecnicoPorId(int id) {
        return tecnicoService.buscarTecnicoPorId(id);
    }

    public void atualizarTecnico(Tecnico tecnico) {
        tecnicoService.atualizarTecnico(tecnico);
    }

    public void deletarTecnico(int id) {
        tecnicoService.deletarTecnico(id);
    }
}

