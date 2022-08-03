package com.kh.common.parse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CommonParse {

	public List<Map<String, String>> parseXML(String url, String index, String[] respParam) {
		List<Map<String, String>> returnList = new ArrayList<>();
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream(),
					"utf-8");

			NodeList nList = doc.getElementsByTagName(index);

			for (int i = 0; i < nList.getLength(); i++) {
				Element el = (Element) nList.item(i);
				HashMap<String, String> tempMap = new HashMap<>();
				for (String param : respParam) {
					if (el.getElementsByTagName(param).item(0) != null) {
						tempMap.put(param, el.getElementsByTagName(param).item(0).getTextContent().strip());
					}
				}
				returnList.add(tempMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}

	public List<Map<String, String>> parsingCSV(String filePath) {
		List<Map<String, String>> tempList = new ArrayList<>();
		try (FileReader fr = new FileReader(filePath, Charset.forName("utf-8"));
				BufferedReader br = new BufferedReader(fr);) {
			String line = br.readLine();
			String[] header = line.split(",");
			while ((line = br.readLine()) != null) {
				Map<String, String> tempMap = new HashMap<>();
				String[] lineArr = line.split(",");
				for (int i = 0; i < header.length; i++) {
					tempMap.put(header[i], lineArr[i]);
				}
				tempList.add(tempMap);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempList;
	}
	
}
