const selectBtn = document.querySelector("#selectBtn");
const tbody = document.querySelector("#tbody");

selectBtn.addEventListener("click", () => {
    
    fetch("/student/select")
    .then(resp => resp.json())
    .then(studentList => {

        if(studentList.length == 0) {
            alert("조회 결과 없음")
            return;
        }
        
        tbody.innerHTML = "";
        studentList.forEach((student) => {
            
            const tr = document.createElement("tr");

            const td1 = document.createElement("td");
            const td2 = document.createElement("td");
            const td3 = document.createElement("td");
            const td4 = document.createElement("td");

            td1.innerHTML = student.studentNo;
            td2.innerHTML = student.studentName;
            td3.innerHTML = student.studentMajor;
            td4.innerHTML = student.studentGender;

            tr.append(td1, td2, td3, td4);

            tbody.append(tr);
        })

    })
})

// -------------------------------------------------------------------------

const addBtn = document.querySelector("#addBtn");

addBtn.addEventListener("click", () => {
    const nameInput = document.querySelector("#nameInput");
    const majorInput = document.querySelector("#majorInput");
    const genderInput = document.querySelector(".genderInput:checked");

    const data = {
        studentName : nameInput.value,
        studentMajor : majorInput.value,
        studentGender : genderInput.value
    }

    fetch("/student/insert", {
        method : "POST",
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(data)
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0) alert("추가 성공")
        else           alert("추가 실패")
    })
    
})



// -------------------------------------------------------------------------
const deleteBtn = document.querySelector("#deleteBtn");
const studentNoInput = document.querySelector("#studentNoInput");


deleteBtn.addEventListener("click", () => {


    fetch("/student/delete", {
        method : "DELETE",
        headers : {
            "Content-Type" : "application/json"
        },
        body : studentNoInput.value
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0) alert("삭제 성공")
        else           alert("삭제 실패")
    })
})