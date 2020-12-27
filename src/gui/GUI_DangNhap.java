package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import dao.DAO_NHANVIEN;
import dao.DAO_TAIKHOAN;
import entity.NHANVIEN;
import entity.TAIKHOAN;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class GUI_DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenDangNhap;
	private JPasswordField txtPassword;
	private JButton btnDangNhap;
	private DAO_TAIKHOAN dao_taiKhoan;
	private DAO_NHANVIEN dao_nhanVien = new DAO_NHANVIEN();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_DangNhap frame = new GUI_DangNhap();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public GUI_DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 344);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setBounds(199, 48, 269, 30);
		contentPane.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);
		
		 btnDangNhap = new JButton("ĐĂNG NHẬP");
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setBackground(new Color(185, 25, 66));
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDangNhap.setBounds(351, 153, 117, 30);
		contentPane.add(btnDangNhap);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 51, 102));
		lblNewLabel.setBounds(0, 0, 520, 297);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(GUI_DangNhap.class.getResource("/img/dangnhap2.jpg")).getImage().getScaledInstance(
				lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH));
		
		JLabel lblTnngNhp = new JLabel("Tên đăng nhập");
		lblTnngNhp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnngNhp.setForeground(Color.BLACK);
		lblTnngNhp.setBounds(59, 54, 117, 16);
		contentPane.add(lblTnngNhp);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setForeground(Color.BLACK);
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMtKhu.setBounds(59, 106, 117, 16);
		contentPane.add(lblMtKhu);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(199, 100, 269, 30);
		contentPane.add(txtPassword);
		lblNewLabel.setIcon(imageIcon);
		//	lblNewLabel.setIcon(new ImageIcon(GUI_DangNhap.class.getResource("/img/dangnhap.jpg")));
			
//		try {
//			byte[] img = dao_loaiPhong.getalltbLoaiPhong().get(row).getHinhAnh();
//			if (img != null) {
//				ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(
//						lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
//				lblHinhAnh.setIcon(imageIcon);
//			} else {
//				lblHinhAnh.setIcon(null);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			JOptionPane.showMessageDialog(null, "Hình Ảnh Chưa Được Thêm Vào");
//		}
			
			
		contentPane.add(lblNewLabel);
		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dao_taiKhoan = new DAO_TAIKHOAN();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTenDangNhap.setText("NVQL002");
		txtPassword.setText("NVQL002");
		btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(txtTenDangNhap.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên đăng nhập không được để trống");
					txtTenDangNhap.requestFocus();
					return;
				}
				if(txtPassword.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống");
					txtPassword.requestFocus();
					return;
				}
				int tam = 0;
				for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
					if(taiKhoan.getTentk().equals(txtTenDangNhap.getText())&&taiKhoan.getMk().equals(txtPassword.getText())) {
						tam = 1;
						for (NHANVIEN nhanVien : dao_nhanVien.getalltbNhanVien()) {
							if(taiKhoan.getNhanVien().getMaNV().equals(nhanVien.getMaNV())) {
								if(nhanVien.getChucVu().equals("QUẢN LÝ")) {
									setVisible(false);
									GUI_ChinhQuanLy guiQuanLy;
									try {
										guiQuanLy = new GUI_ChinhQuanLy();
										guiQuanLy.setThongTinNhanVien(nhanVien.getMaNV());
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								else if(nhanVien.getChucVu().equals("TIẾP TÂN")) {
									setVisible(false);
									GUI_ChinhNhanVien guiNhanVien;
									try {
										guiNhanVien = new GUI_ChinhNhanVien();
										guiNhanVien.setThongTinNhanVien(nhanVien.getMaNV());
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							}
						}
					}
				}
				if(tam ==0) {
					JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu đã sai. Vui lòng nhập lại ");
					txtTenDangNhap.requestFocus();
					return;
				}
			}
		});		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
//				int result = 0;
//				result = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát thật chứ", "THOÁT",
//						JOptionPane.YES_NO_OPTION);
//				if (result == JOptionPane.YES_OPTION)
//			         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			        else if (result == JOptionPane.NO_OPTION)
//			         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
