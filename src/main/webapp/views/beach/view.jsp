<%@page import="java.util.List"%>
<%@page import="com.kh.beach.model.vo.BchReply"%>
<%@page import="com.kh.beach.model.vo.Beach"%>
<%@page import="com.kh.parse.common.CommonParse"%>
<%@page import="com.kh.arround.model.vo.Park"%>
<%@page import="com.kh.arround.model.vo.Res"%>
<%@page import="com.kh.arround.model.vo.Cafe"%>
<%@page import="com.kh.arround.model.vo.Lei"%>
<%@page import="com.kh.arround.model.vo.Stay"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.arround.model.vo.Camp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>
<%
String weather = (String) request.getAttribute("weather");
Beach b = (Beach) request.getAttribute("beach");
String bi = (String) request.getAttribute("bi");
List<String> favor = (List<String>) request.getAttribute("favor");

String bch_lat = (String)request.getAttribute("lat");
String bch_lng = (String)request.getAttribute("lng");

List<Camp> campTopList = (List<Camp>) request.getAttribute("campTopList");
List<Stay> stayTopList = (List<Stay>) request.getAttribute("stayTopList");
List<Lei> leiTopList = (List<Lei>) request.getAttribute("leiTopList");
List<Cafe> cafeTopList = (List<Cafe>) request.getAttribute("cafeTopList");
List<Res> resTopList = (List<Res>) request.getAttribute("resTopList");
List<Park> parkTopList = (List<Park>) request.getAttribute("parkTopList");
%>
<style>
.fadeimg1 {
    width: 100%;
    object-fit: cover;
    aspect-ratio: 16/8;
}

.fadeimg2 {
    width: 100%;
    object-fit: cover;
    aspect-ratio: 16/10;
    width: 400px;
}

