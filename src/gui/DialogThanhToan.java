package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class DialogThanhToan extends JDialog {

	private final JPanel dlPanel = new JPanel();
	private JTable dlTableDichVu;
	private JTable dlTableChiTiet;
	private JTextField dltxtTongTien;
	private JTextField dlTienKhachDua;
	private JTextField dlTienThua;
	private DefaultTableModel dlModelTableDichVu;
	private DefaultTableModel dlModelTableChiTiet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogThanhToan dialog = new DialogThanhToan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogThanhToan() {
		setBounds(100, 100, 514, 411);
		getContentPane().setLayout(new BorderLayout());
		dlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(dlPanel, BorderLayout.CENTER);
		dlPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "H\u00F3a \u0110\u01A1n D\u1ECBch V\u1EE5", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 0, 472, 99);
		dlPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane dlscrollPane = new JScrollPane();
		dlscrollPane.setBounds(12, 26, 448, 60);
		panel.add(dlscrollPane);
		
		dlTableDichVu = new JTable();
		dlTableDichVu.setModel(dlModelTableDichVu =  new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 kh\u00E1ch h\u00E0ng", "Ng\u00E0y l\u1EADp"
			}
		));
		dlscrollPane.setViewportView(dlTableDichVu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chi Ti\u1EBFt H\u00F3a \u0110\u01A1n D\u1ECBch V\u1EE5", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 106, 472, 141);
		dlPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane dlScrollPane_2 = new JScrollPane();
		dlScrollPane_2.setBounds(12, 19, 448, 109);
		panel_1.add(dlScrollPane_2);
		
		dlTableChiTiet = new JTable();
		dlTableChiTiet.setModel(dlModelTableChiTiet = new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"M\u00E3 H\u00F3a \u0110\u01A1n", "T\u00EAn D\u1ECBch V\u1EE5", "S\u1ED1 L\u01B0\u1EE3ng"
			}
		));
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
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton dlThanhToan = new JButton("THANH TOÁN");
				dlThanhToan.setActionCommand("OK");
				buttonPane.add(dlThanhToan);
				getRootPane().setDefaultButton(dlThanhToan);
			}
			{
				JButton dlThoat = new JButton("THOÁT");
				dlThoat.setActionCommand("Cancel");
				buttonPane.add(dlThoat);
			}
		}
	}
}
