package entity;

public class DICHVU {
	private String maDV;
	private String tenDV;
	private Double giaDV;
	private String tinhtrang;
	
	public DICHVU() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DICHVU(String maDV, String tenDV, Double giaDV, String tinhtrang) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.giaDV = giaDV;
		this.tinhtrang = tinhtrang;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public Double getGiaDV() {
		return giaDV;
	}

	public void setGiaDV(Double giaDV) {
		this.giaDV = giaDV;
	}

	public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public DICHVU(String maDV) {
		super();
		this.maDV = maDV;
	}
	
	
}
