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

import com.kh.arround.model.vo.Cafe;
import com.kh.parse.common.DistanceFilter5;

public class CafeApiManager {
	
	public static String AREA_URL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList";
	public static String DETAIL_URL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon";
	public static String IMG_URL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage";
	
	//toRiCe7NxO/3pxQhW3HO9cq2zIJqZPVsJoOs1vD1x1NQKCluxyj14Hn5RapPx23Y57sjGOp64PtbOXKMtC1ezg==
	//GFYaIRR7h%2B5VzBC0UOr0Dfa1JlCCgM%2Fe6P4cE7yER1%2Bi%2FOSIf8sUDicnuExyGImszns9Bo%2FqfcQR1eXlSiu6jA%3D%3D
	//orCD5outbUakEqSxWPAu4C3NHBeT2DtV5DHSQbUBCr4Wx74vx2MumvtD23yU8ImOKwK8KWIjpiG5ubsei4j9dA%3D%3D
	public static String key = "GFYaIRR7h%2B5VzBC0UOr0Dfa1JlCCgM%2Fe6P4cE7yER1%2Bi%2FOSIf8sUDicnuExyGImszns9Bo%2FqfcQR1eXlSiu6jA%3D%3D";
	public static String os = "ETC";
	public static String app = "AppTest";
	
	public static String contentTypeId = "39";
	public static String cat1 = "A05";
	public static String cat2 = "A0502";
	public static String cat3 = "A05020900";
			
	public static String totalCount;
	
	public static int fcategory_no_set = 1;	
	
	public static int addImgLimit = 10;
	public static int start;	
	public static int end;	
	
