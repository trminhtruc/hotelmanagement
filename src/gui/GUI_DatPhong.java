package gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;

import java.awt.Image;


import ConnectDB.ConnectDB;
import dao.DAO_CTHDP;
import dao.DAO_HDPHONG;
import dao.DAO_KHACHHANG;
import dao.DAO_LOAIPHONG;
import dao.DAO_PHONG;
import entity.CTHDPHONG;
import entity.HOADONPHONG;
import entity.KHACHHANG;
import entity.LOAIPHONG;
import entity.PHONG;

import javax.swing.border.EmptyBorder;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;

public class GUI_DatPhong extends JPanel {

	private JTextField txtSDT;
	private JTextField txtCMND;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtSDT2;
	private JTextField txtCMND2;
	private JTable tablePhong;
	private JTable tableLoaiPhong;
	private DefaultTableModel modelPhong;
	private DefaultTableModel modelLoaiPhong;
	private DAO_LOAIPHONG dao_LoaiPhong;
	private DAO_PHONG dao_phong;

	private JComboBox<String> cbbChonPhong;
	private JButton btnDatPhong;
	private JButton btnLuu, btnXoa;
	private JButton btnThemKhachHang;
	private JButton btnTimSDT;
	private JButton btnTimCMND;
	private DAO_KHACHHANG dao_KhachHang;
	private DAO_HDPHONG dao_hdPhong = new DAO_HDPHONG();
	private DAO_CTHDP dao_cthdPhong = new DAO_CTHDP();
	private SimpleDateFormat formatNgay = new SimpleDateFormat("dd.MM.yyyy");

	private final JPanel contentPanelDialog = new JPanel();
	private JTextField dltxtTenKhachHang;
	private JTextField dltxtSoDienThoai;
	private JTextField dltxtCMND;
	private JButton dlbtnThem;
	private JRadioButton dlrdbtnNam;
	private JRadioButton dlrdbtnNu;
	private ButtonGroup groupButton;

	private JDialog dialog;

	private JLabel lblHinhAnh;
	private String filename = null;
	private byte[] imagePhong = null;
	private String maNhanVien;
	
	/**
	 * Create the panel.
	 * 
	 * @throws ClassNotFoundException
	 */
	public GUI_DatPhong() throws ClassNotFoundException {

		setBackground(new Color(204, 102, 102));
		setLayout(null);
		setSize(1195, 635);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 204));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lo\u1EA1i Ph\u00F2ng",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 31, 383, 591);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 28, 359, 275);
		panel.add(scrollPane);

		
