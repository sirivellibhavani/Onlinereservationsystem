import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultScreen extends JFrame implements ActionListener {

    JLabel title;
    JLabel scoreLabel;

    JButton logout;

    public ResultScreen(int score,int total){

        setTitle("Result");

        setSize(400,250);

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        title=new JLabel("EXAM COMPLETED");

        title.setFont(new Font("Arial",Font.BOLD,18));

        title.setBounds(90,30,250,30);

        scoreLabel=new JLabel("Score : "+score+" / "+total);

        scoreLabel.setFont(new Font("Arial",Font.BOLD,16));

        scoreLabel.setBounds(120,90,200,30);

        logout=new JButton("Logout");

        logout.setBounds(130,150,120,35);

        logout.addActionListener(this);

        add(title);

        add(scoreLabel);

        add(logout);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){

        dispose();

        new Login();

    }

}