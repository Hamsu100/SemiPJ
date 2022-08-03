<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>

<%
String saveId="";
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie c : cookies) { 
		%>
		
		<%=c.getName() %> : <%=c.getValue() %>
		<%
		if(c.getName().equals("saveId")){
			saveId = c.getValue();
			break;
		}
	}
} 
%>
saveId : <%=saveId %>

    <div class="container-fluid px-3">
        <div class="row min-vh-100">
            <div class="col-md-8 col-lg-6 col-xl-5 d-flex align-items-center">
                <div class="w-100 py-5 px-md-5 px-xxl-6 position-relative">
                    <div class="mb-5"><img class="img-fluid mb-3" src="<%=path %>/resources/resources/images/wave.png" alt="..." style="max-width: 4rem;">
                        <h2>Welcome back</h2>
                        <p class="text-muted">You can start your trip here. <br> Explore and experience the world <br> I support your journey to an unknown place called the sea.</p>
                    </div>
                    <form class="form-validate" action="<%=path %>/user/login" method="post">
                        <div class="mb-4">
                            <label class="form-label" for="loginUsername"> Email Address</label>
                            <input class="form-control" name="loginUsername" id="loginUsername" type="email" placeholder="name@address.com" value="<%=saveId %>" autocomplete="off" required data-msg="Please enter your email">
                        </div>
                        <div class="mb-4">
                            <div class="row">
                                <div class="col">
                                    <label class="form-label" for="loginPassword"> Password</label>
                                </div>
                            </div>
                            <input class="form-control" name="userPw" id="loginPassword" placeholder="Password" type="password" required data-msg="Please enter your password">
                        </div>
                        <div class="mb-4">
                            <div class="form-check">
                                <input class="form-check-input" id="loginRemember" name="saveId" type="checkbox" <%=saveId.length() == 0 ? "":"checked" %>>
                                <label class="form-check-label text-muted" for="loginRemember"> <span class="text-sm">Remember me for 30 days</span></label>
                            </div>
                        </div>
                        <!-- Submit-->
                        <div class="d-grid">
                            <button class="btn btn-lg btn-primary">SIGN IN</button>
                        </div>
                        <hr class="my-4">
                        <p class="text-center"><small class="text-muted text-center">Don't have an account yet? <a href="<%=path%>/user/signup">Sign Up                </a></small></p>
                    </form>
                    <a class="close-absolute me-md-5 me-xl-6 pt-5" href="<%=path%>/index">
                        <svg class="svg-icon w-3rem h-3rem">
                        <use xlink:href="#close-1"> </use>
              </svg></a>
                </div>
            </div>
            <div class="col-md-4 col-lg-6 col-xl-7 d-none d-md-block">
                <!-- Image-->
                <div class="bg-cover h-100 me-n3" style="background-image: url(<%=path %>/resources/resources/images/sign2.jpg);"></div>
            </div>
        </div>
    </div>

<%@include file="/views/common/footer.jsp" %>