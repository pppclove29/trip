<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body id="page-top">
  <!-- Navigation-->
  <%@include file="/include/header.jsp" %>
  <div class="container mt-5 p-5">
            <div class="row">
                <div class="col md-8">
                    <div class="chat_container">
                        <div class="job-box">
                            <div class="job-box-filter">
                                <div class="row">
                                    <div class="col-md-6 col-sm-6">
                                        <label
                                            >Show
                                            <select
                                                name="datatable_length"
                                                class="form-control input-sm"
                                            >
                                                <option value="10">10</option>
                                                <option value="25">25</option>
                                                <option value="50">50</option>
                                                <option value="100">100</option>
                                            </select>
                                            entries</label
                                        >
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <div
                                            class="filter-search-box text-right"
                                        >
                                            <button
                                                type="button"
                                                class="btn btn-outline-dark"
                                                onclick="location.href='write.jsp'"
                                            >
                                                Write
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="inbox-message">
                                <ul>
                                    <li>
                                        <a href="#">
                                            <div class="message-avatar">
                                                <img
                                                    src="https://bootdey.com/img/Content/avatar/avatar6.png"
                                                    alt=""
                                                />
                                            </div>
                                            <div class="message-body">
                                                <div
                                                    class="message-body-heading"
                                                >
                                                    <h5>
                                                        관리자<span
                                                            class="important"
                                                            >Event</span
                                                        >
                                                    </h5>
                                                    <span>7 hours ago</span>
                                                </div>
                                                <p>
                                                    [이벤트(~3/20)] 마음에 드는
                                                    장소를 hot place에
                                                    공유해주세요 !
                                                </p>
                                            </div>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">
                                            <div class="message-avatar">
                                                <img
                                                    src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                                    alt=""
                                                />
                                            </div>
                                            <div class="message-body">
                                                <div
                                                    class="message-body-heading"
                                                >
                                                    <h5>
                                                        관리자
                                                        <span class="unread"
                                                            >Update</span
                                                        >
                                                    </h5>
                                                    <span>7 hours ago</span>
                                                </div>
                                                <p>
                                                    [안내] version 1.1.0
                                                    업데이트 안내 드립니다.
                                                </p>
                                            </div>
                                        </a>
                                    </li>

                                    <li>
                                        <p>
                                            <a
                                                class=""
                                                data-bs-toggle="collapse"
                                                href="#collapseExample1"
                                                role="button"
                                                aria-expanded="false"
                                                aria-controls="collapseExample"
                                            >
                                                <div class="message-avatar">
                                                    <img
                                                        src="./assets/img/yeosu.jpg"
                                                        alt=""
                                                    />
                                                </div>
                                                <div class="message-body">
                                                    <div
                                                        class="message-body-heading"
                                                    >
                                                        <h5>
                                                            2️박 3일 여수여행
                                                            후기
                                                        </h5>
                                                        <span>
                                                            3 minutes ago</span
                                                        >
                                                    </div>
                                                    <p></p>
                                                </div>
                                            </a>
                                        </p>
                                        <div
                                            class="collapse"
                                            id="collapseExample1"
                                        >
                                            <div class="card card-body row">
                                                <div
                                                    class="message-body-heading col-4"
                                                >
                                                    전남 여수는 겨울에도 기온이
                                                    영하로 내려가지 않아서
                                                    여행하기에 굉장히 좋은
                                                    지역인 것 같아요.<br />
                                                    또 바다로 둘러싸여 있어서
                                                    어느 쪽에서 보아도 경치가
                                                    굉장히 탁월하였고 낮에는
                                                    여수의 아름다운 섬을
                                                    둘러보고, 밤에는 황홀한
                                                    야경을 보는 것으로 일정을
                                                    마무리 하여 행복한
                                                    여행이었습니다.
                                                </div>
                                                <div class="row float-end my-3">
                                                  <!--  --><form id=modifyForm" method="POST" action="${root}/board">
				    								<!--  --><input type="hidden" name="action" value="modify">
                                                    <button
                                                        type="button"
                                                        class="btn btn-outline-dark col-2 mx-1"
                                                    >
                                                        modify
                                                    </button>
                                                 <!--  -->   </form>
                                                 <!--  -->    <form id="deleteForm" method="POST" action="${root}/board">
                                                 <!--  -->    <input type="hidden" name="action" value="delete">
                                                    <button
                                                        type="button"
                                                        class="btn btn-outline-dark col-2"
                                                    > 
                                                        delete
                                                    </button>
                                                 <!--  -->   </form>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>

  
  