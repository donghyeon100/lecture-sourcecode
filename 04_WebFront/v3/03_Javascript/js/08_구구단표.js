function createTable(){

  // 시작 단
  const start = Number(document.getElementById("start").value);

  // 끝 단
  const end   = Number(document.getElementById("end").value);

  // 단(제목)이 작성되는 행 요소(tr 태그)
  const theadRow = document.getElementById("thead-row");

  // 구구단(내용)이 작성되는 영역(tbody 태그)
  const tbody = document.getElementById("tbody");


  // -----------------------------------------------------
  // 단(제목) 만들기

  theadRow.innerHTML = ""; // 이전 내용 삭제
  
  // start 부터 end까지 1씩 증가
  for(let dan = start ; dan <= end ; dan++){
    theadRow.innerHTML += `<th>${dan}단</th>`;
  }


  // -----------------------------------------------------
  // 구구단(내용) 만들기

  tbody.innerHTML = ""; // 이전 내용 삭제

  // 곱해지는 수(또는 n번째 줄)
  for(let num = 1 ; num <= 9 ; num++){

    let str = "<tr>";

    for(let dan=start ; dan <= end ; dan++){
      str += `<td>${dan} x ${num} = ${dan * num}</td>`;
    }

    str += "</tr>";
  
    tbody.innerHTML += str;
  }

}