package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;

public class DialogDoiMatKhau extends JDialog {

	private final JPanel panelDoiMatKhau = new JPanel();
	private JPasswordField txtPassCu;
	private JPasswordField txtPassMoi;
	private JPasswordField txtPassXacNhan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDoiMatKhau dialog = new DialogDoiMatKhau();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDoiMatKhau() {
		setBounds(100, 100, 415, 275);
		getContentPane().setLayout(new BorderLayout());
		panelDoiMatKhau.setBackground(new Color(51, 204, 204));
		panelDoiMatKhau.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelDoiMatKhau, BorderLayout.CENTER);
		panelDoiMatKhau.setLayout(null);
		{
			JButton btnXNDoiMatKhau = new JButton("Đổi Mật Khẩu");
			btnXNDoiMatKhau.setForeground(Color.WHITE);
			btnXNDoiMatKhau.setBackground(new Color(204, 0, 51));
			btnXNDoiMatKhau.setBounds(258, 192, 119, 32);
			panelDoiMatKhau.add(btnXNDoiMatKhau);
		}
		
		JLabel lblMtKhuC = new JLabel("Mật khẩu cũ");
		lblMtKhuC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMtKhuC.setBounds(12, 41, 86, 16);
		panelDoiMatKhau.add(lblMtKhuC);
		
		txtPassCu = new JPasswordField();
		txtPassCu.setBounds(139, 34, 246, 32);
		panelDoiMatKhau.add(txtPassCu);
		
		JLabel lblMtKhuMi = new JLabel("Mật khẩu mới");
		lblMtKhuMi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMtKhuMi.setBounds(12, 89, 86, 16);
		panelDoiMatKhau.add(lblMtKhuMi);
		
		JLabel lblXcNhnMt = new JLabel("Xác nhận mật khẩu");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXcNhnMt.setBounds(12, 140, 119, 16);
		panelDoiMatKhau.add(lblXcNhnMt);
		
		txtPassMoi = new JPasswordField();
		txtPassMoi.setBounds(139, 82, 246, 32);
		panelDoiMatKhau.add(txtPassMoi);
		
		txtPassXacNhan = new JPasswordField();
		txtPassXacNhan.setBounds(139, 133, 246, 32);
		panelDoiMatKhau.add(txtPassXacNhan);
	}
}
