<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body id="page-top overflow:auto">

  <!-- Header -->
  <%@include file="include/header.jsp" %>
  <body id="page-top">
   <div
            class="mt-5 pt-5 d-flex justify-content-center align-content-center"
        >

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
                       <form action="http://localhost:8080/posts" method="post" enctype="multipart/form-data">
                           <div class="input-group">
                               <div class="custom-file my-2">
                                   <input
                                           type="file"
                                           class="custom-file-input"
                                           id="inputGroupFile02"
                                           name="images"
                                           accept="image/png, image/jpeg, image/jpg"
                                           multiple
                                   />
                               </div>
                           </div>
                           <div class="mb-3">
                               <label for="recipient-name" class="col-form-label">장소:</label>
                               <input type="text" class="form-control" id="recipient-name" name="title" />
                           </div>
                           <div class="mb-3">
                               <label for="message-text" class="col-form-label">계획:</label>
                               <textarea class="form-control" id="message-text" name="content"></textarea>
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
  