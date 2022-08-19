<%@page import="com.kh.parse.common.CommonParse"%>
<%@page import="com.kh.arround.model.vo.Res"%>
<%@page import="com.kh.arround.model.vo.Cafe"%>
<%@page import="com.kh.arround.model.vo.Lei"%>
<%@page import="com.kh.arround.model.vo.Stay"%>
<%@page import="oracle.net.aso.l"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.arround.model.vo.Camp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%
	String lat = request.getParameter("lat");
	String lng = request.getParameter("lng");
	int no = Integer.parseInt(request.getParameter("no"));
	int type = Integer.parseInt(request.getParameter("type"));
	
	CommonParse parse = new CommonParse();
	
	String imgList = null;
	List<String> list = null;
	String name = null;
	String addr = null;
	String tel = null;
	String con = null;
%>
<%
	if (type == 1) {
		Stay detail = (Stay) request.getAttribute("stayDetail");
		
		imgList = detail.getImg();
		list = parse.textLineToList(imgList);
		name = detail.getName();
		addr = detail.getAddr();
		tel = detail.getTel();
		con = detail.getContent();
	}
%>	
<%
	if (type == 2) {
		Camp detail = (Camp) request.getAttribute("campDetail");
		
		imgList = detail.getCamp_img();
		list = parse.textLineToList(imgList);
		name = detail.getCamp_name();
		addr = detail.getCamp_address();
		tel = detail.getCamp_tel();
		con = detail.getCamp_content();
	}
%>	
<%
	if (type == 3) {
		Lei detail = (Lei) request.getAttribute("leiDetail");
		
		imgList = detail.getLeisure_img();
		list = parse.textLineToList(imgList);
		name = detail.getLeisure_name();
		addr = detail.getLeisure_address();
		tel = detail.getLeisure_tel();
		con = detail.getLeisure_content();
	}
%>	
<%
	if (type == 4) {
		Cafe detail = (Cafe) request.getAttribute("cafeDetail");
		
		imgList = detail.getCafe_img();
		list = parse.textLineToList(imgList);
		name = detail.getCafe_name();
		addr = detail.getCafe_addr();
		tel = detail.getCafe_tel();
		con = detail.getCafe_con();
	}
%>	
<%
	if (type == 5) {
		Res detail = (Res) request.getAttribute("resDetail");
		
		imgList = detail.getRes_img();
		list = parse.textLineToList(imgList);
		name = detail.getRes_name();
		addr = detail.getRes_addr();
		tel = detail.getRes_tel();
		con = detail.getRes_con();
	}
%>	
	

<!-- for img null -->
<% 
   	int imgNullCount = 0;
   	List<String> imgNull = new ArrayList<>();
		if (type == 1) {
			imgNull.add("http://tong.visitkorea.or.kr/cms/resource/64/1981364_image2_1.jpg");       
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/78/1609978_image2_1.jpg");       
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/47/2819547_image2_1.jpg");       
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/11/1964211_image2_1.jpg");       
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/63/2568463_image2_1.jpg");       
		}
		if (type == 2) {
			imgNull.add("https://gocamping.or.kr/upload/camp/860/thumb/thumb_720_2152i6qSAzmLzqqMypI7TZuh.jpg");         
		    imgNull.add("https://www.gocamping.or.kr/upload/camp/2372/thumb/thumb_720_6981Qdx0j7nLXN5ERSxh5IzM.jpg");    
		    imgNull.add("https://gocamping.or.kr/upload/camp/2372/thumb/thumb_1000_7956tsNSBAd7EupKZXZPszmA.jpg");       
		    imgNull.add("https://www.gocamping.or.kr/upload/camp/100451/thumb/thumb_720_1833SDJbxcoJRsYKlaXRur1G.jpg");  
		    imgNull.add("https://www.gocamping.or.kr/upload/camp/7410/thumb/thumb_720_87791D2liRmJTPp8SeCmArqo.jpg");    
		}
		if (type == 3) {
			imgNull.add("http://tong.visitkorea.or.kr/cms/resource/45/2791245_image2_1.jpg");             
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/55/2745155_image2_1.jpeg");            
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/92/2607292_image2_1.bmp");             
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/93/2607293_image2_1.bmp");             
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/68/2745168_image2_1.jpg");             
		}
		if (type == 4) {
			imgNull.add("http://tong.visitkorea.or.kr/cms/resource/85/2830885_image2_1.jpeg");       
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/94/2822894_image2_1.JPEG");       
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/95/2822895_image2_1.jpg");        
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/45/2791145_image2_1.jpg");        
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/48/2791148_image2_1.jpg");        
		}
		if (type == 5) {
			imgNull.add("http://tong.visitkorea.or.kr/cms/resource/91/2675191_image2_1.jpg"); 
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/98/2755598_image2_1.jpg"); 
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/82/2785282_image2_1.jpg"); 
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/29/218629_image2_1.jpg");  
		    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/75/2792975_image2_1.jpg"); 
		}
    Collections.shuffle(imgNull);
