package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.PHONG;
import entity.LOAIPHONG;

public class DAO_PHONG {	
	public ArrayList<PHONG> getAlltbPhong() {
		ArrayList<PHONG> dsnv = new ArrayList<PHONG>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from [dbo].[PHONG]");
			while (rs.next()) {
				PHONG nv = new PHONG(rs.getString(1), new LOAIPHONG(rs.getString(2)), rs.getString(3));	 		
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsnv;
	}
	public void updateTinhTrangPhong(String soPhong) {
		Connection con = ConnectDB.getInstance().getConnection();
		//proc name = 'UpdateTinhTrangPhong'in database
		String query = "exec UpdateTinhTrangPhong ?";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, soPhong);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Cập Nhật Tình Trạng Phòng!");
		}	
	}
	public void updateTinhTrang(String soPhong, String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		String query = "update PHONG set TinhTrang=? where SoPhong=?";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(2, soPhong);
			ps.setString(1, tinhTrang);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Cập Nhật Tình Trạng Phòng!");
		}	
	}
	public ArrayList<PHONG> TimKiemTinhTrangPhong() {
		ArrayList<PHONG> dsnv = new ArrayList<PHONG>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from [dbo].[PHONG] where TinhTrang=?");
			while (rs.next()) {
				PHONG nv = new PHONG(rs.getString(1), new LOAIPHONG(rs.getString(2)), rs.getString(3));	 		
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsnv;
	}
	public void ThemPhong(String soPhong, String loaiPhong, String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into [dbo].[PHONG](SoPhong,MaLoaiPhong,TinhTrang) values(?,?,?)";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, soPhong);
			ps.setString(2, loaiPhong);
			ps.setString(3, tinhTrang);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Thêm Phòng");
		}		
	}
	public void CapNhatPhong(String soPhong, String loaiPhong, String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "update [dbo].[PHONG] set MaLoaiPhong=?,TinhTrang=? where SoPhong=?";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(3, soPhong);
			ps.setString(1, loaiPhong);
			ps.setString(2, tinhTrang);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Cập Nhật Phòng");
		}		
	}
	public void XoaPhong(String soPhong) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "delete [dbo].[PHONG] where SoPhong=?";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, soPhong);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Xóa Phòng");
		}		
	}
}
