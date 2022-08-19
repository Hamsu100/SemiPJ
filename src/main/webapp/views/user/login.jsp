<%@page import="com.kh.member.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String bchNoImg ="https://cdn.imweb.me/thumbnail/20210712/61009d196445a.jpg";
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">
<title>BADADA</title>
<!-- Price Slider Stylesheets -->
<link rel="stylesheet" href="<%=path %>/resources/vendor/nouislider/nouislider.css">
<!-- Google fonts - Playfair Display-->
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css"
	rel="stylesheet">
<!-- Google fonts - Poppins-->
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css"
	rel="stylesheet">
<!-- swiper-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.4.1/css/swiper.min.css">
<!-- Magnigic Popup-->
<link rel="stylesheet"
	href="<%=path %>/resources/vendor/magnific-popup/magnific-popup.css">
<!-- theme stylesheet-->
<link rel="stylesheet" href="<%=path %>/resources/resources/css/style.sea.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="<%=path %>/resources/resources/css/custom.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<!-- 타이틀 옆 아이콘-->
<link rel="shortcut icon" href="<%=path %>/resources/resources/images/wave.png">
</head>

<%
String saveId="";
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie c : cookies) { 
		if(c.getName().equals("saveId")){
			saveId = c.getValue();
			break;
		}
	}
} 
%>

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

<script>
        // ------------------------------------------------------- //
        //   Inject SVG Sprite - 
        //   see more here 
        //   https://css-tricks.com/ajaxing-svg-sprite/
        // ------------------------------------------------------ //
        function injectSvgSprite(path) {

            var ajax = new XMLHttpRequest();
            ajax.open("GET", path, true);
            ajax.send();
            ajax.onload = function(e) {
                var div = document.createElement("div");
                div.className = 'd-none';
                div.innerHTML = ajax.responseText;
                document.body.insertBefore(div, document.body.childNodes[0]);
            }
        }
        // to avoid CORS issues when viewing using file:// protocol, using the demo URL for the SVG sprite
        // use your own URL in production, please :)
        // https://demo.bootstrapious.com/directory/1-0/icons/orion-svg-sprite.svg
        //- injectSvgSprite('${path}icons/orion-svg-sprite.svg'); 
        injectSvgSprite('https://demo.bootstrapious.com/directory/1-4/icons/orion-svg-sprite.svg');
    </script>
    <!-- jQuery-->
    <script src="<%=path %>/resources/vendor/jquery/jquery.min.js"></script>
    <!-- Bootstrap JS bundle - Bootstrap + PopperJS-->
    <script src="<%=path %>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Magnific Popup - Lightbox for the gallery-->
    <script src="<%=path %>/resources/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>
    <!-- Smooth scroll-->
    <script src="<%=path %>/resources/vendor/smooth-scroll/smooth-scroll.polyfills.min.js"></script>
    <!-- Bootstrap Select-->
    <script src="<%=path %>/resources/vendor/bootstrap-select/js/bootstrap-select.min.js"></script>
    <!-- Object Fit Images - Fallback for browsers that don't support object-fit-->
    <script src="<%=path %>/resources/vendor/object-fit-images/ofi.min.js"></script>
    <!-- Swiper Carousel                       -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.4.1/js/swiper.min.js"></script>
    <script>
        var basePath = ''
    </script>
    <!-- Main Theme JS file    -->
    <script src="<%=path %>/resources/js/theme.js"></script>
</body>

</html>