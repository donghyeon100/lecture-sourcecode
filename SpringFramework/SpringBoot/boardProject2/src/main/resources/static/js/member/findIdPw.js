const findIdBtn = document.querySelector("#findIdBtn");

findIdBtn.addEventListener("click", e => {
   const inputNickname = document.querySelector(".id-container input[name='inputNickname']");
   const inputTel = document.querySelector(".id-container input[name='inputTel']");

   if(inputNickname.value.trim().length === 0){
    alert("닉네임을 입력해 주세요");
    inputNickname.focus();
    return;
   }

   if(inputTel.value.trim().length === 0){
    alert("전화번호를 입력해 주세요");
    inputTel.focus();
    return;
   }

   fetch("/email/findId", {
      method: "POST",
      headers: {
         "Content-Type": "application/json"
      },
      body: JSON.stringify({
        inputNickname: inputNickname.value,
        inputTel: inputTel.value
      })
   })
   .then(res => res.text())``
   .then(email => {
        if(email == ""){
            alert("회원 정보가 일치하는 이메일이 존재하지 않습니다");
        }else{
            alert(email);
        }
   })
});