const container = document.getElementsByClassName("container")[0];
const area = document.getElementsByClassName("area")
const box = document.getElementsByClassName("box");
const boxColor = document.getElementsByClassName("box-color");

// JS로 스타일 추가하기
container.style.display = "flex";

for(item of area){
    item.style.width = "150px";
    item.style.height = "170px";
    item.style.border = "1px solid black";
    item.style.display = "flex";
    item.style.flexDirection = "column";
}

for(item of box){
    item.style.height = "150px";
    item.style.borderBottom = "1px solid black";
}

for(item of boxColor){
    item.style.border = "none";
    item.style.outline = "none";
}



// box-color에 입력된 값이 변했을 때 시 같은 인덱스의 box 요소의 배경색 변경
for(let i=0 ; i<boxColor.length ; i++){
    // text 타입의 input 태그의 blur, change 이벤트
    // blur : 포커스를 잃었을 때
    // change : 포커스를 잃고, 작성된 값이 이전과 다를 때 또는 엔터 입력
    boxColor[i].addEventListener("change", function(){
        console.log(i + "번 인덱스 배경색 변경");
        box[i].style.backgroundColor = this.value;
        boxColor[i].style.color = this.value;
    });
}

// 변경 버튼 클릭 시 box의 transition-duration 변경
const btn1 = document.getElementById("btn1");
const input1 = document.getElementById("input1");
const print1 = document.getElementById("print1");

btn1.addEventListener("click", function(){

    print1.innerText = input1.value;

    for(item of box){
        item.style.transitionDuration = input1.value + "s";
    }
})

const clearBtn = document.getElementById("clearBtn");

clearBtn.addEventListener("click", function(){
    
    for(let i=0 ; i<boxColor.length ; i++){
        box[i].style.backgroundColor = "";
        boxColor[i].value = "";
    }
})