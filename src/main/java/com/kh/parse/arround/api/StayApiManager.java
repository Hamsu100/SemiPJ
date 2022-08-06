package com.kh.parse.arround.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.arround.model.vo.Stay;
import com.kh.parse.common.DistanceFilter5;

public class StayApiManager {	
	
	private APIParse ap = new APIParse();
	private DistanceFilter5 df = new DistanceFilter5();
	
	public List<Stay> getStayBasic(List<Map<String, String>> stayData) {
		
		List<Stay> stayList = new ArrayList<Stay>();
		for (Map<String, String> map : stayData) {
			Stay s = new Stay();
			try {
				String lat = map.get("mapy");
				String lng = map.get("mapx");
				s.setStay_no(Integer.parseInt(map.get("contentid")));
				s.setName(map.get("title"));
				s.setLongitude(lng);
				s.setLatitude(lat);
				s.setImg(map.get("firstimage"));
				s.setAddr(map.get("addr1"));
				s.setTel(map.get("tel"));
				s.setArea_code(Integer.parseInt(map.get("areacode")));
				
				if (df.calDistance(Double.parseDouble(lat), Double.parseDouble(lng))) {
					stayList.add(s);
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		return stayList;
	}
	
	public List<Stay> getStay(List<Stay> stayList, int start, int end) {
		List<Stay> returnList = new ArrayList<Stay>();
		for (int i = start; i <= end; i++) {
			Stay s = stayList.get(i);
			String contentid = s.getStay_no() + "";
			for (Map<String, String> map : ap.getOverview(contentid)) {
				s.setContent(map.get("overview"));
			}
			StringBuffer sb = new StringBuffer();
//			if (s.getImg() != null) {
			if (s.getImg() != null || !s.getImg().isBlank()) {
				sb.append(s.getImg() + ",");
			}
			int count = 0;
			for (Map<String, String> map : ap.getImg(contentid)) {
				sb.append(map.get("originimgurl") + ",");
				count++;
				if (count == 10) {
					break;
				}
			}
			
			s.setImg(removeEndComma(removeStartComma(sb.toString().strip())));
			
			returnList.add(s);
		}
		return returnList;
	}
	
	
	private static String removeStartComma(String string) {
		if (string.startsWith(",")) {
			return string.substring(1, string.length());
		} 
		return string;
	}
	
	private static String removeEndComma(String string) {
		if (string.endsWith(",")) {
			return string.substring(0, string.length() - 1);
		} 
		return string;
	}

}
