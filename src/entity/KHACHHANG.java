package entity;

public class KHACHHANG {
	private String MaKH, TenKH, CMND, SDT;
	private boolean GioiTinh;
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getTenKH() {
		return TenKH;
	}
	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public boolean isGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}
	
	public KHACHHANG(String maKH, String tenKH, String cMND, String sDT, boolean gioiTinh) {
		super();
		MaKH = maKH;
		TenKH = tenKH;
		CMND = cMND;
		SDT = sDT;
		GioiTinh = gioiTinh;
	}
	
	public KHACHHANG(String maKH) {
		super();
		MaKH = maKH;
	}
	public KHACHHANG() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
