window.onload = function () {

  // dummy data
  let localName = ["부산", "경주", "서울", "대구", "대전", "여수"];
  let imgPath = ["assets/img/busan.jpg", "assets/img/gyeongju.jpg", "assets/img/seoul.jpg", "assets/img/daegu.jpg", "assets/img/daejeon.jpg", "assets/img/yeosu.jpg"];
  let lineIntro =
    ["부산가서 해운대보고 요트탄 후, 광안리 산책할 계획!!",
      "올해는 꼭 애들이랑 다시 경주 여행 가야지 !!!",
      "남산타워가 보이는 서울의 야경은 계속 봐도 안 질려",
      "대구도 꼭 가보고 싶어.. 맛집 많다던데 ㅎㅎ",
      "대전하면 역시 성심당이지,, 튀김 소보로 먹고 싶다 !",
      "여수 밤바다 들으면서 여수 밤바다 걸어야지 !!",
    ];

  let tripList = ``;
  for (let i = 0; i < localName.length; i++){
    tripList
      += `<div class="card col-3 my-3 mx-3">
          <img
            class="card-img-top my-2"
            src="${imgPath[i]}"
            alt="load image error"
            style="width: 100%; height: 100%"
          />
          <div class="card-body">
              <h4>${localName[i]}</h4>
              <p class="card-text">${lineIntro[i]}</p>
              <a href="#" class="btn border">detail</a>
            </div>
          </div>`
  }
  document.getElementById("card-list").innerHTML = tripList;
}