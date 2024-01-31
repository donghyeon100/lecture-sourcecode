/* JS 객체
  - { K:V, K:V, K:V } 형태

  - (중요) Key는 무조건 문자열(string)
    -> "Key", 'key', key  ("", '' 작성 안해도 string으로 인식)

  - key는 오름차순으로 정렬된다
*/

/* {} 객체 생성 + 다루기 */
const btn1 = document.querySelector("#btn1");

btn1.addEventListener("click", () => {

  // 1. 객체 생성
  //const product = {}; // 비어있는 객체 생성

  const product = {
    "productName" : "갤럭시북4프로",
    'brand' : "samsung",
    price : 2000000
  }; 

  console.log(product);
  
  // ----------------------------------------------------
  // 2. 객체 내 key 값을 이용해 원하는 value 얻어오기

  // 1) 객체명.key
  console.log(product.productName,  product.brand,  product.price);

  // 2) 객체명["key"]
  console.log(product["productName"],  product['brand'], product["price"]);

  
  // ----------------------------------------------------
  // 3. 객체에 K:V 추가

  // 원하는 key를 생각해두고 

  // 객체명.key = value ;  
  // 또는  
  // 객체명["key"] = value;
  product.color = ["black", "silver", "red"];
  console.log(product);
  console.log(product.color[2]);

  product["storage"] = ["256GB", "512GB", "1TB"];
  console.log(product);


  // ----------------------------------------------------
  // 4. 특정 key의 value 수정하기
  // -> 덮어쓰기
  product.price = 1800000;
  console.log(product);


  // ----------------------------------------------------
  // 5. 원하는 K:V 삭제하기(delete 연산자 사용)
  // -  delete 객체명.key

  delete product.productName; // productName 삭제
  console.log("delete 후 product : ", product);

});

// =========================================================

/* method(메서드) */
const btn2 = document.querySelector("#btn2");

btn2.addEventListener("click", () => {

  const smartPhone = {}; // 비어있는 객체 생성

  smartPhone.modelName = "아이폰15프로맥스";
  smartPhone.price = 1700000;

  // 메서드(객체의 기능(함수)) 추가
  smartPhone.information = function(){

    const brand = "사과";

    /* this 참조 변수 
      - 작성되는 위치에 따라서 의미가 다름

      ex) <button onclick=testFn(this)>
      //  -> 이 때 this == 클릭된 요소(버튼)

      ex) 객체명.메서드명 = function(){
        this.변수명 = "값";

        -> 이 때 this == 메서드가 작성된 객체
      }
    */

    // this == smartPhone
    console.log("모델명 : ", this.modelName);
    console.log("가격 : ", this.price);
    console.log("브랜드 : ", this.brand); // smartPhone.brand
    console.log("브랜드 : ", brand); // 지역 변수 brand
  }


  // 메서드 수행
  smartPhone.information();

  // -----------------------------------------

  
  // 매개 변수가 존재하는 메서드
  smartPhone.call = function(phoneNumber){
    console.log(`${phoneNumber}로 전화를 거는중...`);
  }

  // 매개 변수가 존재하는 메서드 호출하기
  smartPhone.call("010-1234-1234");


  // (참고) 메서드 생성 시 화살표 함수 + this 사용
  // -> 화살표 함수 내에서 this는 Window 객체를 나타낸다!
  // * 객체 메서드 작성 시 function(){} 구문 사용을 권장


  smartPhone.test1 = function(){
    console.log(this); // smartPhone 객체
  }

  smartPhone.test1();

  // 화살표 함수 사용 
  smartPhone.test2 = () => {
    console.log(this);  // -> Window 객체
  }

  smartPhone.test2();

});

// ==================================================

/* 생성자 함수 */

// 1. 생성자 함수 선언 + 정의
// (중요) 생성자 함수의 이름은 무조건 "대문자"로 시작!!

/** 학생 객체 생성자 함수 */
function Student(name, grade, ban, number, score){

  // 속성
  // (전달 받은 값(parameter)을 현재 객체(this)에 속성으로 추가)
  this.name   = name;   // 이름
  this.grade  = grade;  // 학년
  this.ban    = ban;    // 반
  this.number = number; // 번호
  this.score  = score;  // 점수

  // 기능(메서드)
  this.introduce = function(){
    console.log(`${this.grade}학년 ${this.ban}반 ${this.number}번 ` 
                +`${this.name} ${score}점 입니다.`);
  }
}



// 생성자 함수 호출
const btn3 = document.querySelector("#btn3");
btn3.addEventListener("click", ()=>{

  // const student1 = {
  //   name : "홍길동",
  //   grade : 3,
  //   ban : 5,
  //   number : 14,
  //   score : 80
  // };

  // const student2 = {
  //   name : "김길동",
  //   grade : 2,
  //   ban : 6,
  //   number : 2,
  //   score : 90
  // };

  const student1 = new Student("홍길동", 3, 5, 17, 80);
  console.log(student1);

  const student2 = new Student("김철수", 1, 2, 3, 100);
  console.log(student2);


  /* 객체 배열 (배열에 저장된 모든 요소가 객체) */
  const studentList = []; // 빈 배열 생성

  // 배열명.push(값) : 값을 배열의 마지막 요소로 추가
  studentList.push(student1);
  studentList.push(student2);

  // 생성자 함수 Student를 이용해서 객체 생성 후
  // 바로 studentList에 추가
  studentList.push(new Student("김미영",2,8,4,90));
  studentList.push(new Student("신짱구",3,1,12,30));

  console.log(studentList);

  // 모든 학생의 introduce() 메서드 호출
  for(let i=0 ; i<studentList.length ; i++){
    studentList[i].introduce();
  }
});



/* JSON */
const btn4 = document.querySelector("#btn4");

btn4.addEventListener("click", () => {

  // JS 객체 생성
  const obj = {"id":"test01", "pw":"1234", "number" : 9999};

  // JS 객체 -> JSON 문자열로 변환
  const str = JSON.stringify(obj);

  console.log("obj : ", obj);
  console.log("str : ", str);


  // JSON 문자열 -> JS 객체 변환
  const str2 = '{"memberId":"user01","memberPw":"pass01","age":20}';
  const obj2 = JSON.parse(str2);

  console.log("str2 : ", str2);
  console.log("obj2 : ", obj2);

  // 서버 데이터 비동기 요청
  fetch('https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth?serviceKey=fC0CJR9Lmwp3VfoLcND3XVFDW7FKGuTMECDT6TZD3aE7q4XUVL6PYscpePWfKNMwh0p6ZUt%2FmOm5xGbckoRGCQ%3D%3D&returnType=JSON&numOfRows=100&pageNo=1&searchDate=2024-01-22&InformCode=PM10')
  .then( response => response.json())
  .then( result => console.log(result.response.body.items[0].informGrade) );
});