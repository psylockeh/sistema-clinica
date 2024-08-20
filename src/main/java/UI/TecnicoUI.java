package UI;

import DAO.AgendamentoDAO;
import DAO.TecnicoDAO;
import DB.Conexao;
import org.teste.Agendamento;
import org.teste.Tecnico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TecnicoUI extends JPanel{
    private JTextField idField;
    private JTextField nomeField;
    private JTextField especialidadeField;
    private JTextField contatoField;
    private TecnicoDAO tecnicoDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    public TecnicoUI() {
        try {
            Connection connection = Conexao.getConnection();
            tecnicoDAO = new TecnicoDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        idField = new JTextField();
        nomeField = new JTextField();
        especialidadeField = new JTextField();
        contatoField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar Técnico");
        JButton atualizarButton = new JButton("Atualizar Técnico");
        JButton buscarButton = new JButton("Buscar Técnico");
        JButton deletarButton = new JButton("Deletar Técnico");
        JButton listarButton = new JButton("Listar Técnicos");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarTecnico();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTecnico();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTecnico();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarTecnico();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTecnicos();
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("Especialidade:"));
        add(especialidadeField);
        add(new JLabel("Contato:"));
        add(contatoField);
        add(adicionarButton);
        add(atualizarButton);
        add(buscarButton);
        add(deletarButton);
        add(listarButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Especialidade", "Contato"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table));


    }

    private void adicionarTecnico() {
        try {
            // Validação dos campos
            if (nomeField.getText().isEmpty() || especialidadeField.getText().isEmpty() || contatoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criação do objeto Tecnico
            String nome = nomeField.getText();
            String especialidade = especialidadeField.getText();
            String contato = contatoField.getText();
            Tecnico tecnico = new Tecnico(0, nome, especialidade, contato);

            // Tentativa de adicionar o tecnico ao banco de dados
            tecnicoDAO.adicionarTecnico(tecnico);
            JOptionPane.showMessageDialog(this, "Técnico adicionado com sucesso.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar técnico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void atualizarTecnico() {
        int id = Integer.parseInt(idField.getText());
        String nome = nomeField.getText();
        String especialidade = especialidadeField.getText();
        String contato = contatoField.getText();
        Tecnico tecnico = new Tecnico(id, nome, especialidade, contato);
        try {
            tecnicoDAO.atualizarTecnico(tecnico);
            JOptionPane.showMessageDialog(this, "Técnico atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar técnico.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarTecnico() {
        int id = Integer.parseInt(idField.getText());
        try {
            Tecnico tecnico = tecnicoDAO.buscarTecnicoPorId(id);
            if (tecnico != null) {
                nomeField.setText(tecnico.getNome());
                especialidadeField.setText(tecnico.getEspecialidade());
                contatoField.setText(tecnico.getContato());
            } else {
                JOptionPane.showMessageDialog(this, "Técnico não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar técnico.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarTecnico() {
        int id = Integer.parseInt(idField.getText());
        try {
            // Verificar se existem agendamentos associados ao técnico
            List<Agendamento> agendamentos = AgendamentoDAO.listarAgendamentosPorTecnicoId(id);
            if (!agendamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é possível deletar o técnico. Existem agendamentos associados a este técnico.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tecnicoDAO.deletarTecnico(id);
            JOptionPane.showMessageDialog(this, "Técnico deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao deletar técnico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void listarTecnicos() {
        try {
            List<Tecnico> tecnicos = tecnicoDAO.listarTecnicos();
            tableModel.setRowCount(0); // Limpar tabela
            for (Tecnico tecnico : tecnicos) {
                tableModel.addRow(new Object[]{
                        tecnico.getId(),
                        tecnico.getNome(),
                        tecnico.getEspecialidade(),
                        tecnico.getContato()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar técnicos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


