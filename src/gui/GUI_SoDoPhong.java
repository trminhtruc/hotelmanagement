package gui;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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

import javax.swing.UIManager;

public class GUI_SoDoPhong extends JPanel {
	private JTextField txtTimKiem;
	private JComboBox<String> cbbLoaiPhong;
	private JComboBox<String> cbbTimKiem;
	private JButton btnTatCa;
	private JButton btnPhongTrong;
	private JButton btnKhachLe;
	private JButton btnTraPhong;
	private JButton btnChiTietDoan;
	private JButton btnChiTietPhong;
	private JButton btnKhachDoan;
	private DAO_PHONG dao_phong = new DAO_PHONG();
	private DAO_CTHDP dao_cthdPhong = new DAO_CTHDP();
	private DAO_HDPHONG dao_hoaDonPhong = new DAO_HDPHONG();
	private DAO_KHACHHANG dao_khachHang = new DAO_KHACHHANG();
	private DAO_HDDICHVU dao_hoaDonDV = new DAO_HDDICHVU();
	private DAO_CTHDDV dao_cthdDV = new DAO_CTHDDV();
	private DAO_DICHVU dao_dichvu = new DAO_DICHVU();
	private DAO_LOAIPHONG dao_loaiPhong = new DAO_LOAIPHONG();
	private DAO_NHANVIEN dao_nhanVien = new DAO_NHANVIEN();
	private JButton[] b1;
	private JLabel lblSoPhong;
	private JPanel panel;
	private JDialog dialogThanhToan;

	private final JPanel TTjpanel = new JPanel();
	private JTextField dlTTtxtTienDichVu;
	private JTextField dlTTtxtTienPhong;
	private JTextField dlTTTongTien;
	private JTextField dlTTKhachDua;
	private JTextField dlTTTienThua;
	private JButton dlTTbtnThanhToan;
	private JButton dlTTThoat;
	private int soluong;
	private String maNhanVien;

	/**
	 * Create the panel.
	 * 
	 * @throws ClassNotFoundException
	 */
	public GUI_SoDoPhong() throws ClassNotFoundException {
		setBackground(new Color(255, 218, 185));
		setLayout(null);
		setSize(1195, 635);

		ConnectDB.getInstance().connect();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 78, 1195, 469);
		add(scrollPane);

		panel = new JPanel();
		panel.setBackground(new Color(204, 153, 153));
		scrollPane.setViewportView(panel);
		panel.setPreferredSize(new Dimension(100, 800));
		panel.setLayout(null);

		soluong = 0;
		for (PHONG phong : dao_phong.getAlltbPhong()) {
			if (phong.getTinhTrang().equals("SỬA CHỮA")) {
				soluong++;
			}
		}

		int y = 7;
		int x = 35;
		// System.out.println(dao_phong.getAlltbPhong().size());
		b1 = new JButton[dao_phong.getAlltbPhong().size() - soluong];
		int i = 0;
		for (PHONG element : dao_phong.getAlltbPhong()) {
			if (!element.getTinhTrang().equals("SỬA CHỮA")) {

				b1[i] = new JButton("Phòng " + element.getSoPhong());

				panel.add(b1[i]);
//			if(i == 13) {
//				b1.setText("<html>Phòng 14 <br/> KIM ĐỊNH <br/> 0905.345.132</html>");
//			}
				b1[i].setBounds(x, y, 120, 70);
				x += 140;
				if (x % 1155 == 0) {

					y += 80;
					x = 35;
				}
				if (element.getTinhTrang().trim().equals("TRỐNG")) {
					b1[i].setBackground(new Color(51, 204, 102));
				}
				if (element.getTinhTrang().trim().equals("KHÁCH ĐOÀN")) {
					b1[i].setBackground(new Color(204, 0, 51));
				}
				if (element.getTinhTrang().trim().equals("KHÁCH LẺ")) {
					b1[i].setBackground(new Color(255, 51, 102));
				}
//				int z;
//				for (HOADONPHONG hoaDonPhong : dao_hoaDonPhong.getalltbHDP()) {
//					z = 0;
//					for (CTHDPHONG cthoaDonPHong : dao_cthdPhong.getalltbHDP()) {
//						if (hoaDonPhong.getMaHDP().equals(cthoaDonPHong.getHdP().getMaHDP())) {
//							z++;
//							// System.out.println(z +hoaDonPhong.getMaHDP() +"====="
//							// +cthoaDonPHong.getHdP().getMaHDP());
//							System.out.println(z + element.getSoPhong());
//							// &&cthoaDonPHong.getPhong().getSoPhong().equals(element.getSoPhong())
//						}
//					}
//					System.out.println(z + " da ra =========");
//					if (z == 1) {
//						b1[i].setBackground(new Color(255, 51, 102));// le
//
////						System.out.println("le");
////						System.out.println(i+"khach le       dfwaef");
//					} else if (z > 1) {
//						// System.out.println("doan");
//						b1[i].setBackground(new Color(204, 0, 51));// doan
//					}
//				}
//			}
//			System.out.println("lan thu " + i);
				i++;
			}
//			// System.out.println(x);
		}

//		for(int i =0 ;i<dao_phong.getAlltbPhong().size();i++) {
//			
//			JButton b1 = new  JButton("Phòng "+(i+1));
//			panel.add(b1);
//			if(i == 13) {
//				b1.setText("<html>Phòng 14 <br/> KIM ĐỊNH <br/> 0905.345.132</html>");
//			}
//			b1.setBounds(x, y, 120, 70);
//			x+=140;
//			if(x%1155==0) {
//				
//				y+=80;
//				x= 35;
//			}
//			b1.setBackground(new Color(51, 204, 102));
//		//	System.out.println(x);
//		}

