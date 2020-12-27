package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;

import dao.DAO_NHANVIEN;
import dao.DAO_TAIKHOAN;

import entity.NHANVIEN;
import entity.TAIKHOAN;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class GUI_QuanLyNhanVien extends JPanel {
	private JTextField txtTimTen;
	private JTextField txtTimSDT;
	private JTextField txtMaNhanVien;
	private JTable table;
	private JTextField txtTenNhanVien;
	private JTextField txtCMND;
	private JTextField txtSoDienThoai;
	private JTextField txtTimMa;
	private JTextField txtTimCMND;
	private JRadioButton rdbtnNu;
	private JRadioButton rdbtnNam;
	private JButton btnThemNhanVien;
	private JButton btnSuaThongTin;
	private DefaultTableModel modelNhanVien;
	private DAO_NHANVIEN dao_nhanVien = new DAO_NHANVIEN();
	private ButtonGroup btnGroup;
	private JPasswordField txtPassword;
	private JComboBox<String> cbbChucVu;
	private JButton btnResetPass;
	private JTextField txtTenTaiKhoan;
	private DAO_TAIKHOAN dao_taiKhoan;
	private JComboBox<String> cbbTinhTrang;

	/**
	 * Create the panel.
	 */
	public GUI_QuanLyNhanVien() {
		setBackground(new Color(153, 153, 51));
		setLayout(null);
		setSize(1195, 635);
		try {
			dao_taiKhoan = new DAO_TAIKHOAN();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		txtTimTen = new JTextField();
		txtTimTen.setBounds(145, 202, 181, 28);
		add(txtTimTen);
		txtTimTen.setColumns(10);

		JLabel lblTmKimTheo = new JLabel("Tìm Kiếm Theo Tên");
		lblTmKimTheo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo.setBounds(12, 207, 127, 16);
		add(lblTmKimTheo);

		JLabel lblTmKimTheo_1 = new JLabel("Tìm Kiếm Theo SDT");
		lblTmKimTheo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo_1.setBounds(12, 248, 127, 16);
		add(lblTmKimTheo_1);

		txtTimSDT = new JTextField();
		txtTimSDT.setBounds(145, 243, 181, 28);
		add(txtTimSDT);
		txtTimSDT.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 153, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng Tin Nh\u00E2n Vi\u00EAn", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 13, 1171, 176);
		add(panel_1);
		panel_1.setLayout(null);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBackground(new Color(102, 153, 153));
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNam.setBounds(926, 120, 95, 25);
		panel_1.add(rdbtnNam);

		JLabel lblMKhchHng = new JLabel("Mã nhân viên");
		lblMKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMKhchHng.setBounds(12, 36, 114, 16);
		panel_1.add(lblMKhchHng);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(118, 30, 253, 30);
		panel_1.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JLabel lblTnKhchHng = new JLabel("Tên Nhân Viên");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTnKhchHng.setBounds(12, 81, 114, 16);
		panel_1.add(lblTnKhchHng);

		JLabel lblSThCmnd = new JLabel("Số Thẻ CMND");
		lblSThCmnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSThCmnd.setBounds(12, 124, 116, 16);
		panel_1.add(lblSThCmnd);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(118, 75, 253, 30);
		panel_1.add(txtTenNhanVien);

		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(118, 118, 253, 30);
		panel_1.add(txtCMND);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSinThoi.setBounds(809, 81, 95, 16);
		panel_1.add(lblSinThoi);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(916, 75, 243, 30);
		panel_1.add(txtSoDienThoai);

		JLabel lblGiiTnh = new JLabel("Giới Tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGiiTnh.setBounds(809, 124, 79, 16);
		panel_1.add(lblGiiTnh);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(new Color(102, 153, 153));
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNu.setBounds(1047, 120, 55, 25);
		panel_1.add(rdbtnNu);
		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNam);
		btnGroup.add(rdbtnNu);

		JLabel lblChcV = new JLabel("Chức Vụ");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChcV.setBounds(416, 36, 79, 16);
		panel_1.add(lblChcV);

		cbbChucVu = new JComboBox<String>();
		cbbChucVu.setBackground(Color.WHITE);
		cbbChucVu.setForeground(Color.BLACK);
		cbbChucVu.setBounds(524, 30, 253, 30);
		panel_1.add(cbbChucVu);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(524, 118, 208, 30);
		panel_1.add(txtPassword);

		JLabel lblMtKhu = new JLabel("Mật Khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMtKhu.setBounds(416, 124, 79, 16);
		panel_1.add(lblMtKhu);

		btnResetPass = new JButton("");
		btnResetPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnResetPass.setBounds(744, 118, 33, 30);
		panel_1.add(btnResetPass);

		JLabel lblTnTiKhon = new JLabel("Tên Tài Khoản");
		lblTnTiKhon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTnTiKhon.setBounds(416, 81, 102, 16);
		panel_1.add(lblTnTiKhon);

		txtTenTaiKhoan = new JTextField();
		txtTenTaiKhoan.setEditable(false);
		txtTenTaiKhoan.setColumns(10);
		txtTenTaiKhoan.setBounds(524, 75, 253, 30);
		panel_1.add(txtTenTaiKhoan);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 153, 102));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CH\u1EE8C N\u0102NG ",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(749, 202, 434, 78);
		add(panel_2);
		panel_2.setLayout(null);

		btnThemNhanVien = new JButton("Thêm Nhân Viên");
		btnThemNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemNhanVien.setBounds(38, 25, 147, 38);
		panel_2.add(btnThemNhanVien);

		btnSuaThongTin = new JButton("Sửa Thông Tin");
		btnSuaThongTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuaThongTin.setBounds(256, 25, 147, 38);
		panel_2.add(btnSuaThongTin);

		JLabel lblTmKimTheo_2 = new JLabel("Tìm Kiếm Theo Mã");
		lblTmKimTheo_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo_2.setBounds(364, 207, 127, 16);
		add(lblTmKimTheo_2);

		txtTimMa = new JTextField();
		txtTimMa.setColumns(10);
		txtTimMa.setBounds(517, 202, 194, 28);
		add(txtTimMa);

		JLabel lblTmKimTheo_3 = new JLabel("Tìm Kiếm Theo CMND");
		lblTmKimTheo_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo_3.setBounds(364, 248, 139, 16);
		add(lblTmKimTheo_3);

		txtTimCMND = new JTextField();
		txtTimCMND.setColumns(10);
		txtTimCMND.setBounds(517, 243, 194, 28);
		add(txtTimCMND);

		cbbChucVu.addItem("QUẢN LÝ");
		cbbChucVu.addItem("TIẾP TÂN");
		cbbChucVu.setSelectedIndex(-1);
		cbbChucVu.setEditable(false);
		txtPassword.setEditable(false);

		JLabel lblTinhTrang = new JLabel("Tình trạng");
		lblTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTinhTrang.setBounds(809, 36, 79, 16);
		panel_1.add(lblTinhTrang);

		JPanel panel = new JPanel();
		panel.setBounds(12, 297, 1171, 325);
		add(panel);
		panel.setBackground(new Color(0, 153, 153));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh S\u00E1ch Nh\u00E2n Vi\u00EAn", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 26, 1147, 286);
		panel.add(scrollPane);

		table = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.setModel(modelNhanVien = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã nhân viên", "Tên nhân viên", "S\u1ED1 Th\u1EBB CMND",
						"S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "Gi\u1EDBi T\u00EDnh", "Chức Vụ", "Tên Tài Khoản",
						"Tình Trạng" }));
		scrollPane.setViewportView(table);
		
		cbbTinhTrang = new JComboBox<String>();
		cbbTinhTrang.setSelectedIndex(-1);
		cbbTinhTrang.setForeground(Color.BLACK);
		cbbTinhTrang.setEditable(false);
		cbbTinhTrang.setBackground(Color.WHITE);
		cbbTinhTrang.setBounds(918, 30, 241, 30);
		panel_1.add(cbbTinhTrang);
		cbbTinhTrang.addItem("ĐANG LÀM VIỆC");
		cbbTinhTrang.addItem("ĐÃ NGHỈ VIỆC");
		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cbbTinhTrang.setSelectedIndex(-1);

		CapNhatBangNhanVien();
		SuKienTableNhanVien();
		SuKienTXTTimKiemTheoTen();
		SuKienTXTTimKiemTheoCMND();
		SuKienTXTTimKiemTheoSDT();
		SuKienTXTTimKiemTheoMa();
		SuKienThemNhanVien();
		SuKienResetMatKhau();