//		{
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		}
		tableLoaiPhong = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tableLoaiPhong.setModel(modelLoaiPhong = new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 lo\u1EA1i ph\u00F2ng", "\u0110\u01A1n Gi\u00E1", "S\u1EE9c ch\u1EE9a" }));
		tableLoaiPhong.getColumnModel().getColumn(1).setPreferredWidth(74);
		scrollPane.setViewportView(tableLoaiPhong);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "H\u00ECnh \u1EA3nh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 316, 359, 262);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setBounds(12, 26, 335, 223);
		panel_1.add(lblHinhAnh);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 153, 204));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng Tin Ph\u00F2ng",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(407, 31, 395, 591);
		add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 112, 371, 402);
		panel_2.add(scrollPane_1);

		tablePhong = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablePhong.setModel(modelPhong = new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 Lo\u1EA1i", "S\u1ED1 Ph\u00F2ng", "Đơn Giá" }));
		scrollPane_1.setViewportView(tablePhong);

		cbbChonPhong = new JComboBox<String>();
		cbbChonPhong.setBounds(197, 31, 130, 22);
		panel_2.add(cbbChonPhong);

		JLabel lblNewLabel_5 = new JLabel("Chọn phòng");
		lblNewLabel_5.setBounds(92, 34, 93, 16);
		panel_2.add(lblNewLabel_5);

		btnLuu = new JButton("LƯU");
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setBackground(new Color(51, 204, 102));
		btnLuu.setBounds(230, 66, 97, 25);
		panel_2.add(btnLuu);

		btnXoa = new JButton("XÓA");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setBackground(new Color(51, 51, 102));
		btnXoa.setBounds(275, 527, 97, 25);
		panel_2.add(btnXoa);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(153, 204, 255));
		panel_3.setForeground(Color.BLACK);
		panel_3.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng Tin Kh\u00E1ch H\u00E0ng",
						TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(814, 31, 369, 591);
		add(panel_3);
		panel_3.setLayout(null);

		btnThemKhachHang = new JButton("THÊM KHÁCH HÀNG MỚI");
		btnThemKhachHang.setBackground(new Color(255, 51, 102));
		btnThemKhachHang.setForeground(new Color(255, 255, 255));
		btnThemKhachHang.setBounds(156, 34, 187, 25);
		panel_3.add(btnThemKhachHang);

		JLabel lblSoDienThoai1 = new JLabel("SỐ ĐIỆN THOẠI");
		lblSoDienThoai1.setBounds(12, 95, 103, 16);
		panel_3.add(lblSoDienThoai1);

		txtSDT = new JTextField();
		txtSDT.setBounds(157, 92, 138, 22);
		panel_3.add(txtSDT);
		txtSDT.setColumns(10);

		JLabel lblCMND1 = new JLabel("CMND/PASSPORT");
		lblCMND1.setBounds(12, 130, 131, 16);
		panel_3.add(lblCMND1);

		txtCMND = new JTextField();
		txtCMND.setBounds(157, 127, 138, 22);
		panel_3.add(txtCMND);
		txtCMND.setColumns(10);

		btnTimSDT = new JButton("TÌM");
		btnTimSDT.setBackground(new Color(0, 153, 102));
		btnTimSDT.setForeground(new Color(0, 0, 0));
		btnTimSDT.setBounds(300, 91, 55, 25);
		panel_3.add(btnTimSDT);

		btnTimCMND = new JButton("TÌM");
		btnTimCMND.setBackground(new Color(0, 153, 102));
		btnTimCMND.setBounds(300, 126, 55, 25);
		panel_3.add(btnTimCMND);

		JPanel panelThongTin = new JPanel();
		panelThongTin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng Tin Chi Ti\u1EBFt ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelThongTin.setBounds(12, 176, 343, 189);
		panel_3.add(panelThongTin);
		panelThongTin.setLayout(null);

		JLabel lblMaKhachHang = new JLabel("Mã khách hàng");
		lblMaKhachHang.setBounds(12, 26, 94, 16);
		panelThongTin.add(lblMaKhachHang);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(145, 23, 174, 22);
		panelThongTin.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setBounds(12, 57, 108, 16);
		panelThongTin.add(lblTenKhachHang);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(145, 54, 174, 22);
		panelThongTin.add(txtTenKhachHang);

		JLabel lblSoDienThoai2 = new JLabel("Số điện thoại");
		lblSoDienThoai2.setBounds(12, 89, 108, 16);
		panelThongTin.add(lblSoDienThoai2);

		txtSDT2 = new JTextField();
		txtSDT2.setBounds(145, 86, 174, 22);
		panelThongTin.add(txtSDT2);
		txtSDT2.setColumns(10);

		JLabel lblCMND2 = new JLabel("CMND/PASSPORT");
		lblCMND2.setBounds(12, 120, 108, 16);
		panelThongTin.add(lblCMND2);

		txtCMND2 = new JTextField();
		txtCMND2.setBounds(145, 117, 174, 22);
		panelThongTin.add(txtCMND2);
		txtCMND2.setColumns(10);

		btnDatPhong = new JButton("ĐẶT PHÒNG");
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDatPhong.setBackground(new Color(51, 51, 153));
		btnDatPhong.setForeground(Color.WHITE);
		btnDatPhong.setBounds(198, 378, 138, 36);
		panel_3.add(btnDatPhong);

		btnXoa.setEnabled(false);
		txtTenKhachHang.setEditable(false);
		txtMaKhachHang.setEditable(false);
		txtSDT2.setEditable(false);
		txtCMND2.setEditable(false);
