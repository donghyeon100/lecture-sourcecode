const addBtn = document.querySelector("#addBtn");
const tbody = document.querySelector("#tbody");


const removeFn = (e) => {
    // 요소.closest(선택자)
    // 주어진 CSS 선택자와 일치하는 요소를 찾을 때까지, 
    // 자기 자신을 포함해 위쪽(부모 방향, 문서 루트까지)으로 문서 트리를 순회 탐색

    console.log( e.target.closest("tr"));
    
    console.log( e.target.parentElement.parentElement);
    
    e.target.closest("tr").remove();
}


document.querySelector(".remove-btn").addEventListener("click", removeFn);


// 입력 추가 버튼 클릭 시
addBtn.addEventListener("click", () => {
    const tr = document.createElement("tr");

    const names = ['deptId', 'deptTitle', 'locationId'];

    names.forEach((name) => {
        const td = document.createElement("td");
        const input = document.createElement("input");
        input.type = 'text';
        input.name = name;

        td.append(input);
        tr.append(td);
    });

    const th = document.createElement("th");
    const button = document.createElement("button");
    button.type = 'button';
    button.classList.add("remove-btn");
    button.textContent = '삭제';


    button.addEventListener('click', removeFn);

    th.append(button);
    tr.append(th);

    tbody.append(tr);

});


