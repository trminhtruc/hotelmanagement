package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.CTHDDV;
import entity.CTHDPHONG;
import entity.DICHVU;
import entity.HOADONDICHVU;
import entity.HOADONPHONG;
import entity.NHANVIEN;
import entity.PHONG;

public class DAO_CTHDP {
	private SimpleDateFormat formatDay = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SimpleDateFormat formatNgay = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat formatGio = new SimpleDateFormat("HH:mm:ss");

	public ArrayList<CTHDPHONG> getalltbHDP() {
		ArrayList<CTHDPHONG> dsCTHDP = new ArrayList<CTHDPHONG>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from CTHDPHONG");

			while (rs.next()) {
				CTHDPHONG cthdp = null;
				if (rs.getDate(4) == null) {
					cthdp = cthdp = new CTHDPHONG(new HOADONPHONG(rs.getString(1)), new PHONG(rs.getString(2)),
							rs.getTimestamp(3).toLocalDateTime(), null, rs.getString(5));
					dsCTHDP.add(cthdp);
				} else {
					cthdp = cthdp = new CTHDPHONG(new HOADONPHONG(rs.getString(1)), new PHONG(rs.getString(2)),
							rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(),
							rs.getString(5));
					dsCTHDP.add(cthdp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHDP;
	}

	public void DatPhong(String maHD, String soPhong, String ngayDat, String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into [dbo].[CTHDPHONG](MaHDP,SoPhong,ThoiGianDat,ThoiGianTra,TinhTrang) values(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);

			ps.setString(1, maHD);
			ps.setString(2, soPhong);
			ps.setString(3, ngayDat);
			ps.setString(4, null);
			ps.setString(5, tinhTrang);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CapNhat(String soPhong, String ngayTra, String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "update [dbo].[CTHDPHONG] set ThoiGianTra=?,TinhTrang=? where SoPhong=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);

			ps.setString(3, soPhong);
			ps.setString(1, ngayTra);
			ps.setString(2, tinhTrang);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
