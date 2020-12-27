package entity;

import java.time.LocalDate;
import java.util.Date;

public class HOADONDICHVU {
	private String maHDDV;
	private NHANVIEN nhanVien;
	private KHACHHANG khachHang;
	private LocalDate localdate;
	private String tinhtrang;
	
	public String getMaHDDV() {
		return maHDDV;
	}
	public void setMaHDDV(String maHDDV) {
		this.maHDDV = maHDDV;
	}
	public NHANVIEN getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NHANVIEN nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KHACHHANG getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KHACHHANG khachHang) {
		this.khachHang = khachHang;
	}
	public LocalDate getLocaldate() {
		return localdate;
	}
	public void setLocaldate(LocalDate localdate) {
		this.localdate = localdate;
	}
	public String getTinhtrang() {
		return tinhtrang;
	}
	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public HOADONDICHVU() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HOADONDICHVU(String maHDDV, NHANVIEN nhanVien, KHACHHANG khachHang, LocalDate localdate, String tinhtrang) {
		super();
		this.maHDDV = maHDDV;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.localdate = localdate;
		this.tinhtrang = tinhtrang;
	}
	
	public HOADONDICHVU(String maHDDV) {
		super();
		this.maHDDV = maHDDV;
	}
	
	
}
