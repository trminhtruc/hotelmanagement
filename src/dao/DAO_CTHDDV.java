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
import entity.CTHDDV;
import entity.DICHVU;
import entity.HOADONDICHVU;
import entity.NHANVIEN;

public class DAO_CTHDDV {
	public ArrayList<CTHDDV> getalltbCTHDDV() {
		ArrayList<CTHDDV> dsCTHDDV = new ArrayList<CTHDDV>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();

			Statement statement = con.createStatement();
			// ResultSet rs = statement.executeQuery("Select * from CTHDDV");

			ResultSet rs = statement.executeQuery("select * from CTHDDV ");
			while (rs.next()) {
				// CTHDDV cthddv = new CTHDDV(new DICHVU(rs.getString(1)), new
				// HOADONDICHVU(rs.getString(2)), new NHANVIEN(rs.getString(3)));
				int soluong = Integer.parseInt(rs.getString(3));
			//	CTHDDV cthddv = new CTHDDV(new HOADONDICHVU("4"), new DICHVU(rs.getString(2)), soluong);
				CTHDDV dv = new CTHDDV(new HOADONDICHVU(rs.getString(1)), new DICHVU(rs.getString(2)), soluong);
				
				dsCTHDDV.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHDDV;
	}
	public void themChiTiet(String mahoadon,String madichvu,int soluong) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into [dbo].[CTHDDV](MaHDDV,MaDV,SoLuong) values(?,?,?)";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, mahoadon);
			ps.setString(2, madichvu);
			ps.setInt(3, soluong);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			JOptionPane.showMessageDialog(null, "Lỗi Thêm");
		}		
	}

}
