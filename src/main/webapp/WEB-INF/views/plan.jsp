<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<body id="page-top overflow:auto">

<!-- Header -->
<%@include file="include/header.jsp" %>

<!-- Mashead header-->
<header class="masthead">
    <div class="container px-5">
        <div class="row gx-5 align-items-center">
            <div class="col">
                <!-- Mashead text and app badges-->
                <div class="mb-5 mb-lg-0 text-center text-lg-start">
                    <h1 class="display-1 lh-1 mb-3">My Trip Plans</h1>
                    <p class="lead fw-normal text-muted mb-5">Make a special moment</p>
                </div>
            </div>
        </div>
    </div>
    <!-- create new hot place btn -->
    <div>
        <button
                class="btn border float-end me-5"
                type="button"
                class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#myModal"
        >
            <i style="font-size: 24px" class="fa">new</i>
        </button>
    </div>
</header>

<!-- 카드 -->
<c:if test="${not empty userinfo}">
    <div class="row justify-content-center my-4">
        <c:forEach var="plan" items="${plans}">
            <div class="card col-3 my-3 mx-3">
                <img class="card-img-top my-2" src="${plan.image}" alt="${plan.image}" style="width: 100%; height: 100%" />
                <div class="card-body">
                    <h4>${plan.title}</h4>
                    <p class="card-text">${plan.content}</p>
                        <%--             <a href="${root}/plan?action=detail&id=${plan.id}" class="btn border">detail</a> --%>
                    <a href="${root}/plan?action=delete&id=${plan.id}" class="btn border">delete</a>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>
<c:if test="${empty userinfo}">
    <h2 style="text-align: center; margin: 100px"> 먼저 로그인 해주세요 ! </h2>
</c:if>

<!-- new plan (modal) -->
<div class="modal" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Register new plan</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form action="http://localhost:8080/plans" method="post" enctype="multipart/form-data">
                    <div class="input-group">
                        <div class="custom-file my-2">
                            <input
                                    type="file"
                                    class="custom-file-input"
                                    id="inputGroupFile02"
                                    name="image"
                                    accept="image/png, image/jpeg, image/jpg"
                            />
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="recipient-name" class="col-form-label">장소:</label>
                        <input type="text" class="form-control" id="recipient-name" name="title" />
                    </div>
                    <div class="mb-3">
                        <label for="message-text" class="col-form-label">계획:</label>
                        <textarea class="form-control" id="message-text" name="contents"></textarea>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-light" data-bs-dismiss="modal">Regist</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="include/footer.jsp" %>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<!-- <script src="js/plan.js"></script> -->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
