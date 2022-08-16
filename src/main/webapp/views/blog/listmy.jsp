<%@page import="com.kh.blog.model.vo.Reply"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="com.kh.blog.model.vo.Blog"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy. M. d");
	
	List<Blog> blogList = (List<Blog>) request.getAttribute("blogListMy");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	
%>

<style>
	.modal {
        display: none; 
        position: fixed; 
        z-index: 1; 
        left: 0;
        top: 0;
        width: 100%; 
        height: 100%; 
        overflow: auto; 
        background-color: rgb(0,0,0); 
        background-color: rgba(0,0,0,0.4); 
    }
    
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto; 
        padding: 20px;
        border: 1px solid #888;
        width: 50%; 
    }
    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }

    .multiLine1 {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
	}
	
    .multiLine3 {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	}

</style>

<%@include file="/views/common/header.jsp" %>



<!-- 블로그 히어로 -->
<section class="hero-home1 dark-overlay mb-5"><img class="bg-image" src="<%=path%>/resources/resources/images/back2.jpg" alt="">
    <div class="container py-7">
        <div class="overlay-content text-center text-white">
            <h1 class="display-4 text-shadow mb-0">내 블로그 모아보기</h1>
        </div>
    </div>
</section>
<!-- 블로그 히어로 끝 -->
<!-- 블로그 내용 -->
<section class="py-5">
    <div class="container">
        <div class="row mb-3">
        	<div class="col-12">
           		<button class="btn btn-primary board-btn ms-2 text-white" onclick="location.href='<%=path %>/blog/insert';" type="button" style="float:right; width: 110px;">글쓰기</button>
           		<button class="btn btn-outline-primary board-btn ms-2" onclick="location.href='<%=path%>/blog/list/new';" type="button" style="float:right; width: 150px;">블로그 파도타기</button>
           	</div>
           	<div class="py-3"></div>
        </div>
        
        <div class="row my-4">
        	<% if (blogList == null || blogList.isEmpty()) { %>
         		<div class="m-5 col text-center"> 아직 작성한 블로그가 없습니다 </div>
         	<% } else { %>
        		<% for ( Blog list : blogList ) { %>
	              <!-- venue item-->
                  <div class="col-lg-3 col-sm-6 mb-4 hover-animate" data-marker-id="59c0c8e33b1527bfe2abaf92">
                      <div class="card shadow border-0 h-100">
                      
                          <div class="modalbtn card-img-top overflow-hidden bg-cover" style="background-image: url(<%=path%>/resources/upload/blog/<%=list.getBlog_renameimg()%>); min-height: 200px;">
                              <a href="javascript:void(0)" class="tile-link"></a>
                          </div>
                          
                          <div class="card-body text-center">
	                    	 <% String blog_content = "#" + list.getBlog_content().replaceAll(" ", "_"); %>
	                         <h6 class="my-2 multiLine1"><%= blog_content.replaceAll("###", "#").replaceAll("##", "#").replaceAll("_#", " #") %></h6>
	                         <p class="text-base text-secondary my-3 mb-2"><strong><i class="fa fa-map-marker text-secondary opacity-4 me-2"></i><%= list.getArea_name() %></strong></p>
	                         <p class="my-2 mt-3 mb-3 text-muted text-sm multiLine3"><%= list.getBlog_subcontent() %></p>
	                         <p class="text-sm text-secondary my-3 mb-2"><%= sdf.format(list.getBlog_crt_date()) %>
		                         <% if (list.getBlog_mdf_date() != null && (list.getBlog_mdf_date().equals(list.getBlog_crt_date()))) { %>
		                        	 <span class="text-sm text-secondary my-3 mb-2"></span>
		                         <% } else if (list.getBlog_mdf_date() != null) { %>
		                        	 <span class="text-sm text-secondary my-3 mb-2">&nbsp;<%= "(" + sdf.format(list.getBlog_mdf_date()) + " 수정)" %></span>
		                         <% } %>
	                         </p>
	                     </div>
                      </div>
                  </div>
        		<% } %>
        	<% } %>	
        </div>
        
    <!-- Pagination -->
	<nav aria-label="Blog pagination" class="py-3">
	    <ul class="pagination justify-content-between mb-5">
	    
	        <li class="page-item">
	            <a class="page-link text-sm rounded" href="<%=path%>/blog/list/my?uno=<%=loginUser.getUser_no() %>&page=<%=pageInfo.getPrvePage()%>"> <i class="fa fa-chevron-left me-1"></i>Newer posts</a>
	        </li>
	        
	       
	   		    	<li class="page-link text-sm rounded" style="background : none;" disable><%=pageInfo.getCurrentPage() %></li>
	        	
	        
	        <li class="page-item">
	            <a class="page-link text-sm rounded" href="<%=path%>/blog/list/my?uno=<%=loginUser.getUser_no() %>&page=<%=pageInfo.getNextPage()%>" tabindex="-1">Older posts<i class="fa fa-chevron-right ms-1"></i></a>
	        </li>
	    </ul>
	</nav>
	
	
   <!-- 모달 -->
    <% for ( Blog list : blogList ) { %>
	<div class="modal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
	 			<div class="m-3">
	 			<span class="close">&times;</span> 
	 			</div>
	 				
	 				<section>
	                    <div class="container">
	                        <div class="row px-xl-5">
	                        
	                            <div class="col-lg-7">
	                                <div>
	                                    <!-- 제목-->
	                                </div>
	                                <div id="left-line" class="d-lg-flex text-primary font3">
	                                    <!-- 글쓴이 조회수 지역 -->
	                                    <span><i class="fa fa-map-marker text-secondary opacity-4 me-1"></i>&nbsp;<%= list.getArea_name() %> &nbsp;</span>
	                                    <span> &nbsp;<%= list.getUser_id() %></span>
	                                </div>
	
	                                <hr class="my-4">
	                                <!-- 블로그 내용 -->
	                                <div class="container-fluid my-3 box">
	
	                                    <!-- 첨부된 이미지 -->
	                                    <img class="img-fluid card-img-top" style="width: 100%; object-fit: cover; aspect-ratio: 5/4;" src="<%=path%>/resources/upload/blog/<%=list.getBlog_renameimg()%>" alt="..." /><br>
	
	                                    <!-- 본문 텍스트 -->
	                                    <br>
	                                    <% String blog_content = "#" + list.getBlog_content().replaceAll(" ", "_"); %>
                                 	 			<p><%= blog_content.replaceAll("###", "#").replaceAll("##", "#").replaceAll("_#", " #") %></p>
	                                    <p class="text-muted"><%= list.getBlog_subcontent().replaceAll("\r\n", "<br>") %></p>
	                                    <p class="text-muted text-dark"><%= sdf.format(list.getBlog_crt_date()) %></p>
	                                    
	                                    
	                                    <% if (loginUser != null) { %>
		                                	<% if (loginUser.getUser_id().equals(list.getUser_id())){ %>
		                                		<!-- form -->
	                                    		<a class=" me-2 boarda" href="<%=path%>/blog/update?uid=<%=list.getUser_id() %>&bgno=<%=list.getBlog_no()%>">수정</a>
			                                    <a class="boarda" href="<%=path%>/blog/delete?bgno=<%=list.getBlog_no()%>">삭제</a>
											<% } %>						                                
		                                <% } %>
		                                
		                                
	                                </div>
	                            </div>
	
	
                                <!-- 댓글 영역 -->
	                            <div class="col-lg-5">
	                                <div class="text-block">
	                                    <h4 class="mb-4 text-primary"> 댓글</h4>
	                                    
	                                    
	                                    <!-- 댓글 리스트 및 리스트 내 삭제 -->
	                                    <% for (Reply relist : list.getBlog_reply()) { %>
				                        <div class="d-flex d-block d-sm-flex review">
				                            <div>
				                                <h6 class="mt-1 mb-3"><%= relist.getUser_id() %></h6>
				                                <p class="text-muted text-sx" style="word-break: break-word;"><%= relist.getBlog_reply_content() %></p>
				                                <p class="text-muted text-sm text-dark"><%= sdf.format(relist.getBlog_reply_crt_date()) %></p>
				                                
				                                <% if (loginUser != null) { %>
				                                	<% if (loginUser.getUser_id().equals(relist.getUser_id())){ %>
			                                    		<a class="boarda" href="<%=path%>/blog/reply/delete?uid=<%=list.getUser_id() %>&reno=<%=relist.getBlog_reply_no() %>">삭제</a>
													<% } %>						                                
				                                <% } %>
				                                
				                            </div>
				                        </div>
				                        <% } %>
	
	
										<!-- 댓글 입력 -->
	                                    <div class="py-5">
	                                    
	                                    	<% if (loginUser != null) { %>
	                                    	
		                                        <button class="btn btn-primary btnrow me-2  text-white" type="button" data-bs-toggle="collapse" data-bs-target="#leaveReview" aria-expanded="false" aria-controls="leaveReview">댓글 작성하기</button>
		                                        <div class="collapse mt-4" id="leaveReview">
		                                            <form class="form" id="contact-form" method="POST" action="<%=path%>/blog/reply/insert">
		                                                <div class="mb-4">
		                                                	<input type ="hidden" name="bgno" value="<%=list.getBlog_no() %>">
		                                                	<input type ="hidden" name="writer" value="<%=list.getUser_id() %>">
		                                                    <textarea class="form-control" rows="4" name="con" id="con" placeholder="댓글을 작성하세요" required="required"></textarea>
		                                                </div>
		                                                <button class="btn btn-primary" type="submit">올리기</button>
		                                            </form>
		                                        </div>
	                                    	
	                                    	<% } else { %>
	                                    	
		                                    	<button class="btn btn-primary btnrow me-2  text-white" type="button" data-bs-toggle="collapse" data-bs-target="#leaveReview" aria-expanded="false" aria-controls="leaveReview">댓글 작성하기</button>
			                                	<div class="collapse mt-4" id="leaveReview">
			                                  		<form class="form" id="contact-form" method="POST" action="#">
			                                    		<div class="mb-4">
			                                            <textarea class="form-control" rows="4" name="review" id="review" placeholder="로그인 후 이용해 주세요!" readonly style="resize:none"></textarea>
			                                        	</div>
			                                    	</form>
			                                  	</div>
			                                  	
	                                    	<% } %>
	                                    	
	                                    </div>
	                                    
	                                </div>
	                            </div>
	
	                        </div>
	                    </div>
	                </section>
	 				
	        </div>
	    </div>
	</div>
	<% } %>
        
        
    </div>
</section>
<!-- 블로그 내용 끝 -->



<!-- 모달 -->
	<script src="https://code.jquery.com/jquery-latest.js"></script> 
	 
	<script type="text/javascript">
	      
	  var modals = document.getElementsByClassName("modal");
	  var modalbtns = document.getElementsByClassName("modalbtn");
	  var spanes = document.getElementsByClassName("close");
	  var funcs = [];
	   
	  function Modal(num) {
	    return function() {
	      modalbtns[num].onclick =  function() {
	          modals[num].style.display = "block";
	          console.log(num);
	      };
	   
	      spanes[num].onclick = function() {
	          modals[num].style.display = "none";
	      };
	    };
	  }
	   
	  for(var i = 0; i < modalbtns.length; i++) {
	    funcs[i] = Modal(i);
	  }
	   
	  for(var j = 0; j < modalbtns.length; j++) {
	    funcs[j]();
	  }
	   
	  window.onclick = function(event) {
	    if (event.target.className == "modal") {
	        event.target.style.display = "none";
	    }
	  };
	   	  
	</script>



<%@include file = "/views/common/footer2.jsp" %>