%>

<!-- for content null -->
<% 
  	int contentNullCount = 0; 
  	List<String> contentNull = new ArrayList<>();
	contentNull.add("\" 당신의 소중한 시간을 응원합니다 \"");
	contentNull.add("\" 산뜻한 쉼의 시간을 준비하세요 \"");
	contentNull.add("\" 또 하나의 즐거운 추억을 쌓아보세요 \"");
	Collections.shuffle(contentNull);
%>
	
	

<%@include file="/views/common/header.jsp" %>

<section class="py-4 mb-5">
    <div class="container col-lg-10">
        <!-- 사진, 이름 주소 -->
        <div class="row">
        
        	<!-- img -->
            <div class="four">
            <% if (imgList == null) { %>
                <img class="fadeimg2 ms-3" src="<%= imgNull.get(imgNullCount++) %>" alt="">
            	<% 
               	if (imgNullCount == imgNull.size()) {
               		imgNullCount = 0;
               	} 
               	%>
            <% } else { %>
                <img class="fadeimg2 ms-3" src="<%= list.get(0) %>" alt="">
            <% } %>
                
            </div>
            <div class="five ms-3 box">
           		<!-- name -->
                <h3 class="mb-4"><%= name %></h3>
                
                <!-- address -->
                <h6>
                	<% if (addr == null) { %>
                	<% } else { %>
	                    <%= addr %>
                	<% } %>
                	
                </h6>
                
                <!-- tel -->
                <h6>
                	<% if (tel == null) { %>
                	<% } else { %>
	                    <%= tel %>
                	<% } %>
                </h6>
            </div>
        </div>
        
        <!-- 설명 -->
        <div class="mt-3">
            <div class="ms-3 me-3 text-dark">
            	<!-- content -->
                <h5><br>
                <% if (con == null || con.isEmpty()) { %>
                	<p class="text-md-center me-4 me-xl-8"><br><%= contentNull.get(contentNullCount++) %><br><br>소개글을 준비중입니다</p>
                	<%
                	if (contentNullCount == contentNull.size()) {
                    	contentNullCount = 0;
                    }		
                	%>
              	<% } else { %>
                  	<p><br><%= con %></p>
             	<% } %>
                <br></h5>
            </div>
        </div>
        <hr class="my-4">
        <!-- 사진들 -->
        <!-- 최소 4 사진들 중간 정렬  -->
        <div class="swiper-container detail-slider slider-gallery">
            <!-- Additional required wrapper-->
            <div class="swiper-wrapper">
            
                <!-- Slides-->
                <% if (imgList == null) { %>
                	<% for (int i = 0; i < 5; i++) { %>
                		<div class="swiper-slide me-2">
                			<a href="<%= imgNull.get(imgNullCount) %>" data-toggle="gallery-top" title=""><img class="fadeimg1" src="<%= imgNull.get(imgNullCount) %>" alt=""></a>
                		</div>
						<% imgNullCount++; %>
            			<% 
             		  	if (imgNullCount == imgNull.size()) {
               			imgNullCount = 0;
             	  		} 
             	  		%>          	
                	<% } %>
                	
                <% } else { %>
                	<% if (list.size() < 4) { %>
                		<% for (int i = 0; i < list.size(); i++) { %>
                			<div class="swiper-slide me-2">
		                 		<a href="<%= list.get(i) %>" data-toggle="gallery-top" title=""><img class="fadeimg1" src="<%= list.get(i) %>" alt=""></a>
		              		</div>
                		<% } %>
                		<% for (int i = 0; i < (4 - list.size()); i++) { %>
                			<div class="swiper-slide me-2">
	                			<a href="<%= imgNull.get(i) %>" data-toggle="gallery-top" title=""><img class="fadeimg1" src="<%= imgNull.get(i) %>" alt=""></a>
	                		</div>
                		<% } %>
                		
                	<% } else { %>
                		<% for (int i = 0; i < list.size(); i++) { %>
                			<div class="swiper-slide me-2">
		                 		<a href="<%= list.get(i) %>" data-toggle="gallery-top" title=""><img class="fadeimg1" src="<%= list.get(i) %>" alt=""></a>
		              		</div>
                		<% } %> 
                	<% } %>
                <% } %>


            </div>
            <div class="swiper-pagination swiper-pagination-white"></div>
            <div class="swiper-button-prev swiper-button-white"></div>
            <div class="swiper-button-next swiper-button-white"></div>
        </div>
        <hr class="my-4">
        <!-- 지도 -->
        <div class="map-wrapper-450">
            <div class="h-100" id="map"></div>
        </div>
    </div>
