package front_end;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.concurrent.ThreadPoolExecutor;

public class UserInterface {
    private JFrame frame;
    private JPanel all;
    private JLabel ip;
    private JTextField ip_txt;
    private JLabel name;
    private JTextField name_txt;
    private JLabel port;
    private JTextField port_txt;
    private JButton btn_start;
    private JButton btn_stop;

    private JPanel userInfo;
    private JPanel info_board;
    private JPanel usernameZone;
    private JPanel userScoreZone;
    private JPanel categoryZone;
    private JPanel questionZone;
    private JPanel controlZone;

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
        all = new JPanel();

        info_board = new JPanel();
        info_board.setBorder(new TitledBorder("Client info:"));
        info_board.setLayout(new GridLayout(5,0));

        usernameZone = new JPanel();
        userScoreZone = new JPanel();
        categoryZone = new JPanel();
        answerZone = new JPanel();
        questionZone = new JPanel();

        userInfo = new JPanel();
        userInfo.setLayout(new GridLayout(1,6));
        name = new JLabel("name:");
        name_txt = new JTextField(6);
        port = new JLabel("port:");
        port_txt = new JTextField("8888");
        ip = new JLabel("ip:");
        ip_txt = new JTextField("localhost");
        userInfo.add(name);
        userInfo.add(name_txt);
        userInfo.add(port);
        userInfo.add(port_txt);
        userInfo.add(ip);
        userInfo.add(ip_txt);

        controlZone = new JPanel();
        controlZone.setLayout(new GridLayout(1,2));
        btn_start = new JButton("Start");
        btn_stop = new JButton("Stop");
        controlZone.add(btn_start);
        controlZone.add(btn_stop);

        user1name = new JLabel("user1");
        user2name = new JLabel("user2");
        usernameZone.setLayout(new FlowLayout());
        usernameZone.add(user1name);
        usernameZone.add(new JLabel(":"));
        usernameZone.add(user2name);

        user1score = new JLabel("0");
        scoreSeparator = new JLabel(":");
        user2score = new JLabel("0");
        userScoreZone.setLayout(new FlowLayout());
        userScoreZone.add(user1score);
        userScoreZone.add(scoreSeparator);
        userScoreZone.add(user2score);

        info_board.add(userInfo);
        info_board.add(controlZone);
        info_board.add(categoryZone);
        info_board.add(usernameZone);
        info_board.add(userScoreZone);

        category1 = new JButton("category1");
        category2 = new JButton("category2");
        category3 = new JButton("category3");
        category4 = new JButton("category4");
        categoryZone.setLayout(new GridLayout(1,4));
        categoryZone.add(category1);
        categoryZone.add(category2);
        categoryZone.add(category3);
        categoryZone.add(category4);

        question = new JTextArea(20,40);
        questionZone.add(question);

        answer1 = new JButton("answer1");
        answer2 = new JButton("answer2");
        answer3 = new JButton("answer3");
        answer4 = new JButton("answer4");
        answerZone.setLayout(new GridLayout(2,2));
        answerZone.setSize(100,100);
        answerZone.add(answer1);
        answerZone.add(answer2);
        answerZone.add(answer3);
        answerZone.add(answer4);

        all.add(info_board,BorderLayout.NORTH);
        all.add(questionZone);
        all.add(answerZone,BorderLayout.SOUTH);

        frame.add(all);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
