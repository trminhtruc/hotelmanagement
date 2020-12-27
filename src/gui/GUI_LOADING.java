package gui;

/*  w  w  w. j  ava2s .c om*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.scene.control.ProgressBar;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

class BarThread extends Thread {
	private static int DELAY = 30;

	JProgressBar progressBar;

	public BarThread(JProgressBar bar) {
		progressBar = bar;
		progressBar.setMaximum(100);
	}

	public void run() {
		int minimum = 0;// progressBar.getMinimum();
		int maximum = 100;// progressBar.getMaximum();
		for (int i = minimum; i < maximum; i++) {
			try {
				int value = progressBar.getValue();
				progressBar.setValue(value + 1);

				Thread.sleep(DELAY);

				if (progressBar.getValue() == 95) {	
					GUI_DangNhap dangnhap = new GUI_DangNhap();
					dangnhap.setVisible(true);
					dangnhap.setLocation(100, 200);
					dangnhap.setLocationRelativeTo(null);
				}

			} catch (InterruptedException ignoredException) {
			}
		}
	}

}

public class GUI_LOADING {
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		//Run
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
			//		new GUI_DANGNHAP().setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		
		
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(0, 204, 204));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JProgressBar aJProgressBar = new JProgressBar(0, 50);
		aJProgressBar.setBounds(52, 288, 415, 19);
		aJProgressBar.setStringPainted(true);

//    ActionListener actionListener = new ActionListener() {
//      public void actionPerformed(ActionEvent e) {
//       
//      }
//    };
		// aJButton.addActionListener(actionListener);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(aJProgressBar);
		frame.setSize(561, 396);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Thread stepper = new BarThread(aJProgressBar);

		JLabel lblNewLabel = new JLabel("NHÓM 2 !");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 51, 51));
		lblNewLabel.setBounds(205, 124, 160, 60);
		
		frame.getContentPane().add(lblNewLabel);
		// aJProgressBar.setMaximum(100);
		stepper.start();
		try {
			Thread.sleep(4000);
			frame.setVisible(false);
		//	frame.setLocation(100, 200);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
