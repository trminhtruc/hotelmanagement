package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogTatCa extends JDialog {

	private final JPanel TTjpanel = new JPanel();
	private JTextField dlTTtxtTienDichVu;
	private JTextField dlTTtxtTienPhong;
	private JTextField dlTTTongTien;
	private JTextField dlTTKhachDua;
	private JTextField dlTTTienThua;
	private JButton dlTTbtnThanhToan;
	private JButton dlTTThoat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogTatCa dialog = new DialogTatCa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogTatCa() {
		setBounds(100, 100, 382, 305);
		getContentPane().setLayout(new BorderLayout());
		TTjpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(TTjpanel, BorderLayout.CENTER);
		TTjpanel.setLayout(null);
		{
			JLabel lblTngTinDch = new JLabel("TỔNG TIỀN DỊCH VỤ");
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
			lblVnd.setBounds(332, 28, 32, 16);
			TTjpanel.add(lblVnd);
		}
		{
			JLabel label = new JLabel("VND");
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
			lblTngTin.setBounds(12, 116, 88, 16);
			TTjpanel.add(lblTngTin);
		}
		{
			JLabel label = new JLabel("VND");
			label.setBounds(332, 116, 32, 16);
			TTjpanel.add(label);
		}
		{
			dlTTKhachDua = new JTextField();
			dlTTKhachDua.setBounds(150, 148, 136, 22);
			TTjpanel.add(dlTTKhachDua);
			dlTTKhachDua.setColumns(10);
		}
		{
			JLabel lblTinKhcha = new JLabel("Tiền khách đưa");
			lblTinKhcha.setBounds(12, 151, 110, 16);
			TTjpanel.add(lblTinKhcha);
		}
		{
			JLabel lblTinTha = new JLabel("Tiền thừa");
			lblTinTha.setBounds(12, 180, 73, 16);
			TTjpanel.add(lblTinTha);
		}
		{
			dlTTTienThua = new JTextField();
			dlTTTienThua.setBounds(150, 177, 136, 22);
			TTjpanel.add(dlTTTienThua);
			dlTTTienThua.setColumns(10);
		}
		{
			JLabel label = new JLabel("VND");
			label.setBounds(298, 151, 32, 16);
			TTjpanel.add(label);
		}
		{
			JLabel label = new JLabel("VND");
			label.setBounds(298, 180, 32, 16);
			TTjpanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			dlTTbtnThanhToan = new JButton("THANH TOÁN");
				dlTTbtnThanhToan.setActionCommand("OK");
				buttonPane.add(dlTTbtnThanhToan);
				getRootPane().setDefaultButton(dlTTbtnThanhToan);
			}
			{
				dlTTThoat = new JButton("THOÁT");
				dlTTThoat.setActionCommand("Cancel");
				buttonPane.add(dlTTThoat);
			}
		}
	}

}
