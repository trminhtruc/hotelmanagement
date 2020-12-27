package dao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.NHANVIEN;
import entity.TAIKHOAN;

public class DAO_TAIKHOAN{	
	
	public DAO_TAIKHOAN() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<TAIKHOAN> getalltbTaiKhoan() {
		ArrayList<TAIKHOAN> dsnv = new ArrayList<TAIKHOAN>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select nv.MaNV,tk.TaiKhoan,tk.MatKhau from [dbo].[NHANVIEN] nv join [dbo].[TAIKHOAN] tk on nv.MaNV=tk.MaNV");

			while (rs.next()) {				
				TAIKHOAN nv = new TAIKHOAN(new NHANVIEN(rs.getString(1)), rs.getString(2), rs.getString(3));	 		
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsnv;
	}
//	public int DocTaiKhoan(String inputTenTK, String inputMK) {
//		int tam = 0;
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();			
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery("select * from [dbo].[NHANVIEN] nv join [dbo].[TAIKHOAN] tk on nv.MaNV=tk.MaNV");
//
//			while (rs.next()) {					
//				if(rs.getString(9).equals(inputTenTK)) {
//					GUI.GUI_DANGNHAP.lblThongbaoTK.setText("Tên tài khoản chính xác");
//					GUI.GUI_DANGNHAP.lblThongbaoTK.setForeground(Color.GREEN);
//					if(rs.getString(10).equals(inputMK)) {
//						if(rs.getString(6).equalsIgnoreCase("QUẢN LÝ")) {
//							tam = 1;
//						}else if(rs.getString(6).equalsIgnoreCase("TIẾP TÂN")) {
//							tam = 2;
//						}
//					}else {
//						GUI.GUI_DANGNHAP.lblThongbaoMK.setText("Mật khẩu không đúng");
//					}
//					break;
//				}else {
//					GUI.GUI_DANGNHAP.lblThongbaoTK.setText("Tên tài khoản không đúng");
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();		
//		}
//		return tam;
//	}
	
	public void themTaiKhoan(String ma, String ten, String password) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "INSERT INTO TAIKHOAN(MaNV,TaiKhoan,MatKhau) VALUES(?,?,?)";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, ma);
			ps.setString(2, ten);
			ps.setString(3, password);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu");
		}
		
	}
	
	public void capNhatTaiKhoan(String ten, String matkhau) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query ="UPDATE TAIKHOAN SET MatKhau=? WHERE TaiKhoan= ?";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, matkhau);
			ps.setString(2, ten);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void xoaTaiKhoan(String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "DELETE FROM TAIKHOAN WHERE MaNV LIKE '%"+ ma + "%'";
		
		PreparedStatement ps;
		try {
			ps =con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
