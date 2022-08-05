<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>

<section class="py-5">
        <div class="container">
            <!-- Breadcrumbs -->
            <ol class="breadcrumb ps-0  justify-content-start">
                <li class="breadcrumb-item"><a href="main.html">Home</a></li>
                <li class="breadcrumb-item active">개인정보</li>
            </ol>
            <h1 class="hero-heading mb-0">개인정보</h1>
            <p class="text-muted mb-5">개인정보를 확인 및 수정하실 수 있습니다.</p>
            <div class="row">
                <div class="col-lg-7 mb-5 mb-lg-0">
                    <div class="text-block">
                        <div class="row mb-3">
                            <div class="col-sm-9">
                                <h5>개인정보 상세</h5>
                            </div>
                            <div class="col-sm-3 text-end">
                                <button class="btn btn-link ps-0 text-primary collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#personalDetails" aria-expanded="false" aria-controls="personalDetails">업데이트</button>
                            </div>
                        </div>
                        <p class="text-sm text-muted">
                            <i class="fa fa-id-card fa-fw me-2"></i>John Doe<br>
                            <i class="fa fa-birthday-cake fa-fw me-2"></i>06/22/1980<br>
                            <i class="fa fa-envelope-open fa-fw me-2"></i>john.doe@directory.com <span class="mx-2"> | </span>
                            <i class="fa fa-phone fa-fw me-2"></i>010-1234-1234
                        </p>
                        <div class="collapse" id="personalDetails">
                            <form action="#">
                                <div class="row pt-4">
                                    <div class="mb-4 col-md-6">
                                        <label class="form-label" for="name">이름</label>
                                        <input class="form-control" type="text" name="name" id="name" value="John Doe">
                                    </div>
                                    <div class="mb-4 col-md-6">
                                        <label class="form-label" for="date">생일</label>
                                        <input class="form-control" type="text" name="date" id="date" value="06/22/1980">
                                    </div>
                                    <div class="mb-4 col-md-6">
                                        <label class="form-label" for="email">이메일</label>
                                        <input class="form-control" type="email" name="email" id="email" value="john.doe@directory.com">
                                    </div>
                                    <div class="mb-4 col-md-6">
                                        <label class="form-label" for="phone">핸드폰번호</label>
                                        <input class="form-control" type="text" name="phone" id="phone" value="010-1234-1234">
                                    </div>
                                </div>
                                <button class="btn btn-outline-primary mb-4" type="submit">개인정보 저장</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 col-lg-4 ms-lg-auto">
                    <div class="card border-0 shadow">
                        <div class="card-header bg-primary-light py-4 border-0">
                            <div class="d-flex align-items-center">
                                <div>
                                    <h4 class="h6 subtitle text-sm text-primary">개인정보 활용에 대하여</h4>
                                </div>
                                <svg class="svg-icon svg-icon svg-icon-light w-3rem h-3rem ms-3 text-primary flex-shrink-0">
                    <use xlink:href="#identity-1"> </use>
                  </svg>
                            </div>
                        </div>
                        <div class="card-body p-4">
                            <p class="text-muted text-sm card-text">BADADA only releases contact information for hosts and guests <strong>after a reservation is confirmed</strong>.</p>
                            <p class="text-muted text-sm card-text">Also, we only keep personal information and don't use it. <strong>Don't worry</strong></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


<%@include file="/views/common/footer.jsp"%>