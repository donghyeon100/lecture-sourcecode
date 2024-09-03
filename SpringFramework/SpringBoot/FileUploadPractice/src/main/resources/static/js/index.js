const selectFile = document.querySelector("#selectFile")
const fileConrainer = document.querySelector("#fileConrainer")

// 업로드하기 위해 선택한 파일을 모아두는 배열
let selectFileList = [];

// #selectFileBtn 값이 변한 경우
selectFile?.addEventListener("change", e => {


    // 선택된 파일
    const files = e.target.files;
    console.log(files)

    // selectFileList에 선택한 파일 추가하는 로직
    for(let file of files){

        let flag = true;

        selectFileList.forEach(item => {

            // 같은 이름의 파일을 선택한적이 있다면(파일명 중복)
            if(item.name === file.name){
                flag = false;
            }
        })

        // 같은 이름의 파일이 filesList에 존재하지 않는 경우
        if(flag){
            selectFileList.push(file);   
        } 
    }

    convertFiles();
    displayFileNames();
})


// input type="file"에 기록된 fileList 값을 변경
const convertFiles  = () => {

    const dataTransfer = new DataTransfer();

    for(let file of selectFileList){
        dataTransfer.items.add(file);
    }

    selectFile.files = dataTransfer.files;
}


// 화면에 선택한 파일 목록 출력 + 삭제 버튼 동작 추가
const displayFileNames = () => {

    // 기존 파일 목록 삭제
    fileConrainer.innerHTML = "";
    
    // 파일 목록 다시 만들기
    selectFileList.forEach(item => {
        const fileDiv = document.createElement("div");
        
        const fileName = document.createElement("span");
        fileName.innerHTML = item.name;
        
        const deletnBtn = document.createElement("span");
        deletnBtn.innerHTML = "&times;";
        
        // 삭제 버튼 클릭 시
        deletnBtn.addEventListener("click", e => {
            selectFileList = selectFileList.filter(item => item.name !== e.target.previousElementSibling.innerHTML)
            convertFiles();
            displayFileNames();
        })

        fileDiv.append(fileName, deletnBtn);

        fileConrainer.append(fileDiv);
    })
    
}