package com.kh.parse.arround.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.kh.arround.model.vo.Park;

public class ParkApiManager {

	public static String path = "Park.csv";
	public static String charset = "utf-8";
	public static String delemeter = ",";

	public static int fcategory_no_set = 6;

	public static List<Park> callParkByCSV() {

		List<Park> list = new ArrayList<Park>();
		String lineData = null;
		try {
			FileReader fr = new FileReader(path, Charset.forName(charset));
			try (BufferedReader br = new BufferedReader(fr)) {

				while ((lineData = br.readLine()) != null) {
					List<String> lineList = csvLineToList(lineData);
					if (lineList == null) { // <<1
						continue;
					}

					int index = 1;
					long park_no = 0; // << index 0 (not use)
					int fcategory_no = fcategory_no_set;
					String park_name = getStrValue(lineList, index++);
					String park_new_addr = getStrValue(lineList, index++);
					String park_addr = getStrValue(lineList, index++);
					String park_wday_settime = getStrValue(lineList, index++) + " ~ " + getStrValue(lineList, index++);
					String park_sat_settime = getStrValue(lineList, index++) + " ~ " + getStrValue(lineList, index++);
					String park_hol_settime = getStrValue(lineList, index++) + " ~ " + getStrValue(lineList, index++);
					String park_latitude = getStrValue(lineList, index++);
					String park_longitude = getStrValue(lineList, index++);
					int area_code = setAreaCode(park_new_addr, park_addr);
					double park_distance = 0;

					Park park = new Park(park_no, fcategory_no, park_name, park_new_addr, park_addr, park_wday_settime,
							park_sat_settime, park_hol_settime, park_latitude, park_longitude, area_code,
							park_distance);

					list.add(park);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		int testCount = 1; // <<<< for test
		for (Park test : list) {
			System.out.println(testCount++ + " : " + test.getPark_name());
//			System.out.println(test.toString() + "\n");
		}
		return list;
	}

	private static List<String> csvLineToList(String csvLine) {
		try {

			if (csvLine.contains(",\"") == true) { // <<2
				csvLine = filter(csvLine);
			}

			List<String> list = new ArrayList<String>();
			String[] array = csvLine.split(delemeter);
			for (String str : array) {

				str = str.replace("\"", "").strip().replace("_", ","); // <<2

				list.add(str);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // <<1
	}

	private static String filter(String str) { // <<2
		StringBuffer sb = new StringBuffer(str);
		StringBuffer newsb = new StringBuffer();
		boolean in = false;
		for (int i = 0; i < sb.length(); i++) {
			char value = sb.charAt(i);
			if (value == '\"') {
				in = !in;
			}
			if (in == true) {
				if (value == ',') {
					value = '_';
				}
			}
			newsb.append(value);
		}
		return newsb.toString();
	}

	private static String getStrValue(List<String> list, int index) {
		try {
			return list.get(index);
		} catch (Exception e) {
//			e.printStackTrace();
			return "";
		}
	}

	private static int setAreaCode(String newAddr, String oldAddr) {
		if (newAddr.contains("서울") || oldAddr.contains("서울")) {
			return 1;
		} else if (newAddr.contains("경기") || oldAddr.contains("경기")) {
			return 31;
		} // << 경기 광주 / 광주광역시 구분 위해 선처리
		else if (newAddr.contains("인천") || oldAddr.contains("인천")) {
			return 2;
		} else if (newAddr.contains("대전") || oldAddr.contains("대전")) {
			return 3;
		} else if (newAddr.contains("대구") || oldAddr.contains("대구")) {
			return 4;
		} else if (newAddr.contains("광주") || oldAddr.contains("광주")) {
			return 5;
		} else if (newAddr.contains("부산") || oldAddr.contains("부산")) {
			return 6;
		} else if (newAddr.contains("울산") || oldAddr.contains("울산")) {
			return 7;
		} else if (newAddr.contains("세종") || oldAddr.contains("세종")) {
			return 8;
		} else if (newAddr.contains("강원") || oldAddr.contains("강원")) {
			return 32;
		} else if (newAddr.contains("충청북도") || oldAddr.contains("충청북도")) {
			return 33;
		} else if (newAddr.contains("충북") || oldAddr.contains("충북")) {
			return 33;
		} else if (newAddr.contains("충청남도") || oldAddr.contains("충청남도")) {
			return 34;
		} else if (newAddr.contains("충남") || oldAddr.contains("충남")) {
			return 34;
		} else if (newAddr.contains("경상북도") || oldAddr.contains("경상북도")) {
			return 35;
		} else if (newAddr.contains("경북") || oldAddr.contains("경북")) {
			return 35;
		} else if (newAddr.contains("경상남도") || oldAddr.contains("경상남도")) {
			return 36;
		} else if (newAddr.contains("경남") || oldAddr.contains("경남")) {
			return 36;
		} else if (newAddr.contains("전라북도") || oldAddr.contains("전라북도")) {
			return 37;
		} else if (newAddr.contains("전북") || oldAddr.contains("전북")) {
			return 37;
		} else if (newAddr.contains("전라남도") || oldAddr.contains("전라남도")) {
			return 38;
		} else if (newAddr.contains("전남") || oldAddr.contains("전남")) {
			return 38;
		} else if (newAddr.contains("제주") || oldAddr.contains("제주")) {
			return 39;
		} else
			return 0; // << unknown area code
	}

	public static void main(String[] args) {
		callParkByCSV();
	}

}
