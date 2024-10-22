
/* SSE 연결 */
const connectSse = () => {
	if (!notificationLoginCheck) return;

	try {
		console.log("connectSse() 호출");

		eventSource = new EventSource(`/sse/connect`);

		eventSource.onmessage = function (event) {
			const dataObj = JSON.parse(event.data);
			console.log(dataObj);

			// 종 아이콘 활성화
			const notificationBtn = document.querySelector(".notification-btn");
			notificationBtn.classList.remove("fa-regular");
			notificationBtn.classList.add("fa-solid");

			// 알림 개수 표시
			const notificationCountArea = document.querySelector(".notification-count-area");
			notificationCountArea.innerText = dataObj.notiCount;

			
			// 알림 목록이 보이는 상태일 때 알림 목록 조회
			const notificationList = document.querySelector(".notification-list");

			if(notificationList.classList.contains("notification-show")){
				selectnNotificationList();
			}

		};

		eventSource.onerror = function () {
			console.log("SSE 재연결 시도")
			eventSource.close();
			// 재연결 시도
			setTimeout(() => connectSse(), 5000); // 5초 후 재연결
		};
	} catch (error) {
		console.log("connectSse 오류 발생")
		return;
	}
}


//* SSE 연결되어 있는 특정 클라이언트에게 메시지 전송 */
const sendNotification = (type, url, pkNo, content) => {
	if (!notificationLoginCheck) return;

	const notification = {
		"notificationType": type,
		"notificationUrl": url,
		"pkNo": pkNo,
		"notificationContent": content
	}

	fetch(`/sse/send`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(notification)
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('네트워크 응답이 좋지 않습니다.');
			}
			console.log("알림 전송 성공");
		})
		.catch(error => {
			console.error('문제가 발생했습니다:', error);
		});
}



/* 비동기로 읽지 않은 알림 개수 체크하는 함수 */
const notReadCheck = () => {
	if (!notificationLoginCheck) return;

	fetch("/notification/notReadCheck")
		.then(resp => {
			if (resp.ok)  return resp.text();
			throw new Error("네트워크 응답이 좋지 않습니다.");
		})
		.then(notReadCount => {
			console.log(notReadCount);

			// 알림 개수 표시
			const notificationCountArea = document.querySelector(".notification-count-area");
			notificationCountArea.innerText = notReadCount;


			const notificationBtn = document.querySelector(".notification-btn"); // 종 버튼

			// 읽지 않은 게시글이 존재하는 경우 종 아이콘 활성화
			if(notReadCount > 0){
				notificationBtn.classList.remove("fa-regular");
				notificationBtn.classList.add("fa-solid");

			} else { // 읽지 않은 게시글이 없는 경우 종 아이콘 비활성화
				notificationBtn.classList.add("fa-regular");
				notificationBtn.classList.remove("fa-solid");
			}

		})
		.catch(error => {
			console.error('문제가 발생했습니다:', error);
		});
}





