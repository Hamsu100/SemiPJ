<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- % String path = request.getContextPath(); %> //<<<< header include 이미 있어서 불러와 적용시킬 때 오류 발생 -->  
    
<!-- 풋터-->
     <footer class="position-relative z-index-10 d-print-none">
        <!-- Main block - menus, subscribe form-->
        <div class="py-5 bg-gray-200 text-muted">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <div class="fw-bold text-uppercase text-theme mb-3">BADADA </div>
                        <p>Explore the world <br>Experience the world <br>Feel the world </p>
                        <ul class="list-inline">
                            <li class="list-inline-item"><a class="text-muted text-primary-hover" href="https://twitter.com/home" target="_blank" title="twitter"><i class="fab fa-twitter"></i></a></li>
                            <li class="list-inline-item"><a class="text-muted text-primary-hover" href="https://www.facebook.com/" target="_blank" title="facebook"><i class="fab fa-facebook"></i></a></li>
                            <li class="list-inline-item"><a class="text-muted text-primary-hover" href="https://www.instagram.com/" target="_blank" title="instagram"><i class="fab fa-instagram"></i></a></li>
                            <li class="list-inline-item"><a class="text-muted text-primary-hover" href="https://www.pinterest.co.kr/" target="_blank" title="pinterest"><i class="fab fa-pinterest"></i></a></li>
                            <li class="list-inline-item"><a class="text-muted text-primary-hover" href="https://vimeo.com/" target="_blank" title="vimeo"><i class="fab fa-vimeo"></i></a></li>
                        </ul>
                    </div>
                    <div class="col-lg-2 col-md-6 mb-5 mb-lg-0">
                        <h6 class="text-uppercase text-theme mb-3">Beach & MARKET</h6>
                        <ul class="list-unstyled">
                            <li><a class="text-muted" href="<%=path%>/beach/search">Search</a></li>
                            <li><a class="text-muted" href="<%=path%>/beach/pop">Popularity</a></li>
                        </ul>
                    </div>
                    <div class="col-lg-2 col-md-6 mb-5 mb-lg-0">
                        <h6 class="text-uppercase text-theme mb-3">Community</h6>
                        <ul class="list-unstyled">
                            <li><a class="text-muted" href="<%=path%>/board/list">All Board</a></li>
                            <li><a class="text-muted" href="<%=path%>/blog/list/new">All Blog</a></li>
                            <% if (loginUser != null) { %>
                            <li><a class="text-muted" href="<%=path%>/blog/list/my?uno=<%= loginUser.getUser_no() %>">My Blog</a></li>
                            <% } %>
                        </ul>
                    </div>
                    <div class="col-lg-4">
                        <h6 class="text-uppercase text-theme mb-3">Always by your side</h6>
                        <p class="mb-3"> You can go on a trip through this place.</p>
                        <form action="#" id="newsletter-form">
                            <div class="input-group mb-3">
                                <input class="form-control bg-transparent border-dark border-end-0" type="email" placeholder="Your Email Address" aria-label="Your Email Address">
                                <button class="btn btn-outline-dark border-start-0" type="submit"> <i class="fa fa-paper-plane text-lg"></i></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright section of the footer-->
        <div class="py-4 fw-light bg-gray-800 text-gray-300">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-6 text-center text-md-start">
                        <p class="text-sm mb-md-0">&copy; 2022, BADADA. All rights reserved.</p>
                    </div>
                    <div class="col-md-6">
                        <ul class="list-inline mb-0 mt-2 mt-md-0 text-center text-md-end">
                            <li class="list-inline-item"><img class="w-2rem" src="<%=path %>/resources/resources/images/visa.svg" alt="..."></li>
                            <li class="list-inline-item"><img class="w-2rem" src="<%=path %>/resources/resources/images/mastercard.svg" alt="..."></li>
                            <li class="list-inline-item"><img class="w-2rem" src="<%=path %>/resources/resources/images/paypal.svg" alt="..."></li>
                            <li class="list-inline-item"><img class="w-2rem" src="<%=path %>/resources/resources/images/western-union.svg" alt="..."></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>
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
    <!-- Swiper Carousel -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.4.1/js/swiper.min.js"></script>
    <script>
        var basePath = ''
    </script>
    <!-- Swiper script -->	
	<script>
	$(function(){
	    new Swiper('.swiper-container',{
	    	// 방향: vertical 수직, horizontal 수평, default: horizontal
	        direction: 'horizontal',
	        // 드래그 기능 true/false
	        debugger: true,
	        // 무한 반복 기능 true/false
	        loop: true,
	        // 마지막 슬라이드 -> 첫슬라이드 자연스러운 반복 기능
	        loopAdditionalSlides: 0,
	        // 슬라이드간 간격
	        spaceBetween: 20,
	        // 한번에 보여 줄 슬라이드 개수
	        slidesPerView: 3,
	    	//센터모드
	        centeredSlides: true, 
	        // 자동 스크롤링
	        autoplay: {
	            // 1000: 1초
	            delay: 2500,
	            disableOnInteraction: false,
	        },
	        
	    });
	})
	</script>
    <!-- Main Theme JS file    -->
    <script src="<%=path %>/resources/js/theme.js"></script>
</body>

</html>