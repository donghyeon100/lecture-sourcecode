/* 두 수가 같은지 비교 */
const cv1 = document.getElementById("compareValue1"); // input
const cv2 = document.getElementById("compareValue2"); // input
const result1 = document.getElementById("result1"); // span

function compareFn1(){
  const v1 = cv1.value;
  const v2 = cv2.value;

  result1.innerText = (v1 == v2);
}



/* A > B 초과 비교 */
const cv3 = document.getElementById("compareValue3"); // input
const cv4 = document.getElementById("compareValue4"); // input
const result2 = document.getElementById("result2"); // span

function compareFn2(){

  // 문자열 비교 시 원하는 결과가 출력되지 않음!
  // -> 숫자로 변환
  const v3 = Number(cv3.value);
  const v4 = Number(cv4.value);

  console.log(v3, typeof v3,  v4, typeof v4);

  result2.innerText = (v3 > v4);
}


/* 배수 확인 */
const input3_1 = document.getElementById("input3_1");
const input3_2 = document.getElementById("input3_2");
const result3  = document.getElementById("result3");

function checkFn3(){
  const v1 = Number(input3_1.value);
  const v2 = Number(input3_2.value);

  /* A가 B 의 배수 ==  A를 B로 나눴을 때 나머지가 0 */
  // result3.innerText = v1 + "은/는 " + v2 + "의 배수 : " + (v1 % v2 == 0);

  /* 백틱(`)을 이용한 문자열 작성하기 */

  // - 문자열 전체를 백틱(`)으로 감싼 후
  //   변수, 연산결과 등이 출력되는 자리에
  //   ${변수명} 또는 ${연산식} 을 작성
  
  result3.innerText = `${v1}은/는 ${v2}의 배수 : ${v1 % v2 == 0}`;

}


/* 입력된 값 만큼 누적/차감 */

let count4 = 0;
const input4 = document.getElementById("input4");
const result4 = document.getElementById("result4");

// 차감
function minus4(){
  const value = Number(input4.value);
  count4 -= value;
  result4.innerText = count4; 
}

// 누적
function plus4(){
  const value = Number(input4.value);
  count4 += value;
  result4.innerText = count4; 
}


/* 논리 연산자 확인하기 */
function checkFn5(){

  // AND
  const bool1 =  (104 >= 100)  &&  (104 % 2 == 0);
  console.log(`104는 100 이상의 수 이면서 짝수인가? ${bool1}`);

  const bool2 =  (50 <= 70)  &&  (50 % 4 ==0);
  console.log(`50은 70 이하이고, 4의 배수인가? ${bool2}`);

  const bool3 =  1 <= 13  &&  13 <= 10;
  console.log(`13은 1 부터 10 사이의 수가 맞는가? ${bool3}`);

  // -----------------------------------------------------

  // OR

  const bool4 = (4 > 10) || (4 % 2 == 0);
  console.log(`4는 10을 초과 하거나, 짝수인가? ${bool4}`);

  const bool5 = 50 <= 0 || 50 >= 40;
  console.log(`50은 0 이하 또는 40 이상인가? {bool5}`);

  // ------------------------------------------------------

  // NOT

  console.log(`!true = ${!true}`); // false
  console.log(`!false = ${!false}`); // true

  const bool6 = false;
  console.log(  !(bool6 != true)   ); // false

}


/* 삼항 연산자 - 로그인 하기 */
const inputId = document.getElementById("inputId");
const inputPw = document.getElementById("inputPw");

function login(){

  // ID, PW input에 작성된 값 얻어오기
  const id = inputId.value;
  const pw = inputPw.value;

  const success = "로그인 성공!";
  const fail = "아이디 또는 비밀번호가 일치하지 않습니다.";

  // alert에 출력될 내용을 저장할 변수 선언
  const message 
    = (id == 'member01') && (pw == 'pass01!') ? success : fail ;

  alert(message);
}
