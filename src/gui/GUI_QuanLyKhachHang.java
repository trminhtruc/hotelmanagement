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
import dao.DAO_KHACHHANG;
import entity.KHACHHANG;

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

public class GUI_QuanLyKhachHang extends JPanel {
	private JTextField txtTimTen;
	private JTextField txtTimSDT;
	private JTextField txtMaKhachHang;
	private JTable table;
	private JTextField txtTenKhachHang;
	private JTextField txtCMND;
	private JTextField txtSoDienThoai;
	private JTextField txtTimMa;
	private JTextField txtTimCMND;
	private JRadioButton rdbtnNu;
	private JRadioButton rdbtnNam;
	private JButton btnThemKhachHang;
	private JButton btnSuaThongTin;
	private DefaultTableModel modelKhachHang;
	private DAO_KHACHHANG dao_KhachHang = new DAO_KHACHHANG();
	private ButtonGroup btnGroup;

	/**
	 * Create the panel.
	 */
	public GUI_QuanLyKhachHang() {
		setBackground(new Color(189, 183, 107));
		setLayout(null);
		setSize(1195, 635);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 127, 80));
		panel.setBorder(new TitledBorder(null, "Danh S\u00E1ch Kh\u00E1ch H\u00E0ng", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(12, 113, 594, 489);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 26, 570, 450);
		panel.add(scrollPane);

		
		table = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.setModel(modelKhachHang = new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 Kh\u00E1ch H\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng", "S\u1ED1 Th\u1EBB CMND",
						"S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "Gi\u1EDBi T\u00EDnh" }));
		scrollPane.setViewportView(table);

		txtTimTen = new JTextField();
		txtTimTen.setBounds(163, 11, 250, 28);
		add(txtTimTen);
		txtTimTen.setColumns(10);

		JLabel lblTmKimTheo = new JLabel("Tìm Kiếm Theo Tên");
		lblTmKimTheo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo.setBounds(31, 16, 127, 16);
		add(lblTmKimTheo);

		JLabel lblTmKimTheo_1 = new JLabel("Tìm Kiếm Theo SDT");
		lblTmKimTheo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo_1.setBounds(31, 66, 127, 16);
		add(lblTmKimTheo_1);

		txtTimSDT = new JTextField();
		txtTimSDT.setBounds(163, 61, 250, 28);
		add(txtTimSDT);
		txtTimSDT.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 164, 96));
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng Tin Kh\u00E1ch H\u00E0ng", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(618, 113, 565, 348);
		add(panel_1);
		panel_1.setLayout(null);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBackground(new Color(244, 164, 96));
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNam.setBounds(262, 270, 95, 25);
		panel_1.add(rdbtnNam);

		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMKhchHng.setBounds(98, 41, 114, 16);
		panel_1.add(lblMKhchHng);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(240, 35, 286, 30);
		panel_1.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);

		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTnKhchHng.setBounds(98, 98, 114, 16);
		panel_1.add(lblTnKhchHng);

		JLabel lblSThCmnd = new JLabel("Số Thẻ CMND");
		lblSThCmnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSThCmnd.setBounds(98, 157, 116, 16);
		panel_1.add(lblSThCmnd);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(240, 92, 286, 30);
		panel_1.add(txtTenKhachHang);

		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(240, 151, 286, 30);
		panel_1.add(txtCMND);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSinThoi.setBounds(96, 222, 116, 16);
		panel_1.add(lblSinThoi);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(240, 216, 286, 30);
		panel_1.add(txtSoDienThoai);

		JLabel lblGiiTnh = new JLabel("Giới Tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGiiTnh.setBounds(98, 274, 79, 16);
		panel_1.add(lblGiiTnh);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(new Color(244, 164, 96));
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNu.setBounds(399, 270, 55, 25);
		panel_1.add(rdbtnNu);
		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNam);
		btnGroup.add(rdbtnNu);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 228, 181));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CH\u1EE8C N\u0102NG ",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(618, 474, 565, 114);
		add(panel_2);
		panel_2.setLayout(null);

		btnThemKhachHang = new JButton("Thêm Khách Hàng");
		btnThemKhachHang.setForeground(Color.WHITE);
		btnThemKhachHang.setBackground(new Color(51, 153, 102));
		btnThemKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemKhachHang.setBounds(75, 36, 147, 38);
		panel_2.add(btnThemKhachHang);

		btnSuaThongTin = new JButton("Sửa Thông Tin");
		btnSuaThongTin.setForeground(Color.WHITE);
		btnSuaThongTin.setBackground(new Color(204, 51, 102));
		btnSuaThongTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuaThongTin.setBounds(367, 36, 147, 38);
		panel_2.add(btnSuaThongTin);

		JLabel lblTmKimTheo_2 = new JLabel("Tìm Kiếm Theo Mã");
		lblTmKimTheo_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo_2.setBounds(483, 16, 127, 16);
		add(lblTmKimTheo_2);

		txtTimMa = new JTextField();
		txtTimMa.setColumns(10);
		txtTimMa.setBounds(618, 11, 250, 28);
		add(txtTimMa);

		JLabel lblTmKimTheo_3 = new JLabel("Tìm Kiếm Theo CMND");
		lblTmKimTheo_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKimTheo_3.setBounds(483, 66, 139, 16);
		add(lblTmKimTheo_3);

		txtTimCMND = new JTextField();
		txtTimCMND.setColumns(10);
		txtTimCMND.setBounds(618, 61, 250, 28);
		add(txtTimCMND);

		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CapNhatBangKhachHang();
		SuKienTableKhachHang();
		SuKienTXTTimKiemTheoTen();
		SuKienTXTTimKiemTheoCMND();
		SuKienTXTTimKiemTheoSDT();
		SuKienTXTTimKiemTheoMa();
		SuKienThemKhachHang();
