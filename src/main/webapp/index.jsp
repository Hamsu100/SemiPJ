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
    <!-- 블로그 및 날씨 -->
    <section class="py-4 bg-gray-100">
        <div class="container">
            <div class="row">
                <div class="seven">
                    <div class="row mb-4 ">
                        <div class="col-md-8">
                            <p class="subtitle text-secondary ">Stories from around the globe</p>
                            <h2>From our travel blog</h2>
                        </div>
                        <div class="col-md-4 d-md-flex align-items-center justify-content-end"><a class="text-muted text-sm" href="otherblog.html">
                       See all articles<i class="fas fa-angle-double-right ms-2"></i></a></div>
                    </div>
                    <div class="row">
                        <!-- blog item-->
                        <div class="col-lg-3-1 col-sm-4 mb-4 ms-3 me-4 hover-animate">
                            <div class="card shadow border-0 h-100">
                                <a href="post.html"><img class="img-fluid card-img-top" src="resources/resources/images/image1.jpg" alt="..." /></a>
                                <div class="card-body2">
                                    <h5 class="my-2"><a class="text-dark" href="otherblog.html">How far I'll go</a></h5>
                                    <p class="text-sm text-secondary card-subtitle mb-2"><i class="fa fa-map-marker text-secondary opacity-4 me-1"></i>여행 로케이션</p>
                                    <p class="my-2 text-muted text-sm">See the line where the sky meets the sea? ...</p>
                                    <a class="btn btn-link ps-0" href="otherblog.html">Read more<i class="fa fa-long-arrow-alt-right ms-2"></i></a>
                                </div>
                            </div>
                        </div>
                        <!-- blog item-->
                        <div class="col-lg-3-1 col-sm-4 mb-4 me-4 hover-animate">
                            <div class="card shadow border-0 h-100">
                                <a href="post.html"><img class="img-fluid card-img-top" src="resources/resources/images/image2.jpg" alt="..." /></a>
                                <div class="card-body2">
                                    <h5 class="my-2"><a class="text-dark" href="otherblog.html">How far I'll go</a></h5>
                                    <p class="text-sm text-secondary card-subtitle mb-2"><i class="fa fa-map-marker text-secondary opacity-4 me-1"></i>여행 로케이션</p>
                                    <p class="my-2 text-muted text-sm">See the line where the sky meets the sea? ...</p>
                                    <a class="btn btn-link ps-0" href="otherblog.html">Read more<i class="fa fa-long-arrow-alt-right ms-2"></i></a>
                                </div>
                            </div>
                        </div>
                        <!-- blog item-->
                        <div class="col-lg-3-1 col-sm-4 mb-4 me-4 hover-animate">
                            <div class="card shadow border-0 h-100">
                                <a href="post.html"><img class="img-fluid card-img-top" src="resources/resources/images/image2.jpg" alt="..." /></a>
                                <div class="card-body2">
                                    <h5 class="my-2"><a class="text-dark" href="otherblog.html">How far I'll go</a></h5>
                                    <p class="text-sm text-secondary card-subtitle mb-2"><i class="fa fa-map-marker text-secondary opacity-4 me-1"></i>여행 로케이션</p>
                                    <p class="my-2 text-muted text-sm">See the line where the sky meets the sea? ...</p>
                                    <a class="btn btn-link ps-0" href="otherblog.html">Read more<i class="fa fa-long-arrow-alt-right ms-2"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 날씨 -->
                <div class="two ms-5">
                    <div>
                        <div class="col-md-12">
                            <p class="subtitle text-secondary">Before Travel</p>
                            <h4>Check Weather</h4>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-4 mb-4">
                        <iframe class="shadow" width="350" height="410" src="https://embed.windy.com/embed2.html?lat=35.818&lon=127.925&detailLat=37.514&detailLon=126.886&width=300&height=400&zoom=6&level=surface&overlay=wind&product=ecmwf&menu=&message=true&marker=&calendar=12&pressure=&type=map&location=coordinates&detail=&metricWind=default&metricTemp=default&radarRange=-1"
                            frameborder="0"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 블로그 및 날씨 끝 -->
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

                
                <!-- 쇼핑, 안전교육 -->
                <div class="five-1 ms-3">
                    <div>
                        <!-- Slider main container-->
                        <div class="swiper-container swiper-container-mx-negative swiper-init pt-3" data-swiper="{&quot;slidesPerView&quot;:4,&quot;spaceBetween&quot;:20,&quot;loop&quot;:true,&quot;roundLengths&quot;:true,&quot;breakpoints&quot;:{&quot;1200&quot;:{&quot;slidesPerView&quot;:3},&quot;991&quot;:{&quot;slidesPerView&quot;:2},&quot;565&quot;:{&quot;slidesPerView&quot;:1}},&quot;pagination&quot;:{&quot;el&quot;:&quot;.swiper-pagination&quot;,&quot;clickable&quot;:true,&quot;dynamicBullets&quot;:true}}">
                            <!-- Additional required wrapper-->
                            <div class="swiper-wrapper pb-4">
                                <!-- Slides-->
                                <div class="swiper-slide h-auto px-2">
                                    <!-- place item-->
                                    <div class="w-100 h-100 hover-animate">
                                        <div class="card h-100 border-0 shadow">
                                            <div class="card-img-top overflow-hidden gradient-overlay"> <img class="img-fluid" src="resources/resources/images/image1.jpg" alt="Modern, Well-Appointed Room" />
                                                <a class="tile-link" href="detail-rooms.html"></a>
                                            </div>
                                            <div class="card-body-center d-flex">
                                                <div class="w-100">
                                                    <h6 class="card-title"><a class="text-decoration-none text-dark" href="detail-rooms.html">NIKE 수영복</a></h6>
                                                    <div class="d-flex card-subtitle mb-3">
                                                    </div>
                                                    <p class="card-text text-muted"><span class="h4 text-primary">$80</span><br> 20% Discount</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="swiper-slide h-auto px-2">
                                    <!-- place item-->
                                    <div class="w-100 h-100 hover-animate">
                                        <div class="card h-100 border-0 shadow">
                                            <div class="card-img-top overflow-hidden gradient-overlay"> <img class="img-fluid" src="resources/resources/images/image1.jpg" alt="Modern, Well-Appointed Room" />
                                                <a class="tile-link" href="detail-rooms.html"></a>
                                            </div>
                                            <div class="card-body-center d-flex">
                                                <div class="w-100">
                                                    <h6 class="card-title"><a class="text-decoration-none text-dark" href="detail-rooms.html">선풍기</a></h6>
                                                    <div class="d-flex card-subtitle mb-3">

                                                    </div>
                                                    <p class="card-text text-muted"><span class="h4 text-primary">$80</span><br> 20% Discount</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="swiper-slide h-auto px-2">
                                    <!-- place item-->
                                    <div class="w-100 h-100 hover-animate" data-marker-id="59c0c8e33b1527bfe2abaf92">
                                        <div class="card h-100 border-0 shadow">
                                            <div class="card-img-top overflow-hidden gradient-overlay"> <img class="img-fluid" src="resources/resources/images/image1.jpg" alt="Modern, Well-Appointed Room" />
                                                <a class="tile-link" href="detail-rooms.html"></a>
                                            </div>
                                            <div class="card-body-center d-flex">
                                                <div class="w-100">
                                                    <h6 class="card-title"><a class="text-decoration-none text-dark" href="detail-rooms.html">물안경</a></h6>
                                                    <div class="d-flex card-subtitle mb-3">

                                                    </div>
                                                    <p class="card-text text-muted"><span class="h4 text-primary">$80</span><br> 20% Discount</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="swiper-slide h-auto px-2">
                                    <!-- place item-->
                                    <div class="w-100 h-100 hover-animate" data-marker-id="59c0c8e33b1527bfe2abaf92">
                                        <div class="card h-100 border-0 shadow">
                                            <div class="card-img-top overflow-hidden gradient-overlay"> <img class="img-fluid" src="resources/resources/images/image1.jpg" alt="Modern, Well-Appointed Room" />
                                                <a class="tile-link" href="detail-rooms.html"></a>
                                            </div>
                                            <div class="card-body-center d-flex">
                                                <div class="w-100">
                                                    <h6 class="card-title"><a class="text-decoration-none text-dark" href="detail-rooms.html">구명조끼</a></h6>
                                                    <div class="d-flex card-subtitle mb-3">

                                                    </div>
                                                    <p class="card-text text-muted"><span class="h3 text-primary">$80</span><br> 20% Discount</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 안전교육 -->
                    <div class="five-1">
                        <iframe width="230" height="150" class="me-3" src="https://www.youtube.com/embed/cPzfgOK6zn0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                                    </iframe>
                        <iframe width="230" height="150" class="me-3" src="https://www.youtube.com/embed/UL8C6TaLucI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                                    </iframe>
                        <iframe width="230" height="150" class="me-3" src="https://www.youtube.com/embed/MmvgY-WcMoQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                                    </iframe>
                    </div>
                </div>
            </div>
    </section>
    
    
    
    <!--  자유게시판, 쇼핑, 안전교육 유튜브 섹션 끝  -->
<%@include file="/views/common/footer.jsp" %>