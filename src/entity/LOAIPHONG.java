package entity;

public class LOAIPHONG {
	
	String maLoaiPhong;
	int soLuong;
	byte[] hinhAnh;
	double donGia;
	
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}	
	
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public byte[] getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public LOAIPHONG(String maLoaiPhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
	}
	public LOAIPHONG(double donGia) {
		super();
		this.donGia = donGia;
	}
	
	public LOAIPHONG(String maLoaiPhong, int soLuong, byte[] hinhAnh, double donGia) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.soLuong = soLuong;
		this.hinhAnh = hinhAnh;
		this.donGia = donGia;
	}
	public LOAIPHONG() {
		super();
		// TODO Auto-generated constructor stub
	}	

}
