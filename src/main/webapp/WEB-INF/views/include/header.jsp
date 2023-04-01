<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
 <meta charset="utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
 <meta name="description" content="" />
 <meta name="author" content="" />
 <title>Enjoy Trip</title>
 <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
 <!-- Bootstrap icons-->
 <link
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
   rel="stylesheet"
 />
 <!-- Google fonts-->
 <link rel="preconnect" href="https://fonts.gstatic.com" />
 <link
   href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap"
   rel="stylesheet"
 />
 <link
   href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap"
   rel="stylesheet"
 />
 <link
   href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap"
   rel="stylesheet"
 />
 <!-- Core theme CSS (includes Bootstrap)-->
 <link href="css/styles.css" rel="stylesheet" />
 <link href="css/style.css" rel="stylesheet" />
 
<nav class="navbar navbar-expand-lg navbar-light fixed-top shadow-sm" id="mainNav">
  <div class="container px-5">
    <a class="navbar-brand fw-bold" href="${root}/index.jsp">Enjoy Trip</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarResponsive"
      aria-controls="navbarResponsive"
      aria-expanded="false"
      aria-label="Toggle navigation">
      Menu
      <i class="bi-list"></i>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ms-auto me-4 my-3 my-lg-0">
        <li class="nav-item"><a class="nav-link me-lg-3" href="place.jsp">PLACE</a></li>
        <li class="nav-item"><a class="nav-link me-lg-3" href="${root}/plan?action=list">PLAN</a></li>
        <li class="nav-item"><a class="nav-link me-lg-3" href="${root}/hotplace?action=view">HOT</a></li>
        <li class="nav-item"><a class="nav-link me-lg-3" href="board.jsp">SHARE INFO</a></li>
      </ul>
      <c:if test="${empty userinfo}">
      <button
        class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0 login-btn"
        data-bs-toggle="modal"
        data-bs-target="#feedbackModal">
        <span class="d-flex align-items-center">
          <i class="bi-chat-text-fill me-2"></i>
          <span class="small">LOGIN</span>
        </span>
      </button>
      <button
        class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0 mx-2 join-btn"
        data-bs-toggle="modal"
        data-bs-target="#joinModal">
        <span class="d-flex align-items-center">
          <i class="bi-chat-text-fill me-2"></i>
          <span class="small">JOIN</span>
        </span>
      </button>
      </c:if>
      <c:if test="${not empty userinfo}">
      <form method="GET" action="${root}/user">
      <input type="hidden" name="action" value="logout">
        <button
        class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0 mx-2 ">
        <span class="d-flex align-items-center">
          <i class="bi-chat-text-fill me-2"></i>
          <span class="small">LOGOUT</span>
        </span>
      </button>
      </form>
      <button
        class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0 mx-2 "
        data-bs-toggle="modal"
        data-bs-target="#mypageModal">
        <span class="d-flex align-items-center">
          <i class="bi-chat-text-fill me-2"></i>
          <span class="small">MYPAGE</span>
        </span>
      </button>
      </c:if>
    </div>
  </div>
