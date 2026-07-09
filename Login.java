import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JLabel title, userLabel, passLabel;
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin, btnExit;

    public Login() {

        setTitle("Online Examination System - Login");
        setSize(400,300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("ONLINE EXAMINATION SYSTEM");
        title.setBounds(70,20,250,30);
        title.setFont(new Font("Arial",Font.BOLD,16));

        userLabel = new JLabel("Username");
        userLabel.setBounds(40,80,100,25);

        txtUser = new JTextField();
        txtUser.setBounds(140,80,180,25);

        passLabel = new JLabel("Password");
        passLabel.setBounds(40,120,100,25);

        txtPass = new JPasswordField();
        txtPass.setBounds(140,120,180,25);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(70,180,100,30);

        btnExit = new JButton("Exit");
        btnExit.setBounds(200,180,100,30);

        btnLogin.addActionListener(this);
        btnExit.addActionListener(this);

        add(title);
        add(userLabel);
        add(txtUser);
        add(passLabel);
        add(txtPass);
        add(btnLogin);
        add(btnExit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnLogin){

            String user = txtUser.getText();
            String pass = String.valueOf(txtPass.getPassword());

            if(user.equals(User.username) &&
               pass.equals(User.password)){

                JOptionPane.showMessageDialog(this,
                        "Login Successful");

                dispose();

                new ProfileUpdate();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password");

            }

        }

        if(e.getSource()==btnExit){

            System.exit(0);

        }

    }

}