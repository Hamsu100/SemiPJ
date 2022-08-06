package com.kh.parse.arround.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kh.arround.model.vo.Camp;
import com.kh.parse.common.DistanceFilter10;

public class CampApiManager {
	
	public static String CAMP_URL = "https://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList";
	public static String CAMP_IMG_URL = "https://api.visitkorea.or.kr/openapi/service/rest/GoCamping/imageList";
	
		//toRiCe7NxO/3pxQhW3HO9cq2zIJqZPVsJoOs1vD1x1NQKCluxyj14Hn5RapPx23Y57sjGOp64PtbOXKMtC1ezg==
		//GFYaIRR7h%2B5VzBC0UOr0Dfa1JlCCgM%2Fe6P4cE7yER1%2Bi%2FOSIf8sUDicnuExyGImszns9Bo%2FqfcQR1eXlSiu6jA%3D%3D
		//orCD5outbUakEqSxWPAu4C3NHBeT2DtV5DHSQbUBCr4Wx74vx2MumvtD23yU8ImOKwK8KWIjpiG5ubsei4j9dA%3D%3D
	public static String key = "GFYaIRR7h%2B5VzBC0UOr0Dfa1JlCCgM%2Fe6P4cE7yER1%2Bi%2FOSIf8sUDicnuExyGImszns9Bo%2FqfcQR1eXlSiu6jA%3D%3D";
	public static String os = "ETC";
	public static String app = "AppTest";
	public static String totalCount;
	
	public static int fcategory_no_set = 3;	
	
