/* 매개 변수, 전달 인자 */

const input1 = document.querySelector("#input1");
const btn1 = document.querySelector("#btn1");

// 2개의 값을 전달 받아 출력하는 함수
function print1(num, str){
  console.log(`${num} 은/는 ${str} 입니다`);
}

/* #btn1 클릭 시 #input1의 값을 읽어와 print1() 함수 호출 */
btn1.addEventListener("click", function(){

  const value = input1.value;

  // input1에 값이 입력이 안되었거나, 빈칸만 입력된 경우
  if(value.trim().length == 0){
    console.log("숫자를 입력해 주세요");
    return; // 함수 종료 + 호출한 곳으로 돌아감
  }

  let result;

  if(Number(value) == 0)      result = "0";
  else if(Number(value) > 0)  result = "양수";
  else                        result = "음수";

  /* 함수 호출(값 전달) */
  /* [!!중요!!] 전달되는 값의 순서가 중요하다!! */
  print1(value, result);
  print1(result, value); // 순서 반대로

});



/* 매개 변수로 배열 전달하기 */
function arrayTest(arr){
  // 전달 받은 배열의 모든 요소 출력하기
  for(let i=0 ; i<arr.length ; i++){
    
    if(arr[i] == '멜론')  arr[i] = '딸기';

    console.log(`${i} 번 인덱스 값 : `, arr[i]);
  }
}

// #btn2a 클릭 되었을 때 arrayTest() 함수 호출
document.querySelector("#btn2a").addEventListener("click", function(){
  
  const arr1 = [10,20,30];
  const arr2 = ['사과', '바나나', '멜론'];

  const temp = 100;

  /* 참조란? 
    - 객체 지향 언어에서
      배열, 객체 등 object는 값을 여러 개를 가지고 있는 자료형인데
      변수는 값을 1개만 저장할 수 있기 때문에

      object를 메모리 다른곳에 저장하고
      저장된 곳의 주소를 변수에 저장해서

      필요할 때 마다 해당 주소를 찾아가
      원하는 값을 얻어오거나 수정할 수 있게 하는 것

      이 때, 주소를 찾아 가는 것을 [참조] 라고 한다!
  */
  arrayTest(arr1);

  arrayTest(arr2);

  // 왜 멜론이 아니라 딸기 출력?
  // -> arrayTest(arr2) 호출 시
  //  arr2 배열을 통째로 복사해서 전달하는 것이 아닌
  //  arr2에 저장된 주소만 보내서 함수를 수행.

  // arrayTest() 함수에서 arr2와 같은 배열을 참조해서 수정
  // -> 함수 수행 후 돌아 와서도 
  //   참조하고 있던 배열의 2번 인덱스 값이 변화되어 있음
  console.log("arr2[2] : ", arr2[2]); 

})


/* 매개 변수로 요소 전달 */
function btn2bFn( el ){
  // 매개 변수 el(element) : 이벤트가 발생한 요소(HTML에서 this)

  el.style.backgroundColor = "yellow";
}


/* 매개 변수로 함수 전달 */

function print2(otherFn){

  // otherFn == sumFn
  console.log( `3 + 4 = ${otherFn(3,4)}` );
}


const btn2c = document.querySelector("#btn2c");
btn2c.addEventListener( "click" , function(){

  // 함수도 변수에 저장 가능!
  const sumFn = function(a, b){
    return a + b; // 함수를 끝냄 
                  //+ 값(a+b)을 가지고 호출한 곳으로 돌아감
  }

  // 매개 변수로 함수 전달
  print2(sumFn);

});



/* return 확인하기 1 */
function sumFn(arr){ // 전달된 배열 요소의 합을 반환

  let sum = 0;

  for(let i=0 ; i<arr.length ; i++){
    sum += arr[i];
  }

  return sum;
}


// 전달 받은 수(num)을 x제곱 하여 반환
function pow(num, x){

  let result = 1; // 곱했을 때 결과에 영향을 주지 않기 위해 1 초기화

  for(let i=0; i < x ; i++){
    result *= num;
  }
  return result;
}

const btn3a = document.querySelector("#btn3a");

btn3a.addEventListener("click", function(){

  const numbers = []; // 비어있는 배열 생성

  /* 배열.push(값) : 배열의 마지막 요소로 값을 추가*/

  numbers.push(30);
  numbers.push(50);
  numbers.push(4);
  numbers.push(pow(2,5)); // == numbers.push(32)

  console.log("합계 : ", sumFn(numbers));

});


// ------------------------------------------------------------

/* 화살표 함수 */

// 클래스가 arrow인 요소를 모두 얻어옴(배열)
const arrowList = document.querySelectorAll(".arrow");


/* 화살표 함수 기본 형태 */
arrowList[0].addEventListener("click", () => {
  alert("화살표 함수 기본 형태 연습");
});


/* 매개 변수가 1개인 경우 () 생략 가능 */
arrowList[1].addEventListener("click", e => {

  // e : 이벤트 객체(모든 이벤트 관련 정보가 담겨있는 객체)
  // e.target : 이벤트가 발생한 요소

  e.target.style.backgroundColor = "hotpink";

  print3( arr => {
    let result = 1;

    for(let i = 0 ; i<arr.length ; i++){
      result *= arr[i];
    }
    return result;
  } );
});


function print3(otherFn){

  const numbers = [1,2,3,4];
  console.log( otherFn(numbers) );
}


/* return 한 줄만 작성된 경우 */
arrowList[2].addEventListener("click", () => {

  twoNumberPlus(  (a, b) =>  a + b );
});

function twoNumberPlus( otherFn ){

  const input1 = Number(prompt("첫 번째 값"));
  const input2 = Number(prompt("두 번째 값"));

  alert( otherFn(input1, input2) );
}


/* return 한 줄인데 object 반환하는 경우 */
arrowList[3].addEventListener("click", () => {

  printObject(  (name, age) => {
    return {"name" : name, "age" : age};
    // JS 객체  {  K:V , K:V }
  } );

});

function printObject(otherFn){

  const obj = otherFn("홍길동", 20);

  console.log("obj.name : ", obj.name);
  console.log("obj.age  : ", obj.age);
}


/* 즉시 실행 함수 확인 */
(()=>{
  console.log("즉시 실행 함수 입니다.");
  console.log("함수 모양이 좀 어렵죠?");
})();
