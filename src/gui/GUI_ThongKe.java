package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import javax.swing.JLabel;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.components.JSpinField;

import ConnectDB.ConnectDB;
import dao.DAO_CTHDDV;
import dao.DAO_CTHDP;
import dao.DAO_DICHVU;
import dao.DAO_HDDICHVU;
import dao.DAO_HDPHONG;
import dao.DAO_KHACHHANG;
import dao.DAO_LOAIPHONG;
import dao.DAO_PHONG;
import dao.DAO_THONGKE;
import entity.CTHDDV;
import entity.CTHDPHONG;
import entity.DICHVU;
import entity.HOADONDICHVU;
import entity.HOADONPHONG;
import entity.KHACHHANG;
import entity.LOAIPHONG;
import entity.PHONG;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class GUI_ThongKe extends JPanel {
	private JTable tablePhong;
	private JTable tableDichVu;
	private JTable tableKHTiemNang;
	private JTable tablePNhieuNhat;
	private JTable tableDVNhieuNhat;
	private JTextField txtTongTienPhong;
	private JTextField txtTienDichVu;
	private DefaultTableModel modelPhong;
	private DefaultTableModel modelDichVu;
	private DefaultTableModel modelKHTiemNang;
	private DefaultTableModel modelDVNhieuNhat;
	private DefaultTableModel modelLPNhieuNhat;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JButton btnTimKiem;
	private DAO_HDDICHVU dao_hdDichvu = new DAO_HDDICHVU();
	private DAO_HDPHONG dao_hdPhong = new DAO_HDPHONG();
	private DAO_CTHDDV dao_cthdDichvu = new DAO_CTHDDV();
	private DAO_CTHDP dao_cthdPhong = new DAO_CTHDP();
	private DAO_DICHVU dao_dichVu = new DAO_DICHVU();
	private DAO_PHONG dao_Phong = new DAO_PHONG();
	private DAO_LOAIPHONG dao_loaiPhong = new DAO_LOAIPHONG();
	private DAO_KHACHHANG dao_khachHang = new DAO_KHACHHANG();
	private JTextField txtTongHDPhong;
	private JTextField txtTongHDDVu;
	private DAO_THONGKE dao_thongKe = new DAO_THONGKE();

	/**
	 * Create the panel.
	 * 
	 * @throws ClassNotFoundException
	 */
	public GUI_ThongKe() throws ClassNotFoundException {
		setBackground(new Color(204, 153, 102));
		setLayout(null);
		setSize(1195, 635);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 204));
		panel.setBounds(350, 13, 493, 46);
		add(panel);
		panel.setLayout(null);

		monthChooser = new JMonthChooser();
		monthChooser.setBounds(58, 13, 148, 22);
		panel.add(monthChooser);

		yearChooser = new JYearChooser();
		yearChooser.setBounds(289, 13, 67, 20);
		panel.add(yearChooser);

		JLabel lblNewLabel = new JLabel("Năm");
		lblNewLabel.setBounds(257, 19, 33, 16);
		panel.add(lblNewLabel);

		JLabel lblThng = new JLabel("Tháng");
		lblThng.setBounds(12, 19, 56, 16);
		panel.add(lblThng);

		btnTimKiem = new JButton("Thống Kê");
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setBackground(new Color(51, 153, 102));
		btnTimKiem.setBounds(381, 13, 97, 25);
		panel.add(btnTimKiem);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 102, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh S\u00E1ch H\u00F3a \u0110\u01A1n Ph\u00F2ng", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(12, 72, 583, 326);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 36, 559, 247);
		panel_1.add(scrollPane);

		tablePhong = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tablePhong.setModel(modelPhong = new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 nh\u00E2n vi\u00EAn",
						"M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng" }));
		scrollPane.setViewportView(tablePhong);

		txtTongHDPhong = new JTextField();
		txtTongHDPhong.setBounds(173, 296, 79, 23);
		panel_1.add(txtTongHDPhong);
		txtTongHDPhong.setColumns(10);

		JLabel lblTngHan = new JLabel("Tổng số lượng hóa đơn");
		lblTngHan.setBounds(22, 299, 141, 16);
		panel_1.add(lblTngHan);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(102, 102, 153));
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh S\u00E1ch H\u00F3a \u0110\u01A1n D\u1ECBch V\u1EE5", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_3.setBounds(607, 72, 576, 322);
		add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 36, 552, 247);
		panel_3.add(scrollPane_1);

		tableDichVu = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tableDichVu.setModel(
				modelDichVu = new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n",
						"M\u00E3 nh\u00E2n vi\u00EAn", "M\u00E3 kh\u00E1ch h\u00E0ng", "Th\u1EDDi Gian" }));
		scrollPane_1.setViewportView(tableDichVu);

		JLabel label = new JLabel("Tổng số lượng hóa đơn");
		label.setBounds(12, 293, 141, 16);
		panel_3.add(label);

		txtTongHDDVu = new JTextField();
		txtTongHDDVu.setColumns(10);
		txtTongHDDVu.setBounds(165, 290, 79, 23);
		panel_3.add(txtTongHDDVu);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 153, 204));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Kh\u00E1ch H\u00E0ng Ti\u1EC1m N\u0103ng ", TitledBorder.LEFT, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_2.setBounds(12, 456, 380, 166);
		add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 24, 356, 129);
		panel_2.add(scrollPane_2);

		tableKHTiemNang = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tableKHTiemNang.setModel(modelKHTiemNang = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã khách hàng", "Tên khách hàng", "Lượng thuê phòng" }));
		scrollPane_2.setViewportView(tableKHTiemNang);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(153, 153, 204));
		panel_4.setBorder(
				new TitledBorder(null, "Lo\u1EA1i Ph\u00F2ng \u0110\u01B0\u1EE3c Thu\u00EA Nhi\u1EC1u Nh\u1EA5t",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(404, 456, 392, 166);
		add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 28, 368, 125);
		panel_4.add(scrollPane_3);

		tablePNhieuNhat = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tablePNhieuNhat.setModel(modelLPNhieuNhat = new DefaultTableModel(new Object[][] {

		}, new String[] { "M\u00E3 lo\u1EA1i ph\u00F2ng", "\u0110\u01A1n gi\u00E1", "L\u01B0\u1EE3ng \u0111\u1EB7t" }));
		scrollPane_3.setViewportView(tablePNhieuNhat);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(102, 204, 255));
		panel_5.setBorder(
				new TitledBorder(null, "D\u1ECBch V\u1EE5 \u0110\u01B0\u1EE3c S\u1EED D\u1EE5ng Nhi\u1EC1u Nh\u1EA5t",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(808, 456, 375, 166);
		add(panel_5);
		panel_5.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 29, 351, 124);
		panel_5.add(scrollPane_4);

		tableDVNhieuNhat = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tableDVNhieuNhat.setModel(modelDVNhieuNhat = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã dịch vụ", "Tên dịch vụ", "Lượt mua" }));
		scrollPane_4.setViewportView(tableDVNhieuNhat);

		txtTongTienPhong = new JTextField();
		txtTongTienPhong.setBounds(450, 407, 145, 25);
		add(txtTongTienPhong);
		txtTongTienPhong.setColumns(10);

		JLabel lblTngDoanhThu = new JLabel("Tổng Doanh Thu Tiền Phòng");
		lblTngDoanhThu.setBounds(275, 411, 163, 16);
		add(lblTngDoanhThu);

		txtTienDichVu = new JTextField();
		txtTienDichVu.setColumns(10);
		txtTienDichVu.setBounds(1038, 407, 145, 25);
		add(txtTienDichVu);

		JLabel lblTngDoanhThu_1 = new JLabel("Tổng Doanh Thu Tiền Dịch Vụ");
		lblTngDoanhThu_1.setBounds(848, 411, 178, 16);
		add(lblTngDoanhThu_1);
		ConnectDB.getInstance().connect();

		SuKienTimKiem();
	}

	private void CapNhatBangKhachHangTiemNang(String nam, String thang) {
		DefaultTableModel dm = (DefaultTableModel) modelKHTiemNang;
		dm.setRowCount(0);
		Map<String, Integer> map = dao_thongKe.getKhachHangTiemNang(nam, thang);
		for (Map.Entry<String, Integer> value : map.entrySet()) {
			for (KHACHHANG khachHang : dao_khachHang.getalltbKhachHang()) {
				if (khachHang.getMaKH().equals(value.getKey())) {
					modelKHTiemNang
							.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(), value.getValue() });
				}
			}
		}
	}

	private void CapNhatBangLoaiPhongNhieuNhat(String nam, String thang) {
		DefaultTableModel dm = (DefaultTableModel) modelLPNhieuNhat;
		dm.setRowCount(0);
		Map<String, Integer> map = dao_thongKe.getLoaiPhongPhoBien(nam, thang);
		for (Map.Entry<String, Integer> value : map.entrySet()) {
			for (LOAIPHONG loaiPhong : dao_loaiPhong.getalltbLoaiPhong()) {
				if (loaiPhong.getMaLoaiPhong().equals(value.getKey())) {
					System.out.println(value.getValue());
					modelLPNhieuNhat.addRow(
							new Object[] { loaiPhong.getMaLoaiPhong(), loaiPhong.getDonGia(), value.getValue() });
				}
			}
		}
	}

	private void CapNhatBangDichVuNhieuNhat(String nam, String thang) {
		DefaultTableModel dm = (DefaultTableModel) modelDVNhieuNhat;
		dm.setRowCount(0);
		Map<String, Integer> map = dao_thongKe.getDichVuPhoBien(nam, thang);
		for (Map.Entry<String, Integer> value : map.entrySet()) {
			for (DICHVU dichVu : dao_dichVu.getalltbDichVu()) {
				if (dichVu.getMaDV().equals(value.getKey())) {
					modelDVNhieuNhat.addRow(new Object[] { dichVu.getMaDV(), dichVu.getTenDV(), value.getValue() });
				}
			}
		}
	}

	private void SuKienTimKiem() {
		// TODO Auto-generated method stub
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String mymonth;
				SimpleDateFormat sdfm = new SimpleDateFormat("MM");

				// mymonth = sdfm.format(((JTextField)combom.getEditor()).getText());
//				System.out.println(monthChooser.getMonth()+1);
//				System.out.println(yearChooser.getYear());
				int thang = monthChooser.getMonth() + 1;
				int nam = yearChooser.getYear();
				String stringThang = "";
				String stringNam = "";
				if (thang < 10) {
					stringThang = "0" + String.valueOf(thang);
				} else {
					stringThang = String.valueOf(thang);
				}
				if (nam < 10) {
					stringNam = "0" + String.valueOf(nam);
				} else {
					stringNam = String.valueOf(nam);
				}
				DefaultTableModel dm1 = (DefaultTableModel) modelDichVu;
				dm1.setRowCount(0);
				DefaultTableModel dm2 = (DefaultTableModel) modelPhong;
				dm2.setRowCount(0);

				CapNhatBangKhachHangTiemNang(stringNam, stringThang);
				CapNhatBangDichVuNhieuNhat(stringNam, stringThang);
				CapNhatBangLoaiPhongNhieuNhat(stringNam, stringThang);
				double tongTienPhong = 0;
				int soLuongP = 0;
				for (HOADONPHONG hdPhong : dao_hdPhong.getalltbHDP()) {
					// System.out.println(hdPhong.getMaHDP().substring(3, 7));
					// System.out.println(hdPhong.getMaHDP().substring(8, 10));
					
					if (Integer.parseInt(hdPhong.getMaHDP().substring(3, 7)) == nam
							&& Integer.parseInt(hdPhong.getMaHDP().substring(8, 10)) == thang) {
						int tam = 0;
						int khoangCach = 1;
						for (CTHDPHONG cthdPhong : dao_cthdPhong.getalltbHDP()) {
							if (cthdPhong.getHdP().getMaHDP().equals(hdPhong.getMaHDP())) {
								if (cthdPhong.getTinhTrang().equals("TRẢ PHÒNG")) {
									tam = 1;
									LocalDateTime ngayDatPhong = cthdPhong.getNgayDat();
									LocalDateTime ngayHienTai = LocalDateTime.now();
									Duration dur = Duration.between(ngayDatPhong, LocalDateTime.now());
									khoangCach =Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(cthdPhong.getNgayDat().toLocalDate(),LocalDate.now())))+1;
									for (PHONG phong : dao_Phong.getAlltbPhong()) {
										if (phong.getSoPhong().equals(cthdPhong.getPhong().getSoPhong())) {
											for (LOAIPHONG loaiPhong : dao_loaiPhong.getalltbLoaiPhong()) {
												tongTienPhong += loaiPhong.getDonGia()*khoangCach;
											}
										}
									}
								}
							}
						}
						
						if (tam == 1) {
							for (KHACHHANG khachHang : dao_khachHang.getalltbKhachHang()) {
								if (khachHang.getMaKH().equals(hdPhong.getKhachHang().getMaKH())) {
									modelPhong
											.addRow(new Object[] { hdPhong.getMaHDP(), hdPhong.getNhanVien().getMaNV(),
													hdPhong.getKhachHang().getMaKH(), khachHang.getTenKH() });
									soLuongP++;
								}
							}

						}
						
					}
				}
				txtTongHDPhong.setText(String.valueOf(soLuongP));
				txtTongTienPhong.setText(String.valueOf(tongTienPhong));
				double tongTienDichVu = 0;
				int soLuongDV = 0;
				for (HOADONDICHVU hdDichVu : dao_hdDichvu.getalltbHDDV()) {
					// System.out.println(hdDichVu.getMaHDDV().substring(5, 9));
					// System.out.println(hdDichVu.getMaHDDV().substring(10, 12));
					
					if (hdDichVu.getTinhtrang().equals("ĐÃ THANH TOÁN")
							&& Integer.parseInt(hdDichVu.getMaHDDV().substring(5, 9)) == nam
							&& Integer.parseInt(hdDichVu.getMaHDDV().substring(10, 12)) == thang) {
						modelDichVu.addRow(new Object[] { hdDichVu.getMaHDDV(), hdDichVu.getNhanVien().getMaNV(),
								hdDichVu.getKhachHang().getMaKH(), hdDichVu.getLocaldate() });
						for (CTHDDV cthddv : dao_cthdDichvu.getalltbCTHDDV()) {
							if (cthddv.getHddv().getMaHDDV().equals(hdDichVu.getMaHDDV())) {
								for (DICHVU dichVu : dao_dichVu.getalltbDichVu()) {
									if (dichVu.getMaDV().equals(cthddv.getDichVu().getMaDV())) {
										tongTienDichVu += dichVu.getGiaDV() * cthddv.getSoluong();
									}
								}
							}
						}
						soLuongDV++;
					}

				}
				txtTongHDDVu.setText(String.valueOf(soLuongDV));
				txtTienDichVu.setText(String.valueOf(tongTienDichVu));
			}
		});
	}
}
