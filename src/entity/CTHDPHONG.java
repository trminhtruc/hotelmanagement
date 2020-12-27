package entity;

import java.time.LocalDateTime;
import java.util.Date;

public class CTHDPHONG {
	private HOADONPHONG hdP;
	private PHONG phong;
	private LocalDateTime ngayDat, ngayTra;
	private String tinhTrang;
	

	public CTHDPHONG(HOADONPHONG hdP, PHONG phong, LocalDateTime ngayDat, LocalDateTime ngayTra, String tinhTrang) {
		super();
		this.hdP = hdP;
		this.phong = phong;
		this.ngayDat = ngayDat;
		this.ngayTra = ngayTra;
		this.tinhTrang = tinhTrang;
		
	}

	public LocalDateTime getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(LocalDateTime ngayTra) {
		this.ngayTra = ngayTra;
	}

	public LocalDateTime getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(LocalDateTime ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public PHONG getPhong() {
		return phong;
	}
	public void setPhong(PHONG phong) {
		this.phong = phong;
	}
	public HOADONPHONG getHdP() {
		return hdP;
	}
	public void setHdP(HOADONPHONG hdP) {
		this.hdP = hdP;
	}
	public CTHDPHONG() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
