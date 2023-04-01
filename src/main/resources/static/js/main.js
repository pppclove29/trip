function login() {
  let id = document.getElementById("name").value;
  let password = document.getElementById("email").value;

  const user = JSON.parse(localStorage.getItem("user"));
  if (user.id && id && user.password && password && id === user.id && password === user.password) {
    alert("로그인 성공 !");
    document.querySelector(".login-btn").style.display = "none";
    document.querySelector(".join-btn").style.display = "none";
    document.querySelector(".logout-btn").style.display = "block";
    document.querySelector(".mypage-btn").style.display = "block";
  } else {
    alert("로그인 실패 !");
  }
}

// 로그아웃
function logout() {
  document.querySelector(".login-btn").style.display = "block";
  document.querySelector(".join-btn").style.display = "block";
  document.querySelector(".logout-btn").style.display = "none";
  document.querySelector(".mypage-btn").style.display = "none";
}

function regist() {
  let id = document.getElementById("id").value;
  let password = document.getElementById("password").value;
  let name = document.getElementById("phone").value;
  let email = document.getElementById("message").value;

  if (!id || !password || !name || !email) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
    const user = {
      id: id,
      password: password,
      name: name,
      email: email,
    };
    console.log(user);
    localStorage.setItem("user", JSON.stringify(user));
    alert("사용자 등록 성공!");
  }
}