//		SuKienSuaKhachHang();
		btnSuaThongTin.setEnabled(false);
		
		ThietLapKhongTheTuyChinh();
		for (NHANVIEN nhanVien : dao_nhanVien.getalltbNhanVien()) {
			System.out.println(nhanVien.getTenNV());
		}
	}

	private void SuKienResetMatKhau() {
		// TODO Auto-generated method stub
		btnResetPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPassword.setText("12345");
			}
		});
	}

	public String autoMaNhanVien(String chucVu) {
		List<NHANVIEN> listNhanVien = dao_nhanVien.getalltbNhanVien();
		if (listNhanVien == null && chucVu.toUpperCase().equals("TIẾP TÂN")) {
			return "NVTT000";
		}
		if (listNhanVien == null && chucVu.toUpperCase().equals("QUẢN LÝ")) {
			return "NVQL000";
		}
		if (chucVu.toUpperCase().equals("TIẾP TÂN")) {
			String maNhanVien = "NVTT";
			int so = 0;
			for (NHANVIEN nhanvien : listNhanVien) {
				// System.out.println(khachhang.getMaKH().substring(2, 6));
				if (nhanvien.getMaNV().substring(0, 4).equals("NVTT"))
					so = Integer.parseInt(nhanvien.getMaNV().substring(4, 7)) + 1;
			}
			if (so >= 0 && so < 10) {
				maNhanVien = maNhanVien + "00" + so;
			}
			if (so >= 10 && so < 100) {
				maNhanVien = maNhanVien + "0" + so;
			}
			if (so >= 100 && so < 1000) {
				maNhanVien = maNhanVien + so;
			}
			return maNhanVien;
		} else {
			String maNhanVien = "NVQL";
			int so = 0;
			for (NHANVIEN nhanvien : listNhanVien) {
				// System.out.println(khachhang.getMaKH().substring(2, 6));
				if (nhanvien.getMaNV().substring(0, 4).equals("NVQL"))
					so = Integer.parseInt(nhanvien.getMaNV().substring(4, 7)) + 1;
			}
			if (so >= 0 && so < 10) {
				maNhanVien = maNhanVien + "00" + so;
			}
			if (so >= 10 && so < 100) {
				maNhanVien = maNhanVien + "0" + so;
			}
			if (so >= 100 && so < 1000) {
				maNhanVien = maNhanVien + so;
			}
			return maNhanVien;
		}
	}

	private void SuKienXoaTextTimKiem() {
		// TODO Auto-generated method stub
		txtTimSDT.setText("");
		txtTimTen.setText("");
		txtTimCMND.setText("");
		txtTimMa.setText("");
	}

	private void SuKienThemNhanVien() {
		// TODO Auto-generated method stub
		btnThemNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (btnThemNhanVien.getText().equals("Thêm Nhân Viên")) {
					btnSuaThongTin.setEnabled(true);
					cbbChucVu.setSelectedIndex(-1);

					cbbTinhTrang.setSelectedItem("ĐANG LÀM VIỆC");
					txtTenTaiKhoan.setText("");
					txtPassword.setText("");

					CapNhatBangNhanVien();
					XoaThongTinNhanVien();
					ThietLapCoTheTuyChinh();
					txtTenNhanVien.requestFocus();
					btnThemNhanVien.setText("Lưu");
					btnSuaThongTin.setText("Hủy");
					cbbTinhTrang.setEnabled(false);
				} else if (btnThemNhanVien.getText().equals("Lưu")) {

					if (txtTenNhanVien.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống");
						txtTenNhanVien.requestFocus();
						return;
					}
					if (!txtTenNhanVien.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếẾìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(([',. -][a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")){
						JOptionPane.showMessageDialog(null, "Tên nhân viên không hợp lệ");
						txtTenNhanVien.setText("");
						txtTenNhanVien.requestFocus();
						return;
					}
					if (txtCMND.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Số CMND không được để trống");
						txtCMND.requestFocus();
						return;
					}
					if(!txtCMND.getText().matches("^([0-9]{9})$")) {
						JOptionPane.showMessageDialog(null, "Số cmnd không hợp lệ");
						txtCMND.setText("");
						txtCMND.requestFocus();
						return;
					}

					for (NHANVIEN nhanvien : dao_nhanVien.getalltbNhanVien()) {
						if (nhanvien.getCMND().equals(txtCMND.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng Số CMND với một nhân viên khác. Vui lòng nhập lại ");
							txtCMND.setText("");
							txtCMND.requestFocus();
							return;
						}
					}
					if (txtSoDienThoai.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
						txtSoDienThoai.requestFocus();
						return;
					}
					if(!txtSoDienThoai.getText().matches("^(09|01|02|03|04|05|06|07|08)+([0-9]{8})$")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
						txtSoDienThoai.setText("");
						txtSoDienThoai.requestFocus();
						return;
					}
					for (NHANVIEN nhanvien : dao_nhanVien.getalltbNhanVien()) {
						if (nhanvien.getSdt().equals(txtSoDienThoai.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng số điện thoại với một nhân viên khác. Vui lòng nhập lại ");
							txtSoDienThoai.setText("");
							txtSoDienThoai.requestFocus();
							return;
						}
					}
					boolean gioiTinh;
					if (btnGroup.getSelection() != null) {
						if (rdbtnNam.isSelected()) {
							gioiTinh = true;
						} else
							gioiTinh = false;
					} else {
						JOptionPane.showMessageDialog(null, "Giới tính còn để trống");
						return;
					}
					if (cbbChucVu.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ cho nhân viên");
						return;
					}
					if (txtTenTaiKhoan.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống");
						txtTenTaiKhoan.requestFocus();
						return;
					}
					if(" ".contains(txtTenTaiKhoan.getText())) {
						JOptionPane.showMessageDialog(null,"Tên tài khoản không được chứa ký tự khoảng trắng");
						txtTenTaiKhoan.setText("");
						txtTenTaiKhoan.requestFocus();
						return;
					}
					for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
						if (taiKhoan.getTentk().toUpperCase().equals(txtTenTaiKhoan.getText())) {
							JOptionPane.showMessageDialog(null,
									"Tên tài khoản trùng với một nhân viên khác. Vui lòng nhập lại");
							txtTenTaiKhoan.setText("");
							txtTenTaiKhoan.requestFocus();
							return;
						}
					}
					if (txtPassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Vui lòng thiết lập mật khẩu mặc định cho tài khoản");
						return;
					}
					String maNhanVien = autoMaNhanVien((String) cbbChucVu.getSelectedItem());
					System.out.println(maNhanVien);
//					 dao_KhachHang.themKhachHang(maKhachHang, txtTenNhanVien.getText(),
//					 txtCMND.getText(), txtSoDienThoai.getText(), gioiTinh);
					dao_nhanVien.themNhanVien(maNhanVien, txtTenNhanVien.getText(), gioiTinh, txtCMND.getText(),
							txtSoDienThoai.getText(), cbbChucVu.getSelectedItem().toString(), null,
							cbbTinhTrang.getSelectedItem().toString());
					dao_taiKhoan.themTaiKhoan(maNhanVien, txtTenTaiKhoan.getText(), txtPassword.getText());
					JOptionPane.showMessageDialog(null, "Thêm Nhân Viên Thành Công");
					CapNhatBangNhanVien();
					btnSuaThongTin.setEnabled(false);
					ThietLapKhongTheTuyChinh();
					XoaThongTinNhanVien();
					btnThemNhanVien.setText("Thêm Nhân Viên");
					btnSuaThongTin.setText("Sửa Thông Tin");
					SuKienXoaTextTimKiem();

				} else if (btnThemNhanVien.getText().equals("Cập Nhật")) {

					if (txtTenNhanVien.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống");
						txtTenNhanVien.requestFocus();
						return;
					}
					if (!txtTenNhanVien.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(([',. -][a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")) {
						JOptionPane.showMessageDialog(null, "Tên nhân viên không hợp lệ");
						txtTenNhanVien.setText("");
						txtTenNhanVien.requestFocus();
						return;
					}
					if (txtCMND.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Số CMND không được để trống");
						txtCMND.requestFocus();
						return;
					}
					if(!txtCMND.getText().matches("^([0-9]{9})$")) {
						JOptionPane.showMessageDialog(null, "Số cmnd không hợp lệ");
						txtCMND.setText("");
						txtCMND.requestFocus();
						return;
					}
					List<NHANVIEN> listNhanVien = dao_nhanVien.getalltbNhanVien();
					List<TAIKHOAN> listTaiKhoan = dao_taiKhoan.getalltbTaiKhoan();
					for (int i = 0; i < listNhanVien.size(); i++) {
						if (listNhanVien.get(i).getMaNV().equals(txtMaNhanVien.getText())) {
							listNhanVien.remove(i);
						}
					}
					for (int i = 0; i < listTaiKhoan.size(); i++) {
						if (listTaiKhoan.get(i).getNhanVien().getMaNV().equals(txtMaNhanVien.getText())) {
							listTaiKhoan.remove(i);
						}
					}

					for (NHANVIEN nhanVien : listNhanVien) {
						if (nhanVien.getCMND().equals(txtCMND.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng Số CMND với một nhân viên khác. Vui lòng nhập lại ");
							txtCMND.setText("");
							txtCMND.requestFocus();
							return;
						}
					}
					if (txtSoDienThoai.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
						txtSoDienThoai.requestFocus();
						return;
					}
					if(!txtSoDienThoai.getText().matches("^(09|01|02|03|04|05|06|07|08)+([0-9]{8})$")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
						txtSoDienThoai.setText("");
						txtSoDienThoai.requestFocus();
						return;
					}
					for (NHANVIEN nhanVien : listNhanVien) {
						if (nhanVien.getSdt().equals(txtSoDienThoai.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng số điện thoại với một nhân viên khác. Vui lòng nhập lại ");
							txtSoDienThoai.setText("");
							txtSoDienThoai.requestFocus();
							return;
						}
					}
					boolean gioiTinh;
					if (btnGroup.getSelection() != null) {
						if (rdbtnNam.isSelected()) {
							gioiTinh = true;
						} else
							gioiTinh = false;
					} else {
						JOptionPane.showMessageDialog(null, "Giới tính còn để trống");
						return;
					}

					if (txtTenTaiKhoan.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống");
						txtTenTaiKhoan.requestFocus();
						return;
					}
					if(" ".contains(txtTenTaiKhoan.getText())) {
						JOptionPane.showMessageDialog(null,"Tên tài khoản không được chứa ký tự khoảng trắng");
						txtTenTaiKhoan.setText("");
						txtTenTaiKhoan.requestFocus();
						return;
					}
					for (TAIKHOAN taiKhoan : listTaiKhoan) {
						if (taiKhoan.getTentk().equals(txtTenTaiKhoan.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng tên tài khoản với một nhân viên khác. Vui lòng nhập lại");
							txtTenTaiKhoan.setText("");
							txtTenTaiKhoan.requestFocus();
							return;
						}
					}
					
					// String maKhachHang = autoMaKhachHang();
					// dao_KhachHang.themKhachHang(maKhachHang, txtTenKhachHang.getText(),
					// txtCMND.getText(), txtSoDienThoai.getText(), gioiTinh);
					// dao_KhachHang.upDateCustomer(txtMaNhanVien.getText(),
					// txtTenNhanVien.getText(), txtCMND.getText(), txtSoDienThoai.getText(),
					// gioiTinh);
					dao_nhanVien.CapNhatNhanVien(txtMaNhanVien.getText(), txtTenNhanVien.getText(), gioiTinh,
							txtCMND.getText(), txtSoDienThoai.getText(), cbbChucVu.getSelectedItem().toString(), null,
							cbbTinhTrang.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Cập nhật Nhân Viên Thành Công");
					dao_taiKhoan.capNhatTaiKhoan(txtTenTaiKhoan.getText(), txtPassword.getText());
					CapNhatBangNhanVien();
					btnSuaThongTin.setEnabled(false);
					ThietLapKhongTheTuyChinh();
					XoaThongTinNhanVien();
					btnThemNhanVien.setText("Thêm Nhân Viên");
					btnSuaThongTin.setText("Sửa Thông Tin");
					SuKienXoaTextTimKiem();
//					sữa du lieu ben sql cho khỏi trùng cmnd với sdt
				}
			}
		});
		btnSuaThongTin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (btnSuaThongTin.getText().equals("Hủy")) {
					btnThemNhanVien.setText("Thêm Nhân Viên");
					btnSuaThongTin.setText("Sửa Thông Tin");
					btnSuaThongTin.setEnabled(false);
					cbbTinhTrang.setEnabled(false);
					XoaThongTinNhanVien();
					ThietLapKhongTheTuyChinh();
					SuKienXoaTextTimKiem();
					CapNhatBangNhanVien();
				} else if (btnSuaThongTin.getText().equals("Sửa Thông Tin")) {
					btnThemNhanVien.setText("Cập Nhật");
					btnSuaThongTin.setText("Hủy");
					ThietLapCoTheTuyChinh();
					txtTenNhanVien.requestFocus();
					cbbTinhTrang.setEnabled(true);
				}

			}
		});
	}

	private void SuKienTXTTimKiemTheoMa() {
		// TODO Auto-generated method stub
		txtTimMa.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				txtTimSDT.setText("");
				txtTimTen.setText("");
				txtTimCMND.setText("");
				XoaThongTinNhanVien();
				DefaultTableModel dm = (DefaultTableModel) modelNhanVien;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (NHANVIEN nhanVien : dao_nhanVien.getalltbNhanVien()) {
					if (nhanVien.getMaNV().toUpperCase().contains(txtTimMa.getText().toUpperCase())) {
						if (nhanVien.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
							if (taiKhoan.getNhanVien().getMaNV().equals(nhanVien.getMaNV())) {
								modelNhanVien.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getTenNV(),
										nhanVien.getCMND(), nhanVien.getSdt(), gioiTinh, nhanVien.getChucVu(),
										taiKhoan.getTentk(), nhanVien.getTinhTrang() });
							}
						}
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemNhanVien.setText("Thêm Nhân Viên");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void SuKienTXTTimKiemTheoSDT() {
		// TODO Auto-generated method stub
		txtTimSDT.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				txtTimTen.setText("");
				txtTimMa.setText("");
				txtTimCMND.setText("");
				XoaThongTinNhanVien();
				DefaultTableModel dm = (DefaultTableModel) modelNhanVien;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (NHANVIEN nhanVien : dao_nhanVien.getalltbNhanVien()) {
					if (nhanVien.getSdt().contains(txtTimSDT.getText())) {
						if (nhanVien.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
							if (taiKhoan.getNhanVien().getMaNV().equals(nhanVien.getMaNV())) {
								modelNhanVien.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getTenNV(),
										nhanVien.getCMND(), nhanVien.getSdt(), gioiTinh, nhanVien.getChucVu(),
										taiKhoan.getTentk(), nhanVien.getTinhTrang() });
							}
						}
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemNhanVien.setText("Thêm Nhân Viên");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void SuKienTXTTimKiemTheoCMND() {
		// TODO Auto-generated method stub
		txtTimCMND.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				txtTimSDT.setText("");
				txtTimTen.setText("");
				txtTimMa.setText("");
				XoaThongTinNhanVien();
				DefaultTableModel dm = (DefaultTableModel) modelNhanVien;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (NHANVIEN nhanVien : dao_nhanVien.getalltbNhanVien()) {
					if (nhanVien.getCMND().contains(txtTimCMND.getText())) {
						if (nhanVien.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
							if (taiKhoan.getNhanVien().getMaNV().equals(nhanVien.getMaNV())) {
								modelNhanVien.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getTenNV(),
										nhanVien.getCMND(), nhanVien.getSdt(), gioiTinh, nhanVien.getChucVu(),
										taiKhoan.getTentk(), nhanVien.getTinhTrang() });
							}
						}
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemNhanVien.setText("Thêm Nhân Viên");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void SuKienTXTTimKiemTheoTen() {
		// TODO Auto-generated method stub
		txtTimTen.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				txtTimSDT.setText("");
				txtTimMa.setText("");
				txtTimCMND.setText("");
				XoaThongTinNhanVien();
				DefaultTableModel dm = (DefaultTableModel) modelNhanVien;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (NHANVIEN nhanVien : dao_nhanVien.getalltbNhanVien()) {
					if (nhanVien.getTenNV().toUpperCase().contains(txtTimTen.getText().toUpperCase())) {
						if (nhanVien.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
							if (taiKhoan.getNhanVien().getMaNV().equals(nhanVien.getMaNV())) {
								modelNhanVien.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getTenNV(),
										nhanVien.getCMND(), nhanVien.getSdt(), gioiTinh, nhanVien.getChucVu(),
										taiKhoan.getTentk(), nhanVien.getTinhTrang() });
							}
						}
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemNhanVien.setText("Thêm Nhân Viên");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void XoaThongTinNhanVien() {
		txtTenNhanVien.setText("");
		txtMaNhanVien.setText("");
		txtCMND.setText("");
		txtSoDienThoai.setText("");
		rdbtnNam.setSelected(false);
		rdbtnNu.setSelected(false);
		btnGroup.clearSelection();
		txtTenTaiKhoan.setText("");
		cbbChucVu.setSelectedIndex(-1);
		txtPassword.setText("");
	}

	private void ThietLapCoTheTuyChinh() {
		txtTenNhanVien.setEditable(true);
		txtSoDienThoai.setEditable(true);
		txtTenTaiKhoan.setEditable(true);
		btnResetPass.setEnabled(true);
		txtCMND.setEditable(true);
		rdbtnNam.setEnabled(true);
		rdbtnNu.setEnabled(true);
		cbbChucVu.setEnabled(true);
		cbbTinhTrang.setEnabled(true);
	}

	private void ThietLapKhongTheTuyChinh() {
		txtMaNhanVien.setEditable(false);
		txtTenNhanVien.setEditable(false);
		txtSoDienThoai.setEditable(false);
		txtTenTaiKhoan.setEditable(false);
		btnResetPass.setEnabled(false);
		txtCMND.setEditable(false);
		rdbtnNam.setEnabled(false);
		rdbtnNu.setEnabled(false);
		cbbChucVu.setEnabled(false);
		cbbTinhTrang.setEnabled(false);
	}

	private void HienThiThongTin(String maNhanVien, String tenNhanVien, String cmnd, String sdt, String gioiTinh,
			String chucVu, String tenTaiKhoan, String passWord, String tinhTrang) {
		txtMaNhanVien.setText(maNhanVien);
		txtTenNhanVien.setText(tenNhanVien);
		txtSoDienThoai.setText(sdt);
		txtCMND.setText(cmnd);
		cbbChucVu.setSelectedItem(chucVu);
		txtTenTaiKhoan.setText(tenTaiKhoan);
		txtPassword.setText(passWord);
		cbbTinhTrang.setSelectedItem(tinhTrang);

		if (gioiTinh.toUpperCase().equals("NAM")) {
			rdbtnNam.setSelected(true);
		} else
			rdbtnNu.setSelected(true);
	}

	private void SuKienTableNhanVien() {
		// TODO Auto-generated method stub
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row != -1) {
					for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
						if (taiKhoan.getNhanVien().getMaNV().equals(modelNhanVien.getValueAt(row, 0).toString())) {
							HienThiThongTin(modelNhanVien.getValueAt(row, 0).toString(),
									modelNhanVien.getValueAt(row, 1).toString(),
									modelNhanVien.getValueAt(row, 2).toString(),
									modelNhanVien.getValueAt(row, 3).toString(),
									modelNhanVien.getValueAt(row, 4).toString(),
									modelNhanVien.getValueAt(row, 5).toString(),
									modelNhanVien.getValueAt(row, 6).toString(), taiKhoan.getMk(),
									modelNhanVien.getValueAt(row, 7).toString());
							System.out.println(taiKhoan.getTentk() + taiKhoan.getMk());
						}
					}
				}
				ThietLapKhongTheTuyChinh();
				btnSuaThongTin.setEnabled(true);
				btnThemNhanVien.setText("Thêm Nhân Viên");
				btnSuaThongTin.setText("Sửa Thông Tin");
				cbbTinhTrang.setEnabled(false);
				for (TAIKHOAN iterable_element : dao_taiKhoan.getalltbTaiKhoan()) {
					System.out.println(iterable_element.getNhanVien().getMaNV() + "ten");
					System.out.println(iterable_element.getTentk());
					System.out.println(iterable_element.getMk());
				}
			}
		});
	}

	private void CapNhatBangNhanVien() {
		// TODO Auto-generated method stub
		String gioiTinh = "";
		DefaultTableModel dm = (DefaultTableModel) modelNhanVien;
		dm.setRowCount(0);
		for (NHANVIEN nhanVien : dao_nhanVien.getalltbNhanVien()) {
			if (nhanVien.isGioiTinh()) {
				gioiTinh = "Nam";
			} else
				gioiTinh = "Nữ";
			for (TAIKHOAN taiKhoan : dao_taiKhoan.getalltbTaiKhoan()) {
				if (taiKhoan.getNhanVien().getMaNV().equals(nhanVien.getMaNV())) {
					modelNhanVien.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getCMND(),
							nhanVien.getSdt(), gioiTinh, nhanVien.getChucVu(), taiKhoan.getTentk(),
							nhanVien.getTinhTrang() });
				}
			}

		}

	}
}
