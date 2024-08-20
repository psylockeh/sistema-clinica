package UI;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    public MainUI() {
        setTitle("Sistema de Exames Clínicos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();


        tabbedPane.addTab("Exame", new ExameUI());
        tabbedPane.addTab("Paciente", new PacienteUI());
        tabbedPane.addTab("Agendamento", new AgendamentoUI());
        tabbedPane.addTab("Medico", new MedicoUI());
        tabbedPane.addTab("Resultado Exame", new ResultadoExameUI());
        tabbedPane.addTab("Tecnico", new TecnicoUI());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI mainUI = new MainUI();
            mainUI.setVisible(true);
        });
    }
}
