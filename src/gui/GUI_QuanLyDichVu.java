package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import dao.DAO_DICHVU;
import entity.DICHVU;

public class GUI_QuanLyDichVu extends JPanel {
	private JTable table;
	private JTextField txtTimKiem;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JTextField txtDonGia;
	private DefaultTableModel model;
	private JComboBox<String> cbbTinhTrang;

	private final JPanel contentPanel = new JPanel();
	private JTextField dltxtMaDV;
	private JTextField dltxtTenDV;
	private JTextField dltxtDonGia;
	private JComboBox<String> dlcbbTrangThai;

	private final JPanel contentPanel2 = new JPanel();
	private JTextField dltxtMaDV2;
	private JTextField dltxtTenDV2;
	private JTextField dltxtDonGia2;
	private JComboBox<String> dlcbbTrangThai2;

	private JDialog dialog;
	private JDialog dialog2;

	private DAO_DICHVU dichvuDAO = new DAO_DICHVU();
	private int row;

	/**
	 * Create the panel.
	 */
	public GUI_QuanLyDichVu() {
		setBackground(new Color(255, 127, 80));
		setLayout(null);
		setSize(1195, 635);
		setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(630, 11, 555, 477);
		panel.setBorder(new TitledBorder(null, "Thông tin dịch vụ", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);

		JLabel lblMaDV = new JLabel("Mã dịch vụ:");
		lblMaDV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaDV.setBounds(67, 60, 124, 29);
		panel.add(lblMaDV);

		JLabel lblTenDV = new JLabel("Tên dịch vụ:");
		lblTenDV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenDV.setBounds(67, 152, 124, 29);
		panel.add(lblTenDV);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDonGia.setBounds(67, 244, 124, 29);
		panel.add(lblDonGia);

		JLabel lblTinhTrang = new JLabel("Tình Trạng:");
		lblTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTinhTrang.setBounds(67, 343, 124, 29);
		panel.add(lblTinhTrang);

		txtMaDV = new JTextField();
		txtMaDV.setBounds(195, 60, 322, 40);
		panel.add(txtMaDV);
		txtMaDV.setColumns(10);
		txtMaDV.setEditable(false);

		txtTenDV = new JTextField();
		txtTenDV.setColumns(10);
		txtTenDV.setBounds(195, 152, 322, 40);
		panel.add(txtTenDV);
		txtTenDV.setEditable(false);

		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(195, 244, 322, 40);
		panel.add(txtDonGia);
		txtDonGia.setEditable(false);

		cbbTinhTrang = new JComboBox<String>();
		cbbTinhTrang.setBounds(195, 343, 322, 40);
		panel.add(cbbTinhTrang);
		cbbTinhTrang.addItem("ĐANG KINH DOANH");
		cbbTinhTrang.addItem("NGỪNG KINH DOANH");
		cbbTinhTrang.setEnabled(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(10, 11, 600, 594);
		panel_1.setBorder(
				new TitledBorder(null, "Danh sách dịch vụ", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 580, 522);
		panel_1.add(scrollPane);

		table = new JTable() {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.setModel(model = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã dịch vụ", "Tên dịch vụ", "Đơn giá", "Tình Trạng" }));
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTimKiem.setBounds(300, 22, 79, 27);
		panel_1.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(389, 23, 201, 27);
		panel_1.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JPanel panelChucNang = new JPanel();
		panelChucNang.setBackground(SystemColor.activeCaption);
		panelChucNang.setBounds(630, 501, 555, 104);
		panelChucNang.setBorder(new TitledBorder(null, "Chức Năng", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		add(panelChucNang);
		panelChucNang.setLayout(null);

		JButton btnThemDV = new JButton("Thêm dịch vụ");
		btnThemDV.setForeground(Color.WHITE);
		btnThemDV.setBackground(new Color(51, 204, 102));
		btnThemDV.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThemDV.setBounds(59, 32, 146, 47);
		panelChucNang.add(btnThemDV);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setBackground(new Color(0, 102, 51));
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCapNhat.setBounds(346, 32, 146, 47);
		btnCapNhat.setEnabled(false);
		panelChucNang.add(btnCapNhat);

		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;
		docDichVu();

		dialog = new JDialog();
		dialogThemDichVu();
		dialog.setLocation(400, 200);
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		dialog2 = new JDialog();
		dialogCapNhatDichVu();
		dialog2.setLocation(400, 200);
		dialog2.setModal(true);
		dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		btnThemDV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
				
			}
		});

		
		
		btnCapNhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				if(btnCapNhat.getText().equals("Cập nhật")){
//					btnCapNhat.setText("Lưu");
//					txtTenDV.setEditable(true);
//					txtDonGia.setEditable(true);
//					cbbTinhTrang.setEnabled(true);
//				}
//				else {
//					try {
//						dichvuDAO.updateDichVu(txtMaDV.getText().toString(), txtTenDV.getText().toString(), Double.parseDouble(txtDonGia.getText()), cbbTinhTrang.getSelectedItem().toString());
//						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
//						clearTable();
//						docDichVu();
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//						JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
//					}
//					btnCapNhat.setText("Cập nhật");
//					txtTenDV.setEditable(false);
//					txtDonGia.setEditable(false);
//					cbbTinhTrang.setEnabled(false);
//					
//				}
				dltxtMaDV2.setText(txtMaDV.getText().toString());
				dltxtTenDV2.setText(txtTenDV.getText().toString());
				dltxtDonGia2.setText(txtDonGia.getText().toString());
				dlcbbTrangThai2.setSelectedItem(cbbTinhTrang.getSelectedItem().toString());
				dialog2.setVisible(true);
				btnCapNhat.setEnabled(false);
				
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				row = table.getSelectedRow();
				fillForm(row);
				btnCapNhat.setEnabled(true);
			}
		});

		txtTimKiem.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				clearTable();

				List<DICHVU> list = dichvuDAO.getalltbDichVu();
				for (DICHVU dv : list) {
					if (dv.getTenDV().toUpperCase().contains(txtTimKiem.getText().toUpperCase())) {
						model.addRow(new Object[] { dv.getMaDV(), dv.getTenDV(), dv.getGiaDV(), dv.getTinhtrang() });
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	public void dialogThemDichVu() {
		dialog.setTitle("Thêm dịch vụ");
		dialog.setBounds(100, 100, 625, 374);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel dllblMaDV = new JLabel("Mã dịch vụ:");
		dllblMaDV.setFont(new Font("Tahoma", Font.BOLD, 12));
		dllblMaDV.setBounds(53, 27, 103, 26);
		contentPanel.add(dllblMaDV);

		dltxtMaDV = new JTextField();
		dltxtMaDV.setBounds(199, 27, 328, 26);
		contentPanel.add(dltxtMaDV);
		dltxtMaDV.setColumns(10);
		dltxtMaDV.setEditable(false);

		JLabel dllblTenDV = new JLabel("Tên dịch vụ:");
		dllblTenDV.setFont(new Font("Tahoma", Font.BOLD, 12));
		dllblTenDV.setBounds(53, 91, 103, 26);
		contentPanel.add(dllblTenDV);

		dltxtTenDV = new JTextField();
		dltxtTenDV.setColumns(10);
		dltxtTenDV.setBounds(199, 91, 328, 26);
		contentPanel.add(dltxtTenDV);

		JLabel dllblDonGia = new JLabel("Đơn giá:");
		dllblDonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		dllblDonGia.setBounds(53, 158, 103, 26);
		contentPanel.add(dllblDonGia);

		dltxtDonGia = new JTextField();
		dltxtDonGia.setColumns(10);
		dltxtDonGia.setBounds(199, 158, 328, 26);
		contentPanel.add(dltxtDonGia);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTrangThai.setBounds(53, 223, 103, 26);
		contentPanel.add(lblTrangThai);

		dlcbbTrangThai = new JComboBox();
		dlcbbTrangThai.setBounds(199, 223, 328, 26);
		contentPanel.add(dlcbbTrangThai);
		dlcbbTrangThai.addItem("ĐANG KINH DOANH");
		dlcbbTrangThai.addItem("NGỪNG KINH DOANH");

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 291, 687, 44);
		contentPanel.add(buttonPane);
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setLayout(null);

		JButton dlbtnThem = new JButton("Thêm");
		dlbtnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		dlbtnThem.setBounds(345, 5, 76, 28);
		dlbtnThem.setActionCommand("OK");
		buttonPane.add(dlbtnThem);
		dialog.getRootPane().setDefaultButton(dlbtnThem);

		JButton dlbtnHuy = new JButton("Hủy");
		dlbtnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		dlbtnHuy.setBounds(461, 5, 74, 28);
		dlbtnHuy.setActionCommand("Cancel");
		buttonPane.add(dlbtnHuy);
		
		dltxtTenDV.requestFocus();
		
		dlcbbTrangThai.setEnabled(false);
		dlcbbTrangThai.setSelectedItem("ĐANG KINH DOANH");

		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;

		dlbtnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		///		regrexThemDichVu();

				if (dltxtTenDV.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên dịch vụ không được để trống");
					dltxtTenDV.requestFocus();
					return;
				}
				if(!dltxtTenDV.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(([',. -][a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")) {
					JOptionPane.showMessageDialog(null, "Tên dịch vụ không hợp lệ");
					dltxtTenDV.setText("");
					dltxtTenDV.requestFocus();
					return;
				}
				if (dltxtDonGia.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Đơn giá không được để trống");
					dltxtDonGia.requestFocus();
					return;
				}
				if(!dltxtDonGia.getText().matches("^\\d{0,8}[.]?\\d{1,4}$")) {
					JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ");
					dltxtDonGia.setText("");
					dltxtDonGia.requestFocus();
					return;
				}
				
				String ma = autoMaDichVu();
				try {
					dichvuDAO.themDichVu(ma, dltxtTenDV.getText(), Double.parseDouble(dltxtDonGia.getText()),
							dlcbbTrangThai.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					dialog.setVisible(false);
					clearTable();
					docDichVu();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Thêm thất bại");
				}
			}
		});

		dlbtnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int reply = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn hủy", "WARNING",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					dialog.setVisible(false);
				}
			}
		});

	}
	public void docDichVu() {
		List<DICHVU> list_DV = dichvuDAO.getalltbDichVu();

		for (DICHVU dv : list_DV) {
			model.addRow(new Object[] { dv.getMaDV(), dv.getTenDV(), dv.getGiaDV(), dv.getTinhtrang() });
		}
	}

	public void fillForm(int row) {
		if (row != -1) {
			txtMaDV.setText(model.getValueAt(row, 0).toString());
			txtTenDV.setText(model.getValueAt(row, 1).toString());
			txtDonGia.setText(model.getValueAt(row, 2).toString());
			cbbTinhTrang.setSelectedItem(model.getValueAt(row, 3));
		}
	}

	public void clearTable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.setRowCount(0);
	}

	

