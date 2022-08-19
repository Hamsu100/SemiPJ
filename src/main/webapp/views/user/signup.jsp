<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%
String path = request.getContextPath();
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <title>Your Journey</title>
    <!-- Price Slider Stylesheets -->
    <link rel="stylesheet" href="<%=path %>/resources/vendor/nouislider/nouislider.css">
    <!-- Google fonts - Playfair Display-->
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">
    <!-- Google fonts - Poppins-->
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">
    <!-- swiper-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.4.1/css/swiper.min.css">
    <!-- Magnigic Popup-->
    <link rel="stylesheet" href="<%=path %>/resources/vendor/magnific-popup/magnific-popup.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="<%=path %>/resources/resources/css/style.sea.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="<%=path %>/resources/resources/css/custom.css">

    <!-- Tweaks for older IEs-->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>

<body>
    <div class="container-fluid px-3">
        <div class="row min-vh-100">
            <div class="col-md-8 col-lg-6 col-xl-5 d-flex align-items-center">
                <div class="w-100 py-5 px-md-5 px-xxl-6 position-relative">
                    <div class="mb-4"><img class="img-fluid mb-4" src="<%=path %>/resources/resources/images/wave.png" alt="..." style="max-width: 4rem;">
                        <h2>Sign up</h2>
                        <p class="text-muted">You can start your trip here. <br> Explore and experience the world <br> I support your journey to an unknown place called the sea.</p>
                    </div>
                    <form class="form-validate" action="<%=path %>/user/signup" method="post">
                        <div class="mb-4">
                            <label class="form-label" for="User_Email"> Email Address</label>
                            <input class="form-control" name="User_Email" id="User_Email" type="email" placeholder="name@address.com" autocomplete="off" required data-msg="Please enter your email">
                        </div>
                        <div class="mb-4">
                            <label class="form-label" for="User_PW"> Password</label>
                            <input class="form-control" name="userPw" id="userPwd" placeholder="Password" type="password" required data-msg="Please enter your password">
                        </div>
                        <div class="mb-4">
                            <label class="form-label" for="User_PW2"> Confirm your password</label>
                            <input class="form-control" name="userPw" id="userPwd2" placeholder="Password" type="password" required data-msg="Please enter your password">
                        </div>
                        <div class="mb-4">
                            <label class="form-label" for="User_Name"> Name</label>
                            <input class="form-control" name="User_Name" id="User_Name" type="name" placeholder="name" required data-msg="Please enter your Name">
                        </div>
                        <div class="mb-4">
                            <label class="form-label" for="User_Phone"> Phone Number</label>
                            <input class="form-control" name="User_Phone" id="User_Phone" type="phone" placeholder="010-0000-0000" autocomplete="off" required data-msg="Please enter your Phone Number">
                        </div>
                        <div class="d-grid gap-2">
                            <button class="btn btn-lg btn-primary" type="submit">Sign up</button>
                        </div>
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
    <!-- JavaScript files-->
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