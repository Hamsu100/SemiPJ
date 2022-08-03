package com.kh.realtime;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BeachIndex {
	public String beachIndex(String beachCode) {
		String serviceKey = "aBjuqY5dVG2CQPH/pvV5w==";

		String r = "-";

		try {
			String url = "http://www.khoa.go.kr/api/oceangrid/beach/search.do?ServiceKey="
					+ URLEncoder.encode(serviceKey, "utf-8") + "&BeachCode=" + beachCode + "&ResultType=xml";
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream());

			NodeList nList = doc.getElementsByTagName("data");
			for (int i = 0; i < nList.getLength(); i++) {
				Element el = (Element) nList.item(i);
				if (el.getElementsByTagName("day1_pm_status").item(0) != null) {
					r = el.getElementsByTagName("day1_pm_status").item(0).getTextContent();
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return r;
	}
}
