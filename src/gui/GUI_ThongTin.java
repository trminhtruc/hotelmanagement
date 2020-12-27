package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ConnectDB.ConnectDB;
import dao.DAO_CTHDDV;
import dao.DAO_CTHDP;
import dao.DAO_DICHVU;
import dao.DAO_HDDICHVU;
import dao.DAO_HDPHONG;
import dao.DAO_KHACHHANG;
import dao.DAO_LOAIPHONG;
import dao.DAO_NHANVIEN;
import dao.DAO_PHONG;
import entity.CTHDDV;
import entity.CTHDPHONG;
import entity.DICHVU;
import entity.HOADONDICHVU;
import entity.HOADONPHONG;
import entity.KHACHHANG;
import entity.LOAIPHONG;
import entity.NHANVIEN;
import entity.PHONG;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Canvas;

public class GUI_ThongTin extends JPanel {
	private JTable tablePhong;
	private DefaultTableModel modelPhong;
	private DefaultTableModel modelDichVu;
	private DefaultTableModel modelDichVuSuDung;
	private JTextField txtTimKiem;
	private JTextField txtTenKhachHang;
	private JTable tableDichVuKS;
	private JTable tableDichVuKH;
	private String soPhong;
	private JButton btnLuu;
	private JSpinner spinner;
	private JButton btnXoaBo;
	private JButton btnTatCa;
	private DAO_PHONG dao_phong = new DAO_PHONG();
	private DAO_CTHDP dao_cthoadon = new DAO_CTHDP();
	private DAO_HDPHONG dao_hd = new DAO_HDPHONG();
	private DAO_KHACHHANG dao_kh = new DAO_KHACHHANG();
	private DAO_DICHVU dao_dichvu = new DAO_DICHVU();
	private DAO_HDDICHVU dao_hddv = new DAO_HDDICHVU();
	private DAO_CTHDDV dao_cthddv = new DAO_CTHDDV();
	private DAO_LOAIPHONG dao_loaiPhong = new DAO_LOAIPHONG();
	private DAO_NHANVIEN dao_nhanVien = new DAO_NHANVIEN();
	private JTextField txtMaKhachHang;
	private JTextField txtTongTienP;
	private JTextField txtTongTienDV;
	private JButton btnThanhToan;
	private JButton btnLapHoaDonDV;
	private JLabel lblNgayNhan;
	private JTextField txtTienDichVu;
	private String loaiKhach;

	private final JPanel dlPanel = new JPanel();
	private JTable dlTableDichVu;
	private JTable dlTableChiTiet;
	private JTextField dltxtTongTien;
	private JTextField dlTienKhachDua;
	private JTextField dlTienThua;
	private DefaultTableModel dlModelTableDichVu;
	private DefaultTableModel dlModelTableChiTiet;
	private JButton dlThanhToan;
	private JButton dlThoat;

	private JDialog dialogDichVu;

	private int tam = 0;
	private JTextField txtTongTienTatCa;
	private JTextField txtTienKhachDua;
	private JTextField txtTienThua;
	private String maNhanVien;

	/**
	 * Create the panel.
	 * 
	 * @throws ClassNotFoundException
	 */

