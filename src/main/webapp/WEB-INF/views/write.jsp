<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body id="page-top overflow:auto">

  <!-- Header -->
  <%@include file="include/header.jsp" %>
  <body id="page-top">
   <div
            class="mt-5 pt-5 d-flex justify-content-center align-content-center"
        >
            <form class="row border-0 w-50 mt-5" id="contactForm" method="POST" action="${root}/posts">
                <h2 class="mb-5 text-center display-4 lh-1">Write</h2>
                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <input
                            class="form-control"
                            id="id"
                            name="title"
                            type="text"
                            placeholder="Enter your name..."
                            data-sb-validations="required"
                        />
                        <label for="title">Title</label>
                        
                    </div>
                    <!-- Message input-->
                    <div class="form-floating mb-3">
                        <textarea
                            class="form-control"
                            id="message"
                            name="contents"
                            type="text"
                            placeholder="Enter your message here..."
                            style="height: 10rem"
                            data-sb-validations="required"
                        ></textarea>
                        <label for="message">Contents</label>
                        <div
                            class="invalid-feedback"
                            data-sb-feedback="message:required"
                        >
                            A message is required.
                        </div>
                    </div>
                    <!-- Submit success message-->
                    <!---->
                    <!-- This is what your users will see when the form-->
                    <!-- has successfully submitted-->
 
                    <!-- Submit Button-->
                    <div class="d-grid">
                    <input type="hidden" name="action" value="">
                        <button
                            class="btn btn-primary rounded-pill btn-lg "
                            id="submitButton"
                            type="submit"
                        >
                            SUBMIT
                        </button>
                    </div>
            </form>
        </div>
        <!--조인 끝-->
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="../../../resources/static/js/scripts.js"></script>
        <script type="text/javascript" src="../../../resources/static/js/main.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
  