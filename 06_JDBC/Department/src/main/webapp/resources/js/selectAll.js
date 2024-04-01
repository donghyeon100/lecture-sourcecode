// 수정 버튼 클릭 시 
// 해당 부서 수정 페이지로 이동

const updateBtnList = document.querySelectorAll(".update-btn");

updateBtnList.forEach((btn) => {
	btn.addEventListener("click", e => {

		const tr = e.target.closest("tr");
		const deptId = tr.children[1].textContent;
		const deptTitle = tr.children[2].textContent;
		const locationId = tr.children[3].textContent;
		// console.log(deptId);


		// 쿼리 스트링 만들기
		const params = new URLSearchParams();
		params.append("deptId", deptId);
		params.append("deptTitle", deptTitle);
		params.append("locationId", locationId);

		console.log(params.toString());


		location.href = "update?"+params.toString();
	});
})


// 삭제 버튼 클릭 시 정말로 삭제할 것인지 물어보고 삭제 수행
const deleteDeptId = document.querySelector("#deleteDeptId");
const deleteBtnList = document.querySelectorAll(".delete-btn");


deleteBtnList.forEach((btn) => {
	btn.addEventListener("click", e => {

		const tr = e.target.closest("tr");
		const deptId = tr.children[1].textContent;

		if(confirm(`${deptId} 부서를 정말 삭제 하시겠습니까?`)){
			
			// body 태그 제일 밑에 form태그를 만들어 추가한 후 제출하기
			
			// deleteForm에 값을 세팅하고 제출하기
			deleteDeptId.value = deptId;
			deleteForm.submit();
		}

		
	});
})