package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.HOADONDICHVU;
import entity.HOADONPHONG;
import entity.KHACHHANG;
import entity.NHANVIEN;

public class DAO_HDDICHVU {
	public ArrayList<HOADONDICHVU> getalltbHDDV() {
		ArrayList<HOADONDICHVU> dsdv = new ArrayList<HOADONDICHVU>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from HOADONDICHVU");

			while (rs.next()) {
				//HOADONPHONG nv = new HOADONPHONG(rs.getString(1), new KHACHHANG(rs.getString(3)), new NHANVIEN(rs.getString(2)));	
				String local[] = rs.getString(4).split(" ");
				String timedate[] = local[0].split("-");		
				LocalDate localdate = LocalDate.of(Integer.parseInt(timedate[0]), Integer.parseInt(timedate[1]), Integer.parseInt(timedate[2]));
				System.out.println("ngay trong tuan "+localdate.getDayOfWeek());
				HOADONDICHVU dv = new HOADONDICHVU(rs.getString(1), new NHANVIEN(rs.getString(2)), new KHACHHANG(rs.getString(3)), localdate, rs.getString(5));
				dsdv.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsdv;
	}
	
//	public void DatPhong(String maHD, String nhanVien, String khachHang) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		String query = "insert into [dbo].[HOADONPHONG](MaHDP,MaNV,MaKH) values(?,?,?)";		
//		PreparedStatement ps;
//		try {
//			ps = con.prepareStatement(query);
//			
//			ps.setString(1, maHD);
//			ps.setString(2, nhanVien);
//			ps.setString(3, khachHang);
//
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block		
//			JOptionPane.showMessageDialog(null, "Lỗi Thêm");
//		}		
//	}
	public void DatDichVu(String mahoadon,String manhanvien, String makhachhang, String thoigian,String tinhtrang) {
		System.out.println("da add vao 2");
		Connection con = ConnectDB.getInstance().getConnection();
		
		String query = "insert into [dbo].[HOADONDICHVU](MaHDDV,MaNV,MaKH,ThoiGian,TinhTrang) values(?,?,?,?,?)";		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, mahoadon);
			ps.setString(2, manhanvien);
			ps.setString(3, makhachhang);
			ps.setString(4, thoigian);
			ps.setString(5, tinhtrang);
			System.out.println("da add vao ");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
		}		
	}
	
//	public void upDateCustomer(String maKh, String tenKh, String cmnd, String sdt, boolean gioiTinh) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		String query = "UPDATE KHACHHANG SET TenKH = ?, CMND = ? , SDT = ?, GioiTinh = ? WHERE MaKH =?";
//		PreparedStatement ps;
//		
//		try {
//			ps = con.prepareStatement(query);
//			
//			ps.setString(5, maKh);
//			ps.setString(1, tenKh);
//			ps.setString(2, cmnd);
//			ps.setString(3, sdt);
//			ps.setBoolean(4, gioiTinh);
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public void updateHoaDonDichVu(String mahoadon ,String tinhTrang) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "UPDATE HOADONDICHVU SET TinhTrang = ? where MaHDDV = ?";
		PreparedStatement ps;
		try {
			
			ps = con.prepareStatement(query);
			ps.setString(2, mahoadon);
			ps.setString(1, tinhTrang);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
