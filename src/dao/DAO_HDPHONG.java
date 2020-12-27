package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.DICHVU;
import entity.HOADONPHONG;
import entity.KHACHHANG;
import entity.NHANVIEN;
import entity.PHONG;

public class DAO_HDPHONG {
	public ArrayList<HOADONPHONG> getalltbHDP() {
		ArrayList<HOADONPHONG> dsP = new ArrayList<HOADONPHONG>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from HOADONPHONG");

			while (rs.next()) {
				HOADONPHONG nv = new HOADONPHONG(rs.getString(1), new KHACHHANG(rs.getString(3)), new NHANVIEN(rs.getString(2)));	 		
				dsP.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsP;
	}
	
	public void LapHoaDon(String maHD, String nhanVien, String khachHang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into [dbo].[HOADONPHONG](MaHDP,MaNV,MaKH) values(?,?,?)";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, maHD);
			ps.setString(2, nhanVien);
			ps.setString(3, khachHang);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
		}		
	}
	public void CapNhat(String maHDP, String maNV, String maKH, String tenKH, String sdt, String cmnd) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "update [dbo].[HOADONPHONG] set MaNV=?, MaKH=?, TenKH=?, SDT=?, CMND=? where MaHDP=?";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(7, maHDP);		
			ps.setString(1, maHDP);			
			ps.setString(2, maNV);			
			ps.setString(3, maKH); 	
			ps.setString(4, tenKH); 								
			ps.setString(5, sdt); 	
			ps.setString(6, cmnd); 	
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
		}		
	}	
}
