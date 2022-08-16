<%@page import="com.kh.blog.model.vo.Blog"%>
<%@page import="com.kh.beach.model.vo.Beach"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="com.kh.board.model.service.BoardService"%>
<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<%
List<Board> list = (List<Board>)request.getAttribute("bList");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy.M.d");

List<Beach> pbList = (List<Beach>) request.getAttribute("pbList");
List<Blog> blogList = (List<Blog>) request.getAttribute("blogListTop");
%>

<script>

	function dateParsing(date){
		str = date.substring(date.indexOf(',')+1,date.length)+'.'+date.substring(0,date.indexOf('월'))+'.'+date.substring(date.indexOf('월')+2,date.indexOf(','));
        return str;
	}
	
	
    function indexajax(category){
    	$.ajax(
    			{type: 'get',
	            url: 'indexajax',
	            data : {category},
	            dataType: 'json',
	            success: (list) => {
	            	
	            	var str='<div class="tab-pane fade show active" id="free" role="tabpanel">'
                	    +	'<form action="#">'
                		+		'<div class="row">'
                	    +			'<div class=" d-flex align-items-center form-group no-divider">'
                		+			    '<ul>' ;
                	if(list!=''){
	                	$.each(list, (i, obj) => {
	                		str += 	'<li class="boardlist" style="width:470px;"><a class="text-black font3 mb-n1" '
		                		+	'href="<%=path%>/board/view?boardNo='+obj.board_no+'" style="display:inline-block;'
		                		+	'width:350px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">'
		                		+	obj.board_title
		                		+	'</a><span class="text-block" style="display:inline-block;float:right;padding-top:8px;margin-bottom:-20px;">'
		                		+	dateParsing(obj.board_mdf_date)
		                		+	'</span></li>';
	                	})
	                }else{
	                	str += 	'<li class="boardlist" style="width:470px;text-align:center;list-style:none;border:none;">'
	                	    +	'<p class="text-black font3"'
	                	    +	'style="margin-bottom:-10px;display:inline-block;width:300px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">'
	                	    +	'조회된 게시글이 없습니다.</p>'
	                	    +	'<span class="text-block font3" style="float:right;"></span></li>';
	                };
	                str +=		    '</ul>'
            	    +			'</div>'
            	    +		'</div>'
            		+    '</form>'
            	    +'</div>'
	                document.getElementById('ajax1').innerHTML +=str;
	                $('#ajax1').html(str);
	            },
	            error: (e) => {
	                $('#ajax1').html('DB Error');
	                console.log(e);
	            }}
    		);
    }
    </script>
    <!-- 히어로 -->
    <section class="hero-home2">
        <div class="swiper-container hero-slider">
            <div class="swiper-wrapper dark-overlay">
                <div class="swiper-slide" style="background-image:url(resources/resources/images/back1.jpg)"></div>
                <div class="swiper-slide" style="background-image:url(resources/resources/images/back2.jpg)"></div>
                <div class="swiper-slide" style="background-image:url(resources/resources/images/back3.jpg)"></div>
                <div class="swiper-slide" style="background-image:url(http://tong.visitkorea.or.kr/cms/resource/88/599788_image2_1.jpg)"></div>
            </div>
        </div>
        <div class="container py-6 py-md-6 text-white z-index-20">
            <div class="row">
                <div class="col-xl-10">
                    <div class="text-center text-lg-start">
                        <p class="subtitle letter-spacing-4 mb-2 text-white text-shadow">The best holiday experience</p>
                        <h1 class="display-3 fw-bold text-shadow">SURF THE WORLD</h1>
                    </div>
                    <div class="search-bar mt-5 p-3 p-lg-1 ps-lg-4">
                        <form action="<%=path %>/beach/list" method="get">
                            <div class="row">
                                <div class="col-lg-7 d-flex align-items-center form-group">
                                    <input class="form-control border-0 shadow-0" type="text" name="searchValue" placeholder="What are you searching for?">
                                </div>
                                <div class="col-lg-3 d-flex align-items-center form-group no-divider">
                                    <select class="selectpicker" title="location" name="locName" data-style="btn-form-control">
                                        <option value="전국" selected>전국</option>
                                        <option value="서해">서해</option>
                                        <option value="남해">남해</option>
                                        <option value="동해">동해</option>
                                        <option value="제주도">제주</option>
                                    </select>
                                </div>
                                <div class="col-lg-2 d-grid">
                                    <button class="btn btn-primary rounded-pill h-100" type="submit">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 히어로 끝 -->
    <!-- 인기 지역 섹션 -->
    <section class="py-4">
        <div class="container">
            <div class="row mb-3">
                <div class="col-md-8">
                    <p class="subtitle text-primary">Stay and eat like a local</p>
                    <h3>Our guides</h3>
                </div>
                <div class="col-md-4 d-lg-flex align-items-center justify-content-end"><a class="text-muted text-sm" href="<%=path%>/beach/pop">
               See all guides<i class="fas fa-angle-double-right ms-2"></i></a></div>
            </div>
            <div class="swiper-container guides-slider mx-n2 pt-3">
                <!-- Additional required wrapper-->
                <div class="swiper-wrapper pb-4">
                    <!-- Slides-->
                    <%
                    if (pbList != null && pbList.isEmpty() == false){
                    	for (int i = 0 ; i < 5 ; i++ ){
                  	%>
                    <div class="swiper-slide h-auto px-2">
                        <div class="card card-poster gradient-overlay hover-animate mb-4 mb-lg-0">
                            <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=pbList.get(i).getBEACH_CODE()%>"></a><img class="bg-image" src="<%=pbList.get(i).getBEACH_IMG() != null? pbList.get(i).getBEACH_IMG().split(",")[0]:"" %>" alt="Card image">
                            <div class="card-body-center overlay-content">
                                <h5 class="card-title text-shadow text-uppercase"><%=pbList.get(i).getBEACH_NAME() %></h5>
                            </div>
                        </div>
                    </div>
                    <%
                    	}
                    } else {
                    %>
                    	Error!
                    <%
                    }
                    %>
                    
                </div>
                <div class="swiper-pagination d-md-none"> </div>
            </div>
        </div>
    </section>
    <!-- 인기 지역 센션 끝 -->
    <section class="py-5 bg-gray-100">
        <div class="container">
            <div class="row">
            
                <div class="row mb-4 ">
                    <div class="col-md-8">
                        <p class="subtitle text-secondary">Recently stories</p>
                        <h2>From our travel blog</h2>
                    </div>
                </div>
                
                <div class="row">
	                <% if (blogList != null && blogList.isEmpty() == false) { %>
	                
	                	<% for (Blog bglist : blogList) { %>
		                    <!-- blog item-->
		                    <div class="col-lg-3 hover-animate">
		                        <div class="card shadow border-0 h-100">
		                       		<img class="img-fluid card-img-top" style="width: 100%; object-fit: cover; aspect-ratio: 5/4;" src="<%=path%>/resources/upload/blog/<%=bglist.getBlog_renameimg()%>" alt="" />
		                            
		                            <div class="card-body2 text-center">
		                          		<% String blog_content = "#" + bglist.getBlog_content().replaceAll(" ", "_"); %>
		                                <h6 class="my-2 text-dark multiLine1"><%= blog_content.replaceAll("###", "#").replaceAll("##", "#").replaceAll("_#", " #") %></h6>
		                                <p class="text-sm text-secondary card-subtitle mb-2"><i class="fa fa-map-marker text-secondary opacity-4 me-1"></i><strong><%= bglist.getArea_name() %></strong></p>
		                                <p class="my-2 text-muted text-sm multiLine3"><%= bglist.getBlog_subcontent() %></p>
		                                <p class="text-sm text-secondary text-uppercase mb-1">작성자 &nbsp;<span class="text-dark"><%= bglist.getUser_id().substring(0, bglist.getUser_id().indexOf("@")) %></span></p>
		                            </div>
		                        </div>
		                    </div>
		                    
	                	<% } %>
	                <% } else { %>
	                	Error!
	                <% } %>
                </div>
                <div class="py-4"></div>
              	<div style=" text-align: center;"><a class="btn btn-link ps-0 py-2" href="<%=path%>/blog/list/new">Read details<i class="fa fa-long-arrow-alt-right ms-2"></i></a></div>
            </div>
        </div>
    </section>
    <!--  자유게시판, 쇼핑, 안전교육 유튜브 섹션  -->
    <section class="py-4">
        <section class="py-4">
        <div class="container">
            <div class="row">
                <!-- 자유게시판 -->
                <div class="four">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="nav-item"><a class="nav-link active font3" href="#notice" data-bs-toggle="tab" role="tab" onclick="indexajax('1');">공지 사항</a></li>
                        <li class="nav-item"><a class="nav-link font3" href="#free" data-bs-toggle="tab" role="tab" onclick="indexajax('2');">자유게시판</a></li>
                        <li class="nav-item"><a class="nav-link font3" href="#location" data-bs-toggle="tab" role="tab" onclick="indexajax(3456);">지역별 게시판</a></li>
                    </ul>
                    <div class="tab-content" id="ajax1">
                        <div class="tab-pane fade show active" id="notice" role="tabpanel">
                            <form action="#">
                                <div class="row">
                                    <div class=" d-flex align-items-center form-group no-divider">
                                        <ul style="padding-left: 2rem;">
                                        <%
                                        if (list != null && list.isEmpty() == false){ 
                                        	for (Board b : list){
                                        %>
                                            <li class="boardlist" style="width:470px;"><a class="text-black font3 mb-n1" href="<%=path %>/board/view?boardNo=<%=b.getBoard_no() %>" style="display:inline-block;width:350px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis"><%=b.getBoard_title() %></a><span class="text-block" style="display:inline-block;float:right;padding-top:8px;margin-bottom:-20px;"><%=sdf.format(b.getBoard_mdf_date()) %></span></li>
                                        <%
                                        	}
                                        } else {
                                        %>
                                            <li class="boardlist" style="width:470px;text-align:center;list-style:none;border:none;"><p class="text-black font3" style="margin-bottom:-10px;display:inline-block;width:300px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">조회된 게시글이 없습니다.</p><span class="text-block font3" style="float:right;"></span></li>
                                        <%
                                        } 
                                        %>
                                      	</ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

				<div class="five-1 ms-3 row">
                    <!-- 안전교육 -->
                    <div class="col-lg-6 me-3">
                        <iframe width="350" height="200" src="https://www.youtube.com/embed/cPzfgOK6zn0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                                    </iframe>
                        <hr class="my-2">
                        <iframe width="350" height="200" src="https://www.youtube.com/embed/UL8C6TaLucI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                                    </iframe>
                    </div>
                    <!-- 날씨 -->
                    <div class="col-lg-5 ">
                        <iframe class="shadow" width="350" height="425" src="https://embed.windy.com/embed2.html?lat=35.818&lon=127.925&detailLat=37.514&detailLon=126.886&width=300&height=400&zoom=6&level=surface&overlay=wind&product=ecmwf&menu=&message=true&marker=&calendar=12&pressure=&type=map&location=coordinates&detail=&metricWind=default&metricTemp=default&radarRange=-1"
                            frameborder="0"></iframe>
                    </div>
                </div>                
                
            </div>
    </section>
    
    
    
    <!--  자유게시판, 쇼핑, 안전교육 유튜브 섹션 끝  -->
<%@include file="/views/common/footer.jsp" %>