</section>




<!-- 지도 스크립트 -->
<script>
    window.kakao = window.kakao || {}, window.kakao.maps = window.kakao.maps || {}, window.daum && window.daum.maps ? window.kakao.maps = window.daum.maps : (window.daum = window.daum || {}, window.daum.maps = window.kakao.maps),
        function() {
            function a() {
                if (E.length) {
                    t(I[E.shift()], a).start()
                } else e()
            }

            function t(a, t) {
                var e = document.createElement("script");
                return e.charset = "utf-8", e.onload = t, e.onreadystatechange = function() {
                    /loaded|complete/.test(this.readyState) && t()
                }, {
                    start: function() {
                        e.src = a || "",
                            document.getElementsByTagName("head")[0].appendChild(e), e = null
                    }
                }
            }

            function e() {
                for (; c[0];) c.shift()();
                o.readyState = 2
            }
            var o = kakao.maps = kakao.maps || {};
            if (void 0 === o.readyState) o.onloadcallbacks = [], o.readyState = 0;
            else if (2 === o.readyState) return;
            o.VERSION = {
                ROADMAP: "2205pfk",
                ROADMAP_SUFFIX: "",
                HYBRID: "2205pfk",
                SR: "3.00",
                ROADVIEW: "7.00",
                ROADVIEW_FLASH: "200402",
                BICYCLE: "6.00",
                USE_DISTRICT: "2205pfk",
                SKYVIEW_VERSION: "160114",
                SKYVIEW_HD_VERSION: "160107"
            }, o.RESOURCE_PATH = {
                ROADVIEW_AJAX: "//t1.daumcdn.net/roadviewjscore/core/css3d/200204/standard/1580795088957/roadview.js",
                ROADVIEW_CSS: "//t1.daumcdn.net/roadviewjscore/core/openapi/standard/211122/roadview.js"
            };
            for (var n, r = "https:" == location.protocol ? "https:" : "http:", s = "", i = document.getElementsByTagName("script"), d = i.length; n = i[--d];)
                if (/\/(beta-)?dapi\.kakao\.com\/v2\/maps\/sdk\.js\b/.test(n.src)) {
                    s = n.src;
                    break
                }
            i = null;
            var c = o.onloadcallbacks,
                E = ["v3"],
                S = "",
                I = {
                    v3: r + "//t1.daumcdn.net/mapjsapi/js/main/4.4.6-fixed2/kakao.js",
                    services: r + "//t1.daumcdn.net/mapjsapi/js/libs/services/1.0.2/services.js",
                    drawing: r + "//t1.daumcdn.net/mapjsapi/js/libs/drawing/1.2.6/drawing.js",
                    clusterer: r + "//t1.daumcdn.net/mapjsapi/js/libs/clusterer/1.0.9/clusterer.js"
                },
                _ = function(a) {
                    var t = {};
                    return a.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(a, e, o) {
                        t[e] = o
                    }), t
                }(s);
            S = _.appkey, S && (o.apikey = S), o.version = "4.4.6-fixed2";
            var R = _.libraries;
            if (R && (E = E.concat(R.split(","))), "false" !== _.autoload) {
                for (var d = 0, l = E.length; d < l; d++) ! function(a) {
                    a && document.write('<script charset="UTF-8" src="' + a + '"><\/script>')
                }(I[E[d]]);
                o.readyState = 2
            }
            o.load = function(t) {
                switch (c.push(t), o.readyState) {
                    case 0:
                        o.readyState = 1, a();
                        break;
                    case 2:
                        e()
                }
            }
        }();
</script>


<!-- Map-->
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js" integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og==" crossorigin=""></script>
<!-- Available tile layers-->
<script src="resources/js/map-layers.js">
</script>
<!-- 지도 스크립트 -->
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(<%= lat %>, <%= lng %>), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커가 표시될 위치입니다 
    var markerPosition = new kakao.maps.LatLng(<%= lat %>, <%= lng %>);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
</script>

<%@include file = "/views/common/footer2.jsp" %>
