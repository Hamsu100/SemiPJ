package com.kh.beach.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.MyHttpServlet;
import com.kh.realtime.BeachIndex;

@WebServlet("/beach/view")
public class BeachViewServlet extends MyHttpServlet{

	private static final long serialVersionUID = 1L;
	
	private BeachService bs = new BeachService();
	private BeachIndex bi = new BeachIndex();

	@Override
	public String getServletName() {
		return "BeachViewServlet";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String beachCode = req.getParameter("beachCode");
		System.out.println(beachCode);
		String beachIndex = bi.beachIndex(beachCode);
		
		
		Beach b = bs.getBeach(beachCode);
		String weather = getWeather().get(Integer.parseInt(b.getAREA_CODE()));
		
		req.setAttribute("beach", b);
		req.setAttribute("weather", weather);
		req.setAttribute("bi", beachIndex);
		req.getRequestDispatcher("/views/beach/view.jsp").forward(req, resp);
	}
	
	public Map<Integer, String> getWeather(){ // 웨 않뒈
		Map<Integer,String> weather = new HashMap<>();
		String gyeong = 	"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7471\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/gyeonggi/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">Gyeonggi map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String gang = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7478\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/gangwon/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">Gangwon map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String nC = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7473\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/north_chungcheong/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">North Chungcheong map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String sC = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7473\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/north_chungcheong/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">North Chungcheong map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String nG = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7476\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/north_gyeongsang/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">North Gyeongsang map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String sG = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7472\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/south_gyeongsang/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">South Gyeongsang map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String nJ = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7475\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/north_jeolla/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">North Jeolla map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String sJ = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7479\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/south_jeolla/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">South Jeolla map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String jeju = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7477\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/jeju_province/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">Jeju Province map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String seoul = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl4479\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather Data Source: <a href=\"https://wetterlabs.de/wetter_seoul/woche/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">wetter Seoul 7 tage</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String incheon = 	"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl2311\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather Data Source: <a href=\"https://wetterlabs.de/wetter_incheon/3_tage/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">wetter in Incheon 3 tage</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String daejeon=		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl2308\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/daejeon/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">2ua.org/kor/daejeon/map/</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String daegu =		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl2309\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather Data Source: <a href=\"https://wetterlabs.de/wetter_daegu/30_tage/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">30 tage wetter Daegu</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String gwangju = 	"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl3230\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather Data Source: <a href=\"https://wetterlabs.de/wetter_gwangju/woche/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">wetter Gwangju woche</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String busan = 		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl1419\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather Data Source: <a href=\"https://wetterlabs.de/wetter_busan/woche/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">7 tage wetter Busan</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String ulsan =		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl3229\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather Data Source: <a href=\"https://wetterlabs.de/wetter_ulsan/30_tage/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">Ulsan 30 tage wetter</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		String sejong =		"<div id=\"ww_18f057cbc9307\" v='1.20' loc='id' a='{\"t\":\"horizontal\",\"lang\":\"en\",\"ids\":[\"wl7416\"],\"cl_bkg\":\"image\",\"cl_font\":\"#FFFFFF\",\"cl_cloud\":\"#FFFFFF\",\"cl_persp\":\"#81D4FA\",\"cl_sun\":\"#FFC107\",\"cl_moon\":\"#FFC107\",\"cl_thund\":\"#FF5722\",\"sl_sot\":\"celsius\",\"sl_ics\":\"one\",\"font\":\"Arial\"}'>Weather for the Following Location: <a href=\"https://2ua.org/kor/sejong/map/\" id=\"ww_18f057cbc9307_u\" target=\"_blank\">Sejong map, South Korea</a></div><script async src=\"https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307\"></script>";
		weather.put(1, seoul);
		weather.put(2, incheon);
		weather.put(3, daejeon);
		weather.put(4, daegu);
		weather.put(5, gwangju);
		weather.put(6, busan);
		weather.put(7, ulsan);
		weather.put(8, sejong);
		weather.put(31, gyeong);
		weather.put(32, gang);
		weather.put(33, nC);
		weather.put(34, sC);
		weather.put(35, nG);
		weather.put(36, sG);
		weather.put(37, nJ);
		weather.put(38, sJ);
		weather.put(39, jeju);
		return weather;
	}
	
}
