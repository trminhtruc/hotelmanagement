package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main {
    private JFrame window;
    private JPanel mainPanel;
    private JPanel currentButtonsPanel;

    private ArrayList<JButton> buttons;

    private final int WINDOW_X, WINDOW_Y;
    private final int N_BUTTONS = 10;

    private final Color[] colors = new Color[]{
            Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.ORANGE, Color.MAGENTA};
    private final Random random = new Random();
    private JButton selectedButton = null;

    public Main(int w, int h, String title){
        WINDOW_X = w;
        WINDOW_Y = h;


        window = new JFrame(title);
        window.setLayout(new BorderLayout());
        window.setSize(new Dimension(w, h));
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        //mainPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        buttons = new ArrayList<>();

        for(int i = 0; i < N_BUTTONS*3.5; i++)
            addNewButton();

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setPreferredSize(new Dimension(w,h));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        window.add(scrollPane, BorderLayout.CENTER);
        window.add(generateButtonsPanel(), BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
    }

    private JPanel generateButtonsPanel(){
        JPanel ret = new JPanel();

        JButton btn = new JButton("Check");
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                copyButtons();
            }
        });
        ret.add(btn);

        return ret;
    }

    private void copyButtons(){
        ArrayList<JButton> newList = new ArrayList<>();
        for(JButton btn : buttons){
            JButton newBtn = new JButton();
            newBtn.setBackground(btn.getBackground());
            newBtn.addActionListener(btn.getActionListeners()[0]);
            newList.add(newBtn);
        }
        for(JButton btn : newList)
            addNewButton(btn);
    }

    private void addNewButton(){
        JButton newButton = new JButton();

        Color col = colors[random.nextInt(colors.length)];
        newButton.setBackground(col);

        newButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JButton btn = (JButton)e.getSource();
                if(selectedButton == null){
                    selectedButton = btn;
                    btn.setText(".");
                    return;
                }
                if(selectedButton == btn){
                    selectedButton = null;
                    btn.setText("");
                    return;
                }
                if(selectedButton.getBackground() == btn.getBackground()){
                    System.out.println("Matching!");
                    btn.setBackground(Color.BLACK);
                    selectedButton.setBackground(Color.BLACK);
                    btn.setEnabled(false);
                    selectedButton.setEnabled(false);

                    buttons.remove(btn);
                    buttons.remove(selectedButton);
                }else
                    System.out.println("No match!");

                btn.setText("");
                selectedButton.setText("");
                selectedButton = null;
            }
        });

        addNewButton(newButton);
    }

    private void addNewButton(JButton btn){
        if(buttons.size() % 10 == 0){
            currentButtonsPanel = new JPanel();
            currentButtonsPanel.setLayout(new GridLayout(0,10));
            currentButtonsPanel.setMaximumSize(new Dimension(WINDOW_X*WINDOW_X,WINDOW_X/N_BUTTONS+10));
            currentButtonsPanel.add(btn);
            mainPanel.add(currentButtonsPanel);
        }else
            currentButtonsPanel.add(btn);
        buttons.add(btn);
        window.setVisible(true);
    }

    public static void main(String[] args){
        new Main(400, 300, "Game");
    }
}