	public void dialogCapNhatDichVu() {
		dialog2.setTitle("Cập nhật dịch vụ");
		dialog2.setBounds(100, 100, 625, 374);
		dialog2.getContentPane().setLayout(new BorderLayout());
		contentPanel2.setBackground(Color.WHITE);
		contentPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog2.getContentPane().add(contentPanel2, BorderLayout.CENTER);
		contentPanel2.setLayout(null);

		JLabel dllblMaDV2 = new JLabel("Mã dịch vụ:");
		dllblMaDV2.setFont(new Font("Tahoma", Font.BOLD, 12));
		dllblMaDV2.setBounds(53, 27, 103, 26);
		contentPanel2.add(dllblMaDV2);

		dltxtMaDV2 = new JTextField();
		dltxtMaDV2.setBounds(199, 27, 328, 26);
		contentPanel2.add(dltxtMaDV2);
		dltxtMaDV2.setColumns(10);
		dltxtMaDV2.setEditable(false);

		JLabel dllblTenDV2 = new JLabel("Tên dịch vụ:");
		dllblTenDV2.setFont(new Font("Tahoma", Font.BOLD, 12));
		dllblTenDV2.setBounds(53, 91, 103, 26);
		contentPanel2.add(dllblTenDV2);

		dltxtTenDV2 = new JTextField();
		dltxtTenDV2.setColumns(10);
		dltxtTenDV2.setBounds(199, 91, 328, 26);
		contentPanel2.add(dltxtTenDV2);

		JLabel dllblDonGia2 = new JLabel("Đơn giá:");
		dllblDonGia2.setFont(new Font("Tahoma", Font.BOLD, 12));
		dllblDonGia2.setBounds(53, 158, 103, 26);
		contentPanel2.add(dllblDonGia2);

		dltxtDonGia2 = new JTextField();
		dltxtDonGia2.setColumns(10);
		dltxtDonGia2.setBounds(199, 158, 328, 26);
		contentPanel2.add(dltxtDonGia2);

		JLabel lblTrangThai2 = new JLabel("Trạng thái:");
		lblTrangThai2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTrangThai2.setBounds(53, 223, 103, 26);
		contentPanel2.add(lblTrangThai2);

		dlcbbTrangThai2 = new JComboBox();
		dlcbbTrangThai2.setBounds(199, 223, 328, 26);
		contentPanel2.add(dlcbbTrangThai2);
		dlcbbTrangThai2.addItem("ĐANG KINH DOANH");
		dlcbbTrangThai2.addItem("NGỪNG KINH DOANH");

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 291, 687, 44);
		contentPanel2.add(buttonPane);
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setLayout(null);