/* 비동기로 알림을 조회하는 함수  */
const selectnNotificationList = () => {
	if (!notificationLoginCheck) return; // 로그인 안되어있음 종료

	fetch("/notification")
		.then(resp => resp.json())
		.then(selectList => {

			console.log(selectList);

			// 이전 알림 목록 삭제
			const notiList = document.querySelector(".notification-list");
			notiList.innerHTML = '';

			for (let data of selectList) {

				// 알림 전체를 감싸는 요소
				const notiItem = document.createElement("li");
				notiItem.className = 'notification-item';


				// 알림을 읽지 않은 경우 'not-read' 추가
				if (data.notificationCheck == 'N') notiItem.classList.add("not-read");


				// 알림 관련 내용(프로필 이미지 + 시간 + 내용)
				const notiText = document.createElement("div");
				notiText.className = 'notification-text';


				// 알림 클릭 시 동작
				notiText.addEventListener("click", e => {

					// 만약 읽지 않은 알람인 경우
					if (data.notificationCheck == 'N') {
						fetch("/notification", {
							method: "PUT",
							headers: { "Content-Type": "application/json" },
							body: data.notificationNo
						})
						// 컨트롤러 메서드 반환값이 없으므로 then 작성 X
					}

					// 클릭 시 알림에 기록된 경로로 이동
					location.href = data.notificationUrl;
				})


				// 알림 보낸 회원 프로필 이미지
				const senderProfile = document.createElement("img");
				if (data.sendMemberProfileImg == null) senderProfile.src = notificationDefaultImage;  // 기본 이미지
				else senderProfile.src = data.sendMemberProfileImg; // 프로필 이미지


				// 알림 내용 영역
				const contentContainer = document.createElement("div");
				contentContainer.className = 'notification-content-container';

				// 알림 보내진 시간
				const notiDate = document.createElement("p");
				notiDate.className = 'notification-date';
				notiDate.innerText = data.notificationDate;

				// 알림 내용
				const notiContent = document.createElement("p");
				notiContent.className = 'notification-content';
				notiContent.innerHTML = data.notificationContent; // 태그가 해석 될 수 있도록 innerHTML

				// 삭제 버튼
				const notiDelete = document.createElement("span");
				notiDelete.className = 'notidication-delete';
				notiDelete.innerHTML = '&times;';


				/* 삭제 버튼 클릭 시 비동기로 해당 알림 지움 */
				notiDelete.addEventListener("click", e => {

					fetch("/notification", {
						method: "DELETE",
						headers: { "Content-Type": "application/json" },
						body: data.notificationNo
					})
						.then(resp => {
							if(resp.ok) return resp.text();
							throw new Error("네트워크 응답이 좋지 않습니다.");
						})
						.then(result => {
							// 클릭된 x버튼이 포함된 알림 삭제
							notiDelete.parentElement.remove();
							notReadCheck();

						})
				})

				// 조립
				notiList.append(notiItem);
				notiItem.append(notiText, notiDelete);
				notiText.append(senderProfile, contentContainer);
				contentContainer.append(notiDate, notiContent);

			}
		})
}








if (notificationLoginCheck) { // 로그인 상태일 경우

	/* 페이지 DOM 요소 내용이 모두 로딩된 후(화면 렌더링이 끝난 후) */
	document.addEventListener("DOMContentLoaded", () => {

		/* 읽지 않은 알림 개수 체크 */
		notReadCheck();


		// 알람 버튼
		const notificationBtn = document.querySelector(".notification-btn");

		/* 알림 버튼(종) 클릭 시*/
		notificationBtn.addEventListener("click", e => {
			const notiList = document.querySelector(".notification-list");

			// 보이는 상태일 때
			if (notiList.classList.contains("notification-show")) {
				notiList.classList.remove("notification-show");
				return;
			}

			/* 로그인 상태인 경우 알림 목록을 바로 비동기로 조회 */
			selectnNotificationList();
			notiList.classList.add("notification-show");
		})

	})

}








/* 페이지 DOM 요소 내용이 모두 로딩된 후(화면 렌더링이 끝난 후) */
document.addEventListener("DOMContentLoaded", () => {

	connectSse();

	// 주소에 #아이디속성명 이 작성되어 있으면서
	// 해당 아이디를 가진 요소가 존재하는 경우
	// 해당 요소의 위치로 스크롤 옮기기
	// const targetId = location.href.substring(location.href.indexOf("#") + 1);

	// 쿼리스트링 파라미터 중 cn 값을 얻어와 같은 아이디를 가지는 요소로 이동
	const params = new URLSearchParams(location.search)
	const targetId = "c" + params.get("cn");

	let targetElement = document.getElementById(targetId);

	if (targetElement) {
		const scrollPosition = targetElement.offsetTop;
		window.scrollTo({
			top: scrollPosition - 200,
			behavior: 'smooth'
		});
	}
})

