<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/views/common/header.jsp"%>



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


<form action="<%=path %>/blog/insert" method="POST" enctype="multipart/form-data">
<section class="py-3 container">
    <div class="container">

		<div class="row">
		
	        <div class="col-6">
	            <h3> 블로그 작성</h3>
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
        
      
        <input type="hidden" name="writer" value="<%= loginUser.getUser_id() %>">
        <div class="form-group">
        	<div class="row">
	            <div class="form-group mb-2 col-2">
	                <select class="selectpicker mb-3" title="지역" name="area" required="required">
	                            
		                <option value="2">인천</option>
		                <option value="6">부산</option>
		                <option value="7">울산</option>
		                <option value="31">경기</option>
		                <option value="32">강원</option>
		                <option value="34">충남</option>
		                <option value="35">경북</option>
		                <option value="36">경남</option>
		                <option value="37">전북</option>
		                <option value="38">전남</option>
		                <option value="39">제주</option>
	                            
					</select>
	            </div>
            </div>
            
            <input type="text" class="form-control mb-3" id="con1" name="con1" placeholder="어디 해수욕장 다녀오셨나요?" maxlength="35" pattern=".{1,35}" required="required">
            <textarea class="form-control mb-3" id="con2" name="con2" placeholder="함께할 이야기를 적어주세요" rows="5" style="white-space: pre-line;"></textarea>
            
            
            <div>
	            <input type="file" class="form-control mb-3" name="imgfile" id="imgSelector" accept="image/gif, image/jpeg, image/png" required="required">
	            <img class="rounded mx-auto d-block img-fluid py-4" src="" id="preview">
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
