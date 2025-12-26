import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JLabel labelUsername = new JLabel("Username:");
    private JTextField textUsername = new JTextField(20);
    private JLabel labelPassword = new JLabel("Password:");
    private JPasswordField textPassword = new JPasswordField(20);
    private JButton buttonLogin = new JButton("Login");

    public LoginForm() {
        super("Login Form");

        setSize(350, 200);

        setLayout(null);

        add(labelUsername);
        add(textUsername);
        add(labelPassword);
        add(textPassword);
        add(buttonLogin);

        labelUsername.setBounds(50, 30, 80, 25);
        textUsername.setBounds(140, 30, 150, 25);
        labelPassword.setBounds(50, 70, 80, 25);
        textPassword.setBounds(140, 70, 150, 25);
        buttonLogin.setBounds(140, 110, 80, 25);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textUsername.getText();
                String password = new String(textPassword.getPassword());

                if (username.equals("ahsinn") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil! Selamat datang, " + username + ".");
                    // dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password Salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}