</nav>

  <!-- login Modal-->
  <div
    class="modal fade"
    id="feedbackModal"
    tabindex="-1"
    aria-labelledby="feedbackModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header bg-gradient-primary-to-secondary p-4">
          <h5 class="modal-title font-alt text-white" id="feedbackModalLabel">Log-in</h5>
          <button
            class="btn-close btn-close-white"
            type="button"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body border-0 p-4">
          <form id="contactForm" method="POST" action="${root}/user">
            <!-- Name input-->
            <div class="form-floating mb-3">
              <input
                class="form-control"
                name="userid"
                type="text"
                placeholder="Enter your name..."
                data-sb-validations="required"
              />
              <label for="ID">ID</label>
            </div>
            <!-- Password input-->
            <div class="form-floating mb-3">
              <input
                class="form-control"
                name="userpw"
                type="password"
                placeholder="name@example.com"
                data-sb-validations="required,email"
              />
              <label for="PASSWORD">PASSWORD</label>
              </div>
            </div>
            <!-- Submit Button-->
            <div class="d-grid">
             <input type="hidden" name="action" value="login">
              <button
                class="btn btn-primary rounded-pill btn-lg"
                id="submitButton"
                type="submit"
              >
                LOGIN
              </button>
            </div>
            <div class="d-grid mt-2">
              <button
                class="btn btn-primary rounded-pill btn-lg"
                data-bs-toggle="modal"
                data-bs-target="#joinModal"
              >
                <span>Forget ID/PW</span>
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!--조인-->
  <div
    div
    class="modal fade"
    id="joinModal"
    tabindex="-1"
    aria-labelledby="joinModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header bg-gradient-primary-to-secondary p-4">
          <h5 class="modal-title font-alt text-white" id="joinModalLabel">JOIN</h5>
          <button
            class="btn-close btn-close-white"
            type="button"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body border-0 p-4">
          <form id="contactForm" method="POST" action="${root}/user?action=join">
            <!-- Name input-->
            <div class="form-floating mb-3">
              <input
              class="form-control"
              name="userid"
              type="text"
              placeholder="Enter your name..."
              data-sb-validations="required"
              />
              <label for="ID">ID</label>
              <div class="invalid-feedback" data-sb-feedback="name:required">ID is required.</div>
            </div>
            <!-- Email address input-->
            <div class="form-floating mb-3">
              <input
              class="form-control"
              name="userpw"
              type="password"
              placeholder="name@example.com"
              data-sb-validations="required,email"
              />
              <label for="PASSWORD">Password</label>
            </div>
            <!-- Phone number input-->
            <div class="form-floating mb-3">
              <input
                class="form-control"
                name="phone"
                type="tel"
                placeholder="(123) 456-7890"
                data-sb-validations="required"
              />
              <label for="phone">Phone number</label>
              <div class="invalid-feedback" data-sb-feedback="phone:required">
                A phone number is required.
              </div>
                </div>
                <!-- Message input-->
            <div class="form-floating mb-3">
              <textarea
                class="form-control"
                name="email"
                type="text"
                placeholder="Enter your message here..."
                style="height: 10rem"
                data-sb-validations="required"
              ></textarea>
              <label for="email">Email</label>
              <div class="invalid-feedback" data-sb-feedback="message:required">
                A message is required.
              </div>
            </div>
            <!-- Submit Button-->
            <div class="d-grid">
              <input type="hidden" name="action" value="join">
              <button
                class="btn btn-primary rounded-pill btn-lg"
                id="submitButton"
                type="submit"
              >
                submit
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!--조인 끝-->
  <!--마이페이지 -->
  <div
    div
    class="modal fade"
    id="mypageModal"
    tabindex="-1"
    aria-labelledby="joinModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header bg-gradient-primary-to-secondary p-4">
          <h5 class="modal-title font-alt text-white" id="mypageModalLabel">MY PAGE</h5>
          <button
            class="btn-close btn-close-white"
            type="button"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body border-0 p-4">
          <form id="contactForm" method="POST" action="${root}/user">
            <!-- Name input-->
            <div class="form-floating mb-3">
              <input
                class="form-control"
                id="userid"
                name="userid"
                type="text"
                placeholder="Enter your name..."
                data-sb-validations="required"
              />
              <label for="ID">ID</label>
            </div>
            <div class="form-floating mb-3">
              <input
                class="form-control"
                id="userpw"
                name="userpw"
                type="password"
                placeholder="name@example.com"
                data-sb-validations="required,email"
              />
              <label for="PASSWORD">Password</label>
             
            </div>
            <!-- Phone number input-->
            <div class="form-floating mb-3">
              <input
                class="form-control"
                id="phone"
                type="tel"
                placeholder="(123) 456-7890"
                data-sb-validations="required"
              />
              <label for="phone">Phone number</label>
              <div class="invalid-feedback" data-sb-feedback="phone:required">
                A phone number is required.
              </div>
            </div>
            <!-- Message input-->
            <div class="form-floating mb-3">
              <textarea
                class="form-control"
                id="email"
                type="text"
                placeholder="Enter your message here..."
                style="height: 10rem"
                data-sb-validations="required"
              ></textarea>
              <label for="message">Email</label>
             
            </div>
            <!-- Submit success message-->
            <!---->
            <!-- This is what your users will see when the form-->
            <!-- has successfully submitted-->

            <!-- Submit Button-->
            
            <div class="d-grid">
             <input type="hidden" name="action" value="modify">
              <button
                class="btn btn-primary rounded-pill btn-lg "
                id="submitButton"
                type="submit"
              >
                SUBMIT
              </button>
            </div>
            </form>
            <form id="logoutForm" method="POST" action="${root}/user">
				  <div class="d-grid mt-3">
				    <input type="hidden" name="action" value="signout">
				    <button class="btn btn-primary rounded-pill btn-lg" type="submit">
				      SIGNOUT
				    </button>
				  </div>
			</form>
        </div>
      </div>
    </div>
  </div>
  
  <!-- Bootstrap core JS-->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <script src="js/scripts.js"></script>
  <script type="text/javascript" src="js/main.js"></script>
  <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
  <!-- 마이페이지 끝-->

  <%-- request 객체에 msg가 들어있을 때 해당 내용 알림창 띄우기 --%>
<script>
	let msg="${msg}";
	if(msg) alert(msg);
</script>