/* console.log(값) */
// 브라우저 콘솔에 괄호() 내부의 값을 기록(출력)

console.log(1234);
console.log("문자열은 양쪽에 쌍따옴표를 작성해야 한다!");
console.log('JS는 홑따옴표도 문자열로 취급한다!');

// --------------------------------------------------------------

/* 변수 선언 */
// 메모리에 값을 저장할 공간을 만들고 이름을 붙이는 것
// 작성법 :   변수종류 변수명;
var number1;  


/* 변수에 값 대입 */
// 선언된 변수에 값을 대입하는 것
// 작성법 :  변수명 = 값;
number1 = 10;


/* 콘솔에 number1 값 출력하기 */
console.log(number1);


/* 변수 선언 + 대입 */
var number2 = 20;

console.log(number2);


/* number1, number2의 합 출력하기 */
console.log(number1 + number2); // 30


/* 변수에 대입한 값 변경하기 */
number1 = 300; // number1에 300 대입(기존 값 10을 덮어 쓰기함)
number2 = 400;


/* 변경된 두 변수의 값 출력 */

/* 1) 문자열 + 문자열/숫자/변수 == 이어쓰기 */
console.log( "number1의 값 : " + number1 );
console.log( "number2의 값 : " + number2 );

/* 2) 괄호 내부에 , 를 작성해 각각의 값을 독립적으로 출력하기 */
console.log("number1 : ", number1, " / number2 : ", number2);

// -------------------------------------------------------------


/* var, let, const의 차이점 */

// 1. var (변수, 변수 선언 시 중복되는 변수명으로 선언 가능)
//       -> 먼저 선언된 변수가 나중에 선언된 변수에 덮어 씌워짐
var menu = "삼겹살";
console.log("menu : ", menu);

var menu = "초밥";
console.log("menu : ", menu);
// 변수명이 중복되어 덮어쓰기되면
// 이전에 선언 해놓은 변수를 쓸 수 없게 되는 문제가 생김
// (나중에 쓸려고 했는데 나도모르게 지워져있네? ㅠㅠ )


// ------------------------------------------
// 2. let (변수, var의 변수명 중복 문제 해결)
let number3 = 25;

// 변수명 중복 오류 발생!
//let number3 = 500; // 'number3'을(를) 다시 선언할 수 없습니다.

number3 = 500; // 기존 변수 number3에 새로운 값만 대입

console.log("number3 : ", number3);

//-------------------------------------------------------
// 3. const (상수, constant, 항[상] 같은 [수])
// 한 번 값이 대입되면 새로운 값을 대입할 수 없음

const PI = 3.141592;

// PI = 1.23; // Uncaught TypeError: Assignment to constant variable.
// 상수에 값 대입 못하는데 새로운 값이 대입되었다

console.log("PI : ", PI);

// -------------------------------------------------------------------

/* JS 자료형 확인하기 */

/* typeof 연산자 :  변수/값의 자료형을 출력하는 연산자 */


/* undefined : 정의되지 않은 변수 / 값이 아직 대입되지 않았다 */
let undef; // 변수 선언
console.log("undef : ", undef, typeof undef);


/* string(문자열) : "" 또는 '' 내부에 작성된 값 */
const name = "홍길동"; // 쌍따옴표
console.log("name : ", name, typeof name);

const phone = '01012341234'; // 홑따옴표
console.log("phone : ", phone, typeof phone);

const gender = 'M'; // 한 글자만 작성해도 문자열(string)
console.log("gender : ", gender, typeof gender);


/* number(정수, 실수, 양수, 음수, 0 등 모든 숫자) */
const age = 25;
const height = 178.9;

console.log("age : ", age, typeof age);
console.log("height : ", height, typeof height);



/* boolean (논리 값 true / false) */
const isTrue = true;
const isFalse = false;

console.log("isTrue : ", isTrue, typeof isTrue);
console.log("isFalse : ", isFalse, typeof isFalse);


/*  object (객체)  */
// (쉬운 버전 설명) 값을 여러 개 저장할 수 있는 형태

// 1) 배열(array) : 여러 값이 나열되어 있는 것의 묶음
const numbers = [10, 20, 30];

console.log("numbers : ", numbers, typeof numbers);

console.log("numbers 배열 값 중 0번째 : ", numbers[0], typeof numbers[0])
console.log("numbers 배열 값 중 1번째 : ", numbers[1], typeof numbers[1])
console.log("numbers 배열 값 중 2번째 : ", numbers[2], typeof numbers[2])


// 2) JS 객체(Object)
//  값을 K:V (Map) 형식으로 여러 개 저장하는 형태
//  ->  { K:V , K:V , K:V }

//  K(key)   : 값을 구분하는 이름(변수명 비슷)
//  V(value) : K에 대응되는 값(변수에 대입되는 값 비슷)

const user = { id : "user01", pw : "pass01", userName : "김길동"  };

console.log("user : ", user, typeof user);

/* 객체에 존재하는 값 하나씩 얻어오기 */

// 방법 1 : 변수명['key']
console.log("user['id'] : ", user['id'], typeof user['id']);
console.log("user['pw'] : ", user['pw'], typeof user['pw']);
console.log("user['userName'] : ", user['userName']
            , typeof user['userName']);

// 방법 2 : 변수명.key
console.log(user.id,   user.pw,   user.userName);


/* 함수(function) */

// [작성법]
// const 변수명 = function(){};
// (변수명 == 함수명)

const sumFn = function(a, b){ return a+b; }

console.log( typeof sumFn ); // function

console.log( sumFn(3,5) ); // 함수 호출(기능 수행)



