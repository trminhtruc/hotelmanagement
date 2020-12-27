package gui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;

import ConnectDB.ConnectDB;
import dao.DAO_CTHDP;
import dao.DAO_HDPHONG;
import entity.CTHDPHONG;
import entity.HOADONPHONG;

public class ThoiGian {
	public static void main(String[] args) {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ten = LocalDateTime.now();
//		Date date = new Date();
//		System.out.println(sdf.format(ten)+"fawef"+date);

		try {
			ConnectDB.getInstance().connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DAO_HDPHONG dao_hoadonphong = new DAO_HDPHONG();
		DAO_CTHDP dao_ct = new DAO_CTHDP();

		LocalDate date;
		LocalTime time;
		ten = LocalDateTime.of(LocalDate.of(2019, 01, 03), LocalTime.of(1, 1, 2));
		for (CTHDPHONG string : dao_ct.getalltbHDP()) {
			System.out.println(string.getNgayDat()+"ngaydat"+string.getPhong().getSoPhong());
			System.out.println(string.getNgayTra()+"ngay tra");
		}
		System.out.println(dao_ct.getalltbHDP().size());
//		System.out.println(ten);
		new ThoiGian();
//		System.out.println();
		// HD.30.10.2019.0001
		// dao_hoadonphong.LapHoaDon("fawe", "NVQL001", "KH0001");
//		dao_ct.DatPhong("fawe", "P02005", sdf.format(ten),"NHẬN PHÒNG");
		// dao_ct.DatPhong("fawe", "P02003", sdf.format(ten), "NHẬN PHÒNG");

	}
	public ThoiGian() {
		autoMaHoaDonPhong();
	}
	public String autoMaHoaDonPhong() {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ten = LocalDateTime.now();
		
		DAO_HDPHONG dao_hoadonphong = new DAO_HDPHONG();
		DAO_CTHDP dao_ct = new DAO_CTHDP();

//		System.out.println(LocalDateTime.now().toString());
//		dao_ct.DatPhong("HD.06.11.2019.0005", "P05001",LocalDateTime.now().toString() , "NHẬN PHÒNG");
		
		LocalDate date;
		LocalTime time;
		ten = LocalDateTime.of(LocalDate.of(2019, 01, 03), LocalTime.of(1, 1, 2));
		
		String mahd = "HD";
		LocalDateTime ngaydat = LocalDateTime.now();
		String ngay = null;
		String thang;
		String nam = String.valueOf(ngaydat.getYear());
		String gio;
		String phut;
		String giay;

		if (ngaydat.getDayOfMonth() > 0 && ngaydat.getDayOfMonth() < 10) {
			ngay = "0" + ngaydat.getDayOfMonth();
		} else
			ngay = String.valueOf(ngaydat.getDayOfMonth());

		if (ngaydat.getMonthValue() > 0 && ngaydat.getMonthValue() < 10) {
			thang = "0" + ngaydat.getMonthValue();
		} else
			thang = String.valueOf(ngaydat.getMonthValue());
		
		
		
		if (ngaydat.getHour() > 0 && ngaydat.getHour() < 10) {
			gio = "0" + ngaydat.getHour();
		} else
			gio = String.valueOf(ngaydat.getHour());
		
		
		if (ngaydat.getMinute() > 0 && ngaydat.getMinute() < 10) {
			phut = "0" + ngaydat.getMinute();
		} else
			phut = String.valueOf(ngaydat.getMinute());
		
		if (ngaydat.getSecond() > 0 && ngaydat.getSecond() < 10) {
			giay = "0" + ngaydat.getSecond();
		} else
			giay = String.valueOf(ngaydat.getSecond());
		
		
		mahd = mahd+"."+ngay+"."+thang+"."+nam+".";
//		System.out.println(mahd);
		if(dao_ct.getalltbHDP()==null) {
			return mahd+"0000";
		}
		List<HOADONPHONG> listCtPhong = dao_hoadonphong.getalltbHDP();
		int so = 0;
		String ngayThangNam = ngay+"."+thang+"."+nam;
		for (HOADONPHONG cthdphong : listCtPhong) {
	//		System.out.println(cthdphong.getMaHDP().substring(14, 18));
			so = Integer.parseInt(cthdphong.getMaHDP().substring(14, 18))+1;
	//		System.out.println(cthdphong.getMaHDP().substring(3, 13));
			if(!ngayThangNam.equals(cthdphong.getMaHDP().substring(3, 13))) {
				so = 0;
			}
		}
		
		if(so>=0&&so<10) {
			mahd = mahd+"000"+so;
		}
		if(so>=10&&so<100) {
			mahd = mahd+"00"+so;
		}
		if(so>=100&&so<1000) {
			mahd = mahd+"0"+so;
		}
		if(so>=1000&&so<10000) {
			mahd = mahd+so;
		}	
		return mahd;
	}
}
