<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/views/common/header.jsp"%>
<%
String prevBoCat = request.getParameter("boardCat");
%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

 <section class="hero-home2 dark-overlay mb-5"><img class="bg-image" src="<%=path %>/resources/resources/images/back2.jpg" alt="">
        <div class="container py-7">
            <div class="overlay-content text-center text-white">
                <h1 class="display-3 text-serif fw-bold text-shadow mb-0">Escape the city today</h1>
            </div>
        </div>
    </section>

    <section class="py-4">
        <div class="container">
            <div class="row col-lg-12">
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
                <div class="col-lg-9">
                    <div>
                        <h3> 게시판 작성</h3>
                        <hr class="my-4">
                    </div>
                    <form action="<%=path %>/board/write" method="POST">
                    <input type="hidden" name="writer" value="<%=loginUser.getUser_id()%>">
                    <input type="hidden" name="prevBoCat" value="<%=prevBoCat%>">
                    <div class="form-group">
                        <div class="form-group mb-2">
                            <select class="selectpicker" title="지역" name="boardCat">
                                
                                <option value="1" selected>
                                    공지
                                </option>
                                <option value="2">
                                    자유
                                </option>
                                <option value="3">
                                    동해
                                </option>
                                <option value="4">
                                    서해
                                </option>
                                <option value="5">
                                    남해
                                </option>
                                <option value="6">
                                    제주
                                </option>

                        </select>
                        </div>
                        <input type="text" class="form-control mb-3" id="title" placeholder="제목" name="title" maxlength="100" required="required" pattern=".{4,100}">
                    </div>
                        <textarea id="summernote" name="content"></textarea>

                    <div class="row mt-3">
                             <button class="btn btn-primary board-btn text-white" style="display:inline;float:right;margin-left:90.5%" type="submit">
                            작성
                        </button>
                    </div>
                    </form>
                </div>

            </div>

        </div>

    </section>

    <script>
        $('#summernote').summernote({
            placeholder: 'Hello stand alone ui',
            tabsize: 2,
            height: 500,

            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'underline', 'clear']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'video']],
                ['view', ['fullscreen', 'codeview', 'help']]
            ]
        });

        $('#summernote').change
    </script>

<%@include file = "/views/common/footer.jsp"%>