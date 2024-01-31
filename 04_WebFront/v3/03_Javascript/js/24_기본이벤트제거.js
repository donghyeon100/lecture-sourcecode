/* a태그 기본 이벤트 제거 */
document.getElementById('move')
    .addEventListener("click", function (e) {

        // e : 이벤트 객체
        // prevent : 하지 못하게 막다
        // default : 기본

        e.preventDefault(); // HTML 요소의 기본 이벤트 막음(제거)

    });


/* form 태그 기본 이벤트 제거 */

// 방법 1. e.preventDefault()

// form태그가 제출 되었을 때
/* 
document.getElementById("login")
  .addEventListener("submit", function(e){
  // alert("제출 되었습니다.");
  e.preventDefault();
});
 */


/** 인라인 이벤트 모델 onsubmit 이용
* 사용자명과 비밀번호 입력이 비어있지 않은지 확인하여 폼을 유효성 검사합니다.
* @returns {boolean} - 폼이 유효하면 true, 그렇지 않으면 false를 반환합니다.
*/
function validateFn() {
    const id = document.getElementById("inputId");
    const pw = document.getElementById("inputPw");

    // 아이디 또는 비밀번호를 입력하지 않았을 때
    if (id.value.trim().length == 0 ||
        pw.value.trim().length == 0) {

        alert("아이디 또는 비밀번호를 입력해 주세요.");
        return false;
    }

    // 둘 다 입력한 경우
    return true;
}


// 방법 3. 일반 버튼 클릭으로 제출 막음

// #login-btn2 버튼이 클릭 되었을 때
const loginBtn = document.querySelector("#loginBtn");
loginBtn.addEventListener("click", function () {

    const id = document.getElementById("inputId");
    const pw = document.getElementById("inputPw");

    // 아이디, 비밀번호 둘다 입력했을 때
    if (id.value.trim().length == 0 ||
        pw.value.trim().length == 0) {

        alert("아이디 또는 비밀번호를 입력해 주세요.");
        /* document.form태그name속성 == 해당 form 요소 선택 */
        /* form요소.submit()  :  form 제출 */
        // 문서 내에서 name 속성 값이 loginForm인 form 태그를 제출 시킴

        return;
    }
    document.loginForm.submit();

});