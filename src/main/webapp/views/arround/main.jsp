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
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String bch_lat = request.getParameter("lat");
	String bch_lng = request.getParameter("lng");
	
	List<Camp> campTopList = (List<Camp>) request.getAttribute("campTopList");
	List<Stay> stayTopList = (List<Stay>) request.getAttribute("stayTopList");
	List<Lei> leiTopList = (List<Lei>) request.getAttribute("leiTopList");
	List<Cafe> cafeTopList = (List<Cafe>) request.getAttribute("cafeTopList");
	List<Res> resTopList = (List<Res>) request.getAttribute("resTopList");
	List<Park> parkTopList = (List<Park>) request.getAttribute("parkTopList");
%>
	

<%@include file="/views/common/header.jsp" %>

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
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%= list.get(0) %>">
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
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%= list.get(0) %>">
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
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%= list.get(0) %>">
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
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%= list.get(0) %>">
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
                                       	} 
                                       	%>
                                    <% } else { %>
                                    	<% 
                                    	CommonParse parse = new CommonParse();
                                    	List<String> list = parse.textLineToList(resMain.getRes_img());
                                    	%>
                                    	<img class="d-block p-2 mb-2 fadeimg3" src="<%= list.get(0) %>">
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


<%@include file = "/views/common/footer.jsp" %>

