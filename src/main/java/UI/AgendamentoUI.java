package UI;

import DAO.AgendamentoDAO;
import DB.Conexao;
import org.teste.Agendamento;

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

public class AgendamentoUI extends JPanel {
    private JTextField idField;
    private JTextField pacienteIdField;
    private JTextField exameIdField;
    private JTextField medicoIdField;
    private JTextField tecnicoIdField;
    private JTextField dataField;
    private JTextField horarioField;
    private AgendamentoDAO agendamentoDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    public AgendamentoUI() {
        try {
            Connection connection = Conexao.getConnection();
            agendamentoDAO = new AgendamentoDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        idField = new JTextField();
        pacienteIdField = new JTextField();
        exameIdField = new JTextField();
        medicoIdField = new JTextField();
        tecnicoIdField = new JTextField();
        dataField = new JTextField();
        horarioField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar Agendamento");
        JButton atualizarButton = new JButton("Atualizar Agendamento");
        JButton buscarButton = new JButton("Buscar Agendamento");
        JButton deletarButton = new JButton("Deletar Agendamento");
        JButton listarButton = new JButton("Listar Agendamentos");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAgendamento();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    atualizarAgendamento();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAgendamento();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarAgendamento();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAgendamentos();
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("ID do Paciente:"));
        add(pacienteIdField);
        add(new JLabel("ID do Exame:"));
        add(exameIdField);
        add(new JLabel("ID do Médico:"));
        add(medicoIdField);
        add(new JLabel("ID do Técnico:"));
        add(tecnicoIdField);
        add(new JLabel("Data (yyyy-MM-dd):"));
        add(dataField);
        add(new JLabel("Horário (HH:mm:ss):"));
        add(horarioField);
        add(adicionarButton);
        add(atualizarButton);
        add(buscarButton);
        add(deletarButton);
        add(listarButton);

        // Inicializar a tabela
        tableModel = new DefaultTableModel(new Object[]{"ID", "Paciente ID", "Exame ID", "Médico ID", "Técnico ID", "Data", "Horário"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table));
    }

    private void adicionarAgendamento() {
        try {
            // Validação dos campos
            if (pacienteIdField.getText().isEmpty() || exameIdField.getText().isEmpty() || medicoIdField.getText().isEmpty() || tecnicoIdField.getText().isEmpty() || dataField.getText().isEmpty() || horarioField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Conversão e criação do objeto Agendamento
            int pacienteId = Integer.parseInt(pacienteIdField.getText());
            int exameId = Integer.parseInt(exameIdField.getText());
            int medicoId = Integer.parseInt(medicoIdField.getText());
            int tecnicoId = Integer.parseInt(tecnicoIdField.getText());
            Date data = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dataField.getText()).getTime());
            Date horario = new Date(new SimpleDateFormat("HH:mm:ss").parse(horarioField.getText()).getTime());
            Agendamento agendamento = new Agendamento(0, pacienteId, exameId, data, horario, medicoId, tecnicoId);

            // Tentativa de adicionar o agendamento ao banco de dados
            agendamentoDAO.adicionarAgendamento(agendamento);
            JOptionPane.showMessageDialog(this, "Agendamento adicionado com sucesso.");

        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Formato de data ou horário inválido. Use yyyy-MM-dd para data e HH:mm:ss para horário.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar agendamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "IDs de paciente, exame, médico e técnico devem ser números inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void atualizarAgendamento() throws ParseException {
        int id = Integer.parseInt(idField.getText());
        int pacienteId = Integer.parseInt(pacienteIdField.getText());
        int exameId = Integer.parseInt(exameIdField.getText());
        int medicoId = Integer.parseInt(medicoIdField.getText());
        int tecnicoId = Integer.parseInt(tecnicoIdField.getText());
        Date data = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dataField.getText()).getTime());
        Date horario = new Date(new SimpleDateFormat("HH:mm:ss").parse(horarioField.getText()).getTime());
        Agendamento agendamento = new Agendamento(id, pacienteId, exameId, data, horario, medicoId, tecnicoId);
        try {
            agendamentoDAO.atualizarAgendamento(agendamento);
            JOptionPane.showMessageDialog(this, "Agendamento atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar agendamento.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarAgendamento() {
        int id = Integer.parseInt(idField.getText());
        try {
            Agendamento agendamento = agendamentoDAO.buscarAgendamentoPorId(id);
            if (agendamento != null) {
                pacienteIdField.setText(String.valueOf(agendamento.getPacienteId()));
                exameIdField.setText(String.valueOf(agendamento.getExameId()));
                medicoIdField.setText(String.valueOf(agendamento.getMedicoId()));
                tecnicoIdField.setText(String.valueOf(agendamento.getTecnicoId()));
                dataField.setText(new SimpleDateFormat("yyyy-MM-dd").format(agendamento.getData()));
                horarioField.setText(new SimpleDateFormat("HH:mm:ss").format(agendamento.getHorario()));
            } else {
                JOptionPane.showMessageDialog(this, "Agendamento não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar agendamento.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarAgendamento() {
        int id = Integer.parseInt(idField.getText());
        try {
            agendamentoDAO.deletarAgendamento(id);
            JOptionPane.showMessageDialog(this, "Agendamento deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao deletar agendamento.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarAgendamentos() {
        try {
            List<Agendamento> agendamentos = agendamentoDAO.listarAgendamentos();
            tableModel.setRowCount(0); // Limpar tabela
            for (Agendamento agendamento : agendamentos) {
                tableModel.addRow(new Object[]{
                        agendamento.getId(),
                        agendamento.getPacienteId(),
                        agendamento.getExameId(),
                        agendamento.getMedicoId(),
                        agendamento.getTecnicoId(),
                        new SimpleDateFormat("yyyy-MM-dd").format(agendamento.getData()),
                        new SimpleDateFormat("HH:mm:ss").format(agendamento.getHorario())
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar agendamentos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
