import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExamScreen extends JFrame implements ActionListener {

    JLabel lblName, lblQuestion, lblTimer;

    JRadioButton r1, r2, r3, r4;

    ButtonGroup bg;

    JButton btnNext, btnPrevious, btnSubmit;

    int current = 0;
    int score = 0;
    int timeLeft = 1800; // 30 minutes

    int[] answers = new int[QuestionBank.questions.length];

    Timer timer;

    public ExamScreen() {

        setTitle("Online Examination System");
        setSize(700,450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        lblName = new JLabel("Welcome : " + User.displayName);
        lblName.setBounds(20,20,300,30);

        lblTimer = new JLabel("Time : 30:00");
        lblTimer.setBounds(550,20,120,30);
        lblTimer.setFont(new Font("Arial",Font.BOLD,16));

        lblQuestion = new JLabel();
        lblQuestion.setBounds(20,70,650,30);

        r1 = new JRadioButton();
        r1.setBounds(40,120,500,30);

        r2 = new JRadioButton();
        r2.setBounds(40,160,500,30);

        r3 = new JRadioButton();
        r3.setBounds(40,200,500,30);

        r4 = new JRadioButton();
        r4.setBounds(40,240,500,30);

        bg = new ButtonGroup();

        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);

        btnPrevious = new JButton("Previous");
        btnPrevious.setBounds(80,330,120,35);

        btnNext = new JButton("Next");
        btnNext.setBounds(250,330,120,35);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(430,330,120,35);

        btnPrevious.addActionListener(this);
        btnNext.addActionListener(this);
        btnSubmit.addActionListener(this);

        add(lblName);
        add(lblTimer);
        add(lblQuestion);

        add(r1);
        add(r2);
        add(r3);
        add(r4);

        add(btnPrevious);
        add(btnNext);
        add(btnSubmit);

        for(int i=0;i<answers.length;i++)
            answers[i]=-1;

        loadQuestion();
        timer = new Timer(1000, new ActionListener() {

    public void actionPerformed(ActionEvent e) {

        timeLeft--;

        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;

        lblTimer.setText(
                String.format("Time : %02d:%02d",
                        minutes,
                        seconds));

        if (timeLeft <= 0) {

            timer.stop();

            JOptionPane.showMessageDialog(
                    null,
                    "Time Over! Exam Submitted.");

            calculateResult();

        }

    }

});

timer.start();
addWindowListener(new WindowAdapter() {

    public void windowClosing(WindowEvent e) {

        int option = JOptionPane.showConfirmDialog(

                null,

                "Are you sure you want to quit the exam?",

                "Exit",

                JOptionPane.YES_NO_OPTION);

        if(option==JOptionPane.YES_OPTION){

            System.exit(0);

        }

    }

});

        setVisible(true);
    }
void loadQuestion() {

    bg.clearSelection();

    Question q = QuestionBank.questions[current];

    lblQuestion.setText((current + 1) + ". " + q.question);

    r1.setText(q.options[0]);
    r2.setText(q.options[1]);
    r3.setText(q.options[2]);
    r4.setText(q.options[3]);

    if (answers[current] == 0)
        r1.setSelected(true);

    if (answers[current] == 1)
        r2.setSelected(true);

    if (answers[current] == 2)
        r3.setSelected(true);

    if (answers[current] == 3)
        r4.setSelected(true);

}
void saveAnswer() {

    if (r1.isSelected())
        answers[current] = 0;

    else if (r2.isSelected())
        answers[current] = 1;

    else if (r3.isSelected())
        answers[current] = 2;

    else if (r4.isSelected())
        answers[current] = 3;

}
public void actionPerformed(ActionEvent e) {

    saveAnswer();

    if (e.getSource() == btnNext) {

        if (current < QuestionBank.questions.length - 1) {
            current++;
            loadQuestion();
        }

    }

    if (e.getSource() == btnPrevious) {

        if (current > 0) {
            current--;
            loadQuestion();
        }

    }

    if (e.getSource() == btnSubmit) {

        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to submit?",
                "Submit Exam",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {

            timer.stop();
            calculateResult();

        }

    }



}
void calculateResult() {

    score = 0;

    for (int i = 0; i < QuestionBank.questions.length; i++) {

        if (answers[i] == QuestionBank.questions[i].answer) {

            score++;

        }

    }

    dispose();

    new ResultScreen(score,
            QuestionBank.questions.length);

}