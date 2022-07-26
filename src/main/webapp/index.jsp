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
List<Board> bList1 = null;
List<Board> bList2 = null;
List<Board> bList3 = null;
%>
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
                        <form action="#">
                            <div class="row">
                                <div class="col-lg-7 d-flex align-items-center form-group">
                                    <input class="form-control border-0 shadow-0" type="text" name="search" placeholder="What are you searching for?">
                                </div>
                                <div class="col-lg-3 d-flex align-items-center form-group no-divider">
                                    <select class="selectpicker" title="location" data-style="btn-form-control">
                                        <option value="전국">전국</option>
                                        <option value="서해">서해</option>
                                        <option value="남해">남해</option>
                                        <option value="동해">동해</option>
                                        <option value="제주도">제주도</option>
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
                <div class="col-md-4 d-lg-flex align-items-center justify-content-end"><a class="text-muted text-sm" href="bpop.html">
               See all guides<i class="fas fa-angle-double-right ms-2"></i></a></div>
            </div>
            <div class="swiper-container guides-slider mx-n2 pt-3">
                <!-- Additional required wrapper-->
                <div class="swiper-wrapper pb-4">
                    <!-- Slides-->
                    <div class="swiper-slide h-auto px-2">
                        <div class="card card-poster gradient-overlay hover-animate mb-4 mb-lg-0">
                            <a class="tile-link" href="bpop.html"></a><img class="bg-image" src="resources/resources/images/pop1.jpg" alt="Card image">
                            <div class="card-body-center overlay-content">
                                <h5 class="card-title text-shadow text-uppercase">Busan</h5>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide h-auto px-2">
                        <div class="card card-poster gradient-overlay hover-animate mb-4 mb-lg-0">
                            <a class="tile-link" href="bpop.html"></a><img class="bg-image" src="resources/resources/images/pop2.jpg" alt="Card image">
                            <div class="card-body-center overlay-content">
                                <h5 class="card-title text-shadow text-uppercase">Chungnam</h5>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide h-auto px-2">
                        <div class="card card-poster gradient-overlay hover-animate mb-4 mb-lg-0">
                            <a class="tile-link" href="bpop.html"></a><img class="bg-image" src="resources/resources/images/pop3.jpg" alt="Card image">
                            <div class="card-body-center overlay-content">
                                <h5 class="card-title text-shadow text-uppercase">Jeju</h5>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide h-auto px-2">
                        <div class="card card-poster gradient-overlay hover-animate mb-4 mb-lg-0">
                            <a class="tile-link" href="bpop.html"></a><img class="bg-image" src="resources/resources/images/pop4.jpg" alt="Card image">
                            <div class="card-body-center overlay-content">
                                <h5 class="card-title text-shadow text-uppercase">incheon</h5>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide h-auto px-2">
                        <div class="card card-poster gradient-overlay hover-animate mb-4 mb-lg-0">
                            <a class="tile-link" href="bpop.html"></a><img class="bg-image" src="resources/resources/images/pop5.jpg" alt="Card image">
                            <div class="card-body-center overlay-content">
                                <h5 class="card-title text-shadow text-uppercase">Gangwon</h5>
                            </div>
                        </div>
                    </div>
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
                        <li class="nav-item"><a class="nav-link active font3" href="#notice" data-bs-toggle="tab" role="tab">공지 사항</a></li>
                        <li class="nav-item"><a class="nav-link font3" href="#free" data-bs-toggle="tab" role="tab">자유게시판</a></li>
                        <li class="nav-item"><a class="nav-link font3" href="#location" data-bs-toggle="tab" role="tab">지역별 게시판</a></li>
                    </ul>
                    <div class="tab-content ">
                        <div class="tab-pane fade show active" id="notice" role="tabpanel">
                            <form action="#">
                                <div class="row">
                                    <div class=" d-flex align-items-center form-group no-divider">
                                        <ul style="padding-left: 2rem;">
                                            <li class="boardlist"><a class="text-black font3" href="board.html">여기는 공지사항입니다.</a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html">저희 홈페이지를 이용하실때에는 </a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html">이러쿵 저러쿵 이용해주세욥</a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html">틴탑위고락킹드랍잇탑잇헤이돈스탑잇팝잇더 길게 쓰면 어떻게 될까? </a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html"> 거꾸로해도 똑바로해도 우영우</a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html">틴탑위고락킹드랍잇탑잇헤이돈스탑잇팝잇 </a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html"> 거꾸로해도 똑바로해도 우영우</a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                            <li class="boardlist"><a class="text-black font3" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="tab-pane fade show " id="free" role="tabpanel">
                            <form action="#">
                                <div class="row">
                                    <div class=" d-flex align-items-center form-group no-divider">
                                        <ul id="boardList">
                                            <li class="boardlist"><a class="text-black font5" href="board.html">여기는 자유 게시판입니다.</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">저희 홈페이지를 이용하실때에는 </a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">이러쿵 저러쿵 이용해주세욥</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">틴탑위고락킹드랍잇탑잇헤이돈스탑잇팝잇 </a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html"> 거꾸로해도 똑바로해도 우영우</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">틴탑위고락킹드랍잇탑잇헤이돈스탑잇팝잇 </a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html"> 거꾸로해도 똑바로해도 우영우</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="tab-pane fade show " id="location" role="tabpanel">
                            <form action="#">
                                <div class="row">
                                    <div class=" d-flex align-items-center form-group no-divider">
                                        <ul>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">여기는 지역별 게시판입니다.</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">저희 홈페이지를 이용하실때에는 </a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">이러쿵 저러쿵 이용해주세욥</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">틴탑위고락킹드랍잇탑잇헤이돈스탑잇팝잇 </a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html"> 거꾸로해도 똑바로해도 우영우</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">틴탑위고락킹드랍잇탑잇헤이돈스탑잇팝잇 </a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html"> 거꾸로해도 똑바로해도 우영우</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
                                            <li class="boardlist"><a class="text-black font5" href="board.html">기러기 토마토 스위스 인도인 별똥별</a></li>
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
    <script>
    function ajaxTest1(boardCat) {
        let xhr = new XMLHttpRequest();
        
        xhr.onreadystatechange = function() {
            console.log('read' + xhr.readyState);
            console.log('read' + xhr.status);

            document.getElementById('boardList').innerHTML = 'status : ' + xhr.status + '<br>';

            if (xhr.readyState == 4 && xhr.status == 200) { // 요청 전달 성공 = 4
                let str = xhr.responseText; // 서버가 응답한 text
                document.getElementById('boardList').innerHTML += str + '<br>';
            }
        }
        var category = boardCat;
       
		url ='<%=path%>/getBoard?boardCat=category';
        
        xhr.open('get', url, true);

        // 5. xhr send
        xhr.send(); // 실제 데이터가 전달 되는 코드
    }
    </script>
<%@include file="/views/common/footer.jsp" %>