.fadeimg3 {
    width: 100%;
    object-fit: cover;
    aspect-ratio: 16/11;
    width: 250px;
}
</style>
<!-- 해수욕장 이름과 한줄 설명 띄우는 라인-->
    <section class="py-4">
        <div class="container mt-5 mb-5">
            <h1 class="nameOfBeach"><%=b.getBEACH_NAME() %></h1>
            <h5 class="simpleExplanation"><%=b.getBEACH_CONTENT().substring(0, b.getBEACH_CONTENT().indexOf("다.")+1) %></h5>
        </div>
    </section>
    <!-- 사진 하나, 날씨 해수욕지수 파싱된 것-->
    <section class="py-4 bg-gray-100">
        <div class="container">
            <div class="row">
                <div class="six">
                    <div class="row mb-3 ">
                        <img src="<%=b.getBEACH_IMG().split(",")[0] %>" alt="parsedPicture" height="500px" width="auto" />
                    </div>
                </div>
                <!-- 날씨 https://weatherwidget.org-->
                <div class="two ms-5">
                    <div class="row">
                        <div class="col-md-12 mb-2">
                            <h4>날씨</h4>
                        </div>
                    </div>
    					<!-- 날씨 들어가는 칸 -->
    					<%
    					switch (Integer.parseInt(b.getAREA_CODE())){
    					case 1:
    						%>
    						<div id="ww_925b2a4bfe9d6" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl4479"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather Data Source: <a href="https://wetterlabs.de/wetter_seoul/woche/" id="ww_925b2a4bfe9d6_u" target="_blank">wetterlabs.de/wetter_seoul/woche/</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_925b2a4bfe9d6"></script>
    						<%
    						break;
    					case 2:
    						%>
    						<div id="ww_cb1b0b619a249" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl2311"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather Data Source: <a href="https://wetterlabs.de/wetter_incheon/3_tage/" id="ww_cb1b0b619a249_u" target="_blank">wetter für Incheon 3 tage</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_cb1b0b619a249"></script>
    						<%
    						break;
    					case 3:
    						%>
    						<div id="ww_6d3eeded6ba24" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl2308"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/daejeon/map/" id="ww_6d3eeded6ba24_u" target="_blank">Daejeon map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_6d3eeded6ba24"></script>
    						<%
    						break;
    					case 4:
    						%>
    						<div id="ww_28a40081ca724" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl2309"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather Data Source: <a href="https://wetterlabs.de/wetter_daegu/30_tage/" id="ww_28a40081ca724_u" target="_blank">wetter Daegu 30 tage</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_28a40081ca724"></script>
    						<%
    						break;
    					case 5:
    						%>
    						<div id="ww_6eed4ff1645ca" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl3230"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather Data Source: <a href="https://wetterlabs.de/wetter_gwangju/woche/" id="ww_6eed4ff1645ca_u" target="_blank">wetterlabs.de/wetter_gwangju/woche/</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_6eed4ff1645ca"></script>
    						<%
    						break;
    					case 6:
    						%>
    						<div id="ww_ffca0bda2adf0" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl1419"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one","font":"Arial"}'>Weather Data Source: <a href="https://wetterlabs.de/wetter_busan/woche/" id="ww_ffca0bda2adf0_u" target="_blank">wetter Busan woche</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_ffca0bda2adf0"></script>
    						<%
    						break;
    					case 7:
    						%>
    						<div id="ww_e2070f4e95409" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl3229"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather Data Source: <a href="https://wetterlabs.de/wetter_ulsan/30_tage/" id="ww_e2070f4e95409_u" target="_blank">wetterlabs.de</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_e2070f4e95409"></script>
    						<%
    						break;
    					case 8:
    						%>
    						<div id="ww_39028c1d0ff75" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7416"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/sejong/map/" id="ww_39028c1d0ff75_u" target="_blank">Sejong map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_39028c1d0ff75"></script>
    						<%
    						break;
    					case 31:
    						%>
    						<div id="ww_da12772fdbda1" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7471"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/gyeonggi/map/" id="ww_da12772fdbda1_u" target="_blank">Gyeonggi map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_da12772fdbda1"></script>
    						<%
    						break;
    					case 32:
    						%>
    						<div id="ww_18f057cbc9307" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7478"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/gangwon/map/" id="ww_18f057cbc9307_u" target="_blank">Gangwon map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_18f057cbc9307"></script>
    						<%
    						break;
    					case 33:
    						%>
    						<div id="ww_b9dc184b7d83b" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7473"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/north_chungcheong/map/" id="ww_b9dc184b7d83b_u" target="_blank">North Chungcheong map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_b9dc184b7d83b"></script>
    						<%
    						break;
    					case 34:
    						%>
    						<div id="ww_794cfb37c5b7a" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7474"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/south_chungcheong/map/" id="ww_794cfb37c5b7a_u" target="_blank">South Chungcheong map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_794cfb37c5b7a"></script>
    						<%
    						break;
    					case 35:
    						%>
    						<div id="ww_467c843e4f207" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7476"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/north_gyeongsang/map/" id="ww_467c843e4f207_u" target="_blank">North Gyeongsang map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_467c843e4f207"></script>
    						<%
    						break;
    					case 36:
    						%>
    						<div id="ww_bed685bd76959" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7472"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/south_gyeongsang/map/" id="ww_bed685bd76959_u" target="_blank">South Gyeongsang map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_bed685bd76959"></script>
    						<%
    						break;
    					case 37:
    						%>
    						<div id="ww_635178810ab8b" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7475"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/north_jeolla/map/" id="ww_635178810ab8b_u" target="_blank">North Jeolla map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_635178810ab8b"></script>
    						<%
    						break;
    					case 38:
    						%>
    						<div id="ww_9f110805664e6" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7479"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/south_jeolla/map/" id="ww_9f110805664e6_u" target="_blank">South Jeolla map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_9f110805664e6"></script>
    						<%
    						break;
    					case 39:
    						%>
    						<div id="ww_c75cb96f702fb" v='1.20' loc='id' a='{"t":"horizontal","lang":"en","ids":["wl7477"],"cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_sot":"celsius","sl_ics":"one_a","font":"Arial"}'>Weather for the Following Location: <a href="https://2ua.org/kor/jeju_province/map/" id="ww_c75cb96f702fb_u" target="_blank">Jeju Province map, South Korea</a></div><script async src="https://srv1.weatherwidget.org/js/?id=ww_c75cb96f702fb"></script>
    						<%
    						break;
    					default:
    						break;
    					}
    					%>
                    <div class="row">
                        <div class="col-md-12 mt-5">
                            <h4>해수욕 지수</h4>
                        </div>
                        <div>
                            <p> <%=bi %></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 mt-2">
                            <h4>즐겨찾기</h4>

                            <div class="px-4">
                                	<i class="fa fa-heart me-1 text-secondary"></i> <%=b.getBEACH_FAVOR_CNT() %> &nbsp; &nbsp; &nbsp;&nbsp;
                                <%
                                boolean flag = false;
                                	if (favor!= null && favor.isEmpty() == false){
                                		for (String f : favor){
                                			if (f.equals(b.getBEACH_CODE())){
                                				flag = true;
                                				break;
                                			}
                                		}
                                	}
                                if (loginUser != null) {
                                	if (flag == true) {
                                %>
                                <button class="btn btn-primary" onclick="location.href='<%=path%>/beach/delfavor?beachCode=<%=b.getBEACH_CODE()%>'">즐겨찾기</button>
                                <%
                                	} else {
                                %> 
                                <button class="btn btn-outline-primary" onclick="location.href='<%=path%>/beach/addfavor?beachCode=<%=b.getBEACH_CODE()%>'">즐겨찾기</button>
								<%
									}
                                }
                                %>


                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<!-- 상세정보 -->
    <section class="py-4 mt-5">
        <div class="container">
            <h4>상세정보</h4>
            <p><%=b.getBEACH_ADDRESS() %></p>
            <p><%=b.getBEACH_CONTENT() %></p>
            <hr class="my-4">
        </div>
    </section>

    <!-- 아코디언 스타일 주변정보 -->
