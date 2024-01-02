// window.setTimeout(함수 , 지연시간(ms))
document.getElementById("btn1").addEventListener("click", function(){

    setTimeout( function(){
        alert("3초후 출력 확인!");
    }, 3000 );

});

let interval; // setInterval을 저장하기 위한 전역 변수

function clockFn(){
    const clock = document.getElementById("clock");
    clock.innerText = currentTime(); // 현재 시간 화면에 출력

    // 지연 시간 마다 반복(첫 반복도 지연 시간 후에 시작)
    // -> 페이지 로딩 후 1초 후 부터 반복(지연 -> 함수 -> 지연 -> 함수)
    interval = setInterval(function(){
        clock.innerText = currentTime();
    }, 1000);
}


// 현재 시간 문자열로 반환 함수
function currentTime(){
    const now = new Date();

    let hour = now.getHours();
    let min = now.getMinutes();
    let sec = now.getSeconds();

    if(hour < 10)   hour = "0" + hour;
    if(min < 10)    min  = "0" + min;
    if(sec < 10)    sec  = "0" + sec;


    return hour + " : " + min + " : " + sec;
}


clockFn(); // 함수 호출


// clearInterval
document.getElementById("stop").addEventListener("click", function(){

    clearInterval(interval);

});



// 팝업창 열기
const openPopup1 = document.getElementById("openPopup1");
const openPopup2 = document.getElementById("openPopup2");
const openPopup3 = document.getElementById("openPopup3");

openPopup1.addEventListener("click", function(){
    // 새 탭에서 열기
    window.open("09_배열.html")
})

openPopup2.addEventListener("click", function(){
    // 최소한의 팝업창 형태로 열기
    window.open("09_배열.html","_blank","popup");
})

openPopup3.addEventListener("click", function(){

    // 옵션이 지정된 팝업창
    let options = "width=400,height=450,left=200,top=100"
    window.open("09_배열.html","_blank",options);
})


// 팝업 <-> 부모 데이터 전달
const sendPopup = document.getElementById("sendPopup");

sendPopup.addEventListener("click", function(){
    let options = "width=300,height=300,left=500,top=100"
    window.open("10-2_팝업.html","popupWindow",options);
})