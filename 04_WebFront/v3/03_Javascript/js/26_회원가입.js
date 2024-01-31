// 유효성 검사 여부를 체크해두는 객체 생성

// JS 객체 : { K : V,  K : V  , ....}

// JS 객체 다루는 방법
// checkList["KEY"];  (GET)
// checkList["KEY"] = true; (SET)

const checkList = {
    "inputId": false,
    "inputPw": false,
    "inputPwCheck": false,
    "inputName": false
};


const signupForm = document.signupForm;
const inputId = document.getElementById("inputId");
const inputPw = document.getElementById("inputPw");
const inputPwCheck = document.getElementById("inputPwCheck");
const inputName = document.getElementById("inputName");

// 입력 요소 모두 얻어오기(성별 제외)
const inputList = document.querySelectorAll(".signup-input");

// ---------------------------------------------------------------------

/* 아이디 유효성 검사 */
inputId.addEventListener("input", e => {

    // 양쪽 공백을 제거한 입력 값을 얻어와 val에 저장
    const val = inputId.value.trim();
    const span = e.target.nextElementSibling.nextElementSibling;

    inputId.value = val; // 공백이 제거된 값을 input 값으로 대입

    if (val.length === 0) { // 입력된 값이 없을 경우
        span.innerText = "영어,숫자,-,_ 6~16글자 사이";
        span.classList.remove("check");
        span.classList.remove("error");

        // 유효성 검사 여부 저장 객체에서
        // "inputId"의 값을 false 변경 (유효하지 않다를 뜻함)
        checkList["inputId"] = false;
        return;
    }

    // 정규표현식 객체 생성
    const regEx = /^[A-Za-z\d\-\_]{6,16}$/;

    if (regEx.test(val)) { // 유효한 경우
        span.innerText = "유효한 아이디 형식입니다";
        span.classList.add("check");
        span.classList.remove("error");

        checkList["inputId"] = true;

    } else {
        span.innerText = "아이디가 유효하지 않습니다";
        span.classList.add("error");
        span.classList.remove("check");

        checkList["inputId"] = false;
    }
});


/* 비밀번호 유효성 검사 */
inputPw.addEventListener("input", e => {

    const value = e.target.value;
    const span = e.target.nextElementSibling.nextElementSibling;


    if (value.length === 0) { // 입력된 값이 없을 경우
        span.innerText = "영어,숫자,!@#$%^&* 8~20글자 사이";
        span.classList.remove("check", "error");
        checkList["inputPw"] = false;
        
        checkPwFn();

        return;
    }

    // 정규표현식 객체 생성
    const regExp = /^[A-Za-z\d\!\@\#\$\%\^\&\*]{8,20}$/;

    if (regExp.test(value)) { // 유효한 경우
        span.innerText = "유효한 비밀번호 형식입니다";
        span.classList.add("check");
        span.classList.remove("error");

        checkList["inputPw"] = true;

    } else {
        span.innerText = "비밀번호가 유효하지 않습니다";
        span.classList.add("error");
        span.classList.remove("check");

        checkList["inputPw"] = false;
    }

    checkPwFn();

});



// 비밀번호, 비밀번호 확인 같은지 체크하는 함수
function checkPwFn() {
    const span = inputPwCheck.nextElementSibling.nextElementSibling;

    // 비밀번호, 비밀번호 확인 값 얻어와 변수에 저장
    const pwVal = inputPw.value.trim();
    const checkVal = inputPwCheck.value.trim();

    if (pwVal.length == 0 || checkVal.length == 0) { // 비밀번호 미입력 시
        span.innerText = "비밀번호를 먼저 입력 해주세요";
        span.classList.remove("check");
        span.classList.remove("error");

        checkList["inputPwCheck"] = false;

        return;
    }

    if (pwVal === checkVal) {
        span.innerText = "비밀번호 일치";
        span.classList.add("check");
        span.classList.remove("error");

        checkList["inputPwCheck"] = true;

    } else {
        span.innerText = "비밀번호 불일치";
        span.classList.add("error");
        span.classList.remove("check");

        checkList["inputPwCheck"] = false;

    }
}


// /* 비밀번호 확인 유효성 검사 */
inputPwCheck.addEventListener('input',checkPwFn);



/* 이름 유효성 검사 */
inputName.addEventListener("input", e => {

    // 양쪽 공백을 제거한 입력 값을 얻어와 val에 저장
    const val = inputName.value.trim();
    const span = e.target.nextElementSibling.nextElementSibling;

    inputName.value = val; // 공백이 제거된 값을 input 값으로 대입

    if (val.length === 0) { // 입력된 값이 없을 경우
        span.innerText = "한글 2~15(단자음, 단모음 제외)";
        span.classList.remove("check");
        span.classList.remove("error");

        checkList["inputName"] = false;

        return;
    }

    // 정규표현식 객체 생성
    const regEx = /^[가-힣]{2,15}$/;

    if (regEx.test(val)) { // 유효한 경우
        span.innerText = "유효한 이름 형식입니다";
        span.classList.add("check");
        span.classList.remove("error");

        checkList["inputName"] = true;

    } else {
        span.innerText = "이름이 유효하지 않습니다";
        span.classList.add("error");
        span.classList.remove("check");

        checkList["inputName"] = false;
    }
});


/* form태그 제출 시 */
signupForm.addEventListener('submit', e => {

    // HTML 요소가 가지고 있는 기본 이벤트를 막다(제거)
    // e.preeinputventDefault();

    /* 객체 전용 향상된 for문 : for(let key in 객체명)
      -> 객체가 가지고 있는 K를 순서대로 하나씩 key에 대입
    */

    for (let key in checkList) {
        // console.log(key);

        if (!checkList[key]) { // 유효하지 않은 입력이 있을 경우

            let str;
            switch (key) {
                case "inputId": str = "아이디가 "; break;
                case "inputPw": str = "비밀번호가 "; break;
                case "inputPwCheck": str = "비밀번호 확인이 "; break;
                case "inputName": str = "이름이 "; break;
            }

            alert(str + "유효하지 않습니다");
            e.preventDefault(); // form 제출 막기

            document.getElementById(key).focus();
            return;
        }
    } // for in 끝

    // 성별 선택 여부 검사

    // 성별이 선택되지 않았을 때
    if (document.querySelector("[name='gender']:checked") == null) {
        alert("성별을 선택해주세요");
        e.preventDefault();
        document.querySelector("[name='gender']").focus();
    }

});