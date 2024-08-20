package UI;
import DAO.AgendamentoDAO;
import DAO.MedicoDAO;
import DB.Conexao;
import org.teste.Agendamento;
import org.teste.Medico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MedicoUI extends JPanel {
    private JTextField idField;
    private JTextField nomeField;
    private JTextField especialidadeField;
    private JTextField crmField;
    private MedicoDAO medicoDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    public MedicoUI() {
        try {
            Connection connection = Conexao.getConnection();
            medicoDAO = new MedicoDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        idField = new JTextField();
        nomeField = new JTextField();
        especialidadeField = new JTextField();
        crmField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar Medico");
        JButton atualizarButton = new JButton("Atualizar Medico");
        JButton buscarButton = new JButton("Buscar Medico");
        JButton deletarButton = new JButton("Deletar Medico");
        JButton listarButton = new JButton("Listar Medicos");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarMedico();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarMedico();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarMedico();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarMedico();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarMedicos();
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("Especialidade:"));
        add(especialidadeField);
        add(new JLabel("Crm:"));
        add(crmField);
        add(adicionarButton);
        add(atualizarButton);
        add(buscarButton);
        add(deletarButton);
        add(listarButton);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Especialidade", "CRM"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table));
    }

    private void adicionarMedico() {
        try {
            // Validação dos campos
            if (nomeField.getText().isEmpty() || especialidadeField.getText().isEmpty() || crmField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criação do objeto Medico
            String nome = nomeField.getText();
            String especialidade = especialidadeField.getText();
            String crm = crmField.getText();
            Medico medico = new Medico(0, nome, especialidade, crm);

            // Tentativa de adicionar o medico ao banco de dados
            medicoDAO.adicionarMedico(medico);
            JOptionPane.showMessageDialog(this, "Médico adicionado com sucesso.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar médico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarMedico() {
        int id = Integer.parseInt(idField.getText());
        String nome = nomeField.getText();
        String especialidade = especialidadeField.getText();
        String crm = crmField.getText();
        Medico medico = new Medico(id, nome, especialidade, crm);
        try {
            medicoDAO.atualizarMedico(medico);
            JOptionPane.showMessageDialog(this, "Medico atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar medico.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarMedico() {
        int id = Integer.parseInt(idField.getText());
        try {
            Medico medico = medicoDAO.buscarMedicoPorId(id);
            if (medico != null) {
                nomeField.setText(medico.getNome());
                especialidadeField.setText(medico.getEspecialidade());
                crmField.setText(medico.getCrm());
            } else {
                JOptionPane.showMessageDialog(this, "Medico não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar medico.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarMedico() {
        int id = Integer.parseInt(idField.getText());
        try {
            // Verificar se existem agendamentos associados ao médico
            List<Agendamento> agendamentos = AgendamentoDAO.listarAgendamentosPorMedicoId(id);
            if (!agendamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é possível deletar o médico. Existem agendamentos associados a este médico.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            medicoDAO.deletarMedico(id);
            JOptionPane.showMessageDialog(this, "Médico deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao deletar médico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarMedicos() {
        try {
            List<Medico> medicos = medicoDAO.listarMedicos();
            tableModel.setRowCount(0); // Limpar tabela
            for (Medico medico : medicos) {
                tableModel.addRow(new Object[]{
                        medico.getId(),
                        medico.getNome(),
                        medico.getEspecialidade(),
                        medico.getCrm()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar médicos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
