package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.LOAIPHONG;

public class DAO_LOAIPHONG {	
	public ArrayList<LOAIPHONG> getalltbLoaiPhong() {
		ArrayList<LOAIPHONG> dsLoaiPhong = new ArrayList<LOAIPHONG>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from [dbo].[LOAIPHONG]");

			while (rs.next()) {
				LOAIPHONG p = new LOAIPHONG(rs.getString(1), rs.getInt(2), rs.getBytes(3), rs.getDouble(4));	 		
				dsLoaiPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsLoaiPhong;
	}
	public void ThemLoaiPhong(String maLP, int soluong, byte[] image, double dongia) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into [dbo].[LOAIPHONG](MaLoaiPhong,SoLuongToiDa,HinhAnh,DonGia) values(?,?,?,?)";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, maLP);
			ps.setInt(2, soluong);
			ps.setBytes(3, image);
			ps.setDouble(4, dongia);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Thêm Loại Phòng");
		}		
	}
	public void CapNhatLoaiPhong(String maLP, int soluong, byte[] image, double dongia) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query;
		PreparedStatement ps;
		try {
			if(image == null) {
				query = "update [dbo].[LOAIPHONG] set SoLuongToiDa=?, DonGia=? where MaLoaiPhong = ?";
				ps = con.prepareStatement(query);				
				ps.setString(3, maLP);		
				ps.setInt(1, soluong);				
				ps.setDouble(2, dongia); 													
				ps.executeUpdate();
			}else {
				query = "update [dbo].[LOAIPHONG] set SoLuongToiDa=?, HinhAnh=?, DonGia=? where MaLoaiPhong = ?";
				ps = con.prepareStatement(query);				
				ps.setString(4, maLP);		
				ps.setInt(1, soluong);			
				ps.setBytes(2, image);			
				ps.setDouble(3, dongia); 													
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Cập Nhật Loại Phòng");
		}		
	}
	public void XoaLoaiPhong(String maLoaiPhong) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "delete [dbo].[LOAIPHONG] where MaLoaiPhong=?";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, maLoaiPhong);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Xóa Loại Phòng");
		}		
	}
}
