package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDB.ConnectDB;
import dao.DAO_KHACHHANG;
import entity.KHACHHANG;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class GUI_ThemKhachHang extends JDialog {

	private final JPanel contentPanelDialog = new JPanel();
	private JTextField dltxtTenKhachHang;
	private JTextField dltxtSoDienThoai;
	private JTextField dltxtCMND;
	private JButton dlbtnThem;
	private JRadioButton dlrdbtnNam;
	private JRadioButton dlrdbtnNu;
	private ButtonGroup groupButton;
	private DAO_KHACHHANG dao_khachHang = new DAO_KHACHHANG();
	private KHACHHANG kh = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GUI_ThemKhachHang dialog = new GUI_ThemKhachHang();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GUI_ThemKhachHang() {
		setBounds(100, 100, 426, 227);
		getContentPane().setLayout(new BorderLayout());
		contentPanelDialog.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelDialog, BorderLayout.CENTER);
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
			getRootPane().setDefaultButton(dlbtnThem);
		//	SuKienThemKhachHang();
			try {
				ConnectDB.getInstance().connect();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			autoMaKhachHang();
		}
		
	}
	public String autoMaKhachHang() {
		DAO_KHACHHANG dao_khachHang = new DAO_KHACHHANG();
		List<KHACHHANG> listKhachHang =  dao_khachHang.getalltbKhachHang();
		if(listKhachHang == null) {
			return "KH0000";
		}
		String maKhachHang = "KH";
		int so = 0;
		for (KHACHHANG khachhang : listKhachHang) {
			//System.out.println(khachhang.getMaKH().substring(2, 6));
			 so = Integer.parseInt(khachhang.getMaKH().substring(2, 6))+1;
		}
		if(so>=0&&so<10) {
			maKhachHang = maKhachHang+"000"+so;
		}
		if(so>=10&&so<100) {
			maKhachHang = maKhachHang+"00"+so;
		}
		if(so>=100&&so<1000) {
			maKhachHang = maKhachHang+"0"+so;
		}
		if(so>=1000&&so<10000) {
			maKhachHang = maKhachHang+so;
		}
		
		return maKhachHang;
	}
	public KHACHHANG SuKienThemKhachHang() {
		
		dlbtnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//	System.out.println(groupButton.getSelection());
			//	System.out.println(rdbtnNam.isSelected());
				if(dltxtTenKhachHang.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng");
					return;
				}
				boolean gioiTinh;
				if(groupButton.getSelection()!=null) {
					if(dlrdbtnNam.isSelected()) {
						gioiTinh = true;
					}else
						gioiTinh = false;
				}
				else {
					JOptionPane.showMessageDialog(null, "Giới tính còn để trống");
					return;
				}
				if(dltxtSoDienThoai.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại của khách hàng");
					return;
				}
				if(dltxtCMND.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập CMND khách hàng");
					return;
				}
	//			System.out.println("sussusell");
			//	setVisible(false);
				String makhachhang = autoMaKhachHang();
				dao_khachHang.themKhachHang(makhachhang, dltxtTenKhachHang.getText(), dltxtCMND.getText(), dltxtSoDienThoai.getText(), gioiTinh);
				kh = thongTinKhachHang();
				System.out.println(kh.getSDT());
			}
		});
		
		return kh;
	}
	public KHACHHANG thongTinKhachHang() {
		List<KHACHHANG> listKhachHang = dao_khachHang.getalltbKhachHang();
		KHACHHANG kh = null;
		for (KHACHHANG khachhang : listKhachHang) {
			kh = khachhang;
		}
		return kh;
	}
}
