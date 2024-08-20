package UI;

import DAO.ResultadoExameDAO;
import DB.Conexao;
import org.teste.ResultadoExame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ResultadoExameUI extends JPanel {

    private JTextField idField;
    private JTextField agendamentoIdField;
    private JTextField resultadosField;
    private JTextField observacoesField;
    private JTextField dataField;
    private ResultadoExameDAO resultadoExameDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    public ResultadoExameUI() {
        try {
            Connection connection = Conexao.getConnection();
            resultadoExameDAO = new ResultadoExameDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        idField = new JTextField();
        agendamentoIdField = new JTextField();
        resultadosField = new JTextField();
        observacoesField = new JTextField();
        dataField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar Resultado");
        JButton atualizarButton = new JButton("Atualizar Resultado");
        JButton buscarButton = new JButton("Buscar Resultado");
        JButton deletarButton = new JButton("Deletar Resultado");
        JButton listarButton = new JButton("Listar Resultados");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarResultado();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarResultado();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarResultado();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarResultado();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarResultados();
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("ID do Agendamento:"));
        add(agendamentoIdField);
        add(new JLabel("Resultados:"));
        add(resultadosField);
        add(new JLabel("Observações:"));
        add(observacoesField);
        add(new JLabel("Data:"));
        add(dataField);
        add(adicionarButton);
        add(atualizarButton);
        add(buscarButton);
        add(deletarButton);
        add(listarButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "ID do Agendamento", "Resultados", "Observações", "Data"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table));

    }

    private void adicionarResultado() {
        try {
            // Validação dos campos
            if (agendamentoIdField.getText().isEmpty() || resultadosField.getText().isEmpty() || observacoesField.getText().isEmpty() || dataField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criação do objeto ResultadoExame
            int agendamentoId = Integer.parseInt(agendamentoIdField.getText());
            String resultados = resultadosField.getText();
            String observacoes = observacoesField.getText();
            java.util.Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataField.getText());
            ResultadoExame resultadoExame = new ResultadoExame(0, agendamentoId, resultados, observacoes, new java.sql.Date(data.getTime()));

            // Tentativa de adicionar o resultado ao banco de dados
            resultadoExameDAO.adicionarResultadoExame(resultadoExame);
            JOptionPane.showMessageDialog(this, "Resultado adicionado com sucesso.");

        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use yyyy-MM-dd para data.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar resultado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ID de agendamento deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void atualizarResultado() {
        int id = Integer.parseInt(idField.getText());
        int agendamentoId = Integer.parseInt(agendamentoIdField.getText());
        String resultados = resultadosField.getText();
        String observacoes = observacoesField.getText();
        Date data = Date.valueOf(dataField.getText());
        ResultadoExame resultadoExame = new ResultadoExame(id, agendamentoId, resultados, observacoes, data);
        try {
            resultadoExameDAO.atualizarResultadoExame(resultadoExame);
            JOptionPane.showMessageDialog(this, "Resultado atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar resultado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarResultado() {
        int id = Integer.parseInt(idField.getText());
        try {
            ResultadoExame resultadoExame = resultadoExameDAO.buscarResultadoExamePorId(id);
            if (resultadoExame != null) {
                agendamentoIdField.setText(String.valueOf(resultadoExame.getAgendamentoId()));
                resultadosField.setText(resultadoExame.getResultados());
                observacoesField.setText(resultadoExame.getObservacoes());
                dataField.setText(resultadoExame.getData());
            } else {
                JOptionPane.showMessageDialog(this, "Resultado não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar resultado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarResultado() {
        int id = Integer.parseInt(idField.getText());
        try {
            // Verificar se existe um paciente atrelado ao resultado do exame
            if (resultadoExameDAO.isPacienteAtreladoAoResultado(id)) {
                JOptionPane.showMessageDialog(this, "Não é possível deletar o resultado do exame. Existe um paciente atrelado a este resultado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            resultadoExameDAO.deletarResultadoExame(id);
            JOptionPane.showMessageDialog(this, "Resultado de exame deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao deletar resultado de exame: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarResultados() {
        try {
            List<ResultadoExame> resultados = resultadoExameDAO.listarResultadosExames();
            tableModel.setRowCount(0); // Limpar tabela
            for (ResultadoExame resultado : resultados) {
                tableModel.addRow(new Object[]{
                        resultado.getId(),
                        resultado.getAgendamentoId(),
                        resultado.getResultados(),
                        resultado.getObservacoes(),
                        resultado.getData().toString()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar resultados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
