let notificationSock;       // 알림 웹소켓 객체
let sendNotificationFn;     // 웹소켓을 이용해 알림을 보내는 함수

let selectnNotificationFn;  // 비동기로 알림을 조회하는 함수
let notReadCheckFn;         // 비동기로 읽지 않은 알림 개수 체크하는 함수


let eventSource;

function connectSse() {

    if (!notificationLoginCheck) return;

    try {
        console.log("connectSse 호출")

        eventSource = new EventSource(`/sse/connect`);
    
        eventSource.onmessage = function (event) {
            // const messagesDiv = document.getElementById('messages');
            // messagesDiv.innerHTML += `<p>${event.data}</p>`;
            const notification = JSON.parse(event.data);
            console.log(notification)
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


// 예시: 특정 클라이언트에게 메시지 전송
function notifyClient(type, url, pkNo) {
    if (!notificationLoginCheck) return;

    const notification = {
        "notificationType": type,
        "notificationUrl": url,
        "pkNo": pkNo
    }


    fetch(`/sse/notify`, {
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
        })
        .catch(error => {
            console.error('문제가 발생했습니다:', error);
        });
}




if (notificationLoginCheck) { // 로그인 상태일 경우

    /* 페이지 DOM 요소 내용이 모두 로딩된 후(화면 렌더링이 끝난 후) */
    document.addEventListener("DOMContentLoaded", () => {

        // 알람 버튼
        const notificationBtn = document.querySelector(".notification-btn");

        /* 읽지 않은 알림이 있으면 알림 버튼 활성화 하기 */
        // notReadCheckFn().then(notReadCount => {
        //     if (notReadCount > 0) {
        //         notificationBtn.classList.remove("fa-regular");
        //         notificationBtn.classList.add("fa-solid");
        //     }
        // })


        /* 알림 버튼(종) 클릭 시*/
        notificationBtn.addEventListener("click", e => {
            const notiList = document.querySelector(".notification-list");

            // 보이는 상태일 때
            if (notiList.classList.contains("notification-show")) {
                notiList.classList.remove("notification-show");
                return;
            }

            /* 로그인 상태인 경우 알림 목록을 바로 비동기로 조회 */
            selectnNotificationFn();
            notiList.classList.add("notification-show");
        })

    })

}








/* 페이지 DOM 요소 내용이 모두 로딩된 후(화면 렌더링이 끝난 후) */
document.addEventListener("DOMContentLoaded", () => {
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

