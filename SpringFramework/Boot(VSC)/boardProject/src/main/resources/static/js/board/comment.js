
/* REST(REpresentational State Transfer)  API

- 자원(데이터,파일)을 이름(주소)으로 
  구분(representational) 하여
  자원의 상태(State)를 주고 받는 것(Transfer)

 -> 자원의 이름(주소)를 명시하고
   HTTP Method(GET,POST,PUT,DELETE) 를 이용해
   지정된 자원에 대한 CRUD 진행

  자원의 이름(주소)는 하나만 지정 (ex. /comment)
   
  삽입 == POST    (Create)
  조회 == GET     (Read)
  수정 == PUT     (Update)
  삭제 == DELETE  (Delete)
*/


/* 댓글 목록이 출력되는 영역 */
const commentListArea = document.querySelector(".comment-list-area");;

// -----------------------------------------------------------------------

/** 댓글 목록 조회 함수
 - ajax를 이용해 데이터가 아닌
  Thymeleaf 템플릿이 해석된 html을 읽어와 댓글 목록을 화면에 출력
*/
const selectCommentList = () => {

  fetch("/board/commentList?boardNo=" + boardNo) // GET 방식 요청
    .then(response => {
      if (response.ok) return response.text();
      throw new Error("댓글 목록 조회 실패");
    })
    .then(html => {
      console.log(html);
      commentListArea.innerHTML = html;

      // 답글 버튼에 답글 작성 화면 출력 이벤트 추가
      addEventChildComment();

      // 댓글 수정 버튼에 수정 화면 출력 이벤트 추가
      addEventUpdateComment();

      // 댓글 삭제 버튼에 삭제 동작 추가
      addEventDeleteComment();
    })
    .catch(err => console.error(err));
}

// ---------------------------------------------------------------------------------------------

/** 댓글 등록 함수(ajax)
 * @param {*} parentCommentNo : 부모 댓글 번호(없을경우 undefined)
 */
const insertComment = (parentCommentNo) => {
  // boardNo : 게시글 번호 전역변수(boardDetail.js)
  const data = {
    "commentContent": commentContent.value,
    "boardNo": boardNo
  };


  if (parentCommentNo !== undefined) {
    data.parentCommentNo = parentCommentNo;
    data.commentContent = document.querySelector(".child-comment-content").value;
  }

  fetch("/comment", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data) // data 객체를 JSON 문자열로 변환
  })

    .then(response => {
      if (response.ok) return response.text();
      throw new Error("댓글 등록 실패");
    })
    .then(result => {

      // result == 작성된 댓글 번호
      if (result > 0) {
        alert("댓글이 등록 되었습니다");
        commentContent.value = ""; // 작성한 댓글 내용 지우기
        selectCommentList(); // 댓글 목록을 다시 조회해서 화면에 출력

        /* 알림을 DB에 추가 + 게시글 작성자 접속 시 알림 전달 */
        
        if(parentCommentNo === undefined){ // 댓글인 경우
          const content = `<strong>${memberNickname}</strong>님이 <strong>${boardDetail.boardTitle}</strong> 게시글에 댓글을 작성했습니다.`;
          sendNotification("insertComment", `${location.pathname}?cn=${result}`, boardNo, content);

        } else { // 답글인 경우
          const content = `<strong>${memberNickname}</strong>님이 답글을 작성했습니다.`;
          sendNotification("insertChildComment", `${location.pathname}?cn=${result}`, parentCommentNo, content);
        }
      } else {
        alert("댓글 등록 실패");
      }

    })
    .catch(err => console.log(err));
}

// ---------------------------------------------------------------------------------------------

/** 댓글 삭제 버튼에 삭제 동작을 추가하는 함수 */
const deleteComment = (btn) => {

  const li = btn.closest("li");
  const commentNo = li.dataset.commentNo;

  console.log(commentNo);

  // 취소 선택 시
  if (!confirm("삭제 하시겠습니까?")) return;

  fetch("/comment", {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: commentNo
  })
    .then(resp => resp.text())
    .then(result => {

      if (result > 0) {
        alert("삭제 되었습니다");
        selectCommentList(); // 다시 조회해서 화면 다시 만들기

      } else {
        alert("삭제 실패");
      }

    })
    .catch(err => console.log(err));
}



// ---------------------------------------------------------------------------------------------

/**  답글 버튼 클릭 시 
 *   답글 작성 화면 출력 이벤트 추가 함수 */
