package entity;

public class NHANVIEN {
	private String MaNV, TenNV;
	private boolean GioiTinh;
	private String CMND, Sdt, ChucVu;
	private byte[] hinhAnh;
	private String tinhTrang;
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public String getTenNV() {
		return TenNV;
	}
	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}
	public boolean isGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getSdt() {
		return Sdt;
	}
	public void setSdt(String sdt) {
		Sdt = sdt;
	}
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}
	
	
	public byte[] getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public NHANVIEN(String maNV, String tenNV, boolean gioiTinh, String cMND, String sdt, String chucVu,
			byte[] hinhAnh,String tinhTrang) {
		super();
		MaNV = maNV;
		TenNV = tenNV;
		GioiTinh = gioiTinh;
		CMND = cMND;
		Sdt = sdt;
		ChucVu = chucVu;
		this.hinhAnh = hinhAnh;
		this.tinhTrang = tinhTrang;
	}
	public NHANVIEN() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NHANVIEN(String maNV) {
		super();
		MaNV = maNV;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}	
	
}