//		SuKienSuaKhachHang();
		btnSuaThongTin.setEnabled(false);
		ThietLapKhongTheTuyChinh();
	}

	public String autoMaKhachHang() {
		List<KHACHHANG> listKhachHang = dao_KhachHang.getalltbKhachHang();
		if (listKhachHang == null) {
			return "KH0000";
		}
		String maKhachHang = "KH";
		int so = 0;
		for (KHACHHANG khachhang : listKhachHang) {
			// System.out.println(khachhang.getMaKH().substring(2, 6));
			so = Integer.parseInt(khachhang.getMaKH().substring(2, 6)) + 1;
		}
		if (so >= 0 && so < 10) {
			maKhachHang = maKhachHang + "000" + so;
		}
		if (so >= 10 && so < 100) {
			maKhachHang = maKhachHang + "00" + so;
		}
		if (so >= 100 && so < 1000) {
			maKhachHang = maKhachHang + "0" + so;
		}
		if (so >= 1000 && so < 10000) {
			maKhachHang = maKhachHang + so;
		}

		return maKhachHang;
	}

	private void SuKienXoaTextTimKiem() {
		// TODO Auto-generated method stub
		txtTimSDT.setText("");
		txtTimTen.setText("");
		txtTimCMND.setText("");
		txtTimMa.setText("");
	}

	private void SuKienThemKhachHang() {
		// TODO Auto-generated method stub
		btnThemKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (btnThemKhachHang.getText().equals("Thêm Khách Hàng")) {
					btnSuaThongTin.setEnabled(true);
				
					
					
					
					CapNhatBangKhachHang();
					XoaThongTinKhachHang();
					ThietLapCoTheTuyChinh();
					txtTenKhachHang.requestFocus();
					btnThemKhachHang.setText("Lưu");
					btnSuaThongTin.setText("Hủy");
				} else if (btnThemKhachHang.getText().equals("Lưu")) {

					if (txtTenKhachHang.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Tên khách hàng không được để trống");
						txtTenKhachHang.requestFocus();
						return;
					}
					if (!txtTenKhachHang.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(([',. -][a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")){
						JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ");
						txtTenKhachHang.setText("");
						txtTenKhachHang.requestFocus();
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
					for (KHACHHANG khachHang : dao_KhachHang.getalltbKhachHang()) {
						if (khachHang.getCMND().equals(txtCMND.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng Số CMND với một khách hàng khách. Vui lòng nhập lại ");
							txtCMND.setText("");
							txtCMND.requestFocus();
							return;
						}
					}
					if (txtSoDienThoai.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
						return;
					}
					if(!txtSoDienThoai.getText().matches("^(09|01|02|03|04|05|06|07|08)+([0-9]{8})$")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
						txtSoDienThoai.setText("");
						txtSoDienThoai.requestFocus();
						return;
					}
					for (KHACHHANG khachHang : dao_KhachHang.getalltbKhachHang()) {
						if (khachHang.getSDT().equals(txtSoDienThoai.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng số điện thoại với một khách hàng khách. Vui lòng nhập lại ");
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
					String maKhachHang = autoMaKhachHang();
					 dao_KhachHang.themKhachHang(maKhachHang, txtTenKhachHang.getText(),
					 txtCMND.getText(), txtSoDienThoai.getText(), gioiTinh);
					JOptionPane.showMessageDialog(null, "Thêm Khách Hàng Thành Công");
					CapNhatBangKhachHang();
					btnSuaThongTin.setEnabled(false);
					ThietLapKhongTheTuyChinh();
					XoaThongTinKhachHang();
					btnThemKhachHang.setText("Thêm Khách Hàng");
					btnSuaThongTin.setText("Sửa Khách Hàng");
					SuKienXoaTextTimKiem();

				} else if (btnThemKhachHang.getText().equals("Cập Nhật")) {

					if (txtTenKhachHang.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Tên khách hàng không được để trống");
						return;
					}
					if (!txtTenKhachHang.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(([',. -][a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")){
						JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ");
						txtTenKhachHang.setText("");
						txtTenKhachHang.requestFocus();
						return;
					}
					if (txtCMND.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Số CMND không được để trống");
						return;
					}
					if(!txtCMND.getText().matches("^([0-9]{9})$")) {
						JOptionPane.showMessageDialog(null, "Số cmnd không hợp lệ");
						txtCMND.setText("");
						txtCMND.requestFocus();
						return;
					}
					List<KHACHHANG> listKhachHang = dao_KhachHang.getalltbKhachHang();

					for (int i = 0; i < listKhachHang.size(); i++) {
						if (listKhachHang.get(i).getMaKH().equals(txtMaKhachHang.getText())) {
							listKhachHang.remove(i);
						}
					}

					for (KHACHHANG khachHang : listKhachHang) {
						if (khachHang.getCMND().equals(txtCMND.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng Số CMND với một khách hàng khách. Vui lòng nhập lại ");
							txtCMND.setText("");
							txtCMND.requestFocus();
							return;
						}
					}
					if (txtSoDienThoai.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
						return;
					}
					if(!txtSoDienThoai.getText().matches("^(09|01|02|03|04|05|06|07|08)+([0-9]{8})$")) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
						txtSoDienThoai.setText("");
						txtSoDienThoai.requestFocus();
						return;
					}
					for (KHACHHANG khachHang : listKhachHang) {
						if (khachHang.getSDT().equals(txtSoDienThoai.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"Trùng số điện thoại với một khách hàng khách. Vui lòng nhập lại ");
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
					// String maKhachHang = autoMaKhachHang();
					// dao_KhachHang.themKhachHang(maKhachHang, txtTenKhachHang.getText(),
					// txtCMND.getText(), txtSoDienThoai.getText(), gioiTinh);
					dao_KhachHang.upDateCustomer(txtMaKhachHang.getText(), txtTenKhachHang.getText(), txtCMND.getText(), txtSoDienThoai.getText(), gioiTinh);
					JOptionPane.showMessageDialog(null, "Cập nhật Khách Hàng Thành Công");
					CapNhatBangKhachHang();
					btnSuaThongTin.setEnabled(false);
					ThietLapKhongTheTuyChinh();
					XoaThongTinKhachHang();
					btnThemKhachHang.setText("Thêm Khách Hàng");
					btnSuaThongTin.setText("Sửa Khách Hàng");
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
					btnThemKhachHang.setText("Thêm Khách Hàng");
					btnSuaThongTin.setText("Sửa Thông Tin");
					btnSuaThongTin.setEnabled(false);

					XoaThongTinKhachHang();
					ThietLapKhongTheTuyChinh();
					SuKienXoaTextTimKiem();
					CapNhatBangKhachHang();
				} else if (btnSuaThongTin.getText().equals("Sửa Thông Tin")) {
					btnThemKhachHang.setText("Cập Nhật");
					btnSuaThongTin.setText("Hủy");
					ThietLapCoTheTuyChinh();
					txtTenKhachHang.requestFocus();
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
				XoaThongTinKhachHang();
				DefaultTableModel dm = (DefaultTableModel) modelKhachHang;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (KHACHHANG khachHang : dao_KhachHang.getalltbKhachHang()) {
					if (khachHang.getMaKH().toUpperCase().contains(txtTimMa.getText().toUpperCase())) {
						if (khachHang.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						modelKhachHang.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(),
								khachHang.getCMND(), khachHang.getSDT(), gioiTinh });
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemKhachHang.setText("Thêm Khách Hàng");
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
				XoaThongTinKhachHang();
				DefaultTableModel dm = (DefaultTableModel) modelKhachHang;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (KHACHHANG khachHang : dao_KhachHang.getalltbKhachHang()) {
					if (khachHang.getSDT().contains(txtTimSDT.getText())) {
						if (khachHang.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						modelKhachHang.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(),
								khachHang.getCMND(), khachHang.getSDT(), gioiTinh });
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemKhachHang.setText("Thêm Khách Hàng");
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
				XoaThongTinKhachHang();
				DefaultTableModel dm = (DefaultTableModel) modelKhachHang;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (KHACHHANG khachHang : dao_KhachHang.getalltbKhachHang()) {
					if (khachHang.getCMND().contains(txtTimCMND.getText())) {
						if (khachHang.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						modelKhachHang.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(),
								khachHang.getCMND(), khachHang.getSDT(), gioiTinh });
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemKhachHang.setText("Thêm Khách Hàng");
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
				XoaThongTinKhachHang();
				DefaultTableModel dm = (DefaultTableModel) modelKhachHang;
				dm.setRowCount(0);
				String gioiTinh = "";
				for (KHACHHANG khachHang : dao_KhachHang.getalltbKhachHang()) {
					if (khachHang.getTenKH().toUpperCase().contains(txtTimTen.getText().toUpperCase())) {
						if (khachHang.isGioiTinh()) {
							gioiTinh = "Nam";
						} else
							gioiTinh = "Nữ";
						modelKhachHang.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(),
								khachHang.getCMND(), khachHang.getSDT(), gioiTinh });
					}
				}
				btnSuaThongTin.setEnabled(false);
				btnSuaThongTin.setText("Sửa Thông Tin");
				btnThemKhachHang.setText("Thêm Khách Hàng");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void XoaThongTinKhachHang() {
		txtTenKhachHang.setText("");
		txtMaKhachHang.setText("");
		txtCMND.setText("");
		txtSoDienThoai.setText("");
		rdbtnNam.setSelected(false);
		rdbtnNu.setSelected(false);
		btnGroup.clearSelection();
	}

	private void ThietLapCoTheTuyChinh() {
		txtTenKhachHang.setEditable(true);
		txtSoDienThoai.setEditable(true);
		txtCMND.setEditable(true);
		rdbtnNam.setEnabled(true);
		rdbtnNu.setEnabled(true);
	}

	private void ThietLapKhongTheTuyChinh() {
		txtMaKhachHang.setEditable(false);
		txtTenKhachHang.setEditable(false);
		txtSoDienThoai.setEditable(false);
		txtCMND.setEditable(false);
		rdbtnNam.setEnabled(false);
		rdbtnNu.setEnabled(false);
	}

	private void HienThiThongTin(String maNhanVien, String tenNhanVien, String cmnd, String sdt, String gioiTinh) {
		txtMaKhachHang.setText(maNhanVien);
		txtTenKhachHang.setText(tenNhanVien);
		txtSoDienThoai.setText(sdt);
		txtCMND.setText(cmnd);
		if (gioiTinh.toUpperCase().equals("NAM")) {
			rdbtnNam.setSelected(true);
		} else
			rdbtnNu.setSelected(true);
	}

	private void SuKienTableKhachHang() {
		// TODO Auto-generated method stub
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row != -1)
					HienThiThongTin(modelKhachHang.getValueAt(row, 0).toString(),
							modelKhachHang.getValueAt(row, 1).toString(), modelKhachHang.getValueAt(row, 2).toString(),
							modelKhachHang.getValueAt(row, 3).toString(), modelKhachHang.getValueAt(row, 4).toString());
				
				ThietLapKhongTheTuyChinh();
				btnSuaThongTin.setEnabled(true);
				btnThemKhachHang.setText("Thêm Khách Hàng");
				btnSuaThongTin.setText("Sửa Thông Tin");
			}
		});
	}

	private void CapNhatBangKhachHang() {
		// TODO Auto-generated method stub
		String gioiTinh = "";
		DefaultTableModel dm = (DefaultTableModel) modelKhachHang;
		dm.setRowCount(0);
		for (KHACHHANG khachHang : dao_KhachHang.getalltbKhachHang()) {
			if (khachHang.isGioiTinh()) {
				gioiTinh = "Nam";
			} else
				gioiTinh = "Nữ";
			modelKhachHang.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getCMND(),
					khachHang.getSDT(), gioiTinh });
		}
		
	}
}
