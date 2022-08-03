<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="com.kh.beach.model.vo.Beach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>

<%
List<Beach> bchList= (List<Beach>) request.getAttribute("bchList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
double[] avgLatLng = (double[])request.getAttribute("avgLatLng");
String searchValue = request.getParameter("searchValue");
String locName = request.getParameter("locName");
String bchCnt = (String)request.getAttribute("bchCnt");
List<String> favorList = (List<String>)request.getAttribute("favorList");
%>
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
<!-- 검색내역 -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6 py-5 p-xl-5">
                <h1 class="fontEB mb-4">해수욕장 찾아보기</h1>
                <hr class="my-4">
                <div class="d-flex justify-content-between align-items-center flex-column flex-md-row mb-4">
                    <div class="me-3">
                        <p class="mb-3 mb-md-0"><strong><%=bchList.isEmpty()? "0":bchCnt %></strong> results found</p>
                    </div>

                </div>
                <div class="row">
                    <%
                    if(bchList != null && bchList.isEmpty()==false){
                    	%>
                    	<%
						for (Beach bch : bchList){
							
                    %>
                 
                
                    <!-- venue item-->
                    <div class="col-sm-6 mb-5 hover-animate" data-marker-id="59c0c8e3a31e62979bf147c9">
                        <div class="card h-100 border-0 shadow">
                            <div class="card-img-top overflow-hidden dark-overlay bg-cover" style="background-image: url(<%=bch.getBEACH_IMG().split(",")[0] %>); min-height: 200px;">
                                <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bch.getBEACH_CODE()%>"></a>
                                <div class="card-img-overlay-bottom z-index-20">
                                    <h4 class="text-white text-shadow "><%=bch.getBEACH_NAME() %></h4>
                                </div>
                                <div class="card-img-overlay-top d-flex justify-content-between align-items-center">
                                    <a class="card-fav-icon position-relative z-index-40" >
                                    <%
                                    if (loginUser != null){
                                    	boolean flag = false;
                                    	for (String favor : favorList) {
                                    		if(favor.equals(bch.getBEACH_CODE())){
                                        		flag = true;
                                    		}
                                    	}
                                    	if (flag == true){
                                   	%>
                                   	<img src="<%=path %>/resources/resources/images/hearton.png" alt="" style="width:25px">
                                    <%
                                    	} else {
                                    		%>
                                    		<img src="<%=path %>/resources/resources/images/heartoff.png" alt="" style="width:25px">
                                    		<%
                                    	}
                                    } else {
                                    %>    
                                        <img src="<%=path %>/resources/resources/images/heartoff.png" alt="" style="width:25px">
                                    <%
                                    }
                                    %>
                                    </a>
                                </div>
                            </div>
                            <div class="card-body">
                                <p class="text-sm text-muted mb-3" style=" overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;"> <%=bch.getBEACH_CONTENT() %></p>
                                <p class="text-sm mb-0"><%=bch.getBEACH_ADDRESS() %>
                                </p>
                            </div>
                        </div>
                    </div>
                    
                    <%
						}
                    } else {
                    %>
                   <div style="text-align:center;height:245px">
                    <br><br><br><br>
                    <h4>조회된 해수욕장이 없습니다.</h4>
                    </div>
                    <%
                    }
                    %>
                 </div>
                <!-- Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-template d-flex justify-content-center">
                        <li class="page-item">
                            <a class="page-link" href="<%=path %>/beach/list?page=<%=pageInfo.getPrvePage()%>&searchValue=<%=searchValue%>&locName=<%=locName%>"> <i class="fa fa-angle-left"></i></a>
                        </li>
                        
                        <%
                        for (int i = pageInfo.getStartPage() ; i <= pageInfo.getEndPage();i++) { 
                        %>
                        <%
                        	if (i == pageInfo.getCurrentPage()){
                        %>
                        <li class="page-item active"><a class="page-link" href="#"><%=i %></a></li>
                        <%
                        continue;
                        }
                        %>
                        <li class="page-item"><a class="page-link" href="<%=path %>/beach/list?page=<%=i%>&searchValue=<%=searchValue%>&locName=<%=locName%>"><%=i %></a></li>
                        <%
                        }
                        %>
                        <li class="page-item">
                            <a class="page-link" href="<%=path %>/beach/list?page=<%=pageInfo.getNextPage()%>&searchValue=<%=searchValue%>&locName=<%=locName%>"> <i class="fa fa-angle-right"></i></a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col-sm-6 mb-5">
                <div class="map-full shadow-left" id="map" style="height:100%" ></div>
            </div>
        </div>
    </div>
<!-- 지도 스크립트 -->
    <script>
        var container = document.getElementById('map');
        var options = {
            // 지도의 센터
            center: new kakao.maps.LatLng(<%=avgLatLng[0]==0?"33.450701":avgLatLng[0]%>, <%=avgLatLng[1]==0?"126.570667":avgLatLng[1]%>),
            level: 12
        };
        var map = new kakao.maps.Map(container, options); // 지도 생성

        // 마커를 표시할 위치와 title 객체 배열입니다 
        var positions = [];
        // 마커 이미지의 이미지 주소입니다
        <%
        if (bchList != null && !bchList.isEmpty()){
       	%>
       	<%
        	for (Beach bch : bchList){
        %>
        positions.push({
        	title : '<%=bch.getBEACH_NAME()%>',
        	latlng: new kakao.maps.LatLng(<%=bch.getBEACH_LATITUDE()%>,<%=bch.getBEACH_LONGITUDE()%>)
        });
        <%
	        }
        }
        %>
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

<%@include file="/views/common/footer.jsp"%>