		cbbLoaiPhong = new JComboBox();
		cbbLoaiPhong.setBounds(730, 27, 149, 22);
		add(cbbLoaiPhong);

		JLabel lblNewLabel = new JLabel("LOẠI PHÒNG");
		lblNewLabel.setBounds(641, 30, 77, 16);
		add(lblNewLabel);

		cbbTimKiem = new JComboBox();
		cbbTimKiem.setBounds(891, 27, 164, 22);
		add(cbbTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(1067, 27, 116, 22);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTatCa = new JButton("--TẤT CẢ");
		btnTatCa.setForeground(Color.WHITE);
		btnTatCa.setBackground(Color.LIGHT_GRAY);
		btnTatCa.setBounds(12, 10, 127, 55);
		add(btnTatCa);

		btnPhongTrong = new JButton("PHÒNG TRỐNG");
		btnPhongTrong.setForeground(Color.WHITE);
		btnPhongTrong.setBackground(new Color(51, 204, 102));
		btnPhongTrong.setBounds(173, 10, 127, 55);
		add(btnPhongTrong);

		btnKhachLe = new JButton("KHÁCH LẺ");
		btnKhachLe.setForeground(Color.WHITE);
		btnKhachLe.setBackground(new Color(255, 51, 102));
		btnKhachLe.setBounds(335, 11, 127, 55);
		add(btnKhachLe);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CH\u1EE8C N\u0102NG",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(240, 560, 301, 62);
		add(panel_1);
		panel_1.setLayout(null);

		btnTraPhong = new JButton("TRẢ PHÒNG");
		btnTraPhong.setForeground(Color.WHITE);
		btnTraPhong.setBackground(new Color(0, 153, 102));
		btnTraPhong.setBounds(91, 24, 127, 25);
		panel_1.add(btnTraPhong);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(244, 164, 96));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "TH\u00D4NG TIN",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(566, 560, 617, 62);
		add(panel_2);
		panel_2.setLayout(null);

		btnChiTietDoan = new JButton("CHI TIẾT ĐOÀN");
		btnChiTietDoan.setForeground(Color.WHITE);
		btnChiTietDoan.setBackground(new Color(51, 102, 204));
		btnChiTietDoan.setBounds(357, 24, 200, 25);
		panel_2.add(btnChiTietDoan);

		btnChiTietPhong = new JButton("CHI TIẾT PHÒNG");
		btnChiTietPhong.setForeground(Color.WHITE);
		btnChiTietPhong.setBackground(new Color(51, 153, 204));
		btnChiTietPhong.setBounds(74, 24, 211, 25);
		panel_2.add(btnChiTietPhong);

		btnKhachDoan = new JButton("KHÁCH ĐOÀN");
		btnKhachDoan.setForeground(Color.WHITE);
		btnKhachDoan.setBackground(new Color(204, 0, 51));
		btnKhachDoan.setBounds(496, 10, 127, 55);
		add(btnKhachDoan);

		lblSoPhong = new JLabel("");
		lblSoPhong.setFont(new Font("Cambria", Font.BOLD, 23));
		lblSoPhong.setBounds(12, 572, 164, 38);
		add(lblSoPhong);

		DAO_LOAIPHONG loaiphong = new DAO_LOAIPHONG();
		List<LOAIPHONG> listphong = loaiphong.getalltbLoaiPhong();
		cbbLoaiPhong.addItem("--TẤT CẢ");
		for (LOAIPHONG loaiphong2 : listphong) {
			System.out.println(loaiphong2.getMaLoaiPhong());
			cbbLoaiPhong.addItem(loaiphong2.getMaLoaiPhong());
		}
		cbbTimKiem.addItem("Tìm theo tên");
		cbbTimKiem.addItem("Tìm theo sdt");
		cbbTimKiem.addItem("Tìm theo cmnd");

		SuKienButtonKhachDoan();
		SuKienButtonKhachLe();
		SuKienButtonTatCa();
		SuKienButtonTrong();
		SuKienComboBoxLoaiPhong();
		SuKienTxtTimKiem();
		SuKienCacPhong();
		SuKienThongTinKHLe();
		SuKienThongTinKHDoan();
		SuKienButtonTraPhong();

