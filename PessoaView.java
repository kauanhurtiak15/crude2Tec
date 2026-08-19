import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PessoaView extends JFrame {
    private PessoaController controller;

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public PessoaView() {
        controller = new PessoaController();
        configurarTela();
        atualizarTabela(); // Carrega os dados salvos do banco ao abrir a janela
    }

    private void configurarTela() {
        setTitle("Cadastro de Pessoas (Com Banco de Dados SQLite)");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Formulario Superior
        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelFormulario.add(txtIdade);

        JButton btnSalvar = new JButton("Salvar no Banco");
        painelFormulario.add(btnSalvar);

        JButton btnDeletar = new JButton("Deletar Selecionado");
        painelFormulario.add(btnDeletar);

        add(painelFormulario, BorderLayout.NORTH);

        // Tabela com a coluna ID
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Idade"}, 0);
        tabela = new JTable(modeloTabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Ação de Salvar
        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                if (nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome não pode ser vazio.");
                    return;
                }

                int idade = Integer.parseInt(txtIdade.getText());

                controller.salvar(nome, idade);
                atualizarTabela();

                txtNome.setText("");
                txtIdade.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, digite uma idade válida.");
            }
        });

        // Ação de Deletar
        btnDeletar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                // Obtém o ID que está na coluna 0 da linha selecionada
                int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);

                controller.deletar(id);
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para deletar.");
            }
        });
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpa as linhas visíveis

        // Busca do Banco de Dados via Controller
        for (Pessoa p : controller.listarTodas()) {
            modeloTabela.addRow(new Object[]{p.getId(), p.getNome(), p.getIdade()});
        }
    }
}

