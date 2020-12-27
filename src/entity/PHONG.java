package entity;

public class PHONG {
	private String SoPhong;
	private LOAIPHONG phong;
	private String TinhTrang;
	
	public String getSoPhong() {
		return SoPhong;
	}
	public void setSoPhong(String soPhong) {
		SoPhong = soPhong;
	}
	public LOAIPHONG getPhong() {
		return phong;
	}
	public void setPhong(LOAIPHONG phong) {
		this.phong = phong;
	}
	public String getTinhTrang() {
		return TinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		TinhTrang = tinhTrang;
	}
	public PHONG(String soPhong, LOAIPHONG phong, String tinhTrang) {
		super();
		SoPhong = soPhong;
		this.phong = phong;
		TinhTrang = tinhTrang;
	}
	
	public PHONG(String soPhong) {
		super();
		SoPhong = soPhong;
	}	
	public PHONG(LOAIPHONG phong) {
		super();
		this.phong = phong;
	}
	public PHONG() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
