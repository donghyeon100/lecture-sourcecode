// 1단계

// [jQuery]

// addEventListener == on

$("#btn1").on("click", function(){

    // this : 이벤트가 발생한 요소 (#btn1 버튼) -> JS

    // $(this) : 이벤트가 발생한 요소를 jQuery 방식으로 선택
    //            ->  jQuery의 메서드 사용 가능


    // console.log(  $(this).prev().prev()   ); 

    // 버튼 전,전 요소(input)에 작성된 값 얻어오기
    const color = $(this).prev().prev().val();


    // 버튼 전,전,전 요소(div)에 배경색 변경
    $(this).prev().prev().prev().css("backgroundColor", color);

});



// 2단계
$("#btn2").on("click", function(){

    const inputList = $(".input2");

    /*console.log(inputList);
    console.log(inputList[0]);
    console.log(inputList[1]);*/

    for(let i=0 ; i<inputList.length ; i++){

        $(inputList[i]).prev().css("backgroundColor",  $(inputList[i]).val()  );
    }

});



// 3단계

// jQuery
$(".input3").on("input", function(){ // class가 input3인 요소 6개에 이벤트 추가

    // 입력 이벤트가 발생한 input요소의 이전 요소(div) 배경색을 input의 값으로 변경
    $(this).prev().css("backgroundColor",  $(this).val() );

    // 입력 이벤트가 발생한 input요소의 테두리를 input의 값으로 변경
    $(this).css("borderColor", $(this).val() );

});




// JS
const inputList4 = document.getElementsByClassName("input4");

for(let input of inputList4){ // 배열용 향상된 for문

    // for문으로 .input4 요소를 하나씩 접근에서 이벤트 추가
    input.addEventListener("input", function(){

        // 입력 이벤트가 발생한 input요소의 이전 요소(div) 배경색을 input의 값으로 변경
        this.previousElementSibling.style.backgroundColor = this.value;

        // 입력 이벤트가 발생한 input요소의 테두리를 input의 값으로 변경
        this.style.borderColor = this.value;
    });
}



/*$(".box").find("input").on("input", function(){

});*/