	public static int addImgLimit = 10;
	public static int start;	
	public static int end;
	
	
	public static List<Camp> callCampByXML() {
		
		DistanceFilter10 df = new DistanceFilter10();
		
		
		// total count about camp info 
		try {
			StringBuffer urlBuffer = new StringBuffer(CAMP_URL);
			urlBuffer.append("?" + "serviceKey=" + key);
			urlBuffer.append("&" + "MobileOS=" + os );
			urlBuffer.append("&" + "MobileApp=" + app );
			urlBuffer.append("&" + "numOfRows=0" );
			
			URL url = new URL(urlBuffer.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			int code = conn.getResponseCode();
			System.out.println("statusCode : " + code);		//<<<< for test
			if (code < 200 || code >= 300) {
				System.out.println("ERROR(check http status code)");
				return null;
			}
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(conn.getInputStream());
			doc.normalizeDocument();
			
			totalCount = doc.getElementsByTagName("totalCount").item(0).getTextContent();		//<<1
			System.out.println("totalCount : " + totalCount);		//<<<< for test
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		
		// xml parsing for camp info
		List<Camp> list = new ArrayList<Camp>();
		List<Camp> listTemp = new ArrayList<Camp>();		
		
		try {
			StringBuffer urlBuffer = new StringBuffer(CAMP_URL);
			urlBuffer.append("?" + "serviceKey=" + key);
			urlBuffer.append("&" + "MobileOS=" + os);
			urlBuffer.append("&" + "MobileApp=" + app);
			urlBuffer.append("&" + "numOfRows=" + totalCount);		//<<1
			System.out.println(urlBuffer);		//<<<< for test
			
			URL url = new URL(urlBuffer.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			int code = conn.getResponseCode();
			System.out.println("statusCode : " + code);		//<<<< for test
			if (code < 200 || code >= 300) {
				System.out.println("ERROR(check http status code)");
				return null;
			}
			int count = 0;		//<<<< for test
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(conn.getInputStream());
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getElementsByTagName("item");
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Camp camp = new Camp(); 
					try {
						Element element = (Element) node;
						
						String camp_longitude 	= getStrDate(element, "mapX");
						String camp_latitude  	= getStrDate(element, "mapY");
						String camp_address   	= getStrDate(element, "addr1") + " " + getStrDate(element, "addr2");
						
						if (df.calDistance(Double.parseDouble(camp_latitude) , Double.parseDouble(camp_longitude))) {	
							
							camp.setCamp_no(getIntData(element, "contentId"));
							camp.setFcategory_no(fcategory_no_set);
							camp.setCamp_name(getStrDate(element, "facltNm"));
							camp.setCamp_longitude(camp_longitude);
							camp.setCamp_latitude(camp_latitude );
							camp.setCamp_img(getStrDate(element, "firstImageUrl"));
							camp.setCamp_content(getStrDate(element, "intro"));
							camp.setCamp_address(camp_address);
							camp.setCamp_tel(getStrDate(element, "tel"));
							camp.setArea_code(setAreaCode(camp_address));
							camp.setCamp_distance(0);
							
							listTemp.add(camp);		//<<<< 중복 방지 수정
							System.out.println("filtered index count : " + count++);		//<<<< for test
						};
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}		
			}
			conn.disconnect();
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		
		// xml parsing for camp img
		for (int i = start; i <= end; i++) {
			Camp c = listTemp.get(i);				
			String camp_no = c.getCamp_no() + "";
			String camp_img = c.getCamp_img(); 
			
			StringBuffer sb = new StringBuffer();
			
			if (camp_img != null) {
				sb.append(camp_img + ",");
			}
			
			try {
				StringBuffer urlBufferImg = new StringBuffer(CAMP_IMG_URL);
				urlBufferImg.append("?" + "serviceKey=" + key);
				urlBufferImg.append("&" + "MobileOS=" + os);
				urlBufferImg.append("&" + "MobileApp=" + app);
				urlBufferImg.append("&" + "contentId=" + camp_no);		
				
				URL urlImg = new URL(urlBufferImg.toString());
				HttpURLConnection connImg = (HttpURLConnection) urlImg.openConnection();
				connImg.setRequestMethod("GET");
				connImg.setRequestProperty("Accept", "application/xml");
				int codeImg = connImg.getResponseCode();
				System.out.println("statusCode : " + codeImg);		//<<<< for test
				if (codeImg < 200 || codeImg >= 300) {
					System.out.println("ERROR(check http status code)");
					return null;
				}
				
				DocumentBuilderFactory dbfImg = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbImg = dbfImg.newDocumentBuilder();
				Document docImg = dbImg.parse(connImg.getInputStream());
				docImg.getDocumentElement().normalize();
				
				NodeList nodeListImg = docImg.getElementsByTagName("item");
				
				for (int j = 0; j < addImgLimit; j++) {
					Node node = nodeListImg.item(j);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						
						sb.append(getStrDate(element, "imageUrl") + ",");
					}
				}
			} catch (Exception e) {
//				e.printStackTrace();
			}
			c.setCamp_img(removeEndComma(removeStartComma(sb.toString().strip())));
			
			list.add(c);		
			
			
		}

//		int testCount = 1;			//<<<< for test
//		for (Camp test : list) {
//			System.out.println(testCount++ + " : " + test.getCamp_name() + " / " + test.getCamp_img());
//			System.out.println(test.toString() + "\n");
//		}
		return list;
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
	
	
	private static int getIntData(Element element, String tagName) {
		try {
//			return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent().strip());
			return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
		} catch (Exception e) {
			return 0;
		}
	}

	private static String getStrDate(Element element, String tagName) {
		try {
//			return element.getElementsByTagName(tagName).item(0).getTextContent().strip();
			return element.getElementsByTagName(tagName).item(0).getTextContent();
		} catch (Exception e) {
			return " ";
		}
	}

	
	private static int setAreaCode(String addr) {
		if 		(addr.contains("서울")) { return 1; }
		else if (addr.contains("경기")) { return 31; }		//<< 경기 광주 / 광주광역시 구분 위해 선처리 
		else if (addr.contains("인천")) { return 2; }
		else if (addr.contains("대전")) { return 3; }
		else if (addr.contains("대구")) { return 4; }
		else if (addr.contains("광주")) { return 5; }
		else if (addr.contains("부산")) { return 6; }
		else if (addr.contains("울산")) { return 7; }
		else if (addr.contains("세종")) { return 8; }
		else if (addr.contains("강원")) { return 32; }
		else if (addr.contains("충청북도") || addr.contains("충북")) { return 33; }
		else if (addr.contains("충청남도") || addr.contains("충남")) { return 34; }
		else if (addr.contains("경상북도") || addr.contains("경북")) { return 35; }
		else if (addr.contains("경상남도") || addr.contains("경남")) { return 36; }
		else if (addr.contains("전라북도") || addr.contains("전북")) { return 37; }
		else if (addr.contains("전라남도") || addr.contains("전남")) { return 38; }
		else if (addr.contains("제주")) { return 39; }
		else return 0;		//<< unknown area code
	}
	
	
	
	public static void main(String[] args) {
		callCampByXML();
	}
	
}
