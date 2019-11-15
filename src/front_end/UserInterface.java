package front_end;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadPoolExecutor;

public class UserInterface {
    private JFrame frame;
    private JPanel all;
    private JPanel usernameZone;
    private JPanel userScoreZone;
    private JPanel categoryZone;
    private JPanel questionZone;
    private JTextArea question;
    private JPanel answerZone;
    private JLabel user1name;
    private JLabel user2name;
    private JLabel user1score;
    private JLabel user2score;
    private JLabel scoreSeparator;

    private JButton category1;
    private JButton category2;
    private JButton category3;
    private JButton category4;
    private JButton answer1;
    private JButton answer2;
    private JButton answer3;
    private JButton answer4;

    public UserInterface(){
        frame = new JFrame("Game");

        usernameZone = new JPanel();
        userScoreZone = new JPanel();
        categoryZone = new JPanel();
        answerZone = new JPanel();
        questionZone = new JPanel();
        all = new JPanel();

        user1name = new JLabel("user1");
        user2name = new JLabel("user2");
        usernameZone.setLayout(new FlowLayout());
        usernameZone.add(user1name);
        usernameZone.add(user2name);

        user1score = new JLabel("0");
        scoreSeparator = new JLabel(":");
        user2score = new JLabel("0");
        usernameZone.setLayout(new FlowLayout());
        userScoreZone.add(user1score);
        userScoreZone.add(scoreSeparator);
        userScoreZone.add(user2score);

        category1 = new JButton("category1");
        category2 = new JButton("category2");
        category3 = new JButton("category3");
        category4 = new JButton("category4");
        categoryZone.setLayout(new GridLayout(1,4));
        categoryZone.add(category1);
        categoryZone.add(category2);
        categoryZone.add(category3);
        categoryZone.add(category4);

        question = new JTextArea(30,30);
        questionZone.add(question);

        answer1 = new JButton("answer1");
        answer2 = new JButton("answer2");
        answer3 = new JButton("answer3");
        answer4 = new JButton("answer4");
        answerZone.setLayout(new GridLayout(2,2));
        answerZone.add(answer1);
        answerZone.add(answer2);
        answerZone.add(answer3);
        answerZone.add(answer4);

        all.add(usernameZone);
        all.add(userScoreZone);
        all.add(categoryZone);
        all.add(questionZone);
        all.add(answerZone);

        frame.add(all);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