const showChildComment = (btn) => {
  // ** 답글 작성 textarea가 한 개만 열릴 수 있도록 만들기 **
  const temp = document.getElementsByClassName("child-comment-content");

  if (temp.length > 0) { // 답글 작성 textara가 이미 화면에 존재하는 경우

    if (confirm("다른 답글을 작성 중입니다. 현재 댓글에 답글을 작성 하시겠습니까?")) {
      temp[0].nextElementSibling.remove(); // 버튼 영역부터 삭제
      temp[0].remove(); // textara 삭제 (기준점은 마지막에 삭제해야 된다!)

    } else {
      return; // 함수를 종료시켜 답글이 생성되지 않게함.
    }
  }


  // 클릭된 답글의 부모 댓글
  const li = btn.closest("li");

  // 클릭된 답글 버튼의 부모 댓글 번호
  const parentCommentNo = li.dataset.commentNo;
  // console.log(parentCommentNo )

  // 답글을 작성할 textarea 요소 생성
  const textarea = document.createElement("textarea");
  textarea.classList.add("child-comment-content");

  li.append(textarea);

  // 답글 버튼 영역 + 등록/취소 버튼 생성 및 추가
  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");

  const insertBtn = document.createElement("button");
  insertBtn.innerText = "등록";

  /* 등록 버튼 클릭 시 댓글 등록 함수 호출(부모 댓글 번호 전달)  */
  insertBtn.addEventListener("click", () => insertComment(parentCommentNo));

  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  // cancelBtn.setAttribute("onclick", "insertCancel(this)");

  /* 취소 버튼 클릭 시 답글 작성 화면 삭제 */
  cancelBtn.addEventListener("click", () => {

    // console.log(li.lastElementChild);
    li.lastElementChild.remove();
    li.lastElementChild.remove();
  });

  // 답글 버튼 영역의 자식으로 등록/취소 버튼 추가
  commentBtnArea.append(insertBtn, cancelBtn);

  // 답글 버튼 영역을 화면에 추가된 textarea 뒤쪽에 추가
  textarea.after(commentBtnArea);
}



// ---------------------------------------------------------------------------------------------



// 수정 취소 시 원래 댓글 형태로 돌아가기 위한 백업 변수
let beforeCommentRow;


/** 댓글 수정 화면 전환
 * @param {*} btn : 댓글 수정 버튼
 */
