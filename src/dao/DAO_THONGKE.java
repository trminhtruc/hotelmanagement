package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ConnectDB.ConnectDB;
import entity.LOAIPHONG;
import entity.PHONG;

public class DAO_THONGKE {
	public Map<String, Integer> getKhachHangTiemNang(String nam, String thang) {
		// ArrayList<PHONG> dsnv = new ArrayList<PHONG>();
		Map<String, Integer> map = new HashMap<>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(
					"select top 3 hd.MaKH,COUNT(hd.MaKH) \r\n" + "from HOADONPHONG hd join KHACHHANG kh \r\n"
							+ "on kh.MaKH = hd.MaKH  join CTHDPHONG ct on ct.MaHDP = hd.MaHDP\r\n"
							+ "where ThoiGianTra is not null and  year(ct.ThoiGianDat) = '" + nam
							+ "' and month(ct.ThoiGianDat) = '" + thang + "'\r\n" + "group by hd.MaKH \r\n"
							+ "order by COUNT(hd.MaKH) desc");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sortByValue(map, false);
	}

	public Map<String, Integer> getLoaiPhongPhoBien(String nam, String thang) {
		// ArrayList<PHONG> dsnv = new ArrayList<PHONG>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select top 3 loaiPhong.MaLoaiPhong,count(loaiPhong.MaLoaiPhong)\r\n"
					+ "from CTHDPHONG ct join PHONG p on p.SoPhong = ct.SoPhong \r\n"
					+ "join LOAIPHONG loaiPhong on loaiPhong.MaLoaiPhong = p.MaLoaiPhong \r\n"
					+ "where ThoiGianTra is not null and  year(ct.ThoiGianDat) = '" + nam
					+ "' and month(ct.ThoiGianDat) = '" + thang + "'\r\n" + "group by loaiPhong.MaLoaiPhong \r\n"
					+ "order by count(loaiPhong.MaLoaiPhong) desc");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			
			}
			
			
			// sortHashMapByValues(map)
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sortByValue(map, false);
	}

	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order) {
		List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

		// Sorting the list based on values
		list.sort((o1, o2) -> order
				? o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey())
						: o1.getValue().compareTo(o2.getValue())
				: o2.getValue().compareTo(o1.getValue()) == 0 ? o2.getKey().compareTo(o1.getKey())
						: o2.getValue().compareTo(o1.getValue()));
		return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	}

	public Map<String, Integer> getDichVuPhoBien(String nam, String thang) {
		// ArrayList<PHONG> dsnv = new ArrayList<PHONG>();
		Map<String, Integer> map = new HashMap<>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select top 3 ct.MaDV,count(ct.MaDV)\r\n"
					+ "from HOADONDICHVU hd join CTHDDV ct on ct.MaHDDV = hd.MaHDDV \r\n"
					+ "where TinhTrang =  N'ĐÃ THANH TOÁN' and  year(ThoiGian) = '" + nam + "' and month(ThoiGian) = '"
					+ thang + "'\r\n" + "group by ct.MaDV\r\n" + "order by count(ct.MaDV) desc");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sortByValue(map, false);
	}

	public LinkedHashMap<Integer, String> sortHashMapByValues(HashMap<Integer, String> passedMap) {
		ArrayList<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
		ArrayList<String> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<Integer, String> sortedMap = new LinkedHashMap<>();

		Iterator<String> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			String val = valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Integer key = keyIt.next();
				String comp1 = passedMap.get(key);
				String comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}
}
