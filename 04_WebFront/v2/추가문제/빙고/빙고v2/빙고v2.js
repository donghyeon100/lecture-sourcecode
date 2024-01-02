// 빙고v1.html의 모양을 JS로 만들어서 #bingoBoard에 추가

// const 변수명 = document.createElement("태그명")  : 요소 생성
// 요소.append(변수명) : 요소의 마지막 자식으로 추가

const bingoBoard = document.getElementById("bingoBoard");

const div = document.createElement("div"); 

for(let i=0 ; i<5 ; i++){ 

    // 1) 행 생성
    // 2) 행 스타일 추가

    for(let k=1 ; k<=5 ; k++){

        // 1) 열 생성
        // 2) 내용 (숫자) 삽입
        // 3) 열 스타일 추가
        // 4) 열 이벤트 추가
        // 5) 행의 마지막 자식으로 열 추가
    }

    // 3) #bingoBoard의 마지막 자식으로 행 추가
}