const showUpdateComment = (btn) => {

  /* 댓글 수정 화면이 1개만 열릴 수 있게 하기 */
  const temp = document.querySelector(".update-textarea");

  // .update-textarea 존재 == 열려있는 댓글 수정창이 존재
  if (temp != null) {

    if (confirm("수정 중인 댓글이 있습니다. 현재 댓글을 수정 하시겠습니까?")) {

      const commentRow = temp.parentElement; // 기존 댓글 행
      commentRow.after(beforeCommentRow); // 기존 댓글 다음에 백업 추가
      commentRow.remove(); // 기존 삭제 -> 백업이 기존 행 위치로 이동

      const childeCommentBtn = beforeCommentRow.querySelector(".child-comment-btn");
      const updateCommentBtn = beforeCommentRow.querySelector(".update-comment-btn");
      const deleteCommentBtn = beforeCommentRow.querySelector(".delete-comment-btn");

      childeCommentBtn.addEventListener("click", () => showChildComment(childeCommentBtn));
      updateCommentBtn.addEventListener("click", () => showUpdateComment(updateCommentBtn));
      deleteCommentBtn.addEventListener("click", () => deleteComment(deleteCommentBtn));


    } else { // 취소
      return;
    }
  }


  // 1. 댓글 수정이 클릭된 행 (li.comment-row) 선택
  const commentRow = btn.closest("li");

  const commentNo = commentRow.dataset.commentNo; // 클릭된 댓글 버튼의 부모 댓글 번호
  console.log(commentNo);


  // 2. 행 전체를 백업(복제)
  // 요소.cloneNode(true) : 요소 복제, 
  //           매개변수 true == 하위 요소도 복제
  beforeCommentRow = commentRow.cloneNode(true);
  // console.log(beforeCommentRow);

  // 3. 기존 댓글에 작성되어 있던 내용만 얻어오기
  let beforeContent = commentRow.children[1].innerText;

  // 4. 댓글 행 내부를 모두 삭제
  commentRow.innerHTML = "";

  // 5. textarea 생성 + 클래스 추가 + 내용 추가
  const textarea = document.createElement("textarea");
  textarea.classList.add("update-textarea");
  textarea.value = beforeContent;

  // 6. 댓글 행에 textarea 추가
  commentRow.append(textarea);

  // 7. 버튼 영역 생성
  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");

  // 8. 수정 버튼 생성
  const updateBtn = document.createElement("button");
  updateBtn.innerText = "수정";
  updateBtn.addEventListener("click", () => {

    // 수정된 내용이 작성된 textarea 얻어오기
    //const textarea = btn.parentElement.previousElementSibling;

    // 유효성 검사
    if (textarea.value.trim().length == 0) {
      alert("댓글 작성 후 수정 버튼을 클릭해 주세요");
      textarea.focus();
      return;
    }

    // 댓글 수정 (ajax)
    const data = {
      "commentNo": commentNo,
      "commentContent": textarea.value
    }

    console.log("data : ", data)

    fetch("/comment", {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    })
      .then(resp => resp.text())
      .then(result => {
        if (result > 0) {
          alert("댓글이 수정 되었습니다");
          selectCommentList();
        } else {
          alert("댓글 수정 실패");
        }

      })
      .catch(err => console.log(err));

  });



  // 9. 취소 버튼 생성
  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";

  cancelBtn.addEventListener("click", () => {
    if (confirm("취소 하시겠습니까?")) {
      commentRow.after(beforeCommentRow); // 기존 댓글 다음에 백업 추가
      commentRow.remove(); // 기존 삭제 -> 백업이 기존 행 위치로 이동

      const childeCommentBtn = beforeCommentRow.querySelector(".child-comment-btn");
      const updateCommentBtn = beforeCommentRow.querySelector(".update-comment-btn");
      const deleteCommentBtn = beforeCommentRow.querySelector(".delete-comment-btn");

      childeCommentBtn.addEventListener("click", () => showChildComment(childeCommentBtn));
      updateCommentBtn.addEventListener("click", () => showUpdateComment(updateCommentBtn));
      deleteCommentBtn.addEventListener("click", () => deleteComment(deleteCommentBtn));

    }

  });


  // 10. 버튼 영역에 수정/취소 버튼 추가 후
  //     댓글 행에 버튼 영역 추가
  commentBtnArea.append(updateBtn, cancelBtn);
  commentRow.append(commentBtnArea);

}


// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------



/* ***** 댓글 등록 클릭 시 동작(ajax) ***** */
const addComment = document.querySelector("#addComment"); // button
const commentContent = document.querySelector("#commentContent"); // textarea

// 댓글 등록 버튼 클릭 시
addComment.addEventListener("click", () => {

  // loginCheck : 로그인 여부를 저장한 변수(boardDetail.html)
  // 로그인이 되어있지 않은 경우
  if (loginCheck === false) {
    alert("로그인 후 이용해 주세요");
    return; // early return;
  }

  // 댓글 내용이 작성되지 않은 경우
  if (commentContent.value.trim().length == 0) {
    alert("내용 작성 후 등록 버튼을 클릭해 주세요");
    commentContent.focus();
    return;
  }

  insertComment();
});


// ---------------------------------------------------------------------------------------------


/** 화면에 있는 모든 수정 버튼에 
 * 수정 화면 전환 이벤트 추가
 */
const addEventUpdateComment = () => {
  const updateCommentBtns = document.querySelectorAll(".update-comment-btn");

  updateCommentBtns.forEach(btn => {
    btn.addEventListener("click", () => {

      showUpdateComment(btn);
    });
  });
}


// ---------------------------------------------------------------------------------------------

/** 화면에 있는 모든 삭제 버튼에 
 * 삭제 이벤트 추가
 */

const addEventDeleteComment = () => {
  const deleteCommentbtns = document.querySelectorAll(".delete-comment-btn");

  deleteCommentbtns.forEach(btn => {
    btn.addEventListener("click", () => {
      deleteComment(btn);
    });
  });
}

// ---------------------------------------------------------------------------------------------

/** 화면에 있는 모든 답글 버튼에 답글 화면 출력 이벤트 추가*/
const addEventChildComment = () => {

  const childCommentBtns = document.querySelectorAll(".child-comment-btn");
  childCommentBtns.forEach(btn => {
    btn.addEventListener("click", e => {

      showChildComment(btn);
    });
  });
}



/** 페이지 로딩 완료 시 버튼에 이벤트 추가 */
document.addEventListener("DOMContentLoaded", () => {
  addEventChildComment();
  addEventUpdateComment();
  addEventDeleteComment();
});