//		btnDatPhong.setEnabled(false);

		ConnectDB.getInstance().connect();
		CapNhatDuLieuBangLoaiPhong();
		SuKienLoaiPhong();
		SuKienLuu();
		SuKienXoa();
		SuKienPhong();
		SuKienThemKhachHang();
		SuKienTimSDT();
		SuKienTimCMND();

		dialog = new JDialog();
		giaoDienDialog();
		dialog.setLocation(600, 300);
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// XoaDuLieuText();
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
//				txtCMND2.setText(dltxtCMND.getText());
//				txtSDT2.setText(dltxtSoDienThoai.getText());
//				txtTenKhachHang.setText(dltxtTenKhachHang.getText());
				dltxtCMND.setText("");
				dltxtSoDienThoai.setText("");
				dltxtTenKhachHang.setText("");
				groupButton.clearSelection();
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

		// TODO Auto-generated method stub
//		int row = tableLoaiPhong.getSelectedRow();
//
//		List<LOAIPHONG> listLoaiPhong = dao_LoaiPhong.getalltbLoaiPhong();
//		dao_phong = new DAO_PHONG();
//		List<PHONG> listPhong = dao_phong.getAlltbPhong();
//		System.out.println("don kim dinh fe");
//		tablePhong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				System.out.println("don kim dinh 1111");
//				// TODO Auto-generated method stub
//				DefaultTableModel dm = (DefaultTableModel) tablePhong.getModel();
//				dm.setRowCount(0);
//				for (LOAIPHONG iterable_element : listLoaiPhong) {
//					if (iterable_element.getMaLoaiPhong().equals(String.valueOf(modelLoaiPhong.getValueAt(row, 0)))) {
//						for (PHONG phong : listPhong) {
//							if (phong.getPhong().getMaLoaiPhong().equals(iterable_element.getMaLoaiPhong()))
//								modelPhong.addRow(new Object[] { phong.getSoPhong(), phong.getPhong().getDonGia(),
//										phong.getPhong().getDonGia() });
//						}
//					}
//				}
//			}
//		});
		btnDatPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ((JTextField) dateBatDau.getDateEditor().getUiComponent()).getText()
				// System.out.println(autoMaHoaDonPhong());
				if (tablePhong.getRowCount() < 1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng");
					return;
				}
				if (txtTenKhachHang.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Thông tin khách hàng còn trống");
					return;
				}
				for (HOADONPHONG hoadon : dao_hdPhong.getalltbHDP()) {
					if (hoadon.getKhachHang().getMaKH().equals(txtMaKhachHang.getText())) {
						for (CTHDPHONG ct : dao_cthdPhong.getalltbHDP()) {
							if (ct.getTinhTrang().equals("NHẬN PHÒNG")&&ct.getHdP().getMaHDP().equals(hoadon.getMaHDP())) {
								JOptionPane.showMessageDialog(null,
										"Khách hàng đang thuê phòng. Vui lòng trả phòng để được tiếp tục đặt phòng");
								return;
							}
						}
					}
				}
				int returnValue = 0;
				returnValue = JOptionPane.showConfirmDialog(null, "Bạn muốn đặt phòng thật chứ", "Đặt phòng",
						JOptionPane.YES_NO_OPTION);
				if (returnValue == JOptionPane.YES_OPTION) {
					String maDatPhong = autoMaHoaDonPhong();
					// System.out.println(maDatPhong);
					DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					dao_hdPhong.LapHoaDon(maDatPhong, maNhanVien, txtMaKhachHang.getText());
					for (int i = 0; i < tablePhong.getRowCount(); i++) {
						dao_cthdPhong.DatPhong(maDatPhong, modelPhong.getValueAt(i, 1).toString(),
								sdf.format(LocalDateTime.now()), "NHẬN PHÒNG");
						if (tablePhong.getRowCount() == 1)
							dao_phong.CapNhatPhong(modelPhong.getValueAt(i, 1).toString(),
									modelPhong.getValueAt(i, 0).toString(), "KHÁCH LẺ");
						else
							dao_phong.CapNhatPhong(modelPhong.getValueAt(i, 1).toString(),
									modelPhong.getValueAt(i, 0).toString(), "KHÁCH ĐOÀN");
					}
					xoaDuLieuText();
					txtSDT.setText("");
					txtCMND.setText("");
					DefaultTableModel dm = (DefaultTableModel) tablePhong.getModel();
					dm.setRowCount(0);
					JOptionPane.showMessageDialog(null, "ĐẶT PHÒNG THÀNH CÔNG");
				} else if (returnValue == JOptionPane.NO_OPTION) {

				}
			}
		});
