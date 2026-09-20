import javax.swing.*;
import java.awt.*;

public class Project {
    public static void main(String[] args) {

        // frame setup ----------------------------------------------------
        JFrame frame = new JFrame();
        frame.setTitle("Password Manager");
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("logo.png");
        frame.setIconImage(img.getImage());

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


        final String MASTER_KEY = "scs/11693/25" ;


        // status message (error text) ------------------------------------
        JLabel status = new JLabel("", JLabel.CENTER);
       
        status.setBounds(200, 285, 300, 30);
        frame.add(status);


         // login logic ----------------------------------------------------
    
         loginBtn.addActionListener(e -> {
            String entered = new String(keyField.getPassword());

            if (entered.equals(MASTER_KEY)) {
                 status.setForeground(Color.GREEN);
                status.setText("login successfull");
            } else {
                status.setText("Wrong Master Key");
                status.setForeground(Color.RED);
                keyField.setText("");
            }
        });
        frame.setVisible(true);
    
    }
}