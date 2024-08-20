package Controller;
import Service.ResultadoExameService;
import org.teste.ResultadoExame;

import java.util.List;
public class ResultadoExameController {
    private final ResultadoExameService resultadoExameService;

    public ResultadoExameController(ResultadoExameService resultadoExameService) {
        this.resultadoExameService = resultadoExameService;
    }

    public void adicionarResultadoExame(ResultadoExame resultadoExame) {
        resultadoExameService.adicionarResultadoExame(resultadoExame);
    }

    public List<ResultadoExame> listarResultadosExames() {
        return resultadoExameService.listarResultadosExames();
    }

    public ResultadoExame buscarResultadoExamePorId(int id) {
        return resultadoExameService.buscarResultadoExamePorId(id);
    }

    public void atualizarResultadoExame(ResultadoExame resultadoExame) {
        resultadoExameService.atualizarResultadoExame(resultadoExame);
    }

    public void deletarResultadoExame(int id) {
        resultadoExameService.deletarResultadoExame(id);
    }
}


