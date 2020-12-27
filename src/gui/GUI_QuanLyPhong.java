package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import dao.DAO_KHACHHANG;
import dao.DAO_LOAIPHONG;
import dao.DAO_PHONG;

import entity.LOAIPHONG;
import entity.PHONG;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class GUI_QuanLyPhong extends JPanel {
	private DefaultTableModel modelKhachHang;
	private DAO_KHACHHANG dao_KhachHang = new DAO_KHACHHANG();
	private ButtonGroup btnGroup;
	private JTable tablePhong;
	private JTextField txtSoPhong;
	private JTextField txtTimKiemSoPhong;
	private JTable tableLoaiPhong;
	private JTextField txtLoaiPhong;
	private JTextField txtGiaPhong;
	private JTextField txtSoLuong;
	private JTextField txtTimKiemLoaiPhong;
	private DefaultTableModel modelPhong;
	private DefaultTableModel modelLoaiPhong;
	private JButton btnChonHinh;
	private JComboBox<String> cbbLoaiPhong;
	private JComboBox<String> cbbTinhTrang;
	private JButton btnThemPhong;
	private JButton btnSuaPhong;
	private JButton btnThemLoaiPhong;
	private JButton btnSuaLoaiPhong;
	private DAO_PHONG dao_phong = new DAO_PHONG();
	private DAO_LOAIPHONG dao_loaiPhong = new DAO_LOAIPHONG();
	private JLabel lblHinhAnh;
	private String filename = null;
	private byte[] imagePhong = null;

	/**
	 * Create the panel.
	 */
	public GUI_QuanLyPhong() {
		setBackground(new Color(0, 153, 204));
		setLayout(null);
		setSize(1195, 635);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 204, 204));
		panel.setBounds(12, 13, 399, 609);
		add(panel);
		panel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng Tin Ph\u00F2ng",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(12, 13, 375, 164);
		panel.add(panel_3);
		panel_3.setLayout(null);

		txtSoPhong = new JTextField();
		txtSoPhong.setBounds(111, 32, 252, 30);
		panel_3.add(txtSoPhong);
		txtSoPhong.setColumns(10);

		JLabel lblMPhng = new JLabel("Số Phòng");
		lblMPhng.setBounds(12, 39, 65, 16);
		panel_3.add(lblMPhng);

		cbbLoaiPhong = new JComboBox<String>();
		cbbLoaiPhong.setBounds(111, 75, 252, 30);
		panel_3.add(cbbLoaiPhong);

		cbbTinhTrang = new JComboBox();

		cbbTinhTrang.setForeground(Color.BLACK);
		cbbTinhTrang.setBounds(111, 118, 252, 30);
		panel_3.add(cbbTinhTrang);

		JLabel lblLoiPhng = new JLabel("Loại Phòng");
		lblLoiPhng.setBounds(12, 82, 65, 16);
		panel_3.add(lblLoiPhng);

		JLabel lblTnhTrang = new JLabel("Tình Trạng");
		lblTnhTrang.setBounds(12, 125, 65, 16);
		panel_3.add(lblTnhTrang);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Danh S\u00E1ch Ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_4.setBounds(12, 240, 375, 280);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 29, 351, 238);
		panel_4.add(scrollPane);

		tablePhong = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tablePhong.setModel(modelPhong = new DefaultTableModel(new Object[][] {,},
				new String[] { "S\u1ED1 ph\u00F2ng", "Lo\u1EA1i ph\u00F2ng", "T\u00ECnh tr\u1EA1ng" }));
		scrollPane.setViewportView(tablePhong);

		txtTimKiemSoPhong = new JTextField();
		txtTimKiemSoPhong.setBounds(228, 199, 147, 28);
		panel.add(txtTimKiemSoPhong);
		txtTimKiemSoPhong.setColumns(10);

		JLabel lblTmKimS = new JLabel("Tìm Kiếm Số Phòng");
		lblTmKimS.setBounds(102, 205, 112, 16);
		panel.add(lblTmKimS);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 533, 375, 63);
		panel.add(panel_5);
		panel_5.setLayout(null);

		btnThemPhong = new JButton("Thêm Phòng");
		btnThemPhong.setForeground(Color.WHITE);
		btnThemPhong.setBackground(new Color(51, 153, 51));
		btnThemPhong.setBounds(54, 13, 123, 37);
		panel_5.add(btnThemPhong);

		btnSuaPhong = new JButton("Sửa Phòng");
		btnSuaPhong.setForeground(Color.WHITE);
		btnSuaPhong.setBackground(new Color(51, 102, 204));
		btnSuaPhong.setBounds(206, 13, 123, 37);
		panel_5.add(btnSuaPhong);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 153));
		panel_1.setBounds(423, 13, 399, 609);
		add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh S\u00E1ch Lo\u1EA1i Ph\u00F2ng",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(12, 240, 375, 280);
		panel_1.add(panel_6);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 29, 351, 238);
		panel_6.add(scrollPane_2);
		
		tableLoaiPhong = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tableLoaiPhong.setModel(modelLoaiPhong = new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 lo\u1EA1i ph\u00F2ng", "S\u1EE9c ch\u1EE9a", "\u0110\u01A1n gi\u00E1" }));
		scrollPane_2.setViewportView(tableLoaiPhong);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(12, 533, 375, 63);
		panel_1.add(panel_7);

		btnThemLoaiPhong = new JButton("Thêm Loại Phòng");
		btnThemLoaiPhong.setBackground(new Color(51, 153, 51));
		btnThemLoaiPhong.setForeground(Color.WHITE);
		btnThemLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemLoaiPhong.setBounds(32, 13, 140, 37);
		panel_7.add(btnThemLoaiPhong);

		btnSuaLoaiPhong = new JButton("Sửa Loại Phòng");
		btnSuaLoaiPhong.setForeground(Color.WHITE);
		btnSuaLoaiPhong.setBackground(new Color(102, 102, 255));
		btnSuaLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSuaLoaiPhong.setBounds(206, 13, 140, 37);
		panel_7.add(btnSuaLoaiPhong);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng Tin Lo\u1EA1i Ph\u00F2ng",
						TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_8.setBounds(12, 13, 375, 164);
		panel_1.add(panel_8);

		txtLoaiPhong = new JTextField();
		txtLoaiPhong.setColumns(10);
		txtLoaiPhong.setBounds(111, 32, 252, 30);
		panel_8.add(txtLoaiPhong);

		JLabel lblLoiPhng_1 = new JLabel("Loại Phòng");
		lblLoiPhng_1.setBounds(12, 39, 65, 16);
		panel_8.add(lblLoiPhng_1);

		JLabel lblGi = new JLabel("Giá Phòng");
		lblGi.setBounds(12, 125, 65, 16);
		panel_8.add(lblGi);

		txtGiaPhong = new JTextField();
		txtGiaPhong.setColumns(10);
		txtGiaPhong.setBounds(111, 118, 252, 30);
		panel_8.add(txtGiaPhong);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(111, 75, 252, 30);
		panel_8.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		JLabel lblSLng = new JLabel("Sức chứa");
		lblSLng.setBounds(12, 82, 65, 16);
		panel_8.add(lblSLng);

		JLabel lblTmKimLoi = new JLabel("Tìm Kiếm Loại Phòng");
		lblTmKimLoi.setBounds(90, 211, 120, 16);
		panel_1.add(lblTmKimLoi);

		txtTimKiemLoaiPhong = new JTextField();
		txtTimKiemLoaiPhong.setColumns(10);
		txtTimKiemLoaiPhong.setBounds(226, 199, 147, 28);
		panel_1.add(txtTimKiemLoaiPhong);

		btnChonHinh = new JButton("Chọn Hình");
		btnChonHinh.setForeground(Color.WHITE);
		btnChonHinh.setBackground(new Color(153, 51, 102));
		btnChonHinh.setBounds(834, 59, 121, 25);
		add(btnChonHinh);
		btnGroup = new ButtonGroup();

		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbbTinhTrang.addItem("TRỐNG");
		cbbTinhTrang.addItem("KHÁCH ĐOÀN");
		cbbTinhTrang.addItem("KHÁCH LẺ");
		cbbTinhTrang.addItem("SỬA CHỮA");

		SuKienCapNhatBangLoaiPhong();
		SuKienCapNhatBangPhong();
		SuKienBangPhong();
		SuKienBangLoaiPhong();
		SuKienTimKiemSoPhong();
		SuKienTimKiemLoaiPhong();
		SuKienThemSuaPhong();
		SuKienThemSuaLoaiPhong();
		KhongCoTheChinhSuaThongTinLoaiPhong();
		SuKienThemHinhAnh();

		txtSoPhong.setEditable(false);
		cbbTinhTrang.setEnabled(false);
		btnSuaPhong.setEnabled(false);
		btnSuaLoaiPhong.setEnabled(false);
		cbbLoaiPhong.setSelectedIndex(-1);
		cbbTinhTrang.setSelectedIndex(-1);
		btnChonHinh.setEnabled(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "H\u00ECnh \u1EA2nh Lo\u1EA1i Ph\u00F2ng", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(834, 97, 349, 525);
		add(panel_2);
		panel_2.setLayout(null);
		
				lblHinhAnh = new JLabel("");
				lblHinhAnh.setBounds(12, 35, 325, 477);
				panel_2.add(lblHinhAnh);
	}

	private void SuKienThemHinhAnh() {
		// TODO Auto-generated method stub
		btnChonHinh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					JFileChooser ch = new JFileChooser("src/img/dichvuGim.jpg");
					ch.showOpenDialog(null);
					File f = ch.getSelectedFile();
					filename = f.getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage()
							.getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
					lblHinhAnh.setIcon(imageIcon);

					File image = new File(filename);
					FileInputStream fis = new FileInputStream(image);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for (int readNum; (readNum = fis.read(buf)) != -1;) {
						bos.write(buf, 0, readNum);
					}
					imagePhong = bos.toByteArray();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Ảnh");
				}
			}
		});
	}

	private void XoaThongTinLoaiPhong() {
		txtLoaiPhong.setText("");
		txtSoLuong.setText("");
		txtGiaPhong.setText("");
	}

	private void CoTheChinhSuaThongTinLoaiPhong() {
		txtLoaiPhong.requestFocus();
		txtLoaiPhong.setEditable(true);
		txtSoLuong.setEditable(true);
		txtGiaPhong.setEditable(true);
	}

	private void KhongCoTheChinhSuaThongTinLoaiPhong() {
		txtLoaiPhong.setEditable(false);
		txtSoLuong.setEditable(false);
		txtGiaPhong.setEditable(false);
	}

	private void SuKienThemSuaLoaiPhong() {
		// TODO Auto-generated method stub
		btnThemLoaiPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (btnThemLoaiPhong.getText().equals("Thêm Loại Phòng")) {
					imagePhong = null;
					lblHinhAnh.setIcon(null);
					btnChonHinh.setEnabled(true);
					btnSuaLoaiPhong.setEnabled(true);
					btnThemLoaiPhong.setText("Lưu");
					btnSuaLoaiPhong.setText("Hủy");
					XoaThongTinLoaiPhong();
					CoTheChinhSuaThongTinLoaiPhong();

				} else if (btnThemLoaiPhong.getText().equals("Lưu")) {

					if (txtLoaiPhong.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên loại phòng");
						txtLoaiPhong.requestFocus();
						return;
					}

					for (LOAIPHONG loaiPhong : dao_loaiPhong.getalltbLoaiPhong()) {
						if (loaiPhong.getMaLoaiPhong().equals(txtLoaiPhong.getText())) {
							JOptionPane.showMessageDialog(null, "Tên loại phòng đã tồn tại. Vui lòng nhập tên khác");
							txtLoaiPhong.setText("");
							txtLoaiPhong.requestFocus();
							return;
						}
					}

					if (!txtSoLuong.getText().matches("\\d+")) {
						JOptionPane.showMessageDialog(null, "Sức chứa không hợp lệ. Vui lòng nhập chữ số tự nhiên");
						txtSoLuong.setText("");
						txtSoLuong.requestFocus();
						return;
					}
					if (!txtGiaPhong.getText().matches("\\d+(\\.\\d+)?")) {
						JOptionPane.showMessageDialog(null, "Giá phòng không hợp lệ. Vui lòng nhập chữ số");
						txtGiaPhong.setText("");
						txtGiaPhong.requestFocus();
						return;
					}
					int returnValue = 0;
					if (imagePhong == null) {
						returnValue = JOptionPane.showConfirmDialog(null, "Bạn muốn chọn hình ảnh cho loại phòng không",
								"Chọn hình ảnh", JOptionPane.YES_NO_OPTION);
						if (returnValue == JOptionPane.YES_OPTION) {
							return;
						} else if (returnValue == JOptionPane.NO_OPTION) {
							dao_loaiPhong.ThemLoaiPhong(txtLoaiPhong.getText().toUpperCase(),
									Integer.parseInt(txtSoLuong.getText()), imagePhong,
									Double.parseDouble(txtGiaPhong.getText()));
							JOptionPane.showMessageDialog(null, "Thêm loại phòng thành công");
							XoaThongTinLoaiPhong();
							SuKienCapNhatBangLoaiPhong();
							btnSuaLoaiPhong.setEnabled(false);
							btnThemLoaiPhong.setText("Thêm Loại Phòng");
							btnSuaLoaiPhong.setText("Sửa Loại Phòng");
							btnChonHinh.setEnabled(false);
							return;
						}
					}

					dao_loaiPhong.ThemLoaiPhong(txtLoaiPhong.getText().toUpperCase(),
							Integer.parseInt(txtSoLuong.getText()), imagePhong,
							Double.parseDouble(txtGiaPhong.getText()));
					// cbbLoaiPhong.addItem(txtLoaiPhong.getText());
					JOptionPane.showMessageDialog(null, "Thêm loại phòng thành công");
					XoaThongTinLoaiPhong();
					SuKienCapNhatBangLoaiPhong();
					btnSuaLoaiPhong.setEnabled(false);
					btnThemLoaiPhong.setText("Thêm Loại Phòng");
					btnSuaLoaiPhong.setText("Sửa Loại Phòng");
					btnChonHinh.setEnabled(false);

				} else if (btnThemLoaiPhong.getText().equals("Cập Nhật")) {

//					btnSuaLoaiPhong.setEnabled(false);
//					btnThemLoaiPhong.setText("Thêm Loại Phòng");
//					btnSuaLoaiPhong.setText("Sửa Loại Phòng");
//					
					if (!txtSoLuong.getText().matches("\\d+")) {
						JOptionPane.showMessageDialog(null, "Sức chứa không hợp lệ. Vui lòng nhập chữ số tự nhiên");
						txtSoLuong.setText("");
						txtSoLuong.requestFocus();
						return;
					}
					if (!txtGiaPhong.getText().matches("\\d+(\\.\\d+)?")) {
						JOptionPane.showMessageDialog(null, "Giá phòng không hợp lệ. Vui lòng nhập chữ số");
						txtGiaPhong.setText("");
						txtGiaPhong.requestFocus();
						return;
					}
					dao_loaiPhong.CapNhatLoaiPhong(txtLoaiPhong.getText(), Integer.parseInt(txtSoLuong.getText()),
							imagePhong, Double.parseDouble(txtGiaPhong.getText()));
					JOptionPane.showMessageDialog(null, "Cập Nhật Loại Phòng Thành Công");

					XoaThongTinLoaiPhong();
					SuKienCapNhatBangLoaiPhong();
					btnSuaLoaiPhong.setEnabled(false);
					btnThemLoaiPhong.setText("Thêm Loại Phòng");
					btnSuaLoaiPhong.setText("Sửa Loại Phòng");
					btnChonHinh.setEnabled(false);
				}
			}
		});
		btnSuaLoaiPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(btnSuaLoaiPhong.getText().equals("Sửa Loại Phòng"));
				if (btnSuaLoaiPhong.getText().equals("Sửa Loại Phòng")) {
				//	lblHinhAnh.setIcon(null);
					btnChonHinh.setEnabled(true);
					txtGiaPhong.setEditable(true);
					txtSoLuong.setEditable(true);

					btnThemLoaiPhong.setText("Cập Nhật");
					btnSuaLoaiPhong.setText("Hủy");
					txtGiaPhong.requestFocus();

				}

				else if (btnSuaLoaiPhong.getText().equals("Hủy")) {
					imagePhong = null;
					lblHinhAnh.setIcon(null);
					XoaThongTinLoaiPhong();
					KhongCoTheChinhSuaThongTinLoaiPhong();
					SuKienCapNhatBangLoaiPhong();
					btnSuaLoaiPhong.setEnabled(false);
					btnThemLoaiPhong.setText("Thêm Loại Phòng");
					btnSuaLoaiPhong.setText("Sửa Loại Phòng");
					btnChonHinh.setEnabled(false);
				}
			}
		});
	}

	private void SuKienCoTheSuaThongTinPhong() {
		txtSoPhong.setEditable(true);
		cbbLoaiPhong.setEnabled(true);
		cbbTinhTrang.setEnabled(false);
	}

	private void SuKienXoaTatCaThongTinPhong() {
		txtSoPhong.setText("");
		cbbLoaiPhong.setSelectedIndex(-1);
		cbbTinhTrang.setSelectedItem("TRỐNG");
	}

	private void SuKienThemSuaPhong() {
		// TODO Auto-generated method stub
		btnThemPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (btnThemPhong.getText().equals("Thêm Phòng")) {

					SuKienCoTheSuaThongTinPhong();
					SuKienXoaTatCaThongTinPhong();
					btnSuaPhong.setEnabled(true);

					txtSoPhong.requestFocus();
					btnThemPhong.setText("Lưu");
					btnSuaPhong.setText("Hủy");

				} else if (btnThemPhong.getText().equals("Lưu")) {

					if (txtSoPhong.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập số phòng");
						return;
					}
					for (PHONG phong : dao_phong.getAlltbPhong()) {
						if (phong.getSoPhong().toUpperCase().trim().equals(txtSoPhong.getText().trim().toUpperCase())) {
							JOptionPane.showMessageDialog(null, "Số phòng đã tồn tại ");
							txtSoPhong.setText("");
							txtSoPhong.requestFocus();
							return;
						}
					}
					if (cbbLoaiPhong.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại phòng");
						return;
					}
					dao_phong.ThemPhong(txtSoPhong.getText().trim().toUpperCase(),
							cbbLoaiPhong.getSelectedItem().toString(), cbbTinhTrang.getSelectedItem().toString());
					SuKienCapNhatBangPhong();
					JOptionPane.showMessageDialog(null, "Thêm Phòng mới thành công");
					btnThemPhong.setText("Thêm Phòng");
					btnSuaPhong.setText("Sửa Phòng");

				} else if (btnThemPhong.getText().equals("Cập Nhật")) {
					btnThemPhong.setText("Thêm Phòng");
					btnSuaPhong.setText("Sửa Phòng");

					dao_phong.CapNhatPhong(txtSoPhong.getText(), cbbLoaiPhong.getSelectedItem().toString(),
							cbbTinhTrang.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					SuKienCapNhatBangPhong();
					cbbTinhTrang.setEditable(false);
					btnThemPhong.setText("Thêm Phòng");
					btnSuaPhong.setText("Sửa Phòng");

				}
			}
		});
		btnSuaPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (btnSuaPhong.getText().equals("Sửa Phòng")) {

					if (cbbTinhTrang.getSelectedItem().equals("KHÁCH ĐOÀN")
							|| cbbTinhTrang.getSelectedItem().equals("KHÁCH LẺ")) {
						JOptionPane.showMessageDialog(null,
								"Phòng Hiện tại có khách hàng đang thuê. Không thể chỉnh sửa");
						return;
					}
					if (cbbTinhTrang.getSelectedItem().equals("TRỐNG")
							|| cbbTinhTrang.getSelectedItem().equals("SỬA CHỮA")) {
						cbbTinhTrang.removeItem("KHÁCH ĐOÀN");
						cbbTinhTrang.removeItem("KHÁCH LẺ");
						cbbTinhTrang.setEnabled(true);
					}
					btnThemPhong.setText("Cập Nhật");
					btnSuaPhong.setText("Hủy");
				} else if (btnSuaPhong.getText().equals("Hủy")) {

					if (cbbTinhTrang.getItemCount() == 2) {
						cbbTinhTrang.addItem("KHÁCH ĐOÀN");
						cbbTinhTrang.addItem("KHÁCH LẺ");
					}
					btnSuaPhong.setText("Sửa Phòng");
					btnThemPhong.setText("Thêm Phòng");
					txtSoPhong.setText("");
					cbbLoaiPhong.setSelectedIndex(-1);
					cbbTinhTrang.setEnabled(false);
					btnSuaPhong.setEnabled(false);
					SuKienCapNhatBangPhong();

				}
			}
		});
	}

	private void SuKienTimKiemLoaiPhong() {
		// TODO Auto-generated method stub
		txtTimKiemLoaiPhong.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				DefaultTableModel dm = (DefaultTableModel) modelLoaiPhong;
				dm.setRowCount(0);
				for (LOAIPHONG loaiPhong : dao_loaiPhong.getalltbLoaiPhong()) {
					if (loaiPhong.getMaLoaiPhong().toUpperCase()
							.contains(txtTimKiemLoaiPhong.getText().toUpperCase())) {
						modelLoaiPhong.addRow(new Object[] { loaiPhong.getMaLoaiPhong(), loaiPhong.getSoLuong(),
								loaiPhong.getDonGia() });
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void SuKienTimKiemSoPhong() {
		// TODO Auto-generated method stub
		txtTimKiemSoPhong.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				DefaultTableModel dm = (DefaultTableModel) modelPhong;
				dm.setRowCount(0);
				for (PHONG phong : dao_phong.getAlltbPhong()) {
					if (phong.getSoPhong().toUpperCase().contains(txtTimKiemSoPhong.getText().toUpperCase())) {
						modelPhong.addRow(new Object[] { phong.getSoPhong(), phong.getPhong().getMaLoaiPhong(),
								phong.getTinhTrang() });
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void SuKienBangLoaiPhong() {
		// TODO Auto-generated method stub
		tableLoaiPhong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				lblHinhAnh.setIcon(null);
				imagePhong = null;
				int row = tableLoaiPhong.getSelectedRow();
				if (row != -1) {

					txtLoaiPhong.setText(modelLoaiPhong.getValueAt(row, 0).toString());
					txtSoLuong.setText(modelLoaiPhong.getValueAt(row, 1).toString());
					txtGiaPhong.setText(modelLoaiPhong.getValueAt(row, 2).toString());

					try {
						byte[] img = dao_loaiPhong.getalltbLoaiPhong().get(row).getHinhAnh();
						if (img != null) {
							ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(
									lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
							lblHinhAnh.setIcon(imageIcon);lblHinhAnh.setText("");
						} else {
							lblHinhAnh.setIcon(null);
							lblHinhAnh.setText("                 Loại Phòng Này Hiện Chưa Có Hình Ảnh");
						}
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Hình Ảnh Chưa Được Thêm Vào");
					}
				}
				btnSuaLoaiPhong.setEnabled(true);
				KhongCoTheChinhSuaThongTinLoaiPhong();
				btnThemLoaiPhong.setText("Thêm Loại Phòng");
				btnSuaLoaiPhong.setText("Sửa Loại Phòng");
				for (LOAIPHONG loaiPhong : dao_loaiPhong.getalltbLoaiPhong()) {
					if (loaiPhong.getHinhAnh() != null) {
						// lblHinhAnh.setVisible(true);
					}
				}
				// lblHinhAnh.setVisible(false);
				
				btnChonHinh.setEnabled(false);
			}
		});
	}

	private void SuKienBangPhong() {
		// TODO Auto-generated method stub
		tablePhong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				int row = tablePhong.getSelectedRow();
				if (row != -1) {

					txtSoPhong.setText(modelPhong.getValueAt(row, 0).toString());
					cbbLoaiPhong.setSelectedItem(modelPhong.getValueAt(row, 1).toString());
					cbbTinhTrang.setSelectedItem(modelPhong.getValueAt(row, 2).toString());
				}
				if (cbbTinhTrang.getItemCount() == 2) {
					cbbTinhTrang.addItem("KHÁCH ĐOÀN");
					cbbTinhTrang.addItem("KHÁCH LẺ");
				}
				txtSoPhong.setEditable(false);
				cbbTinhTrang.setEditable(false);
				cbbLoaiPhong.setEditable(false);
				btnSuaPhong.setEnabled(true);
				btnThemPhong.setText("Thêm Phòng");
				btnSuaPhong.setText("Sửa Phòng");
				cbbTinhTrang.setEnabled(false);
			}
		});
	}

	private void SuKienCapNhatBangPhong() {
		// TODO Auto-generated method stub
		DefaultTableModel dm = (DefaultTableModel) modelPhong;
		dm.setRowCount(0);
		for (PHONG phong : dao_phong.getAlltbPhong()) {
			modelPhong.addRow(
					new Object[] { phong.getSoPhong(), phong.getPhong().getMaLoaiPhong(), phong.getTinhTrang() });
		}

	}

	private void SuKienCapNhatBangLoaiPhong() {
		// TODO Auto-generated method stub
		DefaultTableModel dm = (DefaultTableModel) modelLoaiPhong;
		dm.setRowCount(0);
		cbbLoaiPhong.removeAllItems();
		for (LOAIPHONG loaiPhong : dao_loaiPhong.getalltbLoaiPhong()) {
			cbbLoaiPhong.addItem(loaiPhong.getMaLoaiPhong());
			modelLoaiPhong
					.addRow(new Object[] { loaiPhong.getMaLoaiPhong(), loaiPhong.getSoLuong(), loaiPhong.getDonGia() });
		}
		cbbLoaiPhong.setSelectedIndex(-1);
	}
}
