<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body id="page-top overflow:auto">
    <!-- Navigation-->
    <%@include file="/include/header.jsp" %>
    
    <!-- Mashead header-->
    <header class="masthead">
      <div class="container px-5">
        <div class="row gx-5 align-items-center">
          <div class="col">
            <!-- Mashead text and app badges-->
            <div class="mb-5 mb-lg-0 text-center text-lg-start">
              <h1 class="display-1 lh-1 mb-3">Search Place</h1>
              <p class="lead fw-normal text-muted mb-5">Make a special moment</p>
            </div>
          </div>
        </div>
      </div>
    </header>

    <div class="container overflow-auto">
      <div class="row">
        <div class="col">
          <!-- <div class="alert alert-success mt-3 text-center fw-bold" role="alert">전국 관광지 정보</div> -->
          <!-- 관광지 검색 start -->
          <form class="d-flex my-3" onsubmit="return false;" role="search">
            <select id="search-area" class="form-select me-2">
              <option value="0" selected>검색 할 지역 선택</option>
            </select>
            <select id="search-content-id" class="form-select me-2">
              <option value="0" selected>관광지 유형</option>
              <option value="12">관광지</option>
              <option value="14">문화시설</option>
              <option value="15">축제공연행사</option>
              <option value="25">여행코스</option>
              <option value="28">레포츠</option>
              <option value="32">숙박</option>
              <option value="38">쇼핑</option>
              <option value="39">음식점</option>
            </select>
            <input
              id="search-keyword"
              class="form-control me-2"
              type="search"
              placeholder="검색어"
              aria-label="검색어"
            />
            <button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
          </form>
          <!-- kakao map start -->
          <div id="map" class="my-4" style="width: 100%; height: 500px"></div>
          <!-- kakao map end -->
          <div class="row">
            <table class="table table-striped" style="display: none">
              <thead>
                <tr>
                  <th>대표이미지</th>
                  <th>관광지명</th>
                  <th>주소</th>
                  <th>위도</th>
                  <th>경도</th>
                </tr>
              </thead>
              <tbody id="trip-list"></tbody>
            </table>
          </div>
          <!-- 관광지 검색 end -->
        </div>
      </div>
    </div>

	<%@include file="/include/footer.jsp" %>

    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/place.js"></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=21f00fce1faf40a4d24c1144e0b4039c&libraries=services,clusterer,drawing"
    ></script>
    <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
  </body>