package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.border.EmptyBorder;

import dao.DAO_NHANVIEN;
import dao.DAO_TAIKHOAN;
import entity.NHANVIEN;
import entity.TAIKHOAN;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUI_ChinhQuanLy extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private String maNhanVien;
	private JLabel lblTenNhanVien = new JLabel("");
	private DAO_NHANVIEN dao_NHANVIEN = new DAO_NHANVIEN();
	private DAO_TAIKHOAN dao_taiKhoan;
	private JButton btnDangXuat;
	private final JPanel panelDoiMatKhau = new JPanel();
	private JPasswordField txtPassCu;
	private JPasswordField txtPassMoi;
	private JPasswordField txtPassXacNhan;
	private JButton btnDoiMatKhau;
	private JDialog dialog;
	private JButton btnXNDoiMatKhau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ChinhQuanLy frame = new GUI_ChinhQuanLy();
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private GUI_ThongTin guisophong;

	public void setThongTinNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
		for (NHANVIEN nhanVien : dao_NHANVIEN.getalltbNhanVien()) {
			if (nhanVien.getMaNV().equals(maNhanVien)) {
				lblTenNhanVien.setText(nhanVien.getTenNV());
				
			}
		}

	}

	public GUI_ChinhQuanLy() throws ClassNotFoundException {
		
		setVisible(true);
		
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Hotel\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1380, 753);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);

		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBounds(167, 71, 1195, 635);
		
		JLabel lblHinhNen = new JLabel("");
		lblHinhNen.setBounds(0, 0, 1195, 635);
		panel_1.add(lblHinhNen);

		JButton btnSoDoPhong = new JButton("Sơ Đồ Phòng");
		btnSoDoPhong.setForeground(new Color(255, 255, 255));
		btnSoDoPhong.setBackground(new Color(102, 51, 153));
		btnSoDoPhong.setBounds(8, 27, 154, 37);
		contentPane.add(btnSoDoPhong);

		JButton btnDatPhong = new JButton("Đặt Phòng");
		btnDatPhong.setForeground(new Color(255, 255, 255));
		btnDatPhong.setBackground(new Color(102, 51, 153));
		btnDatPhong.setBounds(8, 77, 154, 37);
		contentPane.add(btnDatPhong);

		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setForeground(new Color(255, 255, 255));
		btnDangXuat.setBackground(new Color(255, 0, 102));
		btnDangXuat.setBounds(12, 662, 143, 31);
		contentPane.add(btnDangXuat);

		JButton btnKhachHang = new JButton("Khách Hàng");
		btnKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnKhachHang.setForeground(Color.WHITE);
		btnKhachHang.setBackground(new Color(102, 51, 153));
		btnKhachHang.setBounds(8, 127, 154, 37);
		contentPane.add(btnKhachHang);

		JButton btnPhong = new JButton("Phòng");
		btnPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPhong.setForeground(Color.WHITE);
		btnPhong.setBackground(new Color(102, 51, 153));
		btnPhong.setBounds(8, 177, 154, 37);
		contentPane.add(btnPhong);

		JButton btnDichVu = new JButton("Dịch Vụ");
		btnDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDichVu.setForeground(Color.WHITE);
		btnDichVu.setBackground(new Color(102, 51, 153));
		btnDichVu.setBounds(8, 227, 154, 37);
		contentPane.add(btnDichVu);

		JButton btnNhanVien = new JButton("Nhân Viên");
		btnNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNhanVien.setForeground(Color.WHITE);
		btnNhanVien.setBackground(new Color(102, 51, 153));
		btnNhanVien.setBounds(8, 277, 154, 37);
		contentPane.add(btnNhanVien);

		JButton btnThongKe = new JButton("Thống Kê");
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setBackground(new Color(102, 51, 153));
		btnThongKe.setBounds(8, 327, 154, 37);
		contentPane.add(btnThongKe);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 102));
		panel.setBounds(167, 0, 1195, 70);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblXinCho = new JLabel("Xin Chào :");
		lblXinCho.setForeground(SystemColor.menu);
		lblXinCho.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblXinCho.setBounds(751, 29, 74, 28);
		panel.add(lblXinCho);

		lblTenNhanVien.setForeground(Color.WHITE);
		lblTenNhanVien.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTenNhanVien.setBounds(837, 29, 219, 28);
		panel.add(lblTenNhanVien);

		btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDoiMatKhau.setForeground(Color.WHITE);
		btnDoiMatKhau.setBackground(new Color(0, 102, 102));
		btnDoiMatKhau.setBounds(1068, 32, 115, 25);
		panel.add(btnDoiMatKhau);
		
		dao_taiKhoan = new DAO_TAIKHOAN();
		
		dialog = new JDialog();
		dialog.setModal(true);
		giaoDienDoiMauKhau();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocation(500, 350);
		
		btnDatPhong.setIcon(new ImageIcon("icon/Phong/datphong.png"));
		btnDichVu.setIcon(new ImageIcon("icon/Phong/dichvu.png"));
		btnKhachHang.setIcon( new ImageIcon("icon/User/User_32x32.png"));
		btnNhanVien.setIcon(new ImageIcon("icon/User/User_32x32.png"));
		btnThongKe.setIcon( new ImageIcon("icon/Phong/thongke.png"));
		btnPhong.setIcon(new ImageIcon("icon/Phong/phong.png"));
		btnSoDoPhong.setIcon(new ImageIcon("icon/Archive/Archive_24x24.png"));
		
		btnNhanVien.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnDichVu.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnKhachHang.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnThongKe.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnPhong.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnDatPhong.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnSoDoPhong.setHorizontalTextPosition(AbstractButton.RIGHT);
		
			
