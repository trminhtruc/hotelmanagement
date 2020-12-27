package gui;

import java.util.Map;

import ConnectDB.ConnectDB;
import dao.DAO_KHACHHANG;
import dao.DAO_THONGKE;
import entity.KHACHHANG;

public class TestThongKe {
	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DAO_THONGKE dao = new DAO_THONGKE();
		DAO_KHACHHANG daokh = new DAO_KHACHHANG();
		Map<String, Integer> map = dao.getKhachHangTiemNang("2019", "11");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			for (KHACHHANG string : daokh.getalltbKhachHang()) {
				if(string.getMaKH().equals(entry.getKey())) {
					System.out.println(string.getTenKH());
				}
			}
		}

	}
}