<!-- button 누르면 상세보기 결과로 이동-->
<section class="py-0">
    <div class="container">
        <div id="accordionFlushExample">
            <h3 class="text-primary mb-4">주변 시설 정보 모아보기</h3>
            <div>
            
                               
                <!-- 숙박 -->
                <!-- for img null -->
               	<% 
               	int stayImgNullCount = 0;
               	List<String> stayImgNull = new ArrayList<>();
          		stayImgNull.add("http://tong.visitkorea.or.kr/cms/resource/64/1981364_image2_1.jpg");
                stayImgNull.add("http://tong.visitkorea.or.kr/cms/resource/78/1609978_image2_1.jpg");
                stayImgNull.add("http://tong.visitkorea.or.kr/cms/resource/47/2819547_image2_1.jpg");
                stayImgNull.add("http://tong.visitkorea.or.kr/cms/resource/11/1964211_image2_1.jpg");
                stayImgNull.add("http://tong.visitkorea.or.kr/cms/resource/63/2568463_image2_1.jpg");
                Collections.shuffle(stayImgNull);
               	%>
               	
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                        숙박
                        </button>
                    </h2>
                    <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                        
                        <% if (stayTopList == null || stayTopList.isEmpty()) { %>
							<div class="text-md-center me-4 me-xl-5">
								가까운 거리에 숙박시설이 없습니다
							</div>
						<% } else { %>	
						
                        	<% for (Stay stayMain : stayTopList) { %>
                        
                            <div class="d-flex d-block d-sm-flex">
                                <div class="text-md-left me-4 me-xl-5">
                                	<% if (stayMain.getImg() == null) { %>
                                   		<img class="d-block p-2 mb-2 fadeimg3" src="<%= stayImgNull.get(stayImgNullCount++) %>">
                                    	<% 
                                       	if (stayImgNullCount == stayImgNull.size()) {
                                       		stayImgNullCount = 0;
                                       	} 
                                       	%>
                                    <% } else { %>
                                    	<% 
                                    	CommonParse parse = new CommonParse();
                                    	List<String> list = parse.textLineToList(stayMain.getImg());
                                    	%>
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%=list != null && list.isEmpty() == false ? list.get(0) :""%>">
	                                <% } %>
                                    	
                                </div>
                                <div class="text-md-left col-6">
                                    <h4 class="mt-3 mb-3"><%= stayMain.getName() %></h4>
                                    
                                    <% if (stayMain.getTel() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= stayMain.getTel() %></p>
                                    <% } %>

                                    <% if (stayMain.getAddr() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= stayMain.getAddr() %></p>
                                    <% } %>
                                </div>
                                <div class="text-md-left me-2 me-xl-5">
                                	 <h4 class="mt-3 mb-2"><%= String.format("%.2f", stayMain.getDistance()) %> km</h4>
                                </div>
                            </div>
                            
                        	<% } %>
                        
                            <button class="btn btn-primary offset-11" onclick="location.href='<%=path%>/arround/list/stay?lat=<%=bch_lat%>&lng=<%=bch_lng%>'" type="submit"> 더보기</button>
                            
                        <% } %>    
                        </div>
                    </div>
                </div>
                
                
                <!-- 캠핑장 -->
                <!-- for img null -->
               	<% 
               	int campImgNullCount = 0;
               	List<String> campImgNull = new ArrayList<>();
          		campImgNull.add("https://gocamping.or.kr/upload/camp/860/thumb/thumb_720_2152i6qSAzmLzqqMypI7TZuh.jpg");
                campImgNull.add("https://www.gocamping.or.kr/upload/camp/2372/thumb/thumb_720_6981Qdx0j7nLXN5ERSxh5IzM.jpg");
                campImgNull.add("https://gocamping.or.kr/upload/camp/2372/thumb/thumb_1000_7956tsNSBAd7EupKZXZPszmA.jpg");
                campImgNull.add("https://www.gocamping.or.kr/upload/camp/100451/thumb/thumb_720_1833SDJbxcoJRsYKlaXRur1G.jpg");
                campImgNull.add("https://www.gocamping.or.kr/upload/camp/7410/thumb/thumb_720_87791D2liRmJTPp8SeCmArqo.jpg");
                Collections.shuffle(campImgNull);
               	%>
               	
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                        캠핑장
                        </button>
                    </h2>
                    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                        
                        <% if (campTopList == null || campTopList.isEmpty()) { %>
							<div class="text-md-center me-4 me-xl-5">
								가까운 거리에 캠핑장이 없습니다
							</div>
						<% } else { %>	
						
                        	<% for (Camp campMain : campTopList) { %>
                        
                            <div class="d-flex d-block d-sm-flex">
                                <div class="text-md-left me-4 me-xl-5">
                                	<% if (campMain.getCamp_img() == null) { %>
                                   		<img class="d-block p-2 mb-2 fadeimg3" src="<%= campImgNull.get(campImgNullCount++) %>">
                                    	<% 
                                       	if (campImgNullCount == campImgNull.size()) {
                                       		campImgNullCount = 0;
                                       	} 
                                       	%>
                                    <% } else { %>
                                    	<% 
                                    	CommonParse parse = new CommonParse();
                                    	List<String> list = parse.textLineToList(campMain.getCamp_img());
                                    	%>
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%=list != null && list.isEmpty() == false ? list.get(0) :""%>">
	                                <% } %>
                                    	
                                </div>
                                <div class="text-md-left col-6">
                                    <h4 class="mt-3 mb-3"><%= campMain.getCamp_name() %></h4>
                                    
                                    <% if (campMain.getCamp_tel() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= campMain.getCamp_tel() %></p>
                                    <% } %>

                                    <% if (campMain.getCamp_address() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= campMain.getCamp_address() %></p>
                                    <% } %>
                                </div>
                                <div class="text-md-left me-2 me-xl-5">
                                	 <h4 class="mt-3 mb-2"><%= String.format("%.2f", campMain.getCamp_distance()) %> km</h4>
                                </div>
                            </div>
                            
                        	<% } %>
                        
                            <button class="btn btn-primary offset-11" onclick="location.href='<%=path%>/arround/list/camp?lat=<%=bch_lat%>&lng=<%=bch_lng%>'" type="submit"> 더보기</button>
                            
                        <% } %>    
                        </div>
                    </div>
                </div>
                
                
                <!-- 레저 -->
                <!-- for img null -->
               	<% 
               	int leiImgNullCount = 0;
               	List<String> leiImgNull = new ArrayList<>();
          		leiImgNull.add("http://tong.visitkorea.or.kr/cms/resource/45/2791245_image2_1.jpg");
                leiImgNull.add("http://tong.visitkorea.or.kr/cms/resource/55/2745155_image2_1.jpeg");
                leiImgNull.add("http://tong.visitkorea.or.kr/cms/resource/92/2607292_image2_1.bmp");
                leiImgNull.add("http://tong.visitkorea.or.kr/cms/resource/93/2607293_image2_1.bmp");
                leiImgNull.add("http://tong.visitkorea.or.kr/cms/resource/68/2745168_image2_1.jpg");
                Collections.shuffle(leiImgNull);
               	%>
               	
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                        레저
                        </button>
                    </h2>
                    <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                        
                        <% if (leiTopList == null || leiTopList.isEmpty()) { %>
							<div class="text-md-center me-4 me-xl-5">
								가까운 거리에 카페/바가 없습니다
							</div>
						<% } else { %>	
						
                        	<% for (Lei leiMain : leiTopList) { %>
                        
                            <div class="d-flex d-block d-sm-flex">
                                <div class="text-md-left me-4 me-xl-5">
                                	<% if (leiMain.getLeisure_img() == null) { %>
                                   		<img class="d-block p-2 mb-2 fadeimg3" src="<%= leiImgNull.get(leiImgNullCount++) %>">
                                    	<% 
                                       	if (leiImgNullCount == leiImgNull.size()) {
                                       		leiImgNullCount = 0;
                                       	} 
                                       	%>
                                    <% } else { %>
                                    	<% 
                                    	CommonParse parse = new CommonParse();
                                    	List<String> list = parse.textLineToList(leiMain.getLeisure_img());
                                    	%>
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%=list != null && list.isEmpty() == false ? list.get(0) :""%>">
	                                <% } %>
                                    	
                                </div>
                                <div class="text-md-left col-6">
                                    <h4 class="mt-3 mb-3"><%= leiMain.getLeisure_name() %></h4>
                                    
                                    <% if (leiMain.getLeisure_tel() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= leiMain.getLeisure_tel() %></p>
                                    <% } %>

                                    <% if (leiMain.getLeisure_address() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= leiMain.getLeisure_address() %></p>
                                    <% } %>
                                </div>
                                <div class="text-md-left me-2 me-xl-5">
                                	 <h4 class="mt-3 mb-2"><%= String.format("%.2f", leiMain.getLeisure_distance()) %> km</h4>
                                </div>
                            </div>
                            
                        	<% } %>
                        
                            <button class="btn btn-primary offset-11" onclick="location.href='<%=path%>/arround/list/lei?lat=<%=bch_lat%>&lng=<%=bch_lng%>'" type="submit"> 더보기</button>
                            
                        <% } %>    
                        </div>
                    </div>
                </div>
                
                
                <!-- 카페/바 -->
                <!-- for img null -->
               	<% 
               	int cafeImgNullCount = 0;
               	List<String> cafeImgNull = new ArrayList<>();
          		cafeImgNull.add("http://tong.visitkorea.or.kr/cms/resource/85/2830885_image2_1.jpeg");
                cafeImgNull.add("http://tong.visitkorea.or.kr/cms/resource/94/2822894_image2_1.JPEG");
                cafeImgNull.add("http://tong.visitkorea.or.kr/cms/resource/95/2822895_image2_1.jpg");
                cafeImgNull.add("http://tong.visitkorea.or.kr/cms/resource/45/2791145_image2_1.jpg");
                cafeImgNull.add("http://tong.visitkorea.or.kr/cms/resource/48/2791148_image2_1.jpg");
                Collections.shuffle(cafeImgNull);
               	%>
               	
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingFour">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour">
                        카페/바
                        </button>
                    </h2>
                    <div id="flush-collapseFour" class="accordion-collapse collapse" aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                        
                        <% if (cafeTopList == null || cafeTopList.isEmpty()) { %>
							<div class="text-md-center me-4 me-xl-5">
								가까운 거리에 카페/바가 없습니다
							</div>
						<% } else { %>	
						
                        	<% for (Cafe cafeMain : cafeTopList) { %>
                        
                            <div class="d-flex d-block d-sm-flex">
                                <div class="text-md-left me-4 me-xl-5">
                                	<% if (cafeMain.getCafe_img() == null) { %>
                                   		<img class="d-block p-2 mb-2 fadeimg3" src="<%= cafeImgNull.get(cafeImgNullCount++) %>">
                                    	<% 
                                       	if (cafeImgNullCount == cafeImgNull.size()) {
                                       		cafeImgNullCount = 0;
                                       	} 
                                       	%>
                                    <% } else { %>
                                    	<% 
                                    	CommonParse parse = new CommonParse();
                                    	List<String> list = parse.textLineToList(cafeMain.getCafe_img());
                                    	%>
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%=list != null && list.isEmpty() == false ? list.get(0) :""%>">
	                                <% } %>
                                    	
                                </div>
                                <div class="text-md-left col-6">
                                    <h4 class="mt-3 mb-3"><%= cafeMain.getCafe_name() %></h4>
                                    
                                    <% if (cafeMain.getCafe_tel() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= cafeMain.getCafe_tel() %></p>
                                    <% } %>

                                    <% if (cafeMain.getCafe_addr() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= cafeMain.getCafe_addr() %></p>
                                    <% } %>
                                </div>
                                <div class="text-md-left me-2 me-xl-5">
                                	 <h4 class="mt-3 mb-2"><%= String.format("%.2f", cafeMain.getCafe_distance()) %> km</h4>
                                </div>
                            </div>
                            
                        	<% } %>
                        
                            <button class="btn btn-primary offset-11" onclick="location.href='<%=path%>/arround/list/cafe?lat=<%=bch_lat%>&lng=<%=bch_lng%>'" type="submit"> 더보기</button>
                            
                        <% } %>    
                        </div>
                    </div>
                </div>
                
                
                <!-- 음식점 -->
                <!-- for img null -->
               	<% 
               	int resImgNullCount = 0;
               	List<String> resImgNull = new ArrayList<>();
          		resImgNull.add("http://tong.visitkorea.or.kr/cms/resource/91/2675191_image2_1.jpg");
                resImgNull.add("http://tong.visitkorea.or.kr/cms/resource/98/2755598_image2_1.jpg");
                resImgNull.add("http://tong.visitkorea.or.kr/cms/resource/82/2785282_image2_1.jpg");
                resImgNull.add("http://tong.visitkorea.or.kr/cms/resource/29/218629_image2_1.jpg");
                resImgNull.add("http://tong.visitkorea.or.kr/cms/resource/75/2792975_image2_1.jpg");
                Collections.shuffle(resImgNull);
               	%>
               	
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingFive">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFive" aria-expanded="false" aria-controls="flush-collapseFive">
                        음식점
                        </button>
                    </h2>
                    <div id="flush-collapseFive" class="accordion-collapse collapse" aria-labelledby="flush-headingFive" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                        
                        <% if (resTopList == null || resTopList.isEmpty()) { %>
							<div class="text-md-center me-4 me-xl-5">
								가까운 거리에 음식점이 없습니다
							</div>
						<% } else { %>	
						
                        	<% for (Res resMain : resTopList) { %>
                        
                            <div class="d-flex d-block d-sm-flex">
                                <div class="text-md-left me-4 me-xl-5">
                                	<% if (resMain.getRes_img() == null) { %>
                                   		<img class="d-block p-2 mb-2 fadeimg3" src="<%= resImgNull.get(resImgNullCount++) %>">
                                    	<% 
                                       	if (resImgNullCount == resImgNull.size()) {
                                       		resImgNullCount = 0;
                                       		%>
                                       		<%
                                       	} 
                                       	%>
                                    <% } else { %>
                                    	<% 
                                    	CommonParse parse = new CommonParse();
                                    	List<String> list = parse.textLineToList(resMain.getRes_img());
                                    	%>
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%=list != null && list.isEmpty() == false ? list.get(0) :""%>">
	                                <% } %>
                                    	
                                </div>
                                <div class="text-md-left col-6">
                                    <h4 class="mt-3 mb-3"><%= resMain.getRes_name() %></h4>
                                    
                                    <% if (resMain.getRes_tel() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= resMain.getRes_tel() %></p>
                                    <% } %>

                                    <% if (resMain.getRes_addr() == null) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= resMain.getRes_addr() %></p>
                                    <% } %>
                                </div>
                                <div class="text-md-left me-2 me-xl-5">
                                	 <h4 class="mt-3 mb-2"><%= String.format("%.2f", resMain.getRes_distance()) %> km</h4>
                                </div>
                            </div>
                            
                        	<% } %>
                        
                            <button class="btn btn-primary offset-11" onclick="location.href='<%=path%>/arround/list/res?lat=<%=bch_lat%>&lng=<%=bch_lng%>'" type="submit"> 더보기</button>
                            
                        <% } %>    
                        </div>
                    </div>
                </div>
                
                
                <!-- 주차장 -->
                <!-- for img null -->
               	<% 
           
               	%>
               	
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingSix">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseSix" aria-expanded="false" aria-controls="flush-collapseSix">
                        주차장
                        </button>
                    </h2>
                    <div id="flush-collapseSix" class="accordion-collapse collapse" aria-labelledby="flush-headingSix" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                        
                        <% if (parkTopList == null || parkTopList.isEmpty()) { %>
							<div class="text-md-center me-4 me-xl-5">
								가까운 거리에 주차장이 없습니다
							</div>
						<% } else { %>	
						
                        	<% for (Park parkMain : parkTopList) { %>
                        
                            <div class="d-flex d-block d-sm-flex">
                                <div class="text-md-left me-4 me-xl-5">
                                	<img class="d-block  p-2 mb-2" height="150px" width="150px" src="<%=path%>/resources/resources/images/noparking.png">
                                </div>
                                <div class="text-md-left col-6">
                                    <h4 class="mt-3 mb-3"><%= parkMain.getPark_name() %></h4>
                                    
                                    <% if ((parkMain.getPark_new_addr() == null) && (parkMain.getPark_addr() == null)) {  %>
	                                    <p class="text-muted text-m"></p>
                                    <% } else if (parkMain.getPark_new_addr() == null) { %>
                                  		<p class="text-muted text-m"><%= parkMain.getPark_addr() %></p>
                                    <% } else { %>
	                                    <p class="text-muted text-m"><%= parkMain.getPark_new_addr() %></p>
                                    <% } %>

									<p class="text-muted text-m">운영시간 : <%= parkMain.getPark_wday_settime() %> ( 주말 : <%= parkMain.getPark_sat_settime() %> )</p>
                                    
                                </div>
                                <div class="text-md-left me-2 me-xl-5">
                                	 <h4 class="mt-3 mb-2"><%= String.format("%.2f", parkMain.getPark_distance()) %> km</h4>
                                </div>
                            </div>
                            
                        	<% } %>
                        
                            <button class="btn btn-primary offset-11" onclick="location.href='<%=path%>/arround/list/park?lat=<%=bch_lat%>&lng=<%=bch_lng%>'" type="submit"> 더보기</button>
                            
                        <% } %>    
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</section>
    <!-- 리뷰 -->
    <section class="py-4">
        <div class="container">
            <hr class="my-4">
            
            <%
            if (loginUser != null) {
            %>
            <div class="py-5">
                <button class="btn btn-outline-primary" type="button" data-bs-toggle="collapse" data-bs-target="#leaveReview" aria-expanded="false" aria-controls="leaveReview">리뷰를 남겨보세요</button>
                <div class="collapse mt-4" id="leaveReview">
                    <h5 class="mb-4">리뷰 작성하기</h5>
                    <form class="form" id="contact-form" method="post" action="<%=path%>/beach/revwrite">
                        <div class="col-sm-6">
                        </div>
                        <div class="mb-4">
                        <input type="hidden" name="writer" value="<%=loginUser!=null?loginUser.getUser_id():""%>">
                        <input type="hidden" name="beachCode" value="<%=b.getBEACH_CODE()%>">
                            <textarea class="form-control" rows="4" name="review" id="review" placeholder="여기에다 글을 써주세요" required="required" style="resize:none"></textarea>
                        </div>
                        <button class="btn btn-primary" type="submit">리뷰 올리기</button>
                    </form>
                </div>
            </div>
			<%
            } else {
			%>            
            <div class="py-5">
                <button class="btn btn-outline-primary" type="button" data-bs-toggle="collapse" data-bs-target="#leaveReview" aria-expanded="false" aria-controls="leaveReview">리뷰를 남겨보세요</button>
                <div class="collapse mt-4" id="leaveReview">
                    <h5 class="mb-4">리뷰 작성하기</h5>
                    <form class="form" id="contact-form" method="get" action="#">
                        <div class="col-sm-6">
                        </div>
                        <div class="mb-4">
                        <input type="hidden" name="writer" value="<%=loginUser!=null?loginUser.getUser_id():""%>">
                        <input type="hidden" name="beachCode" value="<%=b.getBEACH_CODE()%>">
                            <textarea class="form-control" rows="4" name="review" id="review" placeholder="로그인 후 이용해 주세요!" required="required" style="resize:none" readonly></textarea>
                        </div>
                    </form>
                </div>
            </div>
            <%
            }
            %>
            
            
            <%
            if (b.getBch_replyList() != null && b.getBch_replyList().isEmpty()==false) {
           	%>
            <p class="subtitle text-primary">How did others enjoy?</p>
            <h3>리뷰 보기</h3>
            
           	<%
	            for (BchReply br : b.getBch_replyList()) {
            %>
            <div class="d-flex d-block d-sm-flex review">
                <div>
                    <h6 class="mt-2 mb-1"><%=br.getWriter() %></h6>
                    <p class="text-muted text-sm"><%=br.getBCH_REVIEW_CONTENT() %></p>
                    
          		    <a class="text-primary me-2 boarda" style=" text-decoration: none"><%=br.getBCH_REVIEW_MDF_DATE() %></a>
          		    <%
          		    if (loginUser!=null && loginUser.getUser_id().equals(br.getWriter())){
          		    %>
                    <a href="#" data-bs-toggle="collapse" data-bs-target="#modifycomment<%=br.getBCH_REVIEW_NO() %>" aria-expanded="false" aria-controls="modifycomment" class=" me-2 boarda">수정</a>
                	<a class="boarda" href="<%=path%>/beach/revdel?replyNo=<%=br.getBCH_REVIEW_NO()%>&beachCode=<%=b.getBEACH_CODE()%>">삭제</a>
                	<%
          		    }
                	%>
                </div>
            </div>
            <div class="collapse mt-4" id="modifycomment<%=br.getBCH_REVIEW_NO() %>">
                <form class="form" id="contact-form" method="post" action="<%=path%>/beach/revupdate">
                <input type="hidden" name="rNo" value="<%=br.getBCH_REVIEW_NO() %>">
                <input type="hidden" name="writer" value="<%=br.getWriter() %>">
                <input type="hidden" name="beachCode" value="<%=b.getBEACH_CODE()%>">
                    <div class="mb-3">
                        <textarea class="form-control" rows="3" name="modifycomment" id="modifycomment" required="required" style="resize:none;"><%=br.getBCH_REVIEW_CONTENT() %></textarea>
                    </div>
                    <button class="btn btn-primary offset-11" type="submit">작성하기</button>
                </form>
            </div>
            
            <%
    	        }
            } 
            %>
            
       </div>
    </section>
    <!-- 리뷰 끝 -->






<%@include file="/views/common/footer.jsp"%>