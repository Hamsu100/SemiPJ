package com.kh.parse.arround.api;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.kh.parse.common.CommonParse;

public class APIParse {

	private CommonParse cp = new CommonParse();

	public final String serviceKey = "toRiCe7NxO/3pxQhW3HO9cq2zIJqZPVsJoOs1vD1x1NQKCluxyj14Hn5RapPx23Y57sjGOp64PtbOXKMtC1ezg==";
	//GFYaIRR7h%2B5VzBC0UOr0Dfa1JlCCgM%2Fe6P4cE7yER1%2Bi%2FOSIf8sUDicnuExyGImszns9Bo%2FqfcQR1eXlSiu6jA%3D%3D
	//orCD5outbUakEqSxWPAu4C3NHBeT2DtV5DHSQbUBCr4Wx74vx2MumvtD23yU8ImOKwK8KWIjpiG5ubsei4j9dA%3D%3D
	
	private String stayURL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/searchStay";
	private String overviewURL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon";
	private String imgURL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage";
	private String keywordURL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword";
	private String locURL = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList";

	private String[] stayResp = { "addr1", "contentid", "firstimage", "mapx", "mapy", "title", "areacode", "tel" };

	private String[] commonReq = { "MobileOS", "MobileApp", "ServiceKey", "numOfRows" };

	private String[] overviewReq = { "contentId", "addrinfoYN", "overviewYN" };
	private String[] overviewResp = { "contentid", "addr1", "overview" };

	private String[] imgReq = { "contentId", "imageYN", "subImageYN" };
	private String[] imgResp = { "originimgurl", "contentid" };

	private String[] keywordReq = { "keyword" };
	private String[] keywordResp = { "areacode", "addr1", "contentid", "firstimage" };

	private String[] locationReq = { "areaCode" };
	private String[] locationResp = { "contentid", "title", "mapy", "firstimage", "mapx", "addr1", "areacode","cat3","contenttypeid" };

	public String urlBuilder(int index, String param) {
		StringBuilder sb = new StringBuilder();
		try {

			switch (index) {
			case 1:
				sb.append(stayURL + "?");
				break;
			case 2:
				sb.append(overviewURL + "?");
				sb.append(overviewReq[0] + "=");
				sb.append(param);
				sb.append("&" + overviewReq[1] + "=");
				sb.append("Y");
				sb.append("&" + overviewReq[2] + "=");
				sb.append("Y" + "&");
				break;
			case 3:
				sb.append(imgURL + "?");
				sb.append(imgReq[0] + "=");
				sb.append(param + "&");
				sb.append(imgReq[1] + "=");
				sb.append("Y" + "&");
				sb.append(imgReq[2] + "=");
				sb.append("Y" + "&");
				break;
			case 4:
				sb.append(keywordURL + "?");
				sb.append(keywordReq[0] + "=");
				sb.append(URLEncoder.encode(param, "utf-8") + "&");
				break;
			case 5:
				sb.append(locURL + "?");
				sb.append(locationReq[0] + "=");
				sb.append(param + "&");
				break;
			}
			sb.append(commonReq[2] + "=");
			sb.append(URLEncoder.encode(serviceKey, "utf-8"));
			sb.append("&" + commonReq[0] + "=");
			sb.append("ETC");
			sb.append("&" + commonReq[1] + "=");
			sb.append("AppTest");
			sb.append("&" + commonReq[3] + "=");
			sb.append("5000");							//<<<<
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public List<Map<String, String>> getStayData() {
		return cp.parseXML(urlBuilder(1, ""), "item", stayResp);
	}

	public List<Map<String, String>> getOverview(String contentid) {
		return cp.parseXML(urlBuilder(2, contentid), "item", overviewResp);
	}

	public List<Map<String, String>> getImg(String contentid) {
		return cp.parseXML(urlBuilder(3, contentid), "item", imgResp);
	}

	public List<Map<String, String>> getKeyword(String keyword) {
		return cp.parseXML(urlBuilder(4, keyword), "item", keywordResp);
	}

	public List<Map<String, String>> getLoc(String areacode) {
		return cp.parseXML(urlBuilder(5, areacode), "item", locationResp);
	}
	
}
