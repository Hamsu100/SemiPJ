<%@page import="com.kh.parse.common.CommonParse"%>
<%@page import="com.kh.arround.model.vo.Lei"%>
<%@page import="com.kh.arround.model.vo.Stay"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="com.kh.arround.model.vo.Camp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String bch_lat = request.getParameter("lat");
	String bch_lng = request.getParameter("lng");
	double mapLevel = 0;
	
	List<String> latList = new ArrayList<>();
	List<String> lngList = new ArrayList<>();
	List<String> nameList = new ArrayList<>();
	
	List<Lei> leiList = (List<Lei>) request.getAttribute("leiList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
%>    
    
    
<%@include file="/views/common/header.jsp" %>


<section class="d-flex align-items-center dark-overlay bg-cover" style="background-image: url(http://tong.visitkorea.or.kr/cms/resource/13/2024213_image2_1.jpg);">
    <div class="container py-6 py-lg-7 text-white overlay-content">
        <div class="row">
            <div class="col-xl-8">
                <h1 class="display-3 fw-bold text-shadow">주변 시설 목록 찾아보기</h1>
                <p class="text-lg text-shadow mb-6">새로운 도전을 해보는 건 어떨까요?</p>
            </div>
        </div>
    </div>
</section>

<div class="container position-relative mt-n6 z-index-20 col-lg-12 mb-3">
    <ul class="nav nav-tabs search-bar-nav-tabs" role="tablist">
  		<li class="nav-item me-2"><a class="nav-link" href="<%=path%>/arround/list/stay?lat=<%=bch_lat%>&lng=<%=bch_lng%>">숙박</a></li>
        <li class="nav-item me-2"><a class="nav-link" href="<%=path%>/arround/list/camp?lat=<%=bch_lat%>&lng=<%=bch_lng%>">캠핑장</a></li>
        <li class="nav-item me-2"><a class="nav-link active" href="<%=path%>/arround/list/lei?lat=<%=bch_lat%>&lng=<%=bch_lng%>">레저</a></li>
        <li class="nav-item me-2"><a class="nav-link" href="<%=path%>/arround/list/cafe?lat=<%=bch_lat%>&lng=<%=bch_lng%>">카페/바</a></li>
        <li class="nav-item me-2"><a class="nav-link" href="<%=path%>/arround/list/res?lat=<%=bch_lat%>&lng=<%=bch_lng%>">음식점</a></li>
        <li class="nav-item"><a class="nav-link" href="<%=path%>/arround/list/park?lat=<%=bch_lat%>&lng=<%=bch_lng%>">주차장</a></li>
    </ul>
    <div class="search-bar search-bar-with-tabs p-3 p-lg-4 ">
        <div class="tab-content">
            <div class="tab-pane fade show active" id="accommodation" role="tabpanel">
                <form action="#">
                    <div class="row">
                        <div class="col-lg-12 py-5 p-xl-5">
                            <div class="row">
                                <div class="row col-lg-6">
                                
                                	<!-- for img null -->
                                   	<% 
                                   	int imgNullCount = 0;
                                   	List<String> imgNull = new ArrayList<>();
                        			imgNull.add("http://tong.visitkorea.or.kr/cms/resource/45/2791245_image2_1.jpg");  
                                    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/55/2745155_image2_1.jpeg"); 
                                    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/92/2607292_image2_1.bmp");  
                                    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/93/2607293_image2_1.bmp");  
                                    imgNull.add("http://tong.visitkorea.or.kr/cms/resource/68/2745168_image2_1.jpg");  
                                    Collections.shuffle(imgNull);
                                   	%>
                                   	
                                	<!-- for content null -->
                                   	<% 
                                   	int contentNullCount = 0; 
                                   	List<String> contentNull = new ArrayList<>();
                                	contentNull.add("당신의 소중한 시간을 응원합니다");
                                	contentNull.add("산뜻한 쉼의 시간을 준비하세요");
                                	contentNull.add("또 하나의 즐거운 추억을 쌓아보세요");
                                	Collections.shuffle(contentNull);
                                   	%>
                                   	
                                   	<% if (leiList.isEmpty()) { %>
			                       	<div class="text-md-center"><br><br><br><br>가까운 거리에 레저업체가 없습니다</div>
			                        <% } %>
                                   	
                                    <!-- venue item-->
                                	<% for (Lei leiAll : leiList) { %>
                                
                                    <div class="col-sm-6 mb-4 hover-animate">
                                        <div class="card h-auto border">
                                        
                                        	<!-- img -->
                                        	<% if (leiAll.getLeisure_img() == null || !leiAll.getLeisure_img().contains("http")) { %>
                                            	<div class="card-img-top overflow-hidden dark-overlay bg-cover" style="background-image: url(<%= imgNull.get(imgNullCount++) %>); min-height: 250px;">
                                            	<% 
                                            	if (imgNullCount == imgNull.size()) {
                                            		imgNullCount = 0;
                                            	} 
                                            	%>
                                        	<% } else { %>
                                        		<%
                                        		CommonParse parse = new CommonParse();
                                        		List<String> list = parse.textLineToList(leiAll.getLeisure_img());
                                        		%>
                                           		<div class="card-img-top overflow-hidden dark-overlay bg-cover" style="background-image: url(<%= list.get(0) %>); min-height: 250px;">
                                            <% } %>
                                                
                                                <!-- detail link with name -->
                                                <a class="tile-link" href="<%=path%>/arround/view?type=3&lat=<%= leiAll.getLeisure_latitude() %>&lng=<%= leiAll.getLeisure_longitude() %>&no=<%= leiAll.getLeisure_no() %>"></a>
                                                <div class="card-img-overlay-bottom z-index-20">
                                                    <h4 class="text-white text-shadow"><%= leiAll.getLeisure_name() %></h4>
                                                    <% nameList.add(leiAll.getLeisure_name()); %>
                                                </div>
                                                <div class="card-img-overlay-top d-flex justify-content-between align-items-center">
                                                    <a class="position-relative z-index-40" href="javascript: void();"></a>
                                                </div>
                                            </div>
                                            
                                            <div class="card-body">
                                            	<!-- distance -->
                                                <p class="text-sm text-muted mb-3 text-dark"><%= String.format("%.2f", leiAll.getLeisure_distance()) %> km</p>
                                                <% mapLevel = leiAll.getLeisure_distance(); %>
                                                
                                                <!-- content with substring -->
                                                <% if (leiAll.getLeisure_content() == null || leiAll.getLeisure_content().isEmpty()) { %>
                                                	<p class="text-sm text-muted mb-3"><br><%= contentNull.get(contentNullCount++) %><br>소개글을 준비중입니다<br><br></p>
                                                	<%
                                                	if (contentNullCount == contentNull.size()) {
                                                    	contentNullCount = 0;
                                                    }		
                                                	%>
                                                <% } else { %>
                                                	<% int lengthCut = 75; %>
                                                	<% if (leiAll.getLeisure_content().length() - 1 > lengthCut) { %>
                                               			<p class="text-sm text-muted mb-3"><%= leiAll.getLeisure_content().substring(0, lengthCut)+" ..." %></p>
                                                	<% } else { %>
                                               			<p class="text-sm text-muted mb-3"><%= leiAll.getLeisure_content() %></p>
                                                	<% } %>
                                                <% } %>
                                                
                                                <!-- tel -->
                                                <% if (leiAll.getLeisure_tel() == null || leiAll.getLeisure_tel().isEmpty()) { %>
                                                	<br>
                                                <% } else { %>
                                              		<p class="text-sm text-muted text-uppercase mb-1 text-dark"><%= leiAll.getLeisure_tel() %></p>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <!-- for js client -->
                                    <%
                                    latList.add(leiAll.getLeisure_latitude());
                                    lngList.add(leiAll.getLeisure_longitude());
                                    %>
                                    
                                   <% } %> 
                                    
                                    
                                    <div class="mt-3">
                                        <!-- Pagination -->
                                        <nav aria-label=" Page navigation example">
                                            <ul class="pagination pagination-template d-flex justify-content-center">
                                            
                                                <li class="page-item">
                                                    <a class="page-link" href="<%=path%>/arround/list/lei?lat=<%=bch_lat%>&lng=<%=bch_lng%>&page=<%=pageInfo.getPrvePage()%>"> <i class="fa fa-angle-left"></i></a>
                                                </li>
                                                
                                                <% for (int i = pageInfo.getStartPage(); i <= pageInfo.getEndPage(); i++) { %>
                                                	<% if (i == pageInfo.getCurrentPage()) { %>
                                           		    	<li class="page-item active"><a class="page-link"><%=i%></a></li>
                                                	<% } else { %>
                                               			<li class="page-item"><a class="page-link" href="<%=path%>/arround/list/lei?lat=<%=bch_lat%>&lng=<%=bch_lng%>&page=<%=i%>"><%=i%></a></li>
                                                	<% } %>
                                                <% } %>	
                                                
                                                <li class="page-item">
                                                    <a class="page-link" href="<%=path%>/arround/list/lei?lat=<%=bch_lat%>&lng=<%=bch_lng%>&page=<%=pageInfo.getNextPage()%>"> <i class="fa fa-angle-right"></i></a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                    
                                    
                                </div>
                                
                                <div class="col-lg-6-1">
                                    <div class="shadow-left" id="map" style="height:950px"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


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

<!-- JavaScript files-->
<script>
<<!-- Map-->
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js" integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og==" crossorigin=""></script>
<!-- Available tile layers-->
<script src="resources/js/map-layers.js">
</script>
<!-- 지도 스크립트 -->
<script>
    var container = document.getElementById('map');
    var options = {
        // 지도의 센터
        center: new kakao.maps.LatLng(<%= bch_lat %>, <%=bch_lng %>),
        <% if (mapLevel < 1) { %>
        level: 5
        <% } else if (mapLevel < 2.5) { %>
        level: 6
        <% } else if (mapLevel < 5.5) { %>
        level: 7
        <% } else if (mapLevel < 8) { %>
        level: 8
        <% } else { %>
        level: 9
        <% } %>
    };

    var map = new kakao.maps.Map(container, options); // 지도 생성

    // 마커를 표시할 위치와 title 객체 배열입니다 
    var positions = [
    <% int count = 0; %>
    <% for (int i = 0; i < nameList.size(); i++) { %>	
    	{
        title: '<%= nameList.get(i) %>',
        latlng: new kakao.maps.LatLng(<%= latList.get(count)%>, <%= lngList.get(count++)%>)
   		}, 
    <% } %>
    ];

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < positions.length; i++) {

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage // 마커 이미지 
        });
    }
</script>


<%@include file = "/views/common/footer.jsp" %>