import javax.swing.*;
import java.awt.*;

public class Project {

    public static void main(String[] args) {

        // frame setup ----------------------------------------------------
        JFrame frame = new JFrame("Password Manager");
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("logo.png").getImage());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // title label ----------------------------------------------------
        JLabel title = new JLabel("Enter the Master Key");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(200, 125, 300, 50);
        frame.add(title);

        // master key input -----------------------------------------------
        JPasswordField keyField = new JPasswordField();
        keyField.setBounds(250, 190, 200, 30);
        frame.add(keyField);

        // login button ---------------------------------------------------
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(300, 240, 100, 30);
        frame.add(loginBtn);

        // status message -------------------------------------------------
        JLabel status = new JLabel("", JLabel.CENTER);
        status.setForeground(Color.RED);
        status.setBounds(200, 285, 300, 30);
        frame.add(status);

        final String MASTER_KEY = "scs/11693/25";

        // login logic ----------------------------------------------------
        loginBtn.addActionListener(e -> {
            String entered = new String(keyField.getPassword());

            if (entered.equals(MASTER_KEY)) {
                frame.dispose();
                openVault();
            } else {
                status.setText("Wrong Master Key");
                keyField.setText("");
            }
        });

        frame.getRootPane().setDefaultButton(loginBtn);
        frame.setVisible(true);
    }

    // vault window (simple CRUD using a list) ----------------------------
    static void openVault() {

        JFrame vault = new JFrame("Password Vault");
        vault.setSize(700, 700);
        vault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vault.setLocationRelativeTo(null);
        vault.setLayout(new BorderLayout());

        // list of entries (READ)
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        vault.add(new JScrollPane(list), BorderLayout.CENTER);

        // input fields
        JTextField siteField = new JTextField(10);
        JTextField userField = new JTextField(10);
        JTextField passField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Website:"));
        inputPanel.add(siteField);
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(userField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passField);

        // buttons
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.add(inputPanel);
        bottom.add(buttonPanel);
        vault.add(bottom, BorderLayout.SOUTH);

        // CREATE
        addBtn.addActionListener(e -> {
            String site = siteField.getText().trim();
            String user = userField.getText().trim();
            String pass = passField.getText().trim();

            if (site.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(vault, "Fill all fields");
                return;
            }
            model.addElement(site + " | " + user + " | " + pass);
            siteField.setText("");
            userField.setText("");
            passField.setText("");
        });

        // selecting an entry fills the fields
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1) {
                String[] parts = list.getSelectedValue().split(" \\| ");
                siteField.setText(parts[0]);
                userField.setText(parts[1]);
                passField.setText(parts[2]);
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(vault, "Select an entry first");
                return;
            }
            String text = siteField.getText().trim() + " | "
                        + userField.getText().trim() + " | "
                        + passField.getText().trim();
            model.set(index, text);
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(vault, "Select an entry first");
                return;
            }
            model.remove(index);
            siteField.setText("");
            userField.setText("");
            passField.setText("");
        });

        vault.setVisible(true);
    }
}