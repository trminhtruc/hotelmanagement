package gui;

import java.awt.BorderLayout;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class few {

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JTextField textField = new JTextField();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		BoundedRangeModel brm = textField.getHorizontalVisibility();
		panel.add(textField);

		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
				JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
				frame.getContentPane().add(scrollBar, BorderLayout.EAST);
				scrollBar.setModel(brm);
				
				JTextArea textArea = new JTextArea();
				frame.getContentPane().add(textArea, BorderLayout.CENTER);
		frame.setSize(450, 306);
		frame.setVisible(true);
	}
}