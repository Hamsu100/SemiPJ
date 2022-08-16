<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="com.kh.blog.model.vo.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/views/common/header.jsp"%>

<% 
	Blog blog = (Blog)request.getAttribute("blog");
%>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>



<section class="hero-home1 dark-overlay mb-5"><img class="bg-image" src="<%=path%>/resources/resources/images/back2.jpg" alt="">
    <div class="container py-7">
        <div class="overlay-content text-center text-white">
            <h1 class="display-3 text-serif fw-bold text-shadow mb-0">Escape the city today</h1>
        </div>
    </div>
</section>


<form action="<%=path %>/blog/update" method="POST" enctype="multipart/form-data">
<section class="py-3 container">
    <div class="container">

		<div class="row">
		
	        <div class="col-6">
	            <h3> 블로그 수정</h3>
	        </div>
	        
	        
	        <div class="col-6">
    			<div class="py-3" style="float:right;">
		            <button type="button" onclick="location.href='<%=path%>/blog/list/new'" class="btn btn-danger offset-9-4 board-btn text-white" style="width: 110px;" >
		                목록
		            </button>
		            <button type="submit" class="btn btn-primary board-btn ms-3 text-white" style="width: 110px;">
		                올리기
		            </button>
		       </div>
	   		</div>
        
        </div>
        
      
        <input type="hidden" name="bgno" value="<%= blog.getBlog_no() %>">
        <input type="hidden" name="writer" value="<%= loginUser.getUser_id() %>">
        <% if (blog.getBlog_originimg() != null && blog.getBlog_originimg().length() > 0) { %>
        	<input type="hidden" name="blog_renameimg" value="<%= blog.getBlog_renameimg() %>"> 
        <% } %>
        
        
        <div class="form-group">
        	<div class="row">
	            <div class="form-group mb-2 col-2">
	                <select class="selectpicker mb-3" title="지역" name="area" required="required">
	                
	                	<% Map<Integer, String> map = new TreeMap<Integer, String>(); %>
	                	<% map.put(2, "인천"); %>
	                	<% map.put(6, "부산"); %>
	                	<% map.put(7, "울산"); %>
	                	<% map.put(31, "경기"); %>
	                	<% map.put(32, "강원"); %>
	                	<% map.put(34, "충남"); %>
	                	<% map.put(35, "경북"); %>
	                	<% map.put(36, "경남"); %>
	                	<% map.put(37, "전북"); %>
	                	<% map.put(38, "전남"); %>
	                	<% map.put(39, "제주"); %>
	                
	                    <% for (Map.Entry<Integer, String> area : map.entrySet()) { %>
	                    	<% if (blog.getArea_code() == area.getKey()) { %>
	                    		<option value="<%= area.getKey() %>" selected><%= area.getValue() %></option>
	                    	<% } else { %>
	                    		<option value="<%= area.getKey() %>"><%= area.getValue() %></option>
	                    	<% } %>	
	                    <% } %>
	                    
					</select>
	            </div>
            </div>
            
            <input type="text" class="form-control mb-3" id="con1" name="con1" maxlength="35" required="required" pattern=".{1,35}" value="<%=blog.getBlog_content() %>">
            <textarea class="form-control mb-3" id="con2" name="con2" rows="5" style="white-space: pre-line;"><%= blog.getBlog_subcontent() %></textarea>
            
            
            <div>
	            <input type="file" class="form-control mb-3" name="imgfile" id="imgSelector" accept="image/gif, image/jpeg, image/png">
	            <% if (blog.getBlog_originimg() != null && blog.getBlog_originimg().length() > 0) { %>
	            <img class="rounded mx-auto d-block img-fluid py-4" src="<%=path%>/resources/upload/blog/<%=blog.getBlog_renameimg() %>" id="preview">
				<% } else { %>	            
	            <img class="rounded mx-auto d-block img-fluid py-4" src="" id="preview">
	            <% } %>
            </div>
            
        </div>
   		
    </div>
</section>
</form>


<script>
	$('#imgSelector').change(function(){
	    setImageFromFile(this, '#preview');
	});
	
	function setImageFromFile(input, expression) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            $(expression).attr('src', e.target.result);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
</script>


<%@include file = "/views/common/footer.jsp"%>
