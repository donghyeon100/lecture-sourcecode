const newBtn = document.getElementById("newBtn");
const size = document.getElementById("size");
const col = document.getElementsByClassName("col");

// 새 빙고판 만들기 버튼 동작
newBtn.addEventListener("click", function(){
    document.getElementById("bingoBoard").innerHTML = "";

    for(let i=0 ; i<size.value ; i++){
        const row = document.createElement("div");
        row.classList.add("row");
        
        for(let j=0 ; j<size.value ; j++){
            const col = document.createElement("div");
            col.classList.add("col");

            col.addEventListener("click", function(){
                this.classList.add("selected");
            });

            row.append(col);
        }

        document.getElementById("bingoBoard").append(row);
    }
});