package UI;

import DAO.AgendamentoDAO;
import DAO.PacienteDAO;
import DB.Conexao;
import org.teste.Agendamento;
import org.teste.Paciente;

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

public class PacienteUI extends JPanel{
    private JTextField idField;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField dataNascimentoField;
    private JTextField contatoField;
    private PacienteDAO pacienteDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    public PacienteUI() {
        try {
            Connection connection = Conexao.getConnection();
            pacienteDAO = new PacienteDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        idField = new JTextField();
        nomeField = new JTextField();
        cpfField = new JTextField();
        dataNascimentoField = new JTextField();
        contatoField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar Paciente");
        JButton atualizarButton = new JButton("Atualizar Paciente");
        JButton buscarButton = new JButton("Buscar Paciente");
        JButton deletarButton = new JButton("Deletar Paciente");
        JButton listarButton = new JButton("Listar Pacientes");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPaciente();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPaciente();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarPaciente();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPacientes();
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("CPF:"));
        add(cpfField);
        add(new JLabel("Data de Nascimento:"));
        add(dataNascimentoField);
        add(new JLabel("Contato:"));
        add(contatoField);
        add(adicionarButton);
        add(atualizarButton);
        add(buscarButton);
        add(deletarButton);
        add(listarButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Data de Nascimento", "Contato"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table));
    }

    private void adicionarPaciente() {
        try {
            // Validação dos campos
            if (nomeField.getText().isEmpty() || cpfField.getText().isEmpty() || dataNascimentoField.getText().isEmpty() || contatoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criação do objeto Paciente
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            java.util.Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimentoField.getText());
            String contato = contatoField.getText();
            Paciente paciente = new Paciente(0, nome, cpf, new java.sql.Date(dataNascimento.getTime()), contato);

            // Tentativa de adicionar o paciente ao banco de dados
            pacienteDAO.adicionarPaciente(paciente);
            JOptionPane.showMessageDialog(this, "Paciente adicionado com sucesso.");

        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use yyyy-MM-dd para data de nascimento.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar paciente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarPaciente() {
        int id = Integer.parseInt(idField.getText());
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        Date dataNascimento = Date.valueOf(dataNascimentoField.getText());
        String contato = contatoField.getText();
        Paciente paciente = new Paciente(id, nome, cpf, dataNascimento, contato);
        try {
            pacienteDAO.atualizarPaciente(paciente);
            JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar paciente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPaciente() {
        int id = Integer.parseInt(idField.getText());
        try {
            Paciente paciente = pacienteDAO.buscarPacientePorId(id);
            if (paciente != null) {
                nomeField.setText(paciente.getNome());
                cpfField.setText(paciente.getCpf());
                dataNascimentoField.setText(paciente.getDataNascimento());
                contatoField.setText(paciente.getContato());
            } else {
                JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar paciente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarPaciente() {
        int id = Integer.parseInt(idField.getText());
        try {
            // Verificar se existem agendamentos associados ao paciente
            List<Agendamento> agendamentos = AgendamentoDAO.listarAgendamentosPorPacienteId(id);
            if (!agendamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é possível deletar o paciente. Existem agendamentos associados a este paciente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pacienteDAO.deletarPaciente(id);
            JOptionPane.showMessageDialog(this, "Paciente deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao deletar paciente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarPacientes() {
        try {
            List<Paciente> pacientes = pacienteDAO.listarPacientes();
            tableModel.setRowCount(0); // Limpar tabela
            for (Paciente paciente : pacientes) {
                tableModel.addRow(new Object[]{
                        paciente.getId(),
                        paciente.getNome(),
                        paciente.getCpf(),
                        paciente.getDataNascimento().toString(),
                        paciente.getContato()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar pacientes.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