//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setBounds(422, 0, 21, 358);
//		panel.add(scrollBar);

//		int x = 0, y = 0;
//		for (int i = 0; i < 7; i++) {
//			JButton b1 = new JButton("fawef");
//			panel.add(b1);
//			x = x + 40;
//			y = y + 40;
//			b1.setBounds(0, y, 50, 50);
//		}
//
//		int u = 0;
//		for (int i = 0; i < 9; i++) {
//
//			JButton b = new JButton("32r");
//			panel_1.add(b);
//			u+=20;
//			b.setBounds(20, 10, 20, 20);
//		}
//		lblNewLabel.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null,"doan kim dinh");
//			}
//		});
	}

	private void SuKienTimSDT() {
		// TODO Auto-generated method stub
		dao_KhachHang = new DAO_KHACHHANG();

		List<KHACHHANG> listKhachHang = dao_KhachHang.getalltbKhachHang();
		btnTimSDT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtSDT.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập số điện thoại cần tìm");
					return;
				}
				int tam = 0;
				for (KHACHHANG khachhang : listKhachHang) {
					if (khachhang.getSDT().equals(txtSDT.getText().trim())) {
						txtCMND2.setText(khachhang.getCMND());
						txtSDT2.setText(khachhang.getSDT());
						txtMaKhachHang.setText(khachhang.getMaKH());
						txtTenKhachHang.setText(khachhang.getTenKH());
						// btnDatPhong.setEnabled(true);
						tam = 1;
					}
				}
				if (tam == 0) {
					JOptionPane.showMessageDialog(null, "Số điện thoại không tồn tại");
					// btnDatPhong.setEnabled(false);
					xoaDuLieuText();
				}

			}
		});
	}

	private void SuKienTimCMND() {
		// TODO Auto-generated method stub
		dao_KhachHang = new DAO_KHACHHANG();
		List<KHACHHANG> listKhachHang = dao_KhachHang.getalltbKhachHang();

		btnTimCMND.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtCMND.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập CMND/PASSPORT cần tìm");
					return;
				}

				int tam = 0;
				for (KHACHHANG khachhang : listKhachHang) {
					if (khachhang.getCMND().equals(txtCMND.getText().trim())) {
						txtCMND2.setText(khachhang.getCMND());
						txtSDT2.setText(khachhang.getSDT());
						txtMaKhachHang.setText(khachhang.getMaKH());
						txtTenKhachHang.setText(khachhang.getTenKH());
						// btnDatPhong.setEnabled(true);
						tam = 1;
					}
				}
				if (tam == 0) {
					JOptionPane.showMessageDialog(null, "Số CMND/PASSPORT không tồn tại");
					// btnDatPhong.setEnabled(false);
					xoaDuLieuText();
				}
			}
		});

	}

	private void xoaDuLieuText() {
		txtCMND2.setText("");
		txtSDT2.setText("");
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
	}

	private void SuKienThemKhachHang() {
		// TODO Auto-generated method stub

		btnThemKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// System.out.println("them khach hang");

				// GUI_ThemKhachHang themkhachhang = new GUI_ThemKhachHang();

				// JButton btt;
				// dialog.add(btt = new JButton("fawefaw"));
				// dialog.setBounds(100, 100, 426, 227);

//				btt.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						txtCMND2.setText("doan kim dinh");
//						dialog.setVisible(false);
//					}
//				});		

				dialog.setVisible(true);
//				
//				GUI_ThemKhachHang n = new GUI_ThemKhachHang();
//				n.setVisible(true);
//				 kh = n.SuKienThemKhachHang();
//				System.out.println(kh+" 11don kim dinh");
//				n.setModal(true);
//				n.setLocation(400, 300);

				// n.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

				// themkhachhang.disable();
				// themkhachhang.enable();
				// JDialog frame = new JDialog(themkhachhang,"fawe",true);
				// themkhachhang.setmo
//				themkhachhang.setVisible(true);
//				JDialog jf= new JDialog();
//				jf.add(themkhachhang);
//				jf.setModal(true);
//				jf.setSize(300, 400);
//				jf.setLocation(300, 400);
//		//		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//				jf.setVisible(true);
				// jf.setLocationByPlatform(false);

				// themkhachhang.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

				// JOptionPane.showMessageDialog(null, "fwae");
				// themkhachhang.setAlwaysOnTop(true);
			}
		});
	}

	public void giaoDienDialog() {
		dialog.setBounds(100, 100, 426, 227);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanelDialog.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanelDialog, BorderLayout.CENTER);
		contentPanelDialog.setLayout(null);

		JLabel dgTenKH = new JLabel("Tên khách hàng");
		dgTenKH.setBounds(12, 13, 133, 16);
		contentPanelDialog.add(dgTenKH);

		dltxtTenKhachHang = new JTextField();
		dltxtTenKhachHang.setBounds(157, 10, 235, 22);
		contentPanelDialog.add(dltxtTenKhachHang);
		dltxtTenKhachHang.setColumns(10);

		JLabel dgGioiTinh = new JLabel("Giới tính");
		dgGioiTinh.setBounds(12, 42, 56, 16);
		contentPanelDialog.add(dgGioiTinh);

		dlrdbtnNam = new JRadioButton("Nam");
		dlrdbtnNam.setBounds(167, 38, 68, 25);
		contentPanelDialog.add(dlrdbtnNam);

		dlrdbtnNu = new JRadioButton("Nữ");
		dlrdbtnNu.setBounds(234, 38, 127, 25);
		contentPanelDialog.add(dlrdbtnNu);

		groupButton = new ButtonGroup();
		groupButton.add(dlrdbtnNu);
		groupButton.add(dlrdbtnNam);

		dltxtSoDienThoai = new JTextField();
		dltxtSoDienThoai.setBounds(157, 68, 235, 22);
		contentPanelDialog.add(dltxtSoDienThoai);
		dltxtSoDienThoai.setColumns(10);

		JLabel dgSoDT = new JLabel("Số điện thoại");
		dgSoDT.setBounds(12, 71, 116, 16);
		contentPanelDialog.add(dgSoDT);

		JLabel dgCmnd = new JLabel("Số CMND/PASSPORT");
		dgCmnd.setBounds(12, 100, 133, 16);
		contentPanelDialog.add(dgCmnd);

		dltxtCMND = new JTextField();
		dltxtCMND.setBounds(157, 97, 235, 22);
		contentPanelDialog.add(dltxtCMND);
		dltxtCMND.setColumns(10);
		{
			dlbtnThem = new JButton("THÊM");

			dlbtnThem.setBounds(284, 142, 108, 25);
			contentPanelDialog.add(dlbtnThem);
			dlbtnThem.setActionCommand("OK");
			dialog.getRootPane().setDefaultButton(dlbtnThem);
			// SuKienThemKhachHang();
			try {
				ConnectDB.getInstance().connect();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			autoMaKhachHang();
		}

		SuKienThemKhachHang2();

	}

	public String autoMaKhachHang() {
		DAO_KHACHHANG dao_khachHang = new DAO_KHACHHANG();
		List<KHACHHANG> listKhachHang = dao_khachHang.getalltbKhachHang();
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

	public void SuKienThemKhachHang2() {
		// XoaDuLieuText();
		dlbtnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// System.out.println(groupButton.getSelection());
				// System.out.println(rdbtnNam.isSelected());
				// XoaDuLieuText();
				if (dltxtTenKhachHang.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng");
					dltxtTenKhachHang.requestFocus();
					return;
				}
				if (!dltxtTenKhachHang.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(([',. -][a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")){
					JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ");
					dltxtTenKhachHang.setText("");
					dltxtTenKhachHang.requestFocus();
					return;
				}
				
				boolean gioiTinh;
				if (groupButton.getSelection() != null) {
					if (dlrdbtnNam.isSelected()) {
						gioiTinh = true;
					} else
						gioiTinh = false;
				} else {
					JOptionPane.showMessageDialog(null, "Giới tính còn để trống");
					return;
				}
				if (dltxtSoDienThoai.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại của khách hàng");
					dltxtSoDienThoai.requestFocus();
					return;
				}
				if(!dltxtSoDienThoai.getText().matches("^(09|01|02|03|04|05|06|07|08)+([0-9]{8})$")) {
					JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
					dltxtSoDienThoai.setText("");
					dltxtSoDienThoai.requestFocus();
					return;
				}
				for (KHACHHANG element : dao_KhachHang.getalltbKhachHang()) {
					if (element.getSDT().equals(dltxtSoDienThoai.getText().trim())) {
						JOptionPane.showMessageDialog(null, "SỐ ĐIỆN THOẠI TRÙNG VỚI 1 KHÁCH HÀNG KHÁC");
						return;
					}
				}
				
				if (dltxtCMND.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập CMND khách hàng");
					return;
				}
				if(!dltxtCMND.getText().matches("^([0-9]{9})$")) {
					JOptionPane.showMessageDialog(null, "Số cmnd không hợp lệ");
					dltxtCMND.setText("");
					dltxtCMND.requestFocus();
					return;
				}
				for (KHACHHANG element : dao_KhachHang.getalltbKhachHang()) {
					if (element.getCMND().equals(dltxtCMND.getText().trim())) {
						JOptionPane.showMessageDialog(null, "CMND TRÙNG VỚI 1 KHÁCH HÀNG KHÁC");
						return;
					}
				}
				// System.out.println("sussusell");
				// setVisible(false);
				String makhachhang = autoMaKhachHang();
				dao_KhachHang.themKhachHang(makhachhang, dltxtTenKhachHang.getText(), dltxtCMND.getText(),
						dltxtSoDienThoai.getText(), gioiTinh);

				JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
				capNhatThongTinKhachHang(makhachhang);
				// kh = thongTinKhachHang();
				dialog.setVisible(false);
			}
		});

		// return kh;
	}

//	 addWindowListener(new WindowAdapter() {
//         public void windowClosing(WindowEvent we) {
//        	 JOptionPane.showMessageDialog(null, "ban co muon thoat");
//            System.exit(0);
//         }
//      });
	public void capNhatThongTinKhachHang(String maKhachHang) {
//		txtCMND2.setText(dltxtCMND.getText());
//		txtSDT2.setText(dltxtSoDienThoai.getText());
//		txtTenKhachHang.setText(dltxtTenKhachHang.getText());
//		txtMaKhachHang.setText(maKhachHang);
//		dltxtCMND.setText("");
//		dltxtCMND.setText("");
//		dltxtSoDienThoai.setText("");
//		dltxtTenKhachHang.setText("");
//		groupButton.clearSelection();
	}

	private void SuKienPhong() {
		// TODO Auto-generated method stub
		tablePhong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				btnXoa.setEnabled(true);

			}
		});
	}

	private void SuKienXoa() {
		// TODO Auto-generated method stub
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tablePhong.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng muốn bỏ");
					return;
				}
				modelPhong.removeRow(row);
				btnXoa.setEnabled(false);
			}
		});
	}

	private void SuKienLuu() {
		// TODO Auto-generated method stub
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String string = (String) cbbChonPhong.getSelectedItem();
				for (PHONG phong : dao_phong.getAlltbPhong()) {
					if (phong.getSoPhong().equals(string)) {
						for (LOAIPHONG loaiPhong : dao_LoaiPhong.getalltbLoaiPhong()) {
							if (loaiPhong.getMaLoaiPhong().equals(phong.getPhong().getMaLoaiPhong())) {
								modelPhong.addRow(new Object[] { loaiPhong.getMaLoaiPhong(), phong.getSoPhong(),
										loaiPhong.getDonGia() });
								cbbChonPhong.removeItem(string);
							}
						}
					}
				}
			}
		});
	}

	private void SuKienLoaiPhong() {
		// TODO Auto-generated method stub

		tableLoaiPhong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				lblHinhAnh.setIcon(null);
				imagePhong = null;
				List<LOAIPHONG> listLoaiPhong = dao_LoaiPhong.getalltbLoaiPhong();
				dao_phong = new DAO_PHONG();
				List<PHONG> listPhong = dao_phong.getAlltbPhong();

				// TODO Auto-generated method stub
				int row = tableLoaiPhong.getSelectedRow();
//				DefaultTableModel dm = (DefaultTableModel) tablePhong.getModel();
//				dm.setRowCount(0);

				cbbChonPhong.removeAllItems();

				for (LOAIPHONG loaiPhong : listLoaiPhong) {
					if (loaiPhong.getMaLoaiPhong().equals(String.valueOf(modelLoaiPhong.getValueAt(row, 0)))) {
						for (PHONG phong : listPhong) {
							if (phong.getPhong().getMaLoaiPhong().equals(loaiPhong.getMaLoaiPhong())
									&& phong.getTinhTrang().equals("TRỐNG")) {
								// System.out.println(phong.getTinhTrang());
//								modelPhong.addRow(new Object[] { phong.getPhong().getMaLoaiPhong(), phong.getSoPhong(),
//										iterable_element.getDonGia() });

								if (tablePhong.getRowCount() != 0) {
									int tam = 0;
									for (int i = 0; i < tablePhong.getRowCount(); i++) {
										if (phong.getSoPhong().equals(((String) modelPhong.getValueAt(i, 1)))) {
											// cbbChonPhong.remove(j);

											tam = 1;
										}
									}
									if (tam == 0) {
										cbbChonPhong.addItem(phong.getSoPhong());
									}

								} else {
									cbbChonPhong.addItem(phong.getSoPhong());
									System.out.println("vao 2");
								}

							}
						}
					}
				}

				if (row != -1) {

					try {
						byte[] img = dao_LoaiPhong.getalltbLoaiPhong().get(row).getHinhAnh();
						if (img != null) {
							ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(
									lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
							lblHinhAnh.setIcon(imageIcon);
							lblHinhAnh.setText("");
						} else {
							lblHinhAnh.setIcon(null);
							lblHinhAnh.setText("                   Loại Phòng Này Hiện Chưa Có Hình Ảnh");
						}
					} catch (Exception y) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Hình Ảnh Chưa Được Thêm Vào");
					}
				}
				for (int i = 0; i < tablePhong.getRowCount(); i++) {
					for (int j = 0; j < cbbChonPhong.getItemCount(); j++) {
						if (((String) cbbChonPhong.getItemAt(j)).equals(((String) modelPhong.getValueAt(i, 1)))) {
							// cbbChonPhong.remove(j);
						}
					}
				}
			}
		});
	}

	private void CapNhatDuLieuBangLoaiPhong() {
		// TODO Auto-generated method stub
		dao_LoaiPhong = new DAO_LOAIPHONG();
		List<LOAIPHONG> listLoaiPhong = dao_LoaiPhong.getalltbLoaiPhong();
		for (LOAIPHONG loaiphong : listLoaiPhong) {
			modelLoaiPhong
					.addRow(new Object[] { loaiphong.getMaLoaiPhong(), loaiphong.getDonGia(), loaiphong.getSoLuong() });

		}
	}

	public String autoMaHoaDonPhong() {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ten = LocalDateTime.now();

		DAO_HDPHONG dao_hoadonphong = new DAO_HDPHONG();
		DAO_CTHDP dao_ct = new DAO_CTHDP();

//		System.out.println(LocalDateTime.now().toString());
//		dao_ct.DatPhong("HD.06.11.2019.0005", "P05001",LocalDateTime.now().toString() , "NHẬN PHÒNG");

		LocalDate date;
		LocalTime time;
		ten = LocalDateTime.of(LocalDate.of(2019, 01, 03), LocalTime.of(1, 1, 2));

		String mahd = "HD";
		LocalDateTime ngaydat = LocalDateTime.now();
		String ngay = null;
		String thang;
		String nam = String.valueOf(ngaydat.getYear());
		String gio;
		String phut;
		String giay;

		if (ngaydat.getDayOfMonth() > 0 && ngaydat.getDayOfMonth() < 10) {
			ngay = "0" + ngaydat.getDayOfMonth();
		} else
			ngay = String.valueOf(ngaydat.getDayOfMonth());

		if (ngaydat.getMonthValue() > 0 && ngaydat.getMonthValue() < 10) {
			thang = "0" + ngaydat.getMonthValue();
		} else
			thang = String.valueOf(ngaydat.getMonthValue());

		if (ngaydat.getHour() > 0 && ngaydat.getHour() < 10) {
			gio = "0" + ngaydat.getHour();
		} else
			gio = String.valueOf(ngaydat.getHour());

		if (ngaydat.getMinute() > 0 && ngaydat.getMinute() < 10) {
			phut = "0" + ngaydat.getMinute();
		} else
			phut = String.valueOf(ngaydat.getMinute());

		if (ngaydat.getSecond() > 0 && ngaydat.getSecond() < 10) {
			giay = "0" + ngaydat.getSecond();
		} else
			giay = String.valueOf(ngaydat.getSecond());

		mahd = mahd + "." + nam + "." + thang + "." + ngay + ".";
//		System.out.println(mahd);
		if (dao_hdPhong.getalltbHDP() == null) {
			return mahd + "0000";
		}

		List<HOADONPHONG> listCtPhong = dao_hoadonphong.getalltbHDP();
		int so = 0;
		String ngayThangNam = nam + "." + thang + "." + ngay;
		for (HOADONPHONG cthdphong : listCtPhong) {
			// System.out.println(cthdphong.getMaHDP().substring(14, 18));
			so = Integer.parseInt(cthdphong.getMaHDP().substring(14, 18)) + 1;
			// System.out.println(cthdphong.getMaHDP().substring(3, 13));
			if (!ngayThangNam.equals(cthdphong.getMaHDP().substring(3, 13))) {
				so = 0;
			}
		}

		if (so >= 0 && so < 10) {
			mahd = mahd + "000" + so;
		}
		if (so >= 10 && so < 100) {
			mahd = mahd + "00" + so;
		}
		if (so >= 100 && so < 1000) {
			mahd = mahd + "0" + so;
		}
		if (so >= 1000 && so < 10000) {
			mahd = mahd + so;
		}
		return mahd;
	}
	public void setMaNhanVien(String manhanvien) {
		this.maNhanVien = manhanvien;
	}
}
