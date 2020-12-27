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
import entity.DICHVU;

public class DAO_DICHVU {
	public ArrayList<DICHVU> getalltbDichVu() {
		ArrayList<DICHVU> dsnv = new ArrayList<DICHVU>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from DICHVU");
			while (rs.next()) {
				DICHVU nv = new DICHVU(rs.getString(1), rs.getString(2), rs.getDouble(3),rs.getString(4));	 		
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsnv;
	}
	public void themDichVu(String ma, String ten, double donGia, String tinhtrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "INSERT INTO DICHVU(MaDV,TenDV,DonGia,TinhTrang) values(?,?,?,?)";
		PreparedStatement ps ;
		
		try {
			ps = con.prepareStatement(query);

			ps.setString(1, ma);
			ps.setString(2, ten);
			ps.setDouble(3, donGia);
			ps.setString(4, tinhtrang);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu");
		}
		
	}
	public void deleteDichVu(String maDV) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "DELETE FROM DICHVU WHERE MaDV LIKE '%"+ maDV + "%'";
		
		PreparedStatement ps;
		try {
			ps =con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateDichVu(String MaDV, String TenDV, double DonGia, String TinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "UPDATE DICHVU SET TenDV = ?, DonGia = ?, TinhTrang =? WHERE MaDV =?";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(4, MaDV);
			ps.setString(1, TenDV);
			ps.setDouble(2, DonGia);
			ps.setString(3, TinhTrang);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DICHVU findMaDichVu(String maDichVu) {
		List<DICHVU> listDichVu = getalltbDichVu();
		for (DICHVU dichvu : listDichVu) {
			if(maDichVu.equals(dichvu.getMaDV())) {
				return dichvu;
			}
		}
		return null;
	}
}
