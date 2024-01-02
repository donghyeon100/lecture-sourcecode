// jQuery 선택자

// (JS/jQuery는 혼용 사용 가능)
document.getElementById("btn1").addEventListener("click", function(){

    // .test JS/jQuery 방식으로 선택

    const list = document.getElementsByClassName("test"); // JS

    // list(배열)의 각 요소에 배경색 "yellow"를 추가
    for( let li of list ){
        li.style.backgroundColor = "yellow";
    }

    // 배열에 직접 스타일을 지정하는 불가능!
    //list.style.backgroundColor = "yellow";

    // .test요소를 모두 선택해서 배열로 반환
    console.log( $(".test") );
    $(".test").css("fontSize", "25px");
    
    // -> jQuery는 요소가 묶여있는 배열에 스타일, 이벤트를 지정하는 경우
    //   배열 내 모든 요소에 스타일, 이벤트를 적용한다.

});