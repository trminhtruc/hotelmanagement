package gui;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Test {

    public static void main(String... args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        for (int i = 0; i < 10; i++) {
            panel.add(new JButton("Hello-" + i));
           
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 300, 200);
        JPanel Pane1 = new JPanel(null);
        
        Pane1.setPreferredSize(new Dimension(500, 400));
        Pane1.add(scrollPane);
        
        frame.setContentPane(Pane1);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}