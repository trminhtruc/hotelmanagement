package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.NHANVIEN;
import entity.TAIKHOAN;

public class DAO_NHANVIEN{
	
	public ArrayList<NHANVIEN> getalltbNhanVien() {
		ArrayList<NHANVIEN> dsnv = new ArrayList<NHANVIEN>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from NHANVIEN");

			while (rs.next()) {
				NHANVIEN nv = new NHANVIEN(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getBytes(7),rs.getString(8));	 		
				dsnv.add(nv);	
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsnv;
	}
	
	public void CapNhatNhanVien(String ma, String ten, boolean gt, String cmnd, String sdt, String chucVu, byte[] image,String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "UPDATE NHANVIEN SET TenNV= ?, GioiTinh= ?, CMND= ?, SDT= ?, ChucVu= ?, HinhAnh= ?,TinhTrang=? WHERE MaNV= ?";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
//			if(image == null) {
//				
//			}
			ps.setString(8, ma);
			ps.setString(1, ten);
			ps.setBoolean(2, gt);
			ps.setString(3, cmnd);
			ps.setString(4, sdt);
			ps.setString(5, chucVu);
			ps.setBytes(6, image);
			ps.setString(7, tinhTrang);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void themNhanVien(String ma , String ten, boolean gioitinh, String cmnd, String sdt, String chucvu, byte[] image,String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "INSERT INTO NHANVIEN(MaNV,TenNV,GioiTinh,CMND,SDT,ChucVu,HinhAnh,TinhTrang) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, ma);
			ps.setString(2, ten);
			ps.setBoolean(3, gioitinh);
			ps.setString(4, cmnd);
			ps.setString(5, sdt);
			ps.setString(6, chucvu);
			ps.setBytes(7, image);
			ps.setString(8, tinhTrang);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void xoaNhanVien(String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		String query = "DELETE FROM NHANVIEN WHERE MaNV LIKE '%" + ma +"%'";
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
