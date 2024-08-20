package UI;

import DAO.AgendamentoDAO;
import DAO.ExameDAO;
import DB.Conexao;
import org.teste.Agendamento;
import org.teste.Exame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExameUI extends JPanel {
    private JTextField idField;
    private JTextField nomeField;
    private JTextField descricaoField;
    private JTextField preparacaoField;
    private JTextField orientacoesField;
    private ExameDAO exameDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    public ExameUI() {
        try {
            Connection connection = Conexao.getConnection();
            exameDAO = new ExameDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        idField = new JTextField();
        nomeField = new JTextField();
        descricaoField = new JTextField();
        preparacaoField = new JTextField();
        orientacoesField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar Exame");
        JButton atualizarButton = new JButton("Atualizar Exame");
        JButton buscarButton = new JButton("Buscar Exame");
        JButton deletarButton = new JButton("Deletar Exame");
        JButton listarButton = new JButton("Listar Exames");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarExame();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarExame();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarExame();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarExame();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarExames();
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("Descrição:"));
        add(descricaoField);
        add(new JLabel("Preparação:"));
        add(preparacaoField);
        add(new JLabel("Orientações:"));
        add(orientacoesField);
        add(adicionarButton);
        add(atualizarButton);
        add(buscarButton);
        add(deletarButton);
        add(listarButton);


    tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Preparação", "Orientações"}, 0);
    table = new JTable(tableModel);
    add(new JScrollPane(table));
}
    private void adicionarExame() {
        try {
            // Validação dos campos
            if (nomeField.getText().isEmpty() || descricaoField.getText().isEmpty() || preparacaoField.getText().isEmpty() || orientacoesField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criação do objeto Exame
            String nome = nomeField.getText();
            String descricao = descricaoField.getText();
            String preparacao = preparacaoField.getText();
            String orientacoes = orientacoesField.getText();
            Exame exame = new Exame(0, nome, descricao, preparacao, orientacoes);

            // Tentativa de adicionar o exame ao banco de dados
            exameDAO.adicionarExame(exame);
            JOptionPane.showMessageDialog(this, "Exame adicionado com sucesso.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar exame: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void atualizarExame() {
        int id = Integer.parseInt(idField.getText());
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        String preparacao = preparacaoField.getText();
        String orientacoes = orientacoesField.getText();
        Exame exame = new Exame(id, nome, descricao, preparacao, orientacoes);
        try {
            exameDAO.atualizarExame(exame);
            JOptionPane.showMessageDialog(this, "Exame atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar exame.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarExame() {
        int id = Integer.parseInt(idField.getText());
        try {
            Exame exame = exameDAO.buscarExamePorId(id);
            if (exame != null) {
                nomeField.setText(exame.getNome());
                descricaoField.setText(exame.getDescricao());
                preparacaoField.setText(exame.getPreparacao());
                orientacoesField.setText(exame.getOrientacoes());
            } else {
                JOptionPane.showMessageDialog(this, "Exame não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar exame.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarExame() {
        int id = Integer.parseInt(idField.getText());
        try {
            // Verificar se existem agendamentos associados ao exame
            List<Agendamento> agendamentos = AgendamentoDAO.listarAgendamentosPorExameId(id);
            if (!agendamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é possível deletar o exame. Existem agendamentos associados a este exame.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            exameDAO.deletarExame(id);
            JOptionPane.showMessageDialog(this, "Exame deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao deletar exame: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarExames() {
        try {
            List<Exame> exames = exameDAO.listarExames();
            tableModel.setRowCount(0); // Limpar tabela
            for (Exame exame : exames) {
                tableModel.addRow(new Object[]{
                        exame.getId(),
                        exame.getNome(),
                        exame.getDescricao(),
                        exame.getPreparacao(),
                        exame.getOrientacoes()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar exames.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
