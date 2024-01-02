// id로 접근하기
function accessId(){

    const div1 = document.getElementById("div1");

    // 접근한 요소의 배경색 얻어오기
    const bgColor = div1.style.backgroundColor;

    // *** 자바스크립트는 문자열 비교시에도 비교 연산자를 사용한다! *** 
    if( bgColor == "red" ){
        div1.style.backgroundColor = "yellow"; // 배경색을 yellow로 변경
    }else{
        div1.style.backgroundColor = "red"; // 배경색을 red로 변경
    }

}


// class로 접근하기
function accessClass(){

    // 요소를 여러 개 접근하는 경우 [배열] 형태로 반환됨
    const arr = document.getElementsByClassName("div2");

    // 인덱스를 이용해서 요소 하나씩 접근
    arr[0].style.backgroundColor = "pink";
    arr[0].innerText = "첫 번째 요소";
    
    arr[1].style.backgroundColor = "tomato";
    arr[1].innerText = "두 번째 요소";
    
    arr[2].style.backgroundColor = "skyblue";
    arr[2].innerText = "세 번째 요소";

}


// 태그명으로 접근하기
function accessTagName(){

    // 문서 내 모든 li태그 접근(배열 반환)
    const arr = document.getElementsByTagName("li");

    // 반복문(Java랑 비슷)
    for(let i = 0 ; i<arr.length ; i++){

        const num = arr[i].innerText; // 요소에 작성된 내용(숫자) 얻어오기

        arr[i].style.backgroundColor = "rgb(130, 220, "+ (50 * num) +")"
    }

}


// input 태그의 값(value) 얻어오기/변경하기
function inputTest(){

    // input 요소 접근하기
    const input = document.getElementById("input-test");

    // ** innerText/innerHTML은 
    //   요소의 내용 (시작태그, 종료태그 사이에 작성된 내용)을
    //   얻어 오거나, 변경할 때만 사용 가능 
    
    // ** input은 [value] 를 이용해서 값을 얻어 오거나, 변경할 수 있음.
    

    // input에 작성된 값을 얻어와 콘솔에 출력
    console.log(input.value);


    // input에 작성된 값 변경하기
    input.value = ""; // 빈 문자열 == value 지우기

    // input에 초점 맞추기 -> focus()
    input.focus();
}


// name으로 접근하기
function accessName(){

    const hobbyList = document.getElementsByName("hobby");

    let str = ""; 

    let count = 0;

    for(let i=0 ; i < hobbyList.length ; i++){

        // * radio/checkbox 전용 속성 *
        // .checked : 해당 요소가 체크되어 있으면 true, 아니면 false 반환
        if( hobbyList[i].checked ){ // 현재 요소가 체크되어 있으면

            // str 변수에 value 누적
            str += hobbyList[i].value + " ";

            count++; // 개수 증가
        }
    }

    // #name-div에 출력
    document.getElementById("name-div").innerText = str;

    document.getElementById("name-div").innerHTML += "<br><br>선택된 개수 : " + count;

}


// CSS 선택자로 접근하기
function accessCss(){

    // querySelector() : 요소 1개 선택 시 사용
    //                   (여러 요소가 선택되면 첫 번째 요소만 선택)

    // 1개만 있는 요소 선택
    document.querySelector("#css-div").style.border = "3px solid red";

    // 여러개 있는 요소 선택(첫 번째 요소 선택 확인)
    document.querySelector("#css-div > div").style.fontSize = "30px";

    
    // querySelectorAll() : 모든 요소 선택 시 사용 (배열 반환)
    const arr = document.querySelectorAll("#css-div > div");

    for(let i=0 ; i < arr.length ; i++){
        arr[i].style.backgroundColor = "gold";
    }

}




// 카카오톡 채팅 만들기
function readValue(){

    // 채팅 입력에 사용되는 요소 모두 얻어오기
    const bg = document.getElementById("chatting-bg");

    const input = document.querySelector("#chatting-input");

    
    // input에 입력된 값이 있을 경우
    if( input.value.trim().length > 0 ){

        // 문자열.trim() : 문자열 양 끝에 공백을 모두 제거
        // ex)  "        K  H           ".trim() -> "K  H"

        // input에 입력된 값을 얻어와 bg에 추가(누적)
        bg.innerHTML += "<p> <span>"+ input.value +"</span> </p>"

       

        // 요소.scrollTop          : 요소 내부 현재 스크롤 위치 반환
        // 요소.scrollTop = 위치   : 스크롤을 특정 위치 이동
        // 요소.scrollHeight       : 스크롤 전체 높이

        // bg의 스크롤을 제일 밑으로 내리기
        bg.scrollTop = bg.scrollHeight;


    } else{
        alert("채팅 내용을 입력해주세요.");
    }


    // 입력된 input의 value 지우기
    input.value = "";

    // 입력 후 다시 input에 포커스 맞추기
    input.focus();

}



// input태그 키가 눌러졌을 때 엔터인 경우를 검사하는 함수
function inputEnter(){

    console.log( window.event.key ); // 현재 눌러진 키를 반환

    if(window.event.key == "Enter"){ // 눌러진 Key가 Enter인 경우
       // readValue(); // 함수 호출
    }
}