package Controller;

import Service.ExameService;
import org.teste.Exame;


import java.util.List;

public class ExameController {
    private ExameService exameService;

    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }


    public void adicionarExame(Exame exame) {
        try {
            exameService.adicionarExame(exame);
            System.out.println("Exame adicionado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao adicionar exame: " + e.getMessage());
        }
    }

    public void listarExames() {
        try {
            List<Exame> exames = exameService.listarExames();
            for (Exame exame : exames) {
                System.out.println(exame);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar exames: " + e.getMessage());
        }
    }


    public void buscarExamePorId(int id) {
        try {
            Exame exame = exameService.buscarExamePorId(id);
            if (exame != null) {
                System.out.println(exame);
            } else {
                System.out.println("Exame não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar exame: " + e.getMessage());
        }
    }


    public void atualizarExame(Exame exame) {
        try {
            exameService.atualizarExame(exame);
            System.out.println("Exame atualizado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar exame: " + e.getMessage());
        }
    }


    public void deletarExame(int id) {
        try {
            exameService.deletarExame(id);
            System.out.println("Exame deletado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao deletar exame: " + e.getMessage());
        }
    }
}

