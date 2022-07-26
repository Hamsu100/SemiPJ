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
	String[] catName = (String[]) request.getAttribute("catName");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
%>

<script>
function write(){
	
	var user = <%=loginUser%>;
	if (user != null){
		location.href='/board/write?boardCat=<%=boardCat%>';
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

<%=bList.toString() %>

    <section class="py-3 bg-gray-100">
        <div class="container">
            <div class="col-lg-10 ms-auto">
                <div>
                    <div class="offset-10">
                        <select class="selectpicker form-control" data-style="btn-selectpicker" name="category" id="category">
                        <option value="announcement">공지</option>
                        <option value="free">자유</option>
                        <option value="east">동해</option>
                        <option value="south">남해</option>
                        <option value="west">서해</option>
                        <option value="jeju">제주</option>
                      </select>
                        <br>
                    </div>

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
								if (bList.isEmpty() == false) {
									for (Board b : bList) {
								%>
								
                                <tr>
                                    <td><%=b.getBoard_no() %></td>
                                    <td><%=catName[b.getBoard_category()-1] %></td>
                                    <td><%=b.getBoard_title() %></td>
                                    <td><%=b.getBoard_writer() %></td>
                                    <td><%=sdf.format(b.getBoard_crt_date())%></td>
                                </tr>
                                <%
									}
                                } else { %>
									<tr>
										<td colspan="5" style="text-align:center;">조회된 게시물이 없습니다.</td>
                                    	
									</tr>                                	
                                <%
                                }
                                %>
                            </tbody>



                        </table>
                        
                        
                        <button class="btn btn-primary" onclick="write();">글쓰기</button>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination pagination-template d-flex justify-content-center">
                                <li class="page-item">
                                    <a class="page-link" href="#"> <i class="fa fa-angle-left"></i></a>
                                </li>
                                <%
                                for (int i = 1/*pageInfo.getStartPage()*/ ; i< 5/*pageInfo.getEndPage()*/ ; i++) {
                                %>
                                	<%
                                	if (i == 1 /*pageInfo.getCurrentPage()*/){
                                	%><li class="page-item active"><a class="page-link" href="#"><%=i %></a></li>
                                	<%
                                		continue;
                                	}
                                	%>
                                <li class="page-item"><a class="page-link" href="#"><%=i %></a></li>
                                <%
                                }
                                %>
                                <li class="page-item">
                                    <a class="page-link" href="#"> <i class="fa fa-angle-right"></i></a>
                                </li>
                            </ul>
                        </nav>
                    </div>

                </div>


            </div>
        </div>
    </section>
    
    <%@include file = "/views/common/footer.jsp"%>