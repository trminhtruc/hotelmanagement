package entity;

public class TAIKHOAN {
	private NHANVIEN nhanVien;
	private String tentk, mk;
	
	public TAIKHOAN(NHANVIEN nhanVien, String tentk, String mk) {
		super();
		this.nhanVien = nhanVien;
		this.tentk = tentk;
		this.mk = mk;
	}

	public NHANVIEN getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NHANVIEN nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getTentk() {
		return tentk;
	}

	public void setTentk(String tentk) {
		this.tentk = tentk;
	}

	public String getMk() {
		return mk;
	}

	public void setMk(String mk) {
		this.mk = mk;
	}
	
	
}
