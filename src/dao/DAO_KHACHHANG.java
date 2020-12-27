package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.KHACHHANG;

public class DAO_KHACHHANG {
	
	public ArrayList<KHACHHANG> getalltbKhachHang() {
		ArrayList<KHACHHANG> ds = new ArrayList<KHACHHANG>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from KHACHHANG");

			while (rs.next()) {
				KHACHHANG kh = new KHACHHANG(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));	 		
				ds.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return ds;
	}
	
	public void themKhachHang(String ma, String ten, String cmnd, String sdt, boolean gioiTinh) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "INSERT INTO KHACHHANG(MaKH,TenKH,CMND,SDT,GioiTinh) values(?,?,?,?,?)";
		PreparedStatement ps ;
		
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, ma);
			ps.setString(2, ten);
			ps.setString(3, cmnd);
			ps.setString(4, sdt);
			ps.setBoolean(5, gioiTinh);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu");
		}
		
	}
	
	public KHACHHANG findCustomerByPhone(String sdt) {
		KHACHHANG kh = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs;
			
			rs = statement.executeQuery("SELECT * FROM KHACHHANG WHERE SDT LIKE '%"+ sdt + "%'");
			while(rs.next()) {
				kh = new KHACHHANG(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kh;
	}
	
	
	public KHACHHANG findCustomerByName(String name) {
		KHACHHANG kh = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("SELECT * FROM KHACHHANG WHERE TenKH LIKE '%"+ name + "%'");
			
			while(rs.next()) {
				kh = new KHACHHANG(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kh;
	}
	
	public KHACHHANG findCustomerByCMND(String cmnd) {
		KHACHHANG kh = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("SELECT * FROM KHACHHANG WHERE CMND LIKE '%"+ cmnd+ "%'");
			while(rs.next()) {
				kh = new KHACHHANG(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kh;
	}
	
	public void upDateCustomer(String maKh, String tenKh, String cmnd, String sdt, boolean gioiTinh) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "UPDATE KHACHHANG SET TenKH = ?, CMND = ? , SDT = ?, GioiTinh = ? WHERE MaKH =?";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(5, maKh);
			ps.setString(1, tenKh);
			ps.setString(2, cmnd);
			ps.setString(3, sdt);
			ps.setBoolean(4, gioiTinh);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteCustomer(String maKh) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "DELETE FROM KHACHHANG WHERE MaKH LIKE '%"+ maKh + "%'";
		
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