		dialogThanhToan = new JDialog();
		DialogThanhToanNhanh();
		dialogThanhToan.setModal(true);
		dialogThanhToan.setLocation(600, 300);
		dialogThanhToan.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// System.out.println(b1[4].getBackground());
		dialogThanhToan.addWindowListener(new WindowListener() {

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
				dlTTTienThua.setText("");
				dlTTtxtTienPhong.setText("");
				dlTTtxtTienDichVu.setText("");
				dlTTKhachDua.setText("");
				dlTTTongTien.setText("");
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
	}

	private void DialogThanhToanNhanh() {
		// TODO Auto-generated method stub

		dialogThanhToan.setBounds(100, 100, 382, 305);
		dialogThanhToan.getContentPane().setLayout(new BorderLayout());
		TTjpanel.setBackground(new Color(102, 102, 204));
		TTjpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialogThanhToan.getContentPane().add(TTjpanel, BorderLayout.CENTER);
		TTjpanel.setLayout(null);
		{
			JLabel lblTngTinDch = new JLabel("TỔNG TIỀN DỊCH VỤ");
			lblTngTinDch.setForeground(Color.WHITE);
			lblTngTinDch.setBounds(12, 28, 136, 16);
			TTjpanel.add(lblTngTinDch);
		}
		{
			dlTTtxtTienDichVu = new JTextField();
			dlTTtxtTienDichVu.setBounds(150, 25, 177, 22);
			TTjpanel.add(dlTTtxtTienDichVu);
			dlTTtxtTienDichVu.setColumns(10);
		}
		{
			JLabel lblTngTinPhng = new JLabel("TỔNG TIỀN PHÒNG");
			lblTngTinPhng.setForeground(Color.WHITE);
			lblTngTinPhng.setBounds(12, 57, 136, 16);
			TTjpanel.add(lblTngTinPhng);
		}
		{
			dlTTtxtTienPhong = new JTextField();
			dlTTtxtTienPhong.setBounds(150, 54, 177, 22);
			TTjpanel.add(dlTTtxtTienPhong);
			dlTTtxtTienPhong.setColumns(10);
		}
		{
			JLabel lblVnd = new JLabel("VND");
			lblVnd.setForeground(Color.WHITE);
			lblVnd.setBounds(332, 28, 32, 16);
			TTjpanel.add(lblVnd);
		}
		{
			JLabel label = new JLabel("VND");
			label.setForeground(Color.WHITE);
			label.setBounds(332, 57, 32, 16);
			TTjpanel.add(label);
		}
		{
			dlTTTongTien = new JTextField();
			dlTTTongTien.setBounds(150, 113, 177, 22);
			TTjpanel.add(dlTTTongTien);
			dlTTTongTien.setColumns(10);
		}
		{
			JLabel lblTngTin = new JLabel("TỔNG TIỀN");
			lblTngTin.setForeground(Color.WHITE);
			lblTngTin.setBounds(12, 116, 88, 16);
			TTjpanel.add(lblTngTin);
		}
		{
			JLabel label = new JLabel("VND");
			label.setForeground(Color.WHITE);
			label.setBounds(332, 116, 32, 16);
			TTjpanel.add(label);
		}
		{
			dlTTKhachDua = new JTextField();
			dlTTKhachDua.setBounds(150, 148, 177, 22);
			TTjpanel.add(dlTTKhachDua);
			dlTTKhachDua.setColumns(10);
		}
		{
			JLabel lblTinKhcha = new JLabel("Tiền khách đưa");
			lblTinKhcha.setForeground(Color.WHITE);
			lblTinKhcha.setBounds(12, 151, 110, 16);
			TTjpanel.add(lblTinKhcha);
		}
		{
			JLabel lblTinTha = new JLabel("Tiền thừa");
			lblTinTha.setForeground(Color.WHITE);
			lblTinTha.setBounds(12, 180, 73, 16);
			TTjpanel.add(lblTinTha);
		}
		{
			dlTTTienThua = new JTextField();
			dlTTTienThua.setBounds(150, 177, 177, 22);
			TTjpanel.add(dlTTTienThua);
			dlTTTienThua.setColumns(10);
		}
		{
			JLabel label = new JLabel("VND");
			label.setForeground(Color.WHITE);
			label.setBounds(332, 151, 32, 16);
			TTjpanel.add(label);
		}
		{
			JLabel label = new JLabel("VND");
			label.setForeground(Color.WHITE);
			label.setBounds(332, 180, 32, 16);
			TTjpanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			dialogThanhToan.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				dlTTbtnThanhToan = new JButton("THANH TOÁN");
				dlTTbtnThanhToan.setForeground(Color.WHITE);
				dlTTbtnThanhToan.setBackground(new Color(0, 153, 102));
				dlTTbtnThanhToan.setActionCommand("OK");
				buttonPane.add(dlTTbtnThanhToan);
				dialogThanhToan.getRootPane().setDefaultButton(dlTTbtnThanhToan);
			}
			{
				dlTTThoat = new JButton("THOÁT");
				dlTTThoat.setForeground(Color.WHITE);
				dlTTThoat.setBackground(new Color(255, 0, 102));
				dlTTThoat.setActionCommand("Cancel");
				buttonPane.add(dlTTThoat);
			}
		}
		dlTTTongTien.setEditable(false);
		dlTTtxtTienDichVu.setEditable(false);
		dlTTtxtTienPhong.setEditable(false);
		dlTTTienThua.setEditable(false);
		dlTTbtnThanhToan.setEnabled(false);
		SuKienTXTTienKhachDua();
		dlTTbtnThanhToan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int returnValue = 0;
				returnValue = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thanh toán chứ",
						"Thanh toán nhanh", JOptionPane.YES_NO_OPTION);
				if (returnValue == JOptionPane.YES_OPTION) {

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

					String soPhong = lblSoPhong.getText().substring(6, 12);
					String maHoaDonPhong = "";
					String maKhachHang = "";
					for (CTHDPHONG cthdPhong : dao_cthdPhong.getalltbHDP()) {
						if (cthdPhong.getPhong().getSoPhong().equals(soPhong)) {
							maHoaDonPhong = cthdPhong.getHdP().getMaHDP();
						}
					}
				
					for (HOADONPHONG hdPhong : dao_hoaDonPhong.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDonPhong)) {
							maKhachHang = hdPhong.getKhachHang().getMaKH();
							
						}
					}
					String tenKhachHang = "";
					String soDienThoai = "";
					String mahoadon = "";
					for (KHACHHANG khachhang : dao_khachHang.getalltbKhachHang()) {
						if (maKhachHang.equals(khachhang.getMaKH())) {
							tenKhachHang = khachhang.getTenKH();
							soDienThoai = khachhang.getSDT();
						}
					}
					for (HOADONDICHVU ma : dao_hoaDonDV.getalltbHDDV()) {
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
					double tienNhan = Double.parseDouble(dlTTKhachDua.getText());
					double tienThua = Double.parseDouble(dlTTTienThua.getText());
					
					double tongtienPhong = 0;
					double tienNhanPhong = Double.parseDouble(dlTTKhachDua.getText());
					double tienThuaPhong = Double.parseDouble(dlTTTienThua.getText());

					for (HOADONDICHVU hdDichVu : dao_hoaDonDV.getalltbHDDV()) {
						if (hdDichVu.getKhachHang().getMaKH().equals(maKhachHang)
								&& hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
							for (CTHDDV ctdichvu : dao_cthdDV.getalltbCTHDDV()) {
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
					
					for (HOADONPHONG hdPhong : dao_hoaDonPhong.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDonPhong)) {
							maKhachHang = hdPhong.getKhachHang().getMaKH();
							int khoangCach = 1;
							for (CTHDPHONG cthoaDon : dao_cthdPhong.getalltbHDP()) {
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
							tongtienPhong = tongtienPhong * khoangCach;
						}
					}
					
			//		int tam = 1;
					int soluong;
					double dongia;

					int temp = 0;
					Map<String, Object> m2 = new HashMap<String, Object>();
					for (HOADONDICHVU hdDichVu : dao_hoaDonDV.getalltbHDDV()) {
						if (hdDichVu.getKhachHang().getMaKH().equals(maKhachHang)
								&& hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
							for (CTHDDV ctdichvu : dao_cthdDV.getalltbCTHDDV()) {
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
					int i =1;
					for (HOADONPHONG hdPhong : dao_hoaDonPhong.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDonPhong)) {
							maKhachHang = hdPhong.getKhachHang().getMaKH();
							int khoangCach = 1;
							for (CTHDPHONG cthoaDon : dao_cthdPhong.getalltbHDP()) {
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
											for (LOAIPHONG loaiphong : dao_loaiPhong.getalltbLoaiPhong()) {
												if (loaiphong.getMaLoaiPhong()
														.equals(phong.getPhong().getMaLoaiPhong())) {
													if (tempPhong == 0) {
														mPhong.put("STT", String.valueOf(i));
													//	soluong = ctdichvu.getSoluong();
														mPhong.put("loaiphong", loaiphong.getMaLoaiPhong());
													//	dongia = dichvu.getGiaDV();
														mPhong.put("sophong",phong.getSoPhong());
														mPhong.put("dongiaphong",String.valueOf(loaiphong.getDonGia()));
														
														//m.put("thanhtien", soluong * dongia);
														mPhong.put("tongtien", tongtienPhong);
														mPhong.put("tiennhan", tienNhan);
														mPhong.put("tientra", tienThua);
														mPhong.put("songay", String.valueOf(khoangCach));
														dataSourcePhong.add(mPhong);
														tempPhong = 1;i++;
													} else {
														
														Map<String, Object> m3 = new HashMap<String, Object>();
															m3.put("STT", String.valueOf(i));
														//	soluong = ctdichvu.getSoluong();
															m3.put("loaiphong", loaiphong.getMaLoaiPhong());
														//	dongia = dichvu.getGiaDV();
															m3.put("sophong",phong.getSoPhong());
															m3.put("dongiaphong",String.valueOf(loaiphong.getDonGia()));
															
															//m.put("thanhtien", soluong * dongia);
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
					
					
					

					String soPhong2 = lblSoPhong.getText().substring(6, 12);
					String maHoaDonPhong2 = "";
					String maKhachHang2 = "";
					for (CTHDPHONG cthdPhong : dao_cthdPhong.getalltbHDP()) {
						if (cthdPhong.getPhong().getSoPhong().equals(soPhong2)) {
							maHoaDonPhong2 = cthdPhong.getHdP().getMaHDP();
						}
					}
					for (HOADONPHONG hdPhong : dao_hoaDonPhong.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDonPhong)) {
							maKhachHang2 = hdPhong.getKhachHang().getMaKH();
						}
					}
					for (CTHDPHONG cthdPhong : dao_cthdPhong.getalltbHDP()) {
						if (cthdPhong.getHdP().getMaHDP().equals(maHoaDonPhong2)) {
							if (cthdPhong.getTinhTrang().equals("NHẬN PHÒNG")) {
								dao_cthdPhong.CapNhat(cthdPhong.getPhong().getSoPhong(), LocalDateTime.now().toString(),
										"TRẢ PHÒNG");
								for (PHONG phong : dao_phong.getAlltbPhong()) {
									if (phong.getSoPhong().equals(cthdPhong.getPhong().getSoPhong())) {
										for (LOAIPHONG loaiPhong : dao_loaiPhong.getalltbLoaiPhong()) {
											if (loaiPhong.getMaLoaiPhong().equals(phong.getPhong().getMaLoaiPhong())) {
												dao_phong.CapNhatPhong(cthdPhong.getPhong().getSoPhong(),
														loaiPhong.getMaLoaiPhong(), "TRỐNG");
											}
										}

									}
								}
							}
						}
					}
					for (HOADONDICHVU hdDichVu : dao_hoaDonDV.getalltbHDDV()) {
						if (maKhachHang2.equals(hdDichVu.getKhachHang().getMaKH())) {
							if (hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
								dao_hoaDonDV.updateHoaDonDichVu(hdDichVu.getMaHDDV(), "ĐÃ THANH TOÁN");
							}
						}
					}
					dialogThanhToan.setVisible(false);
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
					
					if (!mahoadon.equals(""))
						frame.setVisible(true);
					framephong.setVisible(true);

					dialogThanhToan.setVisible(false);

				} else if (returnValue == JOptionPane.NO_OPTION) {

				}
			}
		});
		dlTTThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialogThanhToan.setVisible(false);
			}
		});
	}

	private void SuKienTXTTienKhachDua() {
		// TODO Auto-generated method stub
		dlTTKhachDua.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (dlTTKhachDua.getText().trim().equals("")) {
					return;
				}
				dlTTbtnThanhToan.setEnabled(true);
				try {
					double tienKhachDua = Double.parseDouble(dlTTKhachDua.getText());
					double tongTien = Double.parseDouble(dlTTTongTien.getText());
					if (tienKhachDua - tongTien >= 0) {
						dlTTbtnThanhToan.setEnabled(true);
						dlTTTienThua.setText(String.valueOf(tienKhachDua - tongTien));
					} else {
						dlTTbtnThanhToan.setEnabled(false);
						dlTTTienThua.setText("");
					}

				} catch (Exception e) {
					// TODO: handle exception

					dlTTTienThua.setText("");
					dlTTbtnThanhToan.setEnabled(false);

				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void SuKienButtonTraPhong() {
		// TODO Auto-generated method stub
		btnTraPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (lblSoPhong.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng");
					return;
				}
				int tam = 0;
				for (CTHDPHONG chitiet : dao_cthdPhong.getalltbHDP()) {
					if (chitiet.getPhong().getSoPhong().equals(lblSoPhong.getText().substring(6, 12))) {
						if (chitiet.getTinhTrang().equals("NHẬN PHÒNG"))
							tam = 1;
					}
				}
				if (tam == 0) {
					JOptionPane.showMessageDialog(null, "Phòng trống");
					return;
				} else {
					double tongTienDichVu = 0;
					double tongTienPhong = 0;
					String soPhong = lblSoPhong.getText().substring(6, 12);
					String maHoaDon = "";
					String maKhachHang = "";
					for (CTHDPHONG cthdP : dao_cthdPhong.getalltbHDP()) {
						if (cthdP.getPhong().getSoPhong().equals(soPhong)) {
							maHoaDon = cthdP.getHdP().getMaHDP();
						}
					}
					for (HOADONPHONG hdPhong : dao_hoaDonPhong.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(maHoaDon)) {
							maKhachHang = hdPhong.getKhachHang().getMaKH();
							int khoangCach = 1;
							for (CTHDPHONG cthoaDon : dao_cthdPhong.getalltbHDP()) {
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
											for (LOAIPHONG loaiphong : dao_loaiPhong.getalltbLoaiPhong()) {
												if (loaiphong.getMaLoaiPhong()
														.equals(phong.getPhong().getMaLoaiPhong())) {
													tongTienPhong += loaiphong.getDonGia();
												}
											}
										}
									}
								}
							}
							tongTienPhong = tongTienPhong * khoangCach;
						}
					}
					for (HOADONDICHVU hdDichVu : dao_hoaDonDV.getalltbHDDV()) {
						if (hdDichVu.getKhachHang().getMaKH().equals(maKhachHang)
								&& hdDichVu.getTinhtrang().equals("CHƯA THANH TOÁN")) {
							for (CTHDDV ctdichvu : dao_cthdDV.getalltbCTHDDV()) {
								if (ctdichvu.getHddv().getMaHDDV().equals(hdDichVu.getMaHDDV())) {
									for (DICHVU dichvu : dao_dichvu.getalltbDichVu()) {
										if (dichvu.getMaDV().equals(ctdichvu.getDichVu().getMaDV())) {
											tongTienDichVu += (ctdichvu.getSoluong() * dichvu.getGiaDV());
										}
									}
								}
							}
						}
					}
					dlTTtxtTienDichVu.setText(String.valueOf(tongTienDichVu));
					dlTTtxtTienPhong.setText(String.valueOf(tongTienPhong));
					dlTTTongTien.setText(String.valueOf(tongTienDichVu + tongTienPhong));
					dialogThanhToan.setVisible(true);
				}
			}
		});
	}

	private void SuKienThongTinKHDoan() {
		// TODO Auto-generated method stub
		btnChiTietDoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (lblSoPhong.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng");
					return;
				}
				String mahd = "";
				int tam = 0;
				for (CTHDPHONG chitiet : dao_cthdPhong.getalltbHDP()) {
					if (chitiet.getPhong().getSoPhong().equals(lblSoPhong.getText().substring(6, 12))) {
						if (chitiet.getTinhTrang().equals("NHẬN PHÒNG")) {
							tam = 1;
							mahd = chitiet.getHdP().getMaHDP();
						}

					}
				}
				if (tam != 0) {
					int num = 0;
					for (HOADONPHONG hoaDonPhong : dao_hoaDonPhong.getalltbHDP()) {
						if (mahd.equals(hoaDonPhong.getMaHDP())) {
							for (CTHDPHONG ct : dao_cthdPhong.getalltbHDP()) {
								if (ct.getHdP().getMaHDP().equals(hoaDonPhong.getMaHDP())) {
									num++;
								}
							}
						}
					}
					if (num == 1) {
						JOptionPane.showMessageDialog(null, "ĐÂY LÀ KHÁCH LẺ. BẠN KHÔNG THỂ XEM CHI TIẾT ĐOÀN");
						return;
					}
				}

				if (tam == 0) {
					JOptionPane.showMessageDialog(null, "Phòng trống");
					return;
				}
				// System.out.println(lblSoPhong.getText().substring(6, 12));
				setVisible(false);
				removeAll();

				GUI_ThongTin tl;

				try {
					add(tl = new GUI_ThongTin(lblSoPhong.getText().substring(6, 12), "KHÁCH ĐOÀN"));
					tl.setMaNhanVien(maNhanVien);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				setVisible(true);
			}
		});
	}

	private void SuKienThongTinKHLe() {
		// TODO Auto-generated method stub
		btnChiTietPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (lblSoPhong.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng");
					return;
				}
				int tam = 0;
				for (CTHDPHONG chitiet : dao_cthdPhong.getalltbHDP()) {
					if (chitiet.getPhong().getSoPhong().equals(lblSoPhong.getText().substring(6, 12))) {
						if (chitiet.getTinhTrang().equals("NHẬN PHÒNG"))
							tam = 1;
					}
				}
				if (tam == 0) {
					JOptionPane.showMessageDialog(null, "Phòng trống");
					return;
				}
				// System.out.println(lblSoPhong.getText().substring(6, 12));

				setVisible(false);
				removeAll();

				GUI_ThongTin tl;

				try {
					add(tl = new GUI_ThongTin(lblSoPhong.getText().substring(6, 12), "KHÁCH LẺ"));
					tl.setMaNhanVien(maNhanVien);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				setVisible(true);
			}
		});
	}

	private void SuKienCacPhong() {
		// TODO Auto-generated method stub
		// b1 = new JButton[dao_phong.getAlltbPhong().size()];

		for (int i = 0; i < (dao_phong.getAlltbPhong().size() - soluong); i++) {

			JButton phong = b1[i];
			for (CTHDPHONG cthdPhong : dao_cthdPhong.getalltbHDP()) {
				if (phong.getText().substring(6, 12).equals(cthdPhong.getPhong().getSoPhong())
						&& cthdPhong.getTinhTrang().equals("NHẬN PHÒNG")) {
					for (HOADONPHONG hdPhong : dao_hoaDonPhong.getalltbHDP()) {
						if (hdPhong.getMaHDP().equals(cthdPhong.getHdP().getMaHDP())) {
							for (KHACHHANG khachHang : dao_khachHang.getalltbKhachHang()) {
								if (khachHang.getMaKH().equals(hdPhong.getKhachHang().getMaKH())) {
									b1[i].setToolTipText(khachHang.getTenKH());
								}
							}
						}
					}
				}
			}
			b1[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					lblSoPhong.setText(phong.getText());

				}
			});
		}
	}

	private void HienThiTatCaLenSoDo() {
		panel.setVisible(false);
		panel.removeAll();
		// panel = new JPanel();
		int y = 7;
		int x = 35;

		int i = 0;
		for (PHONG element : dao_phong.getAlltbPhong()) {
			System.out.println(i);
			if (!element.getTinhTrang().equals("SỬA CHỮA")) {
				b1[i] = new JButton("Phòng " + element.getSoPhong());
				panel.add(b1[i]);
				b1[i].setBounds(x, y, 120, 70);
				x += 140;
				if (x % 1155 == 0) {
					y += 80;
					x = 35;
				}
				if (element.getTinhTrang().trim().equals("TRỐNG")) {
					b1[i].setBackground(new Color(51, 204, 102));
				}
				if (element.getTinhTrang().trim().equals("KHÁCH ĐOÀN")) {
					b1[i].setBackground(new Color(204, 0, 51));
				}
				if (element.getTinhTrang().trim().equals("KHÁCH LẺ")) {
					b1[i].setBackground(new Color(255, 51, 102));
				}
			}
			i++;
		}

		SuKienCacPhong();
		panel.setVisible(true);
	}

	private void SuKienTxtTimKiem() {
		// TODO Auto-generated method stub
		txtTimKiem.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (cbbTimKiem.getSelectedItem().equals("Tìm theo tên")) {
					String maKhachHang = "";
					List<String> listSoPhong = new ArrayList<String>();
					if (!txtTimKiem.getText().trim().equals("")) {
						for (KHACHHANG khachhang : dao_khachHang.getalltbKhachHang()) {
							if (khachhang.getTenKH().toUpperCase().contains(txtTimKiem.getText().toUpperCase())) {
								maKhachHang = khachhang.getMaKH();
							}
						}
					} else {
						HienThiTatCaLenSoDo();
						return;
					}
					String maHoaDonP = "";
					if (maKhachHang.trim() != "") {
						// System.out.println("bang nhai");
						for (HOADONPHONG hoadonphong : dao_hoaDonPhong.getalltbHDP()) {
							if (hoadonphong.getKhachHang().getMaKH().equals(maKhachHang)) {
								maHoaDonP = hoadonphong.getMaHDP();
							}
						}
					}
					System.out.println(maHoaDonP);
					if (maHoaDonP != "") {
						HienThiTatCaLenSoDoTheoMaHoaDon(maHoaDonP);
					}

				} else if (cbbTimKiem.getSelectedItem().equals("Tìm theo sdt")) {
					String maKhachHang = "";
					List<String> listSoPhong = new ArrayList<String>();
					if (!txtTimKiem.getText().trim().equals("")) {
						for (KHACHHANG khachhang : dao_khachHang.getalltbKhachHang()) {
							if (khachhang.getSDT().contains(txtTimKiem.getText().toUpperCase())) {
								maKhachHang = khachhang.getMaKH();
							}
						}
					} else {
						HienThiTatCaLenSoDo();
						return;
					}
					String maHoaDonP = "";
					if (maKhachHang.trim() != "") {
						// System.out.println("bang nhai");
						for (HOADONPHONG hoadonphong : dao_hoaDonPhong.getalltbHDP()) {
							if (hoadonphong.getKhachHang().getMaKH().equals(maKhachHang)) {
								maHoaDonP = hoadonphong.getMaHDP();
							}
						}
					}
					System.out.println(maHoaDonP);
					if (maHoaDonP != "") {
						HienThiTatCaLenSoDoTheoMaHoaDon(maHoaDonP);
					}
				} else if (cbbTimKiem.getSelectedItem().equals("Tìm theo cmnd")) {
					String maKhachHang = "";
					List<String> listSoPhong = new ArrayList<String>();
					if (!txtTimKiem.getText().trim().equals("")) {
						for (KHACHHANG khachhang : dao_khachHang.getalltbKhachHang()) {
							if (khachhang.getCMND().contains(txtTimKiem.getText().toUpperCase())) {
								maKhachHang = khachhang.getMaKH();
							}
						}
					} else {
						HienThiTatCaLenSoDo();
						return;
					}
					String maHoaDonP = "";
					if (maKhachHang.trim() != "") {
						// System.out.println("bang nhai");
						for (HOADONPHONG hoadonphong : dao_hoaDonPhong.getalltbHDP()) {
							if (hoadonphong.getKhachHang().getMaKH().equals(maKhachHang)) {
								maHoaDonP = hoadonphong.getMaHDP();
							}
						}
					}
					System.out.println(maHoaDonP);
					if (maHoaDonP != "") {
						HienThiTatCaLenSoDoTheoMaHoaDon(maHoaDonP);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void HienThiTatCaLenSoDoTheoMaHoaDon(String maHoaDonP) {
		panel.setVisible(false);
		panel.removeAll();
		// panel = new JPanel();
		int y = 7;
		int x = 35;

		for (CTHDPHONG cthdPhong : dao_cthdPhong.getalltbHDP()) {
			int i = 0;
			for (PHONG element : dao_phong.getAlltbPhong()) {
				if (cthdPhong.getPhong().getSoPhong().equals(element.getSoPhong())
						&& cthdPhong.getHdP().getMaHDP().equals(maHoaDonP)
						&& cthdPhong.getTinhTrang().equals("NHẬN PHÒNG")) {
					System.out.println(i);
					b1[i] = new JButton("Phòng " + element.getSoPhong());
					panel.add(b1[i]);
					b1[i].setBounds(x, y, 120, 70);
					x += 140;
					if (x % 1155 == 0) {
						y += 80;
						x = 35;
					}
					if (element.getTinhTrang().trim().equals("TRỐNG")) {
						b1[i].setBackground(new Color(51, 204, 102));
					}
					if (element.getTinhTrang().trim().equals("KHÁCH ĐOÀN")) {
						b1[i].setBackground(new Color(204, 0, 51));
					}
					if (element.getTinhTrang().trim().equals("KHÁCH LẺ")) {
						b1[i].setBackground(new Color(255, 51, 102));
					}
				}
				i++;
			}
		}
		SuKienCacPhong();
		panel.setVisible(true);
	}

	private void SuKienComboBoxLoaiPhong() {
		// TODO Auto-generated method stub
		cbbLoaiPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				panel.setVisible(false);
				panel.removeAll();
				// panel = new JPanel();
				int y = 7;
				int x = 35;
				int i = 0;
				for (PHONG element : dao_phong.getAlltbPhong()) {
					if (element.getPhong().getMaLoaiPhong().trim().equals(cbbLoaiPhong.getSelectedItem())
							|| cbbLoaiPhong.getSelectedItem().equals("--TẤT CẢ")) {
						if (!element.getTinhTrang().equals("SỬA CHỮA")) {

							b1[i] = new JButton("Phòng " + element.getSoPhong());
							panel.add(b1[i]);
							b1[i].setBounds(x, y, 120, 70);
							x += 140;
							if (x % 1155 == 0) {
								y += 80;
								x = 35;
							}
							if (element.getTinhTrang().trim().equals("TRỐNG")) {
								b1[i].setBackground(new Color(51, 204, 102));
							}
							if (element.getTinhTrang().trim().equals("KHÁCH ĐOÀN")) {
								b1[i].setBackground(new Color(204, 0, 51));
							}
							if (element.getTinhTrang().trim().equals("KHÁCH LẺ")) {
								b1[i].setBackground(new Color(255, 51, 102));
							}
						}
					}
					i++;
				}
				SuKienCacPhong();
				panel.setVisible(true);
			}
		});
	}

	private void SuKienButtonTrong() {
		// TODO Auto-generated method stub
		btnPhongTrong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(false);
				panel.removeAll();
				// panel = new JPanel();
				int y = 7;
				int x = 35;
				int i = 0;
				for (PHONG element : dao_phong.getAlltbPhong()) {
					if (element.getTinhTrang().trim().equals("TRỐNG")) {
						b1[i] = new JButton("Phòng " + element.getSoPhong());
						panel.add(b1[i]);
						b1[i].setBounds(x, y, 120, 70);
						x += 140;
						if (x % 1155 == 0) {
							y += 80;
							x = 35;
						}
						b1[i].setBackground(new Color(51, 204, 102));
					}
					i++;
				}
				SuKienCacPhong();
				panel.setVisible(true);

			}
		});
	}

	public void SuKienButtonKhachLe() {
		// TODO Auto-generated method stub
		btnKhachLe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(false);
				panel.removeAll();
				// panel = new JPanel();
				int y = 7;
				int x = 35;
				int i = 0;
				for (PHONG element : dao_phong.getAlltbPhong()) {
					if (element.getTinhTrang().trim().equals("KHÁCH LẺ")) {
						b1[i] = new JButton("Phòng " + element.getSoPhong());
						panel.add(b1[i]);
						b1[i].setBounds(x, y, 120, 70);
						x += 140;
						if (x % 1155 == 0) {

							y += 80;
							x = 35;
						}
						b1[i].setBackground(new Color(255, 51, 102));
					}
					i++;
				}
				SuKienCacPhong();
				panel.setVisible(true);
			}
		});
	}

	private void SuKienButtonTatCa() {
		// TODO Auto-generated method stub
		btnTatCa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(false);
				panel.removeAll();
				int y = 7;
				int x = 35;
				// System.out.println(dao_phong.getAlltbPhong().size());
				int i = 0;
				for (PHONG element : dao_phong.getAlltbPhong()) {
					if (!element.getTinhTrang().equals("SỬA CHỮA")) {

						b1[i] = new JButton("Phòng " + element.getSoPhong());
						panel.add(b1[i]);
//					if(i == 13) {
//						b1.setText("<html>Phòng 14 <br/> KIM ĐỊNH <br/> 0905.345.132</html>");
//					}
						b1[i].setBounds(x, y, 120, 70);
						x += 140;
						if (x % 1155 == 0) {

							y += 80;
							x = 35;
						}
						if (element.getTinhTrang().trim().equals("TRỐNG")) {
							b1[i].setBackground(new Color(51, 204, 102));
						}
						if (element.getTinhTrang().trim().equals("KHÁCH ĐOÀN")) {
							b1[i].setBackground(new Color(204, 0, 51));
						}
						if (element.getTinhTrang().trim().equals("KHÁCH LẺ")) {
							b1[i].setBackground(new Color(255, 51, 102));
						}
						i++;
					}
					SuKienCacPhong();
					panel.setVisible(true);

				}
			}
		});
	}

	private void SuKienButtonKhachDoan() {
		// TODO Auto-generated method stub
		btnKhachDoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(false);
				panel.removeAll();
				// panel = new JPanel();
				int y = 7;
				int x = 35;
				int i = 0;
				for (PHONG element : dao_phong.getAlltbPhong()) {
					if (element.getTinhTrang().trim().equals("KHÁCH ĐOÀN")) {
						b1[i] = new JButton("Phòng " + element.getSoPhong());
						panel.add(b1[i]);
						b1[i].setBounds(x, y, 120, 70);
						x += 140;
						if (x % 1155 == 0) {

							y += 80;
							x = 35;
						}
						b1[i].setBackground(new Color(204, 0, 51));
					}
					i++;
				}
				SuKienCacPhong();
				panel.setVisible(true);
			}
		});
	}

	public void setMaNhanVien(String manhanvien) {
		this.maNhanVien = manhanvien;
	}
}
