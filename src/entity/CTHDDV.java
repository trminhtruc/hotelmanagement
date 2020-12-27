package entity;

public class CTHDDV {
	DICHVU dichVu;
	HOADONDICHVU hddv;
	int soluong;
	
	public DICHVU getDichVu() {
		return dichVu;
	}
	public void setDichVu(DICHVU dichVu) {
		this.dichVu = dichVu;
	}
	public HOADONDICHVU getHddv() {
		return hddv;
	}
	public void setHddv(HOADONDICHVU hddv) {
		this.hddv = hddv;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public CTHDDV( HOADONDICHVU hddv,DICHVU dichVu,int soluong) {
		super();
		this.dichVu = dichVu;
		this.hddv = hddv;
		this.soluong = soluong;
	}
	public CTHDDV() {
		super();
		// TODO Auto-generated constructor stub
	}
}