		JButton dlbtnLuu = new JButton("Lưu");
		dlbtnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
		dlbtnLuu.setBounds(345, 5, 76, 28);
		dlbtnLuu.setActionCommand("OK");
		buttonPane.add(dlbtnLuu);
		dialog2.getRootPane().setDefaultButton(dlbtnLuu);

		JButton dlbtnHuy2 = new JButton("Hủy");
		dlbtnHuy2.setFont(new Font("Tahoma", Font.BOLD, 12));
		dlbtnHuy2.setBounds(461, 5, 74, 28);
		dlbtnHuy2.setActionCommand("Cancel");
		buttonPane.add(dlbtnHuy2);
		

		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;

		dlbtnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (dltxtTenDV2.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên dịch vụ không được để trống");
					dltxtTenDV2.requestFocus();
					return;
				}
				if(!dltxtTenDV2.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(([',. -][a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")) {
					JOptionPane.showMessageDialog(null, "Tên dịch vụ không hợp lệ");
					dltxtTenDV2.setText("");
					dltxtTenDV2.requestFocus();
					return;
				}
				if (dltxtDonGia2.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Đơn giá không được để trống");
					dltxtDonGia2.requestFocus();
					return;
				}
				if(!dltxtDonGia2.getText().matches("^\\d{0,8}[.]?\\d{1,4}$")) {
					JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ");
					dltxtDonGia2.setText("");
					dltxtDonGia2.requestFocus();
					return;
				}
				
				try {
					dichvuDAO.updateDichVu(dltxtMaDV2.getText().toString(), dltxtTenDV2.getText().toString(),
							Double.parseDouble(dltxtDonGia2.getText()), dlcbbTrangThai2.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					dialog2.setVisible(false);
					clearTable();
					docDichVu();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
					e1.printStackTrace();
				}
			}
		});

		dlbtnHuy2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int reply = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn hủy", "WARNING",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					dialog2.setVisible(false);
				}
			}
		});
	}

	public String autoMaDichVu() {
		String maDV = "DV";
		List<DICHVU> list = dichvuDAO.getalltbDichVu();
		int so = 0;
		for (DICHVU dv : list) {
			// DV0001
			if (dv.getMaDV().length() == 6) {
				so = Integer.parseInt(dv.getMaDV().substring(2, 6));
			}
//			if(dv.getMaDV().length() == 7) {
//				so = Integer.parseInt(dv.getMaDV().substring(2, 7));
//			}
		}
		so = so + 1;
		if (so >= 0 && so < 10) {
			maDV = maDV + "000" + String.valueOf(so);
		}
		if (so >= 10 && so < 100) {
			maDV = maDV + "00" + String.valueOf(so);
		}
		if (so >= 100 && so < 1000) {
			maDV = maDV + "0" + String.valueOf(so);
		}
		if (so >= 1000 && so < 10000) {
			maDV = maDV + String.valueOf(so);
		}

		return maDV;
	}

	public void regrexThemDichVu() {
		
	}

//	public void regrexCapNhatDichVu() {
//		
//	}
}
