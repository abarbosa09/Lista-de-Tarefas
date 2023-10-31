
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaDeTarefasGui {
    private ArrayList<String> tarefas;
    private DefaultListModel<String> listModel;
    private JList<String> listaTarefas;

    public ListaDeTarefasGui() {
        tarefas = new ArrayList<String>();
        listModel = new DefaultListModel<String>();

        JFrame frame = new JFrame("Lista de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);


        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        listaTarefas = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaTarefas);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Usando FlowLayout para centralizar botões
        JButton adicionarButton = new JButton("Adicionar Tarefa");
        JButton removerButton = new JButton("Remover Tarefa");
        JButton listarTarefasButton = new JButton("Listar Tarefas");
        buttonPanel.add(adicionarButton);
        buttonPanel.add(removerButton);
        buttonPanel.add(listarTarefasButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abre a caixa de dialogo com Usuario e permite inserção de texto
                String tarefa = JOptionPane.showInputDialog("Digite a tarefa a ser adicionada:");
                if (tarefa != null && !tarefa.trim().isEmpty()) {
                    adicionarTarefa(tarefa);
                }
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tarefaParaRemover = JOptionPane.showInputDialog("Digite a tarefa a ser removida:");
                if (tarefaParaRemover != null && !tarefaParaRemover.trim().isEmpty()) {
                    if (removerTarefa(tarefaParaRemover)) {
                        JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Tarefa não encontrada na lista.");
                    }
                }
            }
        });

        listarTarefasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Mostra a lista completa de tarefas
                listarTarefas();
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }

    private void adicionarTarefa(String tarefa) {
        tarefas.add(tarefa);
        listModel.addElement(tarefa);
    }
    private boolean removerTarefa(String tarefa) {
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).equals(tarefa)) {
                tarefas.remove(i);
                listModel.remove(i);
                return true;
            }
        }
        return false;
    }

    //Lista a tarefa em uma janela unica
    private void listarTarefas() {
        JOptionPane.showMessageDialog(null, String.join("\n", tarefas), "Lista de Tarefas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListaDeTarefasGui();
            }
        });
    }
}
