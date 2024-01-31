/* setTimeout(함수, 지연시간(ms)) */
const btn1 = document.querySelector("#btn1");

btn1.addEventListener("click", () => {

  window.setTimeout(()=>{
    console.log("3초 후에 출력된 문자열");
  } , 3000); // 3초 후에 수행


  window.setTimeout(()=>{
    console.log("2초");
  }, 2000);

  window.console.log("바로 출력");
});


/* [window.]setInterval(함수, 지연시간(ms)) */
const btn2 = document.querySelector("#btn2");

btn2.addEventListener("click", (e) => {

  window.setInterval( () => {
    const span = document.querySelector("#box2 > span");

    // 이전 내용 + 1 한 숫자를 다시 내용으로 대입 
    span.innerText =  Number(span.innerText) + 1;
  } , 1000 ); // 1초 마다 수행


  // 클릭된 버튼을 비활성화
  e.target.disabled = true;

});



/* clearInterval( Interval ) */

const target3 = document.querySelector("#target3");
const start3  = document.querySelector("#start3");
const stop3   = document.querySelector("#stop3");

let currInterval; // 현재 인터벌 저장

// 시작 버튼 클릭 시 0.1초마다 target의 내용을 1씩 증가
start3.addEventListener("click", e => {

  currInterval = setInterval(() => {
    target3.innerText = Number(target3.innerText) + 1;
  } , 100); // 100ms == 0.1s

  e.target.disabled = true; // 비활성화
});


// 멈춤 버튼 클릭 시
stop3.addEventListener("click", () => {

  // setInterval()을 제거
  window.clearInterval( currInterval );

  start3.disabled = false;
});