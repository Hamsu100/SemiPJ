package com.kh.common.parse;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAreaCode {

	private ParseCommon cp = new ParseCommon();

	public List<AreaCode> getAreaCode() {
		List<AreaCode> returnList = new ArrayList<>();

		List<Map<String, String>> parseList = cp.parsingCSV("./areaCode.csv");

		for (Map<String, String> map : parseList) {
			AreaCode ac = new AreaCode();

			ac.setArea_code(Integer.parseInt(map.get("areaCode")));
			ac.setArea_name(map.get("﻿areaName"));
			ac.setLocType(getLocType(Integer.parseInt(map.get("areaCode"))));

			returnList.add(ac);
		}
		return returnList;
	}

	public int findAreaCodeByAddr(String addr) {

		List<AreaCode> acList = getAreaCode();

		String[] sidoKey = { "경기도", "강원도", "충청북도", "충청남도", "경상북도", "경상남도", "전라북도", "전라남도", "제주도" };
		String[] sidoValue = { "경기", "강원", "충북", "충남", "경북", "경남", "전북", "전남", "제주" };

		HashMap<String, String> sidoMap = new HashMap<>();
		for (int i = 0; i < sidoKey.length; i++) {
			sidoMap.put(sidoKey[i], sidoValue[i]);
		}
		// 광역 특별시 일경우
		for (AreaCode aCode : acList) {
			String cityName = aCode.getArea_name();
			if (addr.contains(cityName)) {
				return aCode.getArea_code();
			}
		}

		// 8도+제주 일 경우
		for (String sido : sidoKey) {
			if (addr.contains(sido)) {
				for (AreaCode ac : acList) {
					if (sidoMap.get(sido).equals(ac.getArea_name())) {
						return ac.getArea_code();
					}
				}
			}
		}

		return 0;
	}

	public int getLocType(int areaCode) {
		int result = 0;
		switch (areaCode) {
		case 1: case 2: case 3: case 5: case 8: case 31: case 33: case 34: case 37:
			result = 2;	break;
		case 4: case 7: case 32: case 35:
			result = 1;	break;
		case 6:	case 36: case 38:
			result = 3;	break;
		case 39:
			result = 4;	break;
		}
		return result;
	}

	public int toDB(Connection conn, AreaCode ac) {
		String sql = "insert into area values (?, ?, ?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ac.getArea_code());
			pstmt.setString(2, ac.getArea_name());
			pstmt.setInt(3, getLocType(ac.getArea_code()));
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int locTypeToDB(Connection conn, LocType loc) {
		int result = 0;

		String sql = "insert into location values (?, ?)";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc.getLocName());
			pstmt.setInt(2, loc.getLocNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

}