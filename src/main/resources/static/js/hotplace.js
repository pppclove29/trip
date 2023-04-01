// window.onload = function () {

//     // dummy data
//     let hotName = ["협재해변", "남산타워", "경복궁"];
//     let imgPath = ["assets/img/hotplace/hyeopjae.jpg", "assets/img/hotplace/Ntower.jpg", "assets/img/hotplace/gyeongbokgung.jpg"];
//     let lineIntro =
//     ["제주도 협재해면의 애매랄드 빛 풍경이 너무 아름답지 않나요?",
//       "남산타워의 낮과 밤은 다른 의미로 정말 모두 아름다워요!",
//       "조선 왕조 500년의 아름다움을 느껴보세요!"];
    
//     let hotList = ``;
//     for (let i = 0; i < hotName.length; i++){
//         hotList
//             +=
//             `<div class="carousel-item active">
//                 <img
//                 src="${imgPath[i]}"
//                 class="d-block w-100"
//                 alt="이미지 준비 중"
//                 />
//                 <div class="carousel-caption d-none d-md-block">
//                 <h5>${hotName[i]}</h5>
//                 <p>${lineIntro[i]}</p>
//                 </div>
//             </div>`
//     }
//     document.getElementById("hot-carousel-inner").innerHTML = hotList;
    
// }