//		menuItemPhong = new JMenuItem("PHÒNG", new ImageIcon("icon/Phong/phong.png"));
//		menuItemDatPhong = new JMenuItem("ĐẶT PHÒNG", new ImageIcon("icon/Phong/datphong.png"));
//		menuItemDichVu = new JMenuItem("DỊCH VỤ", new ImageIcon("icon/Phong/dichvu.png"));
//		menuItemNhanVien = new JMenuItem("NHÂN VIÊN", new ImageIcon("icon/User/User_32x32.png"));
//		menuItemKhachHang = new JMenuItem("KHÁCH HÀNG", new ImageIcon("icon/User/User_32x32.png"));
//		
//		menuItemTK = new JMenuItem("THỐNG KÊ", new ImageIcon("icon/Phong/thongke.png"));

		// dialog.setVisible(true);
		btnDoiMatKhau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.setVisible(true);

			}
		});

		btnDatPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(false);
				panel_1.removeAll();
				// remove(panel_1);
				GUI_DatPhong guisophong;
				// getContentPane().revalidate();
				try {
					panel_1.add(guisophong = new GUI_DatPhong());
					guisophong.setMaNhanVien(maNhanVien);
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// getContentPane().add(guisophong);
				// guisophong.setBounds(167, 0, 1195, 706);
				panel_1.setVisible(true);
				System.out.println("doan kim dinh1");
				btnKhachHang.setBackground(new Color(102, 51, 153));
				btnPhong.setBackground(new Color(102, 51, 153));
				btnDatPhong.setBackground(new Color(204, 102, 255));
				btnDichVu.setBackground(new Color(102, 51, 153));
				btnSoDoPhong.setBackground(new Color(102, 51, 153));
				btnThongKe.setBackground(new Color(102, 51, 153));
				btnNhanVien.setBackground(new Color(102, 51, 153));
			}
		});

		btnSoDoPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(false);
				panel_1.removeAll();
				// remove(panel_1);

				GUI_SoDoPhong guisophong;
				// getContentPane().revalidate();
				// getContentPane().add(guisophong);
				try {
					panel_1.add(guisophong = new GUI_SoDoPhong());
					guisophong.setMaNhanVien(maNhanVien);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// guisophong.setBounds(167, 0, 1195, 706);
				panel_1.setVisible(true);
				btnKhachHang.setBackground(new Color(102, 51, 153));
				btnPhong.setBackground(new Color(102, 51, 153));
				btnSoDoPhong.setBackground(new Color(204, 102, 255));
				btnDatPhong.setBackground(new Color(102, 51, 153));
				btnDichVu.setBackground(new Color(102, 51, 153));
				btnThongKe.setBackground(new Color(102, 51, 153));
				btnNhanVien.setBackground(new Color(102, 51, 153));
			}
		});
		btnKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(false);
				panel_1.removeAll();
				GUI_QuanLyKhachHang guiKhachHang;
				panel_1.add(guiKhachHang = new GUI_QuanLyKhachHang());
				panel_1.setVisible(true);
				btnDichVu.setBackground(new Color(102, 51, 153));
				btnPhong.setBackground(new Color(102, 51, 153));
				btnKhachHang.setBackground(new Color(204, 102, 255));
				btnDatPhong.setBackground(new Color(102, 51, 153));
				btnSoDoPhong.setBackground(new Color(102, 51, 153));
				btnThongKe.setBackground(new Color(102, 51, 153));
				btnNhanVien.setBackground(new Color(102, 51, 153));
			}
		});
		btnPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(false);
				panel_1.removeAll();
				GUI_QuanLyPhong guiPhong;
				panel_1.add(guiPhong = new GUI_QuanLyPhong());
				panel_1.setVisible(true);
				btnKhachHang.setBackground(new Color(102, 51, 153));
				btnDichVu.setBackground(new Color(102, 51, 153));
				btnPhong.setBackground(new Color(204, 102, 255));
				btnDatPhong.setBackground(new Color(102, 51, 153));
				btnSoDoPhong.setBackground(new Color(102, 51, 153));
				btnThongKe.setBackground(new Color(102, 51, 153));
				btnNhanVien.setBackground(new Color(102, 51, 153));
			}
		});
		btnDichVu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(false);
				panel_1.removeAll();
				GUI_QuanLyDichVu guiDichVu;
				panel_1.add(guiDichVu = new GUI_QuanLyDichVu());
				panel_1.setVisible(true);
				btnKhachHang.setBackground(new Color(102, 51, 153));
				btnPhong.setBackground(new Color(102, 51, 153));
				btnDichVu.setBackground(new Color(204, 102, 255));
				btnDatPhong.setBackground(new Color(102, 51, 153));
				btnSoDoPhong.setBackground(new Color(102, 51, 153));
				btnThongKe.setBackground(new Color(102, 51, 153));
				btnNhanVien.setBackground(new Color(102, 51, 153));
			}
		});
		btnNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(false);
				panel_1.removeAll();
				GUI_QuanLyNhanVien guiNhanVien;
				panel_1.add(guiNhanVien = new GUI_QuanLyNhanVien());
				panel_1.setVisible(true);
				btnKhachHang.setBackground(new Color(102, 51, 153));
				btnPhong.setBackground(new Color(102, 51, 153));
				btnNhanVien.setBackground(new Color(204, 102, 255));
				btnDatPhong.setBackground(new Color(102, 51, 153));
				btnSoDoPhong.setBackground(new Color(102, 51, 153));
				btnThongKe.setBackground(new Color(102, 51, 153));
				btnDichVu.setBackground(new Color(102, 51, 153));
			}
		});
		btnThongKe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(false);
				panel_1.removeAll();
				GUI_ThongKe guiThongKe;
				try {
					panel_1.add(guiThongKe = new GUI_ThongKe());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel_1.setVisible(true);
				btnKhachHang.setBackground(new Color(102, 51, 153));
				btnPhong.setBackground(new Color(102, 51, 153));
				btnThongKe.setBackground(new Color(204, 102, 255));
				btnDatPhong.setBackground(new Color(102, 51, 153));
				btnSoDoPhong.setBackground(new Color(102, 51, 153));
				btnDichVu.setBackground(new Color(102, 51, 153));
				btnNhanVien.setBackground(new Color(102, 51, 153));
			}
		});
		btnDangXuat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				GUI_DangNhap dangNhap = new GUI_DangNhap();
				dangNhap.setVisible(true);
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
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát thật chứ", "THOÁT",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				else if (result == JOptionPane.NO_OPTION)
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		dialog.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				txtPassCu.setText("");
				txtPassMoi.setText("");
				txtPassXacNhan.setText("");
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnXNDoiMatKhau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtPassCu.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập mật khẩu cũ ");
					txtPassCu.requestFocus();
					return;
				}
				if(txtPassMoi.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập mật khẩu mới ");
					txtPassMoi.requestFocus();
					return;
				}
				if(txtPassXacNhan.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập mật khẩu xác nhận ");
					txtPassXacNhan.requestFocus();
					return;
				}
				int tam = 0;
				String tenTaiKhoan="";
				for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
					if(taiKhoan.getNhanVien().getMaNV().equals(maNhanVien)) {
						if(taiKhoan.getMk().equals(txtPassCu.getText())) {
							tenTaiKhoan = taiKhoan.getTentk();
							tam =1;
						}
					}
				}
				if(tam==0) {
					JOptionPane.showMessageDialog(null, "Bạn nhập mật khẩu cũ bị sai. Vui lòng nhập lại");
					txtPassCu.setText("");
					txtPassCu.requestFocus();
					return;
				}
				if(!txtPassMoi.getText().equals(txtPassXacNhan.getText())) {
					JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu mới không khớp với mật khẩu mới");
					txtPassXacNhan.requestFocus();
					return;
				}
				dao_taiKhoan.capNhatTaiKhoan(tenTaiKhoan, txtPassMoi.getText());
				JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thành công");
				txtPassCu.setText("");
				txtPassMoi.setText("");
				txtPassXacNhan.setText("");
				dialog.setVisible(false);
				setVisible(false);
				GUI_DangNhap dangNhap = new GUI_DangNhap();
				dangNhap.setVisible(true);
			}
		});
		
	}

	public void giaoDienDoiMauKhau() {
		dialog.setBounds(100, 100, 415, 275);
		dialog.getContentPane().setLayout(new BorderLayout());
		panelDoiMatKhau.setBackground(new Color(51, 204, 204));
		panelDoiMatKhau.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(panelDoiMatKhau, BorderLayout.CENTER);
		panelDoiMatKhau.setLayout(null);
		{
			btnXNDoiMatKhau = new JButton("Đổi Mật Khẩu");
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
