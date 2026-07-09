import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileUpdate extends JFrame implements ActionListener {

    JLabel lblTitle;
    JLabel lblName;
    JLabel lblPassword;

    JTextField txtName;
    JPasswordField txtPassword;

    JButton btnSave;
    JButton btnStart;

    public ProfileUpdate() {

        setTitle("Profile Update");
        setSize(400,300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("Update Profile");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(120,20,200,30);

        lblName = new JLabel("Display Name");
        lblName.setBounds(40,80,120,25);

        txtName = new JTextField(User.displayName);
        txtName.setBounds(170,80,150,25);

        lblPassword = new JLabel("New Password");
        lblPassword.setBounds(40,120,120,25);

        txtPassword = new JPasswordField(User.password);
        txtPassword.setBounds(170,120,150,25);

        btnSave = new JButton("Save");
        btnSave.setBounds(60,190,100,30);

        btnStart = new JButton("Start Exam");
        btnStart.setBounds(190,190,120,30);

        btnSave.addActionListener(this);
        btnStart.addActionListener(this);

        add(lblTitle);
        add(lblName);
        add(txtName);
        add(lblPassword);
        add(txtPassword);
        add(btnSave);
        add(btnStart);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnSave){

            User.displayName = txtName.getText();
            User.password = String.valueOf(txtPassword.getPassword());

            JOptionPane.showMessageDialog(this,
                    "Profile Updated Successfully");

        }

        if(e.getSource() == btnStart){

            dispose();
            new ExamScreen();

        }

    }

}