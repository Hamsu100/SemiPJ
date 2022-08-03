<%@page import="java.util.Arrays"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file = "/views/common/header.jsp"%>
<%
    List<Board> bList = (List<Board>) request.getAttribute("bList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	String boardCat = request.getParameter("boardCat");
	String searchValue= request.getParameter("searchValue");
	String searchType= request.getParameter("searchType");
			
	String[] catName = (String[]) request.getAttribute("catName");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
%>
<script>
function checkLogin(){
	
	var user = '<%=loginUser%>';
	if (user != null){
		location.href='<%=path%>/board/write?boardCat=<%=boardCat%>';
	} else {
		alert('로그인 후 이용해 주십시오.');
	}	
	
}
</script>
    
<!-- 히어로 시작-->
    <section class="hero-home2  dark-overlay mb-5"><img class="bg-image" src="<%=path %>/resources/resources/images/back3.jpg" alt="">
        <div class="container py-7">
            <div class="overlay-content text-center text-white">
                <h1 class="display-3 fw-bold text-shadow mb-0">게시판 메인</h1>
            </div>
        </div>
    </section>
    <!-- 히어로 끝 -->

    <!-- Breadcrumbs 
    <ol class="breadcrumb ps-0  justify-content-center">
        <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
        <li class="breadcrumb-item"><a href="docs-introduction.html">Documentation</a></li>
        <li class="breadcrumb-item active">Introduction </li>
    </ol>-->




    <section class="py-6">
        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-lg-2" style="margin-right:50px">
                    <div class="sticky-top mb-5" style="top: 120px;">
                        <div class="sidebar-block">
                            <h6 class="sidebar-heading ms-3">게시판 항목</h6>
                            <nav class="nav nav-pills flex-column">
                                <a class="nav-link mb-2" id="b0" href="<%=path%>/board/list">전체보기 </a>
                                <a class="nav-link mb-2" id="b1" href="<%=path%>/board/list?boardCat=1">공지사항  </a>
                                <a class="nav-link mb-2" id="b2" href="<%=path%>/board/list?boardCat=2">자유게시판  </a>
                            </nav>
                        </div>
                        <div class="sidebar-block">
                            <h6 class="sidebar-heading ms-3">지역별 게시판</h6>
                            <nav class="nav nav-pills flex-column">
                                <a class="nav-link mb-2" id="b3" href="<%=path%>/board/list?boardCat=3">동해  </a>
                                <a class="nav-link mb-2" id="b4" href="<%=path%>/board/list?boardCat=4">서해 </a>
                                <a class="nav-link mb-2" id="b5" href="<%=path%>/board/list?boardCat=5">남해  </a>
                                <a class="nav-link mb-2" id="b6" href="<%=path%>/board/list?boardCat=6">제주도  </a>
                            </nav>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-10 col-xl-9">
					
                    <form action="#" method="get">
                    	<input type="hidden" name="boardCat" value="<%=boardCat%>">
                        <div class="row" justify-content-center>

                            <div class="col-xl-2 col-md-6 mb-4">

                                <select class="selectpicker form-control" name="searchType" id="search">
                                    <option value="title" selected>제목</option>
                                    <option value="writer">작성자</option>
                                    <option value="content">내용</option>
                                </select>
                            </div>

                            <div class="col-xl-6 col-md-6 mb-4">

                                <div class="input-label-absolute input-label-absolute-right">
                                    <div class="label-absolute"><i class="fa fa-search"></i></div>
                                    <input class="form-control pe-4" type="search" name="searchValue" placeholder="" id="form_keyword">
                                </div>
                            </div>

                            <div class="col-xl-3 col-md-6 mb-4">

                                <button class="btn btn-outline-primary" onclick="location.href='<%=path %>/board/list';" type="submit">검색</button>
                            </div>

                            <div class="col-xl-1 col-md-6 mb-4">

                                <button class="btn btn-info" onclick="location.href='<%=path %>/board/write?boardCat=<%=boardCat %>';" type="button">글쓰기</button>
                            </div>


                        </div>
                    </form>
                    <div>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>카테고리</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>날짜</th>
                                </tr>
                            </thead>

                            <tbody>
								<%
								if (bList != null && bList.isEmpty() == false){
									for (Board b : bList){
								%>
                                <tr>
                                    <td><%=b.getBoard_no() %></td>
                                    <td><%=catName[b.getBoard_category()-1] %></td>
                                    <td><a style="text-decoration:none;color:black;" href="<%=path%>/board/view?boardNo=<%=b.getBoard_no() %>&boardCat=<%=boardCat%>"><%=b.getBoard_title() %></a></td>
                                    <td>
                                    <%=b.getBoard_writer().substring(0, b.getBoard_writer().indexOf("@")) %>
                                    </td>
                                    <td><%=b.getBoard_mdf_date() %></td>
                                </tr>
                                <%
                                	}
								} else {
								%>
								<tr>
                                    <td colspan='5' style="text-align:center;">조회된 게시물이 없습니다.</td>
                                </tr>
								<%
								}
								%>
                            </tbody>



                        </table>

                        <nav aria-label="Page navigation example">
                            <ul class="pagination pagination-template d-flex justify-content-center">
                                <li class="page-item">
                                <%
                                if (pageInfo != null){
                                %>
                                    <a class="page-link" href="<%=path %>/board/list?page=<%=pageInfo.getPrvePage()%>&boardCat=<%=boardCat%>&searchValue=<%=searchValue%>&searchType=<%=searchType%>"> <i class="fa fa-angle-left"></i></a>
                                <%
                                } 
                                %>
                                </li>
                                
                                <%
                                if (pageInfo != null){
	                               	for (int i = pageInfo.getStartPage() ; i <= pageInfo.getEndPage() ; i++ ) {
	                               		if (i==pageInfo.getCurrentPage()) {
                                %>
                                <li class="page-item active"><a class="page-link" href="<%=path%>/board/list?page=<%=i%>&boardCat=<%=boardCat%>&searchValue=<%=searchValue%>&searchType=<%=searchType%>"><%=i %></a></li>
                                <%
	                               			continue;
	                               		}
                                %>
                                <li class="page-item"><a class="page-link" href="<%=path%>/board/list?page=<%=i%>&boardCat=<%=boardCat%>&searchValue=<%=searchValue%>&searchType=<%=searchType%>"><%=i %></a></li>
                                <%
                               		}
                                }
                                %>
                                <li class="page-item">
                                <%
                                if (pageInfo != null){
                                %>
                                    <a class="page-link" href="<%=path %>/board/list?page=<%=pageInfo.getNextPage()%>&boardCat=<%=boardCat%>&searchValue=<%=searchValue%>&searchType=<%=searchType%>"> <i class="fa fa-angle-right"></i></a>
                                <%
                                } 
                                %>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </section>
    

<script>

var boardCat=<%=boardCat%>;

switch (boardCat) {

case 1: document.getElementById('b1').setAttribute('class', 'nav-link mb-2 active'); break;
case 2: document.getElementById('b2').setAttribute('class', 'nav-link mb-2 active'); break;
case 3: document.getElementById('b3').setAttribute('class', 'nav-link mb-2 active'); break;
case 4: document.getElementById('b4').setAttribute('class', 'nav-link mb-2 active'); break;
case 5: document.getElementById('b5').setAttribute('class', 'nav-link mb-2 active'); break;
case 6: document.getElementById('b6').setAttribute('class', 'nav-link mb-2 active'); break;
default : document.getElementById('b0').setAttribute('class', 'nav-link mb-2 active'); break;
	
}

</script>
    <%@include file = "/views/common/footer.jsp"%>