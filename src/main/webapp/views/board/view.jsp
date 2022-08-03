<%@page import="java.util.List"%>
<%@page import="com.kh.board.model.vo.Reply"%>
<%@page import="com.kh.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>

<%
Board b = (Board)request.getAttribute("board");
String boardCat = (String) request.getParameter("boardCat");
List<Reply> rList = b.getBoard_reply();
%>
<!-- 히어로 시작-->
    <!-- 히어로 시작-->
    <section class="hero-home2  dark-overlay mb-5"><img class="bg-image" src="<%=path %>/resources/resources/images/back3.jpg" alt="">
        <div class="container py-7">
            <div class="overlay-content text-center text-white">
                <h1 class="display-3 fw-bold text-shadow mb-0">게시판 상세</h1>
            </div>
        </div>
    </section>
    <!-- 히어로 끝 -->
    <!-- 중간 내용 시작-->

    <section class="py-4">
        <div class="container">
            <!-- 제목 + 글 + 댓글 영역 -->
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-2">
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
                    <div class="col-lg-10">
                        <!-- 제목,  글쓴이, 조회수, 지역  -->
                        <div class="mb-3 row">
                            <div>
                                <!-- 제목-->
                                <h3> <%=b.getBoard_title() %></h3>
                            </div>
                            <div id="left-line" class="d-lg-flex justify-content-end text-primary font3">
                                <!-- 글쓴이 조회수 지역 -->
                                <span><%=b.getBoard_writer().substring(0, b.getBoard_writer().indexOf("@")) %>&nbsp;</span>
                                <span><i class="fa fa-map-marker text-secondary opacity-4 me-1"></i>
                                <%
                               	String location ="";
                               	
                               	switch (b.getBoard_category()){
                        	       	case 3:location = "동해";break;
                            	   	case 4:location = "서해";break;
                               		case 5:location = "남해";break;
                               		case 6:location = "제주";break;
                               		default:location="-";break;
                               	}
                                %>
                                &nbsp;<%=location %>
                                </span>
                            </div>
                        </div>
                        <hr class="my-4">
                        <!-- 글 내용 + 사진들 -->
                        <div class="container-fluid my-3 box">
                            <%=b.getBoard_content() %>
                        </div>
                        <!-- 댓글 영역 -->
                        <hr class="my-4">
                        
                        <%
                            if (loginUser != null){ 
                            %>
	                            <div class="py-5">
	                                <button class="btn btn-primary btnrow me-2  text-white offset-7-1" type="button" data-bs-toggle="collapse" data-bs-target="#leaveReview" aria-expanded="false" aria-controls="leaveReview">댓글 작성하기</button>
	                                <%
                        	if (loginUser.getUser_id().equals(b.getBoard_writer())){
                        %>
						<button class="btn btn-outline-info btnrow me-2" onclick="location.href='<%=path%>/board/modify?boardNo=<%=b.getBoard_no()%>&boardCat=<%=b.getBoard_category()%>';">글 수정하기</button>
                        <button class="btn btn-outline-info btnrow" onclick="location.href='<%=path%>/board/delete?boardNo=<%=b.getBoard_no()%>'">글 삭제하기</button>
						<%
                        	}
						%>
	                                
	                                <div class="collapse mt-4" id="leaveReview">
	                                
	                                    <form class="form" id="contact-form" method="post" action="<%=path%>/beach/revwrite">
	                                    	<input type="hidden" name="writer" value="<%=loginUser != null ? loginUser.getUser_id() : ""%>">
	                                    	<input type="hidden" name="bNo" value="<%=b.getBoard_no()%>">
	                                        <div class="mb-4">
	                                            <textarea class="form-control" rows="4" name="review" id="review" placeholder="댓글을 작성하세요" required="required" style="resize:none"></textarea>
	                                        </div>
	                                        <button class="btn btn-primary offset-11 text-white" type="submit">작성하기</button>
	                                    </form>
	                                    </div>
                            
	                                    
	                             
							<%
                            } else {
							%>
                                	<button class="btn btn-primary btnrow me-2  text-white offset-10-2" type="button" data-bs-toggle="collapse" data-bs-target="#leaveReview" aria-expanded="false" aria-controls="leaveReview">댓글 작성하기</button>
                                	<div class="collapse mt-4" id="leaveReview">
                                
	                                    <form class="form" id="contact-form" method="post" action="<%=path%>/board/reply">
	                                        <div class="mb-4">
	                                            <textarea class="form-control" rows="4" name="review" id="review" placeholder="로그인 후 이용해 주세요!" readonly style="resize:none"></textarea>
	                                        </div>
	                                    </form>
	                                   </div>
                              
                            <%
                            }
                            %>
                      		  
                        
                    
                
                        
                        
                        
                        <div class="text-block">
                            <h4 class="mb-4 text-primary">댓글</h4>
                            <%
                            if (rList != null && rList.size() > 0){
                            	for (Reply r : rList){
                            %>
                            <div class="d-flex d-block d-sm-flex review">
                                <div id="reply">
                                    <h6 class="mt-2 mb-1">
                                    <%=r.getBoard_reply_writer().substring(0, r.getBoard_reply_writer().indexOf("@")) %>
                                    </h6>
                                    <p class="text-muted text-sm"><%=r.getBoard_reply_content() %></p>
                                    <a class="text-primary me-2 boarda" style=" text-decoration: none"><%=r.getBoard_reply_mdf_date() %></a>
                                    	<%
                                    	if (loginUser != null){
                                    		if (loginUser.getUser_id().equals(r.getBoard_reply_writer())){
                                    	
                                    	%>
                                    		 <a href="#" data-bs-toggle="collapse" data-bs-target="#modifycomment<%=r.getBoard_reply_no() %>" aria-expanded="false" aria-controls="modifycomment" class=" me-2 boarda">수정</a>
                                    		 <a class="boarda" href="<%=path%>/board/repdel?boardNo=<%=b.getBoard_no()%>&replyNo=<%=r.getBoard_reply_no()%>">삭제</a>
                                    		 
                                   		<%
                                    		}
                                    	}
                                   		%>
                                </div>
                                
                            </div>
                            <div class="collapse mt-4" id="modifycomment<%=r.getBoard_reply_no()%>">
                                <form class="form" id="contact-form" method="post" action="<%=path%>/board/repupdate">
                                <input type="hidden" name="rNo" value="<%=r.getBoard_reply_no()%>">
                                <input type="hidden" name="writer" value="<%=r.getBoard_reply_writer()%>">
                                    <div class="mb-3">
                                        <textarea class="form-control" rows="3" name="modifycomment" id="modifycomment" required="required" style="resize:none;"><%=r.getBoard_reply_content() %></textarea>
                                    </div>
                                    <button class="btn btn-primary offset-11" type="submit">작성하기</button>
                                </form>
                            </div>
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
    <!-- 중간 내용 끝 -->

<%@include file="/views/common/footer.jsp"%>