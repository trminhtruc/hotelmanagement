package entity;

import java.util.Date;

public class HOADONPHONG {
	private String maHDP;
	private KHACHHANG khachHang;
	private NHANVIEN nhanVien;
	
	public String getMaHDP() {
		return maHDP;
	}

	public void setMaHDP(String maHDP) {
		this.maHDP = maHDP;
	}
	public KHACHHANG getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KHACHHANG khachHang) {
		this.khachHang = khachHang;
	}
	public NHANVIEN getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NHANVIEN nhanVien) {
		this.nhanVien = nhanVien;
	}
	public HOADONPHONG(String maHDP) {
		super();
		this.maHDP = maHDP;
	}		

	public HOADONPHONG(String maHDP, KHACHHANG khachHang, NHANVIEN nhanVien) {
		super();
		this.maHDP = maHDP;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}

	public HOADONPHONG() {
		super();
		// TODO Auto-generated constructor stub
	}		
	
}
