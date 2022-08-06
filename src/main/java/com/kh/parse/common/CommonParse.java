package com.kh.parse.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CommonParse {

	public List<Map<String, String>> parseXML(String url, String nodeIndex, String[] respParam) {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream(),
					"utf-8");

			NodeList nList = doc.getElementsByTagName(nodeIndex);

			for (int i = 0; i < nList.getLength(); i++) {
				Element el = (Element) nList.item(i);
				HashMap<String, String> tempMap = new HashMap<>();
				for (String param : respParam) {
					if (el.getElementsByTagName(param).item(0) != null) {
//						tempMap.put(param, el.getElementsByTagName(param).item(0).getTextContent());
						tempMap.put(param, getStrDate(el, param));
					}
				}
				list.add(tempMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private static String getStrDate(Element element, String tagName) {
		try {
			return element.getElementsByTagName(tagName).item(0).getTextContent();
		} catch (Exception e) {
			return "";
		}
	}

	public List<Map<String, String>> parseCSV(String filePath) {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try (FileReader fr = new FileReader(filePath, Charset.forName("utf-8"));
				BufferedReader br = new BufferedReader(fr);) {
			String line = br.readLine();
			String[] header = line.split(",");

			while ((line = br.readLine()) != null) {
				Map<String, String> tempMap = new HashMap<>();
				String delQuat = quatation(line);
				String[] lineArr = delQuat.split(",");
				for (int i = 0; i < lineArr.length; i++) {
					tempMap.put(header[i], lineArr[i]);
				}
				list.add(tempMap);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String quatation(String line) {
		int start = line.indexOf("\"");
		int end = line.lastIndexOf("\"");
		if (start > 0) {
			String str1 = line.substring(start, end + 1);
			String str2 = str1.replace(",", " ").replace("\"", "").replace("  ", " ");
			line = line.replace(str1, str2);
		}
		return line;
	}

//	private static List<String> textLineToList(String textLine) {
	public List<String> textLineToList(String textLine) {
		try {
			String delemeter = ",";

//			if (csvLine.contains(",\"") == true) {		//<<2
//				csvLine = filter(csvLine);
//			}

			List<String> list = new ArrayList<String>();
			String[] array = textLine.split(delemeter);
			for (String str : array) {

//				str = str.replace("\"", "").strip().replace("_", ",");		//<<2
				if (str != null && str.length() > 1) {
					list.add(str);
				}
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // <<1
	}

//	private static String filter(String str) {		//<<2
//		StringBuffer sb = new StringBuffer(str);
//		StringBuffer newsb = new StringBuffer();
//		boolean in = false;
//		for (int i = 0; i < sb.length(); i++) {
//			char value = sb.charAt(i);
//			if(value=='\"') {
//				in = !in;
//			}
//			if(in == true) {
//				if(value == ',') {
//					value = '_';
//				}
//			}
//			newsb.append(value);
//		}
//		return newsb.toString();
//	}

}