	public static List<Cafe> callCafeByXML() {
		
		DistanceFilter5 df = new DistanceFilter5();
		
		// total count 
		try {
			StringBuffer urlBuffer = new StringBuffer(AREA_URL);
			urlBuffer.append("?" + "serviceKey=" + key);
			urlBuffer.append("&" + "MobileOS=" + os );
			urlBuffer.append("&" + "MobileApp=" + app );
			urlBuffer.append("&" + "contentTypeId=" + contentTypeId );
			urlBuffer.append("&" + "cat1=" + cat1 );
			urlBuffer.append("&" + "cat2=" + cat2 );
			urlBuffer.append("&" + "cat3=" + cat3 );
			urlBuffer.append("&" + "numOfRows=0" );
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
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(conn.getInputStream());
			doc.normalizeDocument();
			
			totalCount = doc.getElementsByTagName("totalCount").item(0).getTextContent();		//<<1
			System.out.println("totalCount : " + totalCount);		//<<<< for test
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		
		List<Cafe> list = new ArrayList<Cafe>();
		List<Cafe> listTemp = new ArrayList<Cafe>();
		
		// list info
		try {
			StringBuffer urlBuffer = new StringBuffer(AREA_URL);
			urlBuffer.append("?" + "serviceKey=" + key);
			urlBuffer.append("&" + "MobileOS=" + os );
			urlBuffer.append("&" + "MobileApp=" + app );
			urlBuffer.append("&" + "contentTypeId=" + contentTypeId );
			urlBuffer.append("&" + "cat1=" + cat1 );
			urlBuffer.append("&" + "cat2=" + cat2 );
			urlBuffer.append("&" + "cat3=" + cat3 );
			urlBuffer.append("&" + "numOfRows=" + totalCount);	
			urlBuffer.append("&" + "pageNo=1" );
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
					Cafe cafe = new Cafe();
					try {
						Element element = (Element) node;
						
						String camp_longitude 	= getStrDate(element, "mapx");
						String camp_latitude  	= getStrDate(element, "mapy");
						String camp_address   	= getStrDate(element, "addr1") + " " + getStrDate(element, "addr2");
						
						if (df.calDistance(Double.parseDouble(camp_latitude) , Double.parseDouble(camp_longitude))) {
							
							cafe.setCafe_no(getIntData(element, "contentid"));
							cafe.setCategory_no(fcategory_no_set);
							cafe.setCafe_name(getStrDate(element, "title"));
							cafe.setCafe_long(camp_longitude);
							cafe.setCafe_lat(camp_latitude);
							cafe.setCafe_img(getStrDate(element, "firstimage"));
							cafe.setCafe_addr(camp_address);
							cafe.setCafe_tel(getStrDate(element, "tel"));
							cafe.setArea_code(getIntData(element, "areacode"));
							
							listTemp.add(cafe);
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
		
		
		// img add & detail
		for (int i = start; i <= end; i++) {
			Cafe c = listTemp.get(i);				
			String cafe_no = c.getCafe_no() + "";
			
			
			// img add
			String cafe_img = c.getCafe_img(); 
			StringBuffer sb = new StringBuffer();
			
			if (cafe_img != null) {
				sb.append(cafe_img + ",");
			}
			try {
				StringBuffer urlBufferImg = new StringBuffer(IMG_URL);
				urlBufferImg.append("?" + "serviceKey=" + key);
				urlBufferImg.append("&" + "MobileOS=" + os);
				urlBufferImg.append("&" + "MobileApp=" + app);
				urlBufferImg.append("&" + "contentId=" + cafe_no);
				urlBufferImg.append("&" + "imageYN=" + "Y");
				urlBufferImg.append("&" + "subImageYN=" + "Y");
				
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
					Node nodeImg = nodeListImg.item(j);
					if (nodeImg.getNodeType() == Node.ELEMENT_NODE) {
						Element elementImg = (Element) nodeImg;
						
						sb.append(getStrDate(elementImg, "originimgurl") + ",");
					}
				}
			} catch (Exception e) {
//				e.printStackTrace();
			}
			c.setCafe_img(removeEndComma(removeStartComma(sb.toString().strip())));
			
			
			// detail
			try {
				StringBuffer urlBufferDetail = new StringBuffer(DETAIL_URL);
				urlBufferDetail.append("?" + "serviceKey=" + key);
				urlBufferDetail.append("&" + "MobileOS=" + os );
				urlBufferDetail.append("&" + "MobileApp=" + app );
				urlBufferDetail.append("&" + "contentId=" + cafe_no);
				urlBufferDetail.append("&" + "overviewYN=" + "Y");

				URL urlDetail = new URL(urlBufferDetail.toString());
				HttpURLConnection connDetail = (HttpURLConnection) urlDetail.openConnection();
				connDetail.setRequestMethod("GET");
				connDetail.setRequestProperty("Accept", "application/xml");
				int codeDetail = connDetail.getResponseCode();
				System.out.println("statusCode : " + codeDetail);		//<<<< for test
				if (codeDetail < 200 || codeDetail >= 300) {
					System.out.println("ERROR(check http status code)");
					return null;
				}
				
				DocumentBuilderFactory dbfDetail = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbDetail = dbfDetail.newDocumentBuilder();
				Document docDetail = dbDetail.parse(connDetail.getInputStream());
				docDetail.normalizeDocument();
				
				NodeList nodeListDetail = docDetail.getElementsByTagName("item");
				Node nodeDetail = nodeListDetail.item(0);
				if (nodeDetail.getNodeType() == Node.ELEMENT_NODE) {
					Element elementDetail = (Element) nodeDetail;
					
					c.setCafe_con(getStrDate(elementDetail, "overview"));		
				}
//				System.out.println("input overview : " + c.getCafe_con());		//<<<< for test
				
			} catch (Exception e) {
//				e.printStackTrace();
			}
			list.add(c);		
		}

//		int testCount = 1;			//<<<< for test
//		for (Cafe test : list) {
//			System.out.println(testCount++ + " : " + test.getCafe_name() + " / " + test.getCafe_img());
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
			return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent().strip());
//			return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
		} catch (Exception e) {
			return 0;
		}
	}

	private static String getStrDate(Element element, String tagName) {
		try {
			return element.getElementsByTagName(tagName).item(0).getTextContent().strip();
//			return element.getElementsByTagName(tagName).item(0).getTextContent();
		} catch (Exception e) {
			return "";
		}
	}
	
	
	public static void main(String[] args) {
		callCafeByXML();
	}

}