	public GUI_ThongTin(String soPhong, String loaiKhach) throws ClassNotFoundException {
		this.soPhong = soPhong;
		this.loaiKhach = loaiKhach;

		setBackground(new Color(238, 232, 170));
		setLayout(null);
		setSize(1195, 635);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 153));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh S\u00E1ch Ph\u00F2ng \u0110ang Thu\u00EA", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(12, 39, 389, 583);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 131, 365, 439);
		panel.add(scrollPane);

		tablePhong = new JTable(modelPhong) {

			private static final long serialVersionUID = 1L;

			/*
			 * @Override public Class getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				default:
					return Boolean.class;
				}
			}
		};

		tablePhong.setModel(modelPhong = new DefaultTableModel(new Object[][] {},
				new String[] { "LỰA CHỌN", "Số phòng", "Mã loại", "Giá phòng" }));
		scrollPane.setViewportView(tablePhong);

		JLabel lblTnKhchHng = new JLabel("Tên khách hàng");
		lblTnKhchHng.setBounds(32, 73, 110, 16);
		panel.add(lblTnKhchHng);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setBounds(183, 70, 169, 22);
		panel.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		JLabel lblNgyNhnPhng = new JLabel("Ngày nhận phòng");
		lblNgyNhnPhng.setBounds(32, 102, 120, 16);
		panel.add(lblNgyNhnPhng);

		lblNgayNhan = new JLabel("");
		lblNgayNhan.setBounds(180, 102, 172, 16);
		panel.add(lblNgayNhan);

		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setBounds(32, 44, 110, 16);
		panel.add(lblMKhchHng);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(183, 41, 169, 22);
		panel.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 102));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng Tin D\u1ECBch V\u1EE5", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(413, 39, 397, 583);
		add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "D\u1ECBch V\u1EE5 Hi\u1EC7n C\u00F3 T\u1EA1i Kh\u00E1ch S\u1EA1n",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBounds(12, 26, 373, 241);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 62, 349, 139);
		panel_3.add(scrollPane_1);

		tableDichVuKS = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tableDichVuKS.setModel(modelDichVu = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã dịch vụ", "Tên dịch vụ", "Đơn giá", }));
		scrollPane_1.setViewportView(tableDichVuKS);

		JLabel lblNewLabel_2 = new JLabel("Tên dịch vụ");
		lblNewLabel_2.setBounds(162, 33, 84, 16);
		panel_3.add(lblNewLabel_2);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(245, 27, 116, 22);
		panel_3.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnLuu = new JButton("LƯU");
		btnLuu.setBackground(new Color(0, 204, 102));
		btnLuu.setBounds(264, 207, 97, 25);
		panel_3.add(btnLuu);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.setBounds(176, 208, 70, 22);
		panel_3.add(spinner);

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setBounds(108, 211, 56, 16);
		panel_3.add(lblSLng);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "D\u1ECBch V\u1EE5 Kh\u00E1ch H\u00E0ng S\u1EED D\u1EE5ng",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_4.setBounds(12, 280, 373, 290);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 25, 349, 124);
		panel_4.add(scrollPane_2);

		tableDichVuKH = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tableDichVuKH.setModel(modelDichVuSuDung = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Thành tiền" }));
		scrollPane_2.setViewportView(tableDichVuKH);

		btnXoaBo = new JButton("XÓA BỎ");
		btnXoaBo.setBackground(new Color(255, 51, 102));
		btnXoaBo.setBounds(264, 163, 97, 25);
		panel_4.add(btnXoaBo);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 153, 102));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng Tin Thanh To\u00E1n",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(822, 39, 361, 583);
		add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(
				new TitledBorder(null, "Ti\u1EC1n Ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(12, 188, 337, 108);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tổng tiền phòng");
		lblNewLabel.setBounds(12, 29, 98, 16);
		panel_5.add(lblNewLabel);

		txtTongTienP = new JTextField();
		txtTongTienP.setBounds(133, 26, 167, 22);
		panel_5.add(txtTongTienP);
		txtTongTienP.setColumns(10);

		JLabel lblVnd = new JLabel("VND");
		lblVnd.setBounds(306, 29, 31, 16);
		panel_5.add(lblVnd);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Ti\u1EC1n D\u1ECBch V\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_6.setBounds(12, 67, 337, 108);
		panel_2.add(panel_6);

		panel_6.setLayout(null);

		JLabel lblTngTinDch = new JLabel("Tổng tiền dịch vụ");
		lblTngTinDch.setBounds(12, 27, 98, 16);
		panel_6.add(lblTngTinDch);

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setBackground(new Color(0, 153, 255));

		btnThanhToan.setBounds(184, 59, 118, 25);
		panel_6.add(btnThanhToan);

		JLabel label = new JLabel("VND");
		label.setBounds(306, 27, 31, 16);
		panel_6.add(label);

		btnTatCa = new JButton("Thanh Toán Tất Cả");
		btnTatCa.setBackground(new Color(51, 153, 204));
		btnTatCa.setBounds(124, 488, 151, 25);
		panel_2.add(btnTatCa);

		// System.out.println("fawefawe");

		ConnectDB.getInstance().connect();
		DAO_LOAIPHONG loaiphong = new DAO_LOAIPHONG();
		List<LOAIPHONG> listphong = loaiphong.getalltbLoaiPhong();
		for (LOAIPHONG loaiphong2 : listphong) {
			// System.out.println(loaiphong2.getMaLoaiPhong());
		}

		btnLapHoaDonDV = new JButton("Lập Hóa Đơn");
		btnLapHoaDonDV.setBackground(new Color(51, 153, 204));
		btnLapHoaDonDV.setBounds(109, 212, 154, 25);
		panel_4.add(btnLapHoaDonDV);
		btnThanhToan.setEnabled(false);

		txtTienDichVu = new JTextField();
		txtTienDichVu.setBounds(134, 24, 168, 22);
		panel_6.add(txtTienDichVu);
		txtTienDichVu.setColumns(10);
		txtMaKhachHang.setEditable(false);
		txtTenKhachHang.setEditable(false);
		txtTongTienP.setEditable(false);

		txtTongTienTatCa = new JTextField();
		txtTongTienTatCa.setBounds(147, 348, 168, 22);
		panel_2.add(txtTongTienTatCa);
		txtTongTienTatCa.setColumns(10);

		JLabel lblTngTin_1 = new JLabel("TỔNG TIỀN");
		lblTngTin_1.setBounds(55, 351, 86, 16);
		panel_2.add(lblTngTin_1);

		JLabel lblVnd_1 = new JLabel("VND");
		lblVnd_1.setBounds(317, 351, 32, 16);
		panel_2.add(lblVnd_1);

		JLabel lblTinKhcha_1 = new JLabel("Tiền khách đưa");
		lblTinKhcha_1.setBounds(30, 386, 100, 16);
		panel_2.add(lblTinKhcha_1);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setBounds(147, 383, 168, 22);
		panel_2.add(txtTienKhachDua);
		txtTienKhachDua.setColumns(10);

		JLabel lblTinTha_1 = new JLabel("Tiền thừa");
		lblTinTha_1.setBounds(59, 419, 56, 16);
		panel_2.add(lblTinTha_1);

		txtTienThua = new JTextField();
		txtTienThua.setBounds(147, 418, 168, 22);
		panel_2.add(txtTienThua);
		txtTienThua.setColumns(10);

		JLabel label_1 = new JLabel("VND");
		label_1.setBounds(317, 386, 32, 16);
		panel_2.add(label_1);

		JLabel label_2 = new JLabel("VND");
		label_2.setBounds(317, 419, 32, 16);
		panel_2.add(label_2);
		btnLapHoaDonDV.setEnabled(false);

		txtTongTienDV = new JTextField();
		txtTongTienDV.setBounds(71, 164, 154, 22);
		panel_4.add(txtTongTienDV);
		txtTongTienDV.setColumns(10);
		txtTongTienDV.setEditable(false);

		CapNhatTableDichVuKS();
		btnLuu.setEnabled(false);
		btnXoaBo.setEnabled(false);
		txtTongTienTatCa.setEditable(false);
		txtTienThua.setEditable(false);
		// btnTatCa.setEnabled(true);
		txtTienDichVu.setEditable(false);
		JLabel lblTngTin = new JLabel("Tổng tiền");
		lblTngTin.setBounds(12, 167, 56, 16);
		panel_4.add(lblTngTin);
		CapNhatDanhSachPhong();
		SuKienLuu();
		SuKienTableDichVuKS();
		SuKienTableDichVuKH();
		SuKienXoaBo();
		CapNhatTongTienDichVu();
		SuKienTablePhong();
		tinhTongTienPhong();
		SuKienLapHoaDonDV();
		auToMaHoaDonDV();
		CapNhatTXTTongTienDichVu();
		SuKienThanhToanDV();
		SuKienThanhToanTatCa();
		dialogDichVu = new JDialog();
		// dialogDichVu.setVisible(false);
		// dialogDichVu.removeAll();
//		dialogDichVu.invalidate();
//		dialogDichVu.repaint();
		DialogThanhToan();
		SuKienKhachDuaTien();
		dialogDichVu.setModal(true);
		dialogDichVu.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogDichVu.setLocation(600, 300);
//		validate();
//	repaint();
		// btnNewButton_1.setVisible(true);
		SuKienTimKiemDichVu();
	}

	private void SuKienTimKiemDichVu() {
		// TODO Auto-generated method stub
		txtTimKiem.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				// CapNhatTableDichVuKS();
				DefaultTableModel dm = (DefaultTableModel) modelDichVu;
				dm.setRowCount(0);
				List<DICHVU> listDichVu = dao_dichvu.getalltbDichVu();
				List<DICHVU> listDichVu2 = new ArrayList<DICHVU>();
				for (int i = 0; i < listDichVu.size(); i++) {
					if (listDichVu.get(i).getTenDV().toUpperCase().contains(txtTimKiem.getText().toUpperCase())) {
//						listDichVu.remove(i);
						// modelDichVu.addRow(new Object[] { listDichVu.get(i).getMaDV(),
						// listDichVu.get(i).getTenDV(), listDichVu.get(i).getGiaDV() });
						listDichVu2.add(listDichVu.get(i));
						System.out.println(listDichVu.get(i).getTenDV() + "]]]]");
					}
				}
				if (txtTimKiem.getText().toUpperCase().trim().equals("")) {
					listDichVu2 = listDichVu;
				}
				for (DICHVU dichvu : listDichVu2) {
					System.out.println(dichvu.getTenDV() + "ten dich vu ");
				}
				System.out.println(listDichVu2.size());
				// List<DICHVU> listDichVu = dao_dichvu.getalltbDichVu();
//				for (int j = 0; j < listDichVu2.size(); j++) {
//					for (int i = 0; i < tableDichVuKH.getRowCount(); i++) {
//						if (listDichVu2.get(j).getMaDV()
//								.equals(modelDichVuSuDung.getValueAt(i, 0).toString())) {
//							listDichVu2.remove(listDichVu2.get(j));
//						}
//					}
//				}
				Iterator itr = listDichVu2.iterator();
				while (itr.hasNext()) {
					DICHVU x = (DICHVU) itr.next();
					for (int i = 0; i < tableDichVuKH.getRowCount(); i++) {
						if (x.getMaDV().equals(modelDichVuSuDung.getValueAt(i, 0).toString())) {
							// listDichVu2.remove(listDichVu2.get(j));
							itr.remove();
						}
					}

				}
				for (DICHVU dichvu : listDichVu2) {
					System.out.println(dichvu.getTenDV() + "sau xoa");
				}
				for (DICHVU dichvu : listDichVu2) {
					modelDichVu.addRow(new Object[] { dichvu.getMaDV(), dichvu.getTenDV(), dichvu.getGiaDV() });
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void SuKienKhachDuaTien() {
		// TODO Auto-generated method stub
		txtTienKhachDua.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (txtTienKhachDua.getText().trim().equals("")) {
					txtTienThua.setText("");
				}
				try {
					if ((Double.parseDouble(txtTienKhachDua.getText())
							- Double.parseDouble(txtTongTienTatCa.getText())) >= 0) {
						txtTienThua.setText(String.valueOf((Double.parseDouble(txtTienKhachDua.getText())
								- Double.parseDouble(txtTongTienTatCa.getText()))));
					} else {
						txtTienThua.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void tinhTongTienTatCa() {
		txtTongTienTatCa.setText(String
				.valueOf((Double.parseDouble(txtTienDichVu.getText()) + Double.parseDouble(txtTongTienP.getText()))));
	}

	private void SuKienThanhToanTatCa() {
		// TODO Auto-generated method stub
		btnTatCa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTienThua.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ tiền khách đưa ");
					return;
				}
				int returnValue = 0;
				returnValue = JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán hay không", "Bạn chắc chứ",
						JOptionPane.YES_NO_OPTION);
				if (returnValue == JOptionPane.YES_OPTION) {
					// =====
					String soPhong = "";
					for (int i = 0; i < tablePhong.getRowCount(); i++) {
						if (modelPhong.getValueAt(i, 0).toString().equals("true")) {
							soPhong = modelPhong.getValueAt(i, 1).toString();
						}
					}

					JDialog framephong = new JDialog();
					JPanel jpanecontentphong = new JPanel();
					jpanecontentphong.setBounds(5, 5, 5, 5);
					jpanecontentphong.setLayout(new BorderLayout(0, 0));
					framephong.setContentPane(jpanecontentphong);

					JDialog frame = new JDialog();
					JPanel jpanecontent = new JPanel();
					jpanecontent.setBounds(5, 5, 5, 5);
					jpanecontent.setLayout(new BorderLayout(0, 0));
					frame.setContentPane(jpanecontent);

					List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();
					Map<String, Object> m = new HashMap<String, Object>();
					List<Map<String, ?>> dataSourcePhong = new ArrayList<Map<String, ?>>();
					Map<String, Object> mPhong = new HashMap<String, Object>();

					String maHoaDonPhong = "";
					String maKhachHang = "";
					for (CTHDPHONG cthdPhong : dao_cthoadon.getalltbHDP()) {
						if (cthdPhong.getPhong().getSoPhong().equals(soPhong)) {
							maHoaDonPhong = cthdPhong.getHdP().getMaHDP();
						}
					}

					for (HOADONPHONG hdPhong : dao_hd.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDonPhong)) {
							maKhachHang = hdPhong.getKhachHang().getMaKH();

						}
					}
					String tenKhachHang = "";
					String soDienThoai = "";
					String mahoadon = "";
					for (KHACHHANG khachhang : dao_kh.getalltbKhachHang()) {
						if (maKhachHang.equals(khachhang.getMaKH())) {
							tenKhachHang = khachhang.getTenKH();
							soDienThoai = khachhang.getSDT();
						}
					}
					for (HOADONDICHVU ma : dao_hddv.getalltbHDDV()) {
						if (ma.getKhachHang().getMaKH().equals(maKhachHang)
								&& ma.getTinhtrang().equals("CHƯA THANH TOÁN")) {
							mahoadon = ma.getMaHDDV();
						}
					}
					String tenNhanvien = "";
					for (NHANVIEN nv : dao_nhanVien.getalltbNhanVien()) {
						if (nv.getMaNV().equals(maNhanVien)) {
							tenNhanvien = nv.getTenNV();
						}
					}

					m.put("nhanvien", tenNhanvien);
					m.put("ngaylap", String.valueOf(LocalDate.now()));
					m.put("giolap", String.valueOf(LocalTime.now()));
					m.put("tenkhachhang", tenKhachHang);
					m.put("sodienthoai", soDienThoai);
					m.put("mahoadon", mahoadon);

					mPhong.put("nhanvien", tenNhanvien);
					mPhong.put("ngaylap", String.valueOf(LocalDate.now()));
					mPhong.put("giolap", String.valueOf(LocalTime.now()));
					mPhong.put("tenkhachhang", tenKhachHang);
					mPhong.put("sodienthoai", soDienThoai);
					mPhong.put("mahoadon", maHoaDonPhong);

					double tongtien = 0;
					double tienNhan = Double.parseDouble(txtTienKhachDua.getText());
					double tienThua = Double.parseDouble(txtTienThua.getText());

					double tongtienPhong = 0;
					double tienNhanPhong = Double.parseDouble(txtTienKhachDua.getText());
					double tienThuaPhong = Double.parseDouble(txtTienThua.getText());
					for (HOADONDICHVU hdDichVu : dao_hddv.getalltbHDDV()) {
						if (hdDichVu.getKhachHang().getMaKH().equals(maKhachHang)
								&& hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
							for (CTHDDV ctdichvu : dao_cthddv.getalltbCTHDDV()) {
								if (ctdichvu.getHddv().getMaHDDV().equals(hdDichVu.getMaHDDV())) {
									for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
										if (dichvu.getMaDV().equals(ctdichvu.getDichVu().getMaDV())) {
											tongtien += (ctdichvu.getSoluong() * dichvu.getGiaDV());
										}
									}
								}
							}
						}
					}
//					for (int t = 0; t < tablePhong.getRowCount(); t++) {
//					if (modelPhong.getValueAt(t, 0).toString().equals("true")) {
//						dao_cthoadon.CapNhat(modelPhong.getValueAt(t, 1).toString(), LocalDateTime.now().toString(),
//								"TRẢ PHÒNG");
//						dao_phong.CapNhatPhong(modelPhong.getValueAt(t, 1).toString(),
//								modelPhong.getValueAt(t, 2).toString(), "TRỐNG");
//					}
//				}
					for (HOADONPHONG hdPhong : dao_hd.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDonPhong)) {
							maKhachHang = hdPhong.getKhachHang().getMaKH();
							int khoangCach = 1;
							for (CTHDPHONG cthoaDon : dao_cthoadon.getalltbHDP()) {
								if (cthoaDon.getHdP().getMaHDP().equals(hdPhong.getMaHDP())
										&& cthoaDon.getTinhTrang().equals("NHẬN PHÒNG")) {
									LocalDateTime ngayDatPhong = cthoaDon.getNgayDat();
									LocalDateTime ngayHienTai = LocalDateTime.now();
									Duration dur = Duration.between(ngayDatPhong, LocalDateTime.now());
									khoangCach = Integer
											.parseInt(String.valueOf(ChronoUnit.DAYS
													.between(cthoaDon.getNgayDat().toLocalDate(), LocalDate.now())))
											+ 1;
									System.out.println(khoangCach);
									for (PHONG phong : dao_phong.getAlltbPhong()) {

										if (phong.getSoPhong().equals(cthoaDon.getPhong().getSoPhong())) {
											for (int t = 0; t < tablePhong.getRowCount(); t++) {
												if (modelPhong.getValueAt(t, 0).toString().equals("true") && modelPhong
														.getValueAt(t, 1).toString().equals(phong.getSoPhong())) {
													for (LOAIPHONG loaiphong : dao_loaiPhong.getalltbLoaiPhong()) {
														if (loaiphong.getMaLoaiPhong()
																.equals(phong.getPhong().getMaLoaiPhong())) {
															tongtienPhong += loaiphong.getDonGia();
														}
													}
												}
											}
										}
									}
								}
							}
							tongtienPhong = tongtienPhong * khoangCach;
						}
					}

					// int tam = 1;
					int soluong;
					double dongia;

					int temp = 0;
					Map<String, Object> m2 = new HashMap<String, Object>();
					for (HOADONDICHVU hdDichVu : dao_hddv.getalltbHDDV()) {
						if (hdDichVu.getKhachHang().getMaKH().equals(maKhachHang)
								&& hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
							for (CTHDDV ctdichvu : dao_cthddv.getalltbCTHDDV()) {
								if (ctdichvu.getHddv().getMaHDDV().equals(hdDichVu.getMaHDDV())) {
									for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
										if (dichvu.getMaDV().equals(ctdichvu.getDichVu().getMaDV())) {

											if (temp == 0) {
												m.put("tendichvu", dichvu.getTenDV());
												soluong = ctdichvu.getSoluong();
												m.put("soluong", soluong);
												dongia = dichvu.getGiaDV();
												m.put("dongia", dongia);
												m.put("thanhtien", soluong * dongia);
												m.put("tongtien", tongtien);
												m.put("tiennhan", tienNhan);
												m.put("tientra", tienThua);
												dataSource.add(m);
												temp = 1;
											} else {
												Map<String, Object> m3 = new HashMap<String, Object>();
												m3.put("tongtien", tongtien);
												m3.put("tiennhan", tienNhan);
												m3.put("tientra", tienThua);
												m3.put("tendichvu", dichvu.getTenDV());
												soluong = ctdichvu.getSoluong();
												m3.put("soluong", soluong);
												dongia = dichvu.getGiaDV();
												m3.put("dongia", dongia);
												m3.put("thanhtien", soluong * dongia);
												dataSource.add(m3);
											}
										}
									}
								}
							}
						}
					}
					int tempPhong = 0;
					int i = 1;
					for (HOADONPHONG hdPhong : dao_hd.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDonPhong)) {
							maKhachHang = hdPhong.getKhachHang().getMaKH();
							int khoangCach = 1;
							for (CTHDPHONG cthoaDon : dao_cthoadon.getalltbHDP()) {
								if (cthoaDon.getHdP().getMaHDP().equals(hdPhong.getMaHDP())
										&& cthoaDon.getTinhTrang().equals("NHẬN PHÒNG")) {
									LocalDateTime ngayDatPhong = cthoaDon.getNgayDat();
									LocalDateTime ngayHienTai = LocalDateTime.now();
									Duration dur = Duration.between(ngayDatPhong, LocalDateTime.now());
									khoangCach = Integer
											.parseInt(String.valueOf(ChronoUnit.DAYS
													.between(cthoaDon.getNgayDat().toLocalDate(), LocalDate.now())))
											+ 1;
									System.out.println(khoangCach);
									for (PHONG phong : dao_phong.getAlltbPhong()) {
										if (phong.getSoPhong().equals(cthoaDon.getPhong().getSoPhong())) {
											for (int t = 0; t < tablePhong.getRowCount(); t++) {
												if (modelPhong.getValueAt(t, 0).toString().equals("true") && modelPhong
														.getValueAt(t, 1).toString().equals(phong.getSoPhong())) {
													for (LOAIPHONG loaiphong : dao_loaiPhong.getalltbLoaiPhong()) {
														if (loaiphong.getMaLoaiPhong()
																.equals(phong.getPhong().getMaLoaiPhong())) {
															if (tempPhong == 0) {
																mPhong.put("STT", String.valueOf(i));
																// soluong = ctdichvu.getSoluong();
																mPhong.put("loaiphong", loaiphong.getMaLoaiPhong());
																// dongia = dichvu.getGiaDV();
																mPhong.put("sophong", phong.getSoPhong());
																mPhong.put("dongiaphong",
																		String.valueOf(loaiphong.getDonGia()));

																// m.put("thanhtien", soluong * dongia);
																mPhong.put("tongtien", tongtienPhong);
																mPhong.put("tiennhan", tienNhan);
																mPhong.put("tientra", tienThua);
																mPhong.put("songay", String.valueOf(khoangCach));
																dataSourcePhong.add(mPhong);
																tempPhong = 1;
																i++;
															} else {

																Map<String, Object> m3 = new HashMap<String, Object>();
																m3.put("STT", String.valueOf(i));
																// soluong = ctdichvu.getSoluong();
																m3.put("loaiphong", loaiphong.getMaLoaiPhong());
																// dongia = dichvu.getGiaDV();
																m3.put("sophong", phong.getSoPhong());
																m3.put("dongiaphong",
																		String.valueOf(loaiphong.getDonGia()));

																// m.put("thanhtien", soluong * dongia);
																m3.put("tongtien", tongtienPhong);
																m3.put("tiennhan", tienNhan);
																m3.put("tientra", tienThua);
																m3.put("songay", String.valueOf(khoangCach));
																dataSourcePhong.add(m3);
																i++;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							tongtienPhong = tongtienPhong * khoangCach;
						}
					}

					if (!mahoadon.equals("")) {

						JRDataSource datasour = new JRBeanCollectionDataSource(dataSource);
						String source = "src/hoaDon/HoaDonDichVu.jrxml";
						try {
							JasperReport report = JasperCompileManager.compileReport(source);
							JasperPrint filledRed = JasperFillManager.fillReport(report, null, datasour);
							// filledRed.setPageHeight(700);
							// filledRed.setPageWidth(800);
							jpanecontent.add(new JRViewer(filledRed));
							// jpanecontent.add(new JButton("doan "));
							// jpanecontent.setSize(300, 300);

						} catch (JRException t) {
							// TODO Auto-generated catch block
							t.printStackTrace();
						}
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

						frame.setSize(1310, 724);
						frame.setBounds(10, 10, 1300, 724);

						frame.setModal(true);
					}

					JRDataSource datasourPhong = new JRBeanCollectionDataSource(dataSourcePhong);
					String sourcePhong = "src/hoaDon/HoaDonPhong.jrxml";
					try {
						JasperReport reportPhong = JasperCompileManager.compileReport(sourcePhong);
						JasperPrint phongfilledRed = JasperFillManager.fillReport(reportPhong, null, datasourPhong);
						jpanecontentphong.add(new JRViewer(phongfilledRed));
					} catch (JRException t) {
						t.printStackTrace();
					}
					framephong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					framephong.setSize(1310, 724);
					framephong.setBounds(10, 10, 1300, 724);
					framephong.setModal(true);

//					
					for (int t = 0; t < tablePhong.getRowCount(); t++) {
						if (modelPhong.getValueAt(t, 0).toString().equals("true")) {
							dao_cthoadon.CapNhat(modelPhong.getValueAt(t, 1).toString(), LocalDateTime.now().toString(),
									"TRẢ PHÒNG");
							dao_phong.CapNhatPhong(modelPhong.getValueAt(t, 1).toString(),
									modelPhong.getValueAt(t, 2).toString(), "TRỐNG");
						}
					}
					for (HOADONDICHVU hoadondv : dao_hddv.getalltbHDDV()) {
						if (hoadondv.getKhachHang().getMaKH().equals(txtMaKhachHang.getText())) {
							dao_hddv.updateHoaDonDichVu(hoadondv.getMaHDDV(), "ĐÃ THANH TOÁN");
						}
					}

					// dialogThanhToan.setVisible(false);

					if (!mahoadon.equals(""))
						frame.setVisible(true);
					framephong.setVisible(true);

					// dialogThanhToan.setVisible(false);

					// ====

					JOptionPane.showMessageDialog(null, "Thanh toán thành công");
					setVisible(false);
					removeAll();
					// remove(panel_1);

					GUI_SoDoPhong guisophong;
					try {
						add(guisophong = new GUI_SoDoPhong());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					setVisible(true);
				} else if (returnValue == JOptionPane.NO_OPTION) {

				}
			}
		});

	}

	private void SuKienThanhToanDV() {
		// TODO Auto-generated method stub
		btnThanhToan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tam == 1) {
					lodDuLieuBangDichVuDialog();
				}
				dialogDichVu.setVisible(true);
			}
		});
	}

	public void DialogThanhToan() {
		tam = 1;
		dialogDichVu.setBounds(100, 100, 514, 411);
		dialogDichVu.getContentPane().setLayout(new BorderLayout());
		dlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialogDichVu.getContentPane().add(dlPanel, BorderLayout.CENTER);
		dlPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "H\u00F3a \u0110\u01A1n D\u1ECBch V\u1EE5",
						TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 0, 472, 99);
		dlPanel.add(panel);
		panel.setLayout(null);

		JScrollPane dlscrollPane = new JScrollPane();
		dlscrollPane.setBounds(12, 26, 448, 60);
		panel.add(dlscrollPane);

		dlTableDichVu = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		dlTableDichVu.setModel(dlModelTableDichVu = new DefaultTableModel(new Object[][] {

		}, new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 kh\u00E1ch h\u00E0ng", "Ng\u00E0y l\u1EADp" }));
		dlscrollPane.setViewportView(dlTableDichVu);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Chi Ti\u1EBFt H\u00F3a \u0110\u01A1n D\u1ECBch V\u1EE5", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(12, 106, 472, 141);
		dlPanel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane dlScrollPane_2 = new JScrollPane();
		dlScrollPane_2.setBounds(12, 19, 448, 109);
		panel_1.add(dlScrollPane_2);

		dlTableChiTiet = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		dlTableChiTiet.setModel(dlModelTableChiTiet = new DefaultTableModel(new Object[][] {

		}, new String[] { "M\u00E3 H\u00F3a \u0110\u01A1n", "T\u00EAn D\u1ECBch V\u1EE5", "S\u1ED1 L\u01B0\u1EE3ng",
				"Đơn Giá" }));
		dlScrollPane_2.setViewportView(dlTableChiTiet);

		dltxtTongTien = new JTextField();
		dltxtTongTien.setBounds(90, 254, 116, 22);
		dlPanel.add(dltxtTongTien);
		dltxtTongTien.setColumns(10);

		JLabel lblTngTin = new JLabel("Tổng tiền");
		lblTngTin.setBounds(22, 257, 56, 16);
		dlPanel.add(lblTngTin);

		JLabel lblTinKhcha = new JLabel("Tiền khách đưa");
		lblTinKhcha.setBounds(255, 260, 88, 16);
		dlPanel.add(lblTinKhcha);

		dlTienKhachDua = new JTextField();
		dlTienKhachDua.setBounds(355, 254, 116, 22);
		dlPanel.add(dlTienKhachDua);
		dlTienKhachDua.setColumns(10);

		JLabel lblTinTha = new JLabel("Tiền thừa");
		lblTinTha.setBounds(287, 292, 56, 16);
		dlPanel.add(lblTinTha);

		dlTienThua = new JTextField();
		dlTienThua.setBounds(355, 289, 116, 22);
		dlPanel.add(dlTienThua);
		dlTienThua.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			dialogDichVu.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				dlThanhToan = new JButton("THANH TOÁN");
				dlThanhToan.setActionCommand("OK");
				buttonPane.add(dlThanhToan);
				dialogDichVu.getRootPane().setDefaultButton(dlThanhToan);
			}
			{
				dlThoat = new JButton("THOÁT");
				dlThoat.setActionCommand("Cancel");
				buttonPane.add(dlThoat);
			}
		}
		dltxtTongTien.setEditable(false);
		dlThanhToan.setEnabled(false);

		lodDuLieuBangDichVuDialog();
		dlThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialogDichVu.setVisible(false);
			}
		});
		dlTableDichVu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel dm = (DefaultTableModel) dlModelTableChiTiet;
				dm.setRowCount(0);
				int row = -1;
				row = dlTableDichVu.getSelectedRow();
				if (dlTableDichVu.getRowCount() != 0 && row != -1) {
					for (HOADONDICHVU hoadon : dao_hddv.getalltbHDDV()) {
						for (CTHDDV chitiet : dao_cthddv.getalltbCTHDDV()) {
							if (hoadon.getMaHDDV().equals(chitiet.getHddv().getMaHDDV())) {
								if (chitiet.getHddv().getMaHDDV()
										.equals(dlModelTableDichVu.getValueAt(row, 0).toString())) {
									for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
										if (dichvu.getMaDV().equals(chitiet.getDichVu().getMaDV())) {
											dlModelTableChiTiet.addRow(new Object[] { chitiet.getHddv().getMaHDDV(),
													dichvu.getTenDV(), chitiet.getSoluong(), dichvu.getGiaDV() });
										}
									}
								}
							}
						}
					}
				}
			}
		});
		dlTienKhachDua.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				double tongtien = Double.parseDouble(dltxtTongTien.getText());
				double tienkH = Double.parseDouble(dlTienKhachDua.getText());
				double tienthua = tienkH - tongtien;
				if (tienthua >= 0) {
					dlTienThua.setText(String.valueOf(tienthua));
					dlThanhToan.setEnabled(true);
				} else
					dlThanhToan.setEnabled(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		dlThanhToan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
				JDialog frame = new JDialog();
				JPanel jpanecontent = new JPanel();
				jpanecontent.setBounds(5, 5, 5, 5);
				jpanecontent.setLayout(new BorderLayout(0, 0));
				frame.setContentPane(jpanecontent);
				
				
				

				List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();
				Map<String, Object> m = new HashMap<String, Object>();

				String maKhachHang = txtMaKhachHang.getText();
				String tenKhachHang = "";
				String soDienThoai = "";
				String mahoadon = "";
				for (KHACHHANG khachhang : dao_kh.getalltbKhachHang()) {
					if (maKhachHang.equals(khachhang.getMaKH())) {
						tenKhachHang = khachhang.getTenKH();
						soDienThoai = khachhang.getSDT();
					}
				}
				for (HOADONDICHVU ma : dao_hddv.getalltbHDDV()) {
					if (ma.getKhachHang().getMaKH().equals(maKhachHang)
							&& ma.getTinhtrang().equals("CHƯA THANH TOÁN")) {
						mahoadon = ma.getMaHDDV();
					}
				}
				String tenNhanvien = "";
				for (NHANVIEN nv : dao_nhanVien.getalltbNhanVien()) {
					if (nv.getMaNV().equals(maNhanVien)) {
						tenNhanvien = nv.getTenNV();
					}
				}

				m.put("nhanvien", tenNhanvien);
				m.put("ngaylap", String.valueOf(LocalDate.now()));
				m.put("giolap", String.valueOf(LocalTime.now()));
				m.put("tenkhachhang", tenKhachHang);
				m.put("sodienthoai", soDienThoai);
				m.put("mahoadon", mahoadon);
				

				double tongtien = 0;
				double tienNhan = Double.parseDouble(dlTienKhachDua.getText());
				double tienThua = Double.parseDouble(dlTienThua.getText());

				for (HOADONDICHVU hdDichVu : dao_hddv.getalltbHDDV()) {
					if (hdDichVu.getKhachHang().getMaKH().equals(maKhachHang)
							&& hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
						for (CTHDDV ctdichvu : dao_cthddv.getalltbCTHDDV()) {
							if (ctdichvu.getHddv().getMaHDDV().equals(hdDichVu.getMaHDDV())) {
								for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
									if (dichvu.getMaDV().equals(ctdichvu.getDichVu().getMaDV())) {
										tongtien += (ctdichvu.getSoluong() * dichvu.getGiaDV());
									}
								}
							}
						}
					}
				}
				
				
		//		int tam = 1;
				int soluong;
				double dongia;

				int temp = 0;
				Map<String, Object> m2 = new HashMap<String, Object>();
				for (HOADONDICHVU hdDichVu : dao_hddv.getalltbHDDV()) {
					if (hdDichVu.getKhachHang().getMaKH().equals(maKhachHang)
							&& hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
						for (CTHDDV ctdichvu : dao_cthddv.getalltbCTHDDV()) {
							if (ctdichvu.getHddv().getMaHDDV().equals(hdDichVu.getMaHDDV())) {
								for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
									if (dichvu.getMaDV().equals(ctdichvu.getDichVu().getMaDV())) {

										if (temp == 0) {
											m.put("tendichvu", dichvu.getTenDV());
											soluong = ctdichvu.getSoluong();
											m.put("soluong", soluong);
											dongia = dichvu.getGiaDV();
											m.put("dongia", dongia);
											m.put("thanhtien", soluong * dongia);
											m.put("tongtien", tongtien);
											m.put("tiennhan", tienNhan);
											m.put("tientra", tienThua);
											dataSource.add(m);
											temp = 1;
										} else {
											Map<String, Object> m3 = new HashMap<String, Object>();
											m3.put("tongtien", tongtien);
											m3.put("tiennhan", tienNhan);
											m3.put("tientra", tienThua);
											m3.put("tendichvu", dichvu.getTenDV());
											soluong = ctdichvu.getSoluong();
											m3.put("soluong", soluong);
											dongia = dichvu.getGiaDV();
											m3.put("dongia", dongia);
											m3.put("thanhtien", soluong * dongia);
											dataSource.add(m3);
										}
									}
								}
							}
						}
					}
				}
				
				
				
				if (!mahoadon.equals("")) {

					JRDataSource datasour = new JRBeanCollectionDataSource(dataSource);
					String source = "src/hoaDon/HoaDonDichVu.jrxml";
					try {
						JasperReport report = JasperCompileManager.compileReport(source);
						JasperPrint filledRed = JasperFillManager.fillReport(report, null, datasour);
						// filledRed.setPageHeight(700);
						// filledRed.setPageWidth(800);
						jpanecontent.add(new JRViewer(filledRed));
						// jpanecontent.add(new JButton("doan "));
						// jpanecontent.setSize(300, 300);

					} catch (JRException t) {
						// TODO Auto-generated catch block
						t.printStackTrace();
					}
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					frame.setSize(1310, 724);
					frame.setBounds(10, 10, 1300, 724);

					frame.setModal(true);
				}
				
				
				for (HOADONDICHVU element : dao_hddv.getalltbHDDV()) {
					if (element.getKhachHang().getMaKH().equals(txtMaKhachHang.getText())) {
						dao_hddv.updateHoaDonDichVu(element.getMaHDDV(), "ĐÃ THANH TOÁN");
					}
				}
				
			
				

//				dialogThanhToan.setVisible(false)
				dialogDichVu.setVisible(false);
				
				if (!mahoadon.equals(""))
					frame.setVisible(true);
//				framephong.setVisible(true);
//
//				dialogThanhToan.setVisible(false);
				
				
				
				CapNhatTXTTongTienDichVu();

				DefaultTableModel dm1 = (DefaultTableModel) dlModelTableDichVu;
				dm1.setRowCount(0);
				DefaultTableModel dm = (DefaultTableModel) dlModelTableChiTiet;
				dm.setRowCount(0);

				dltxtTongTien.setText("");
				dlTienKhachDua.setText("");
				dlTienThua.setText("");

//				dialogDichVu.removeAll();
//				dialogDichVu.revalidate();
//				dialogDichVu.repaint();		
				dlThanhToan.setEnabled(false);
				dialogDichVu.setVisible(false);

				JOptionPane.showMessageDialog(null, "BẠN ĐÃ THANH TOÁN THÀNH CÔNG");
			}
		});

	}

	private void lodDuLieuBangDichVuDialog() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DefaultTableModel dm = (DefaultTableModel) dlModelTableDichVu;
		dm.setRowCount(0);
		for (HOADONDICHVU hoadondv : dao_hddv.getalltbHDDV()) {
			if (txtMaKhachHang.getText().equals(hoadondv.getKhachHang().getMaKH())) {
				if (hoadondv.getTinhtrang().equals("CHƯA THANH TOÁN")) {
					String ngay = formatter.format(hoadondv.getLocaldate());
					dlModelTableDichVu
							.addRow(new Object[] { hoadondv.getMaHDDV(), hoadondv.getKhachHang().getMaKH(), ngay });

				}
			}
		}
		dltxtTongTien.setText(txtTienDichVu.getText());
	}

	private void CapNhatTXTTongTienDichVu() {
		// TODO Auto-generated method stub
		double tongtien = 0;
		for (HOADONDICHVU element : dao_hddv.getalltbHDDV()) {
			if (element.getKhachHang().getMaKH().equals(txtMaKhachHang.getText())) {
				if (element.getTinhtrang().equals("CHƯA THANH TOÁN")) {
					for (CTHDDV chitiet : dao_cthddv.getalltbCTHDDV()) {
						if (element.getMaHDDV().equals(chitiet.getHddv().getMaHDDV())) {
							for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
								if (chitiet.getDichVu().getMaDV().equals(dichvu.getMaDV())) {
									tongtien += (dichvu.getGiaDV() * chitiet.getSoluong());
								}
							}
						}
					}
				}
			}
		}
		txtTienDichVu.setText(String.valueOf(tongtien));
		tinhTongTienTatCa();
		if (tongtien == 0) {
			btnThanhToan.setEnabled(false);
		} else {
			btnThanhToan.setEnabled(true);
		}
	}

	private String auToMaHoaDonDV() {
		String maHoaDonDV = "HDDV.";
		LocalDate localDate = LocalDate.now();
		int nam = localDate.getYear(), thang = localDate.getMonthValue(), ngay = localDate.getDayOfMonth();
		maHoaDonDV = maHoaDonDV + String.valueOf(nam) + ".";
		if (thang < 10) {
			maHoaDonDV = maHoaDonDV + "0" + String.valueOf(thang) + ".";
		} else {
			maHoaDonDV = maHoaDonDV + String.valueOf(thang) + ".";
		}
		if (ngay < 10) {
			maHoaDonDV = maHoaDonDV + "0" + String.valueOf(ngay) + ".";
		} else {
			maHoaDonDV = maHoaDonDV + String.valueOf(ngay) + ".";
		}
		System.out.println("da vao ne" + maHoaDonDV);
		if (dao_hddv.getalltbHDDV().size() == 0) {
			maHoaDonDV = maHoaDonDV + "0000";
			System.out.println("da vao ");
			// dao_hddv.DatDichVu(maHoaDonDV,"NVQL001", "KH0001", localDate.toString(),
			// "CHƯA THANH TOÁN");
			return maHoaDonDV;
		}

		int so = 0;
		String tendinh = "doan kim dinh";
		for (HOADONDICHVU element : dao_hddv.getalltbHDDV()) {
			System.out.println(element.getMaHDDV().substring(16, 20));
			so = Integer.parseInt(element.getMaHDDV().substring(16, 20)) + 1;
			if (!element.getMaHDDV().substring(0, 16).equals(maHoaDonDV)) {
				so = 0;
			}
		}
		if (so >= 0 && so < 10) {
			maHoaDonDV = maHoaDonDV + "000" + String.valueOf(so);
		}
		if (so >= 10 && so < 100) {
			maHoaDonDV = maHoaDonDV + "00" + String.valueOf(so);
		}
		if (so >= 100 && so < 1000) {
			maHoaDonDV = maHoaDonDV + "0" + String.valueOf(so);
		}
		if (so >= 1000 && so < 10000) {
			maHoaDonDV = maHoaDonDV + String.valueOf(so);
		}
//		 dao_hddv.DatDichVu(maHoaDonDV,"NVQL001", "KH0001", localDate.toString(), "CHƯA THANH TOÁN");
//		System.out.println(maHoaDonDV);
//		dao_cthddv.themChiTiet("HDDV.2019.11.22.0000", "DV0001", 12);
//		dao_cthddv.themChiTiet("HDDV.2019.11.22.0000", "DV0002", 7);
//		dao_hddv.updateHoaDonDichVu("HDDV.2019.11.22.0000", "ĐÃ THANH TOÁN");
		return maHoaDonDV;
	}

	private void SuKienLapHoaDonDV() {
		// TODO Auto-generated method stub
		// dao_hddv.DatDichVu(mahoadon, manhanvien, makhachhang, thoigian, tinhtrang);
		btnLapHoaDonDV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maHoaDonDV = auToMaHoaDonDV();
				dao_hddv.DatDichVu(maHoaDonDV, maNhanVien, txtMaKhachHang.getText(), LocalDate.now().toString(),
						"CHƯA THANH TOÁN");
				for (int i = 0; i < tableDichVuKH.getRowCount(); i++) {
					dao_cthddv.themChiTiet(maHoaDonDV, modelDichVuSuDung.getValueAt(i, 0).toString(),
							Integer.parseInt(modelDichVuSuDung.getValueAt(i, 2).toString()));
				}

				double tongTien = 0;
				for (int i = 0; i < tableDichVuKH.getRowCount(); i++) {
					tongTien += Double.parseDouble(modelDichVuSuDung.getValueAt(i, 3).toString());
				}
				txtTienDichVu.setText(String.valueOf(tongTien));
				DefaultTableModel dm = (DefaultTableModel) modelDichVuSuDung;
				dm.setRowCount(0);

				CapNhatTableDichVuKS();

				btnThanhToan.setEnabled(true);
				btnXoaBo.setEnabled(false);
				btnLapHoaDonDV.setEnabled(false);
				CapNhatTongTienDichVu();

				CapNhatTXTTongTienDichVu();
				tinhTongTienTatCa();
			}
		});
	}

	private double tinhTongTienPhong() {
//		double tongTien = 0;
//		for (int i = 0; i < tablePhong.getRowCount(); i++) {
//			if (modelPhong.getValueAt(i, 0).toString().equals("true")) {
//				tongTien += Double.valueOf(modelPhong.getValueAt(i, 3).toString());
//			}
//		}
		// ==
		double tongTienPhong = 0;
		int khoangCach = 1;
		for (int i = 0; i < tablePhong.getRowCount(); i++) {
			if (modelPhong.getValueAt(i, 0).toString().equals("true")) {
				LocalDateTime ngayDatPhong = LocalDateTime.parse(lblNgayNhan.getText());
				LocalDateTime ngayHienTai = LocalDateTime.now();
				Duration dur = Duration.between(ngayDatPhong, LocalDateTime.now());
				System.out
						.println(Integer
								.parseInt(String.valueOf(ChronoUnit.DAYS
										.between(LocalDateTime.parse(lblNgayNhan.getText()), LocalDateTime.now())))
								+ lblNgayNhan.getText());
				khoangCach = Integer
						.parseInt(String.valueOf(ChronoUnit.DAYS
								.between(LocalDateTime.parse(lblNgayNhan.getText()).toLocalDate(), LocalDate.now())))
						+ 1;
				System.out.println(khoangCach);
//				for (PHONG phong : dao_phong.getAlltbPhong()) {
//					if (phong.getSoPhong().equals(cthoaDon.getPhong().getSoPhong())) {
//						for (LOAIPHONG loaiphong : dao_loaiPhong.getalltbLoaiPhong()) {
//							if (loaiphong.getMaLoaiPhong().equals(phong.getPhong().getMaLoaiPhong())) {
//								tongTienPhong += loaiphong.getDonGia();
//							}
//						}
//					}
//				}
				tongTienPhong += Double.valueOf(modelPhong.getValueAt(i, 3).toString());
			}
		}
		System.out.println(khoangCach + "khoach cach là" + tongTienPhong);
		tongTienPhong = tongTienPhong * khoangCach;
		System.out.println(khoangCach + "khoach cach là" + tongTienPhong);
		txtTongTienP.setText(String.valueOf(tongTienPhong));
		// ==
		// txtTongTienP.setText(String.valueOf(tongTien));
		return tongTienPhong;
	}

	private void SuKienTablePhong() {
		// TODO Auto-generated method stub
		tablePhong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				// System.out.println(modelPhong.getValueAt(0, 0));
			}
		});
		tablePhong.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(modelPhong.getValueAt(0, 0));
				double tongTien = tinhTongTienPhong();
				if (tongTien == 0) {
					if (txtTongTienDV.getText().equals("0.0")) {
						btnTatCa.setEnabled(false);
					}
				} else
					btnTatCa.setEnabled(true);
				tinhTongTienTatCa();
			}
		});
	}

	private void CapNhatDanhSachPhong() {
		// TODO Auto-generated method stub
		String maHoaDonP = "";
		for (CTHDPHONG element : dao_cthoadon.getalltbHDP()) {
			if (element.getPhong().getSoPhong().equals(soPhong)) {
				maHoaDonP = element.getHdP().getMaHDP();
			}
		}
		if (loaiKhach.equals("KHÁCH LẺ")) {
			for (PHONG phong : dao_phong.getAlltbPhong()) {
				if (soPhong.equals(phong.getSoPhong())) {
					for (LOAIPHONG loaiphong2 : dao_loaiPhong.getalltbLoaiPhong()) {
						if (loaiphong2.getMaLoaiPhong().equals(phong.getPhong().getMaLoaiPhong())) {
							modelPhong.addRow(new Object[] { true, phong.getSoPhong(),
									phong.getPhong().getMaLoaiPhong(), loaiphong2.getDonGia() });
						}
					}
				}
			}
		} else {
			for (CTHDPHONG element : dao_cthoadon.getalltbHDP()) {
				if (element.getHdP().getMaHDP().equals(maHoaDonP) && element.getTinhTrang().equals("NHẬN PHÒNG")) {
					for (PHONG phong : dao_phong.getAlltbPhong()) {
						if (phong.getSoPhong().equals(element.getPhong().getSoPhong())) {
							for (LOAIPHONG loaiphong : dao_loaiPhong.getalltbLoaiPhong()) {
								if (loaiphong.getMaLoaiPhong().equals(phong.getPhong().getMaLoaiPhong())) {
									modelPhong.addRow(new Object[] { true, phong.getSoPhong(),
											phong.getPhong().getMaLoaiPhong(), loaiphong.getDonGia() });
								}
							}
						}
					}
				}
			}
		}
		for (CTHDPHONG loaiphong2 : dao_cthoadon.getalltbHDP()) {
			if (loaiphong2.getPhong().getSoPhong().equals(soPhong)) {
				for (HOADONPHONG loaiphong3 : dao_hd.getalltbHDP()) {
					if (loaiphong3.getMaHDP().equals(loaiphong2.getHdP().getMaHDP())) {
						txtMaKhachHang.setText(loaiphong3.getKhachHang().getMaKH());
						lblNgayNhan.setText(loaiphong2.getNgayDat().toString());
						for (KHACHHANG khachhang : dao_kh.getalltbKhachHang()) {
							if (khachhang.getMaKH().equals(loaiphong3.getKhachHang().getMaKH())) {
								txtTenKhachHang.setText(khachhang.getTenKH());
							}
						}
					}
				}
			}
		}
	}

	private void CapNhatTongTienDichVu() {
		double tongtienDV = 0;
		for (int i = 0; i < tableDichVuKH.getRowCount(); i++) {
			tongtienDV += Double.parseDouble(modelDichVuSuDung.getValueAt(i, 3).toString());
		}
		txtTongTienDV.setText(String.valueOf(tongtienDV));
		if (txtTongTienDV.getText().equals("0.0")) {
			btnThanhToan.setEnabled(false);
			btnLapHoaDonDV.setEnabled(false);
		} else {

			btnLapHoaDonDV.setEnabled(true);
		}
//		tinhTongTienTatCa();
	}

	private void CapNhatTableDichVuKS() {
		DefaultTableModel dm = (DefaultTableModel) modelDichVu;
		dm.setRowCount(0);
		
	//	List<DICHVU> listDichVu1 = dao_dichvu.getalltbDichVu();
		List<DICHVU> listDichVu = new ArrayList<DICHVU>();
		for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
			if(dichvu.getTinhtrang().equals("ĐANG KINH DOANH")) {
				listDichVu.add(dichvu);
			}
		}
		//		for (int j = 0; j < listDichVu.size(); j++) {
//			for (int i = 0; i < tableDichVuKH.getRowCount(); i++) {
//				if (listDichVu.get(j).getMaDV().equals(modelDichVuSuDung.getValueAt(i, 0).toString())) {
//					listDichVu.remove(listDichVu.get(j));
//				}
////				System.out.println(
////						listDichVu.get(j).getMaDV() + "==sdfghjkl;==" + modelDichVuSuDung.getValueAt(i, 0).toString());
//			}
//		}
		Iterator itr = listDichVu.iterator();
		while (itr.hasNext()) {
			DICHVU x = (DICHVU) itr.next();
			for (int i = 0; i < tableDichVuKH.getRowCount(); i++) {
				if (x.getMaDV().equals(modelDichVuSuDung.getValueAt(i, 0).toString())) {
					// listDichVu2.remove(listDichVu2.get(j));
					itr.remove();
				}
			}
		}
		for (DICHVU dichvu : listDichVu) {
			modelDichVu.addRow(new Object[] { dichvu.getMaDV(), dichvu.getTenDV(), dichvu.getGiaDV() });
		}
	}

	private void SuKienTableDichVuKH() {
		// TODO Auto-generated method stub
		tableDichVuKH.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				btnXoaBo.setEnabled(true);
			}
		});
	}

	private void SuKienXoaBo() {
		// TODO Auto-generated method stub
		btnXoaBo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableDichVuKH.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ không sử dụng");
					return;
				}
				modelDichVuSuDung.removeRow(row);
				CapNhatTableDichVuKS();

				btnXoaBo.setEnabled(false);
				CapNhatTongTienDichVu();
			}
		});
	}

	private void SuKienTableDichVuKS() {
		// TODO Auto-generated method stub
		tableDichVuKS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				btnLuu.setEnabled(true);
			}
		});
	}

	private void SuKienLuu() {
		// TODO Auto-generated method stub
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableDichVuKS.getSelectedRow();
				int soluong = Integer.parseInt(spinner.getValue().toString());
				double dongia = Double.parseDouble(modelDichVu.getValueAt(row, 2).toString());
				double thanhtien = soluong * dongia;
				modelDichVuSuDung.addRow(new Object[] { modelDichVu.getValueAt(row, 0).toString(),
						modelDichVu.getValueAt(row, 1), spinner.getValue(), thanhtien });
				spinner.setValue(1);
				modelDichVu.removeRow(row);
				btnLuu.setEnabled(false);
				CapNhatTongTienDichVu();
			}
		});
	}

	public void setThongTinSoPhong() {

	}

	public void setMaNhanVien(String manhanvien) {
		this.maNhanVien = manhanvien;
	}
}
