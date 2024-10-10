package edu.kh.project.sse.service;

import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.websocket.model.dto.Notification;

@Service
public class SseServiceImpl implements SseService{
  

  	/**
	 * 알림 종류에 따라 알림 객체에 값 추가하기
	 * 
	 * @param notification
	 * @return
	 */
	private void setNotification(Notification notification, Member sendMember) {

		// // 보낸 사람 받는 번호
		// notification.setSendMemberNo(sendMember.getMemberNo());

		// // 보낸 사람 프로필 이미지
		// notification.setSendMemberProfileImg(sendMember.getProfileImg());

		// // 알림을 보낼 때 필요한 게시글 관련 값 조회
		// // Board board = service.selectBoardData(notification.getPkNo());

		// // *****************************************************************
		// // 로그인한 회원이 자신의 게시글을 좋아요, 댓글 작성 한 경우 -> 알림 필요 없음
		// if (sendMember.getMemberNo() == board.getMemberNo())
		// 	return;
		// // *****************************************************************

		// String content = null;

		// switch (notification.getNotificationType()) {

		// 	/* ********* 게시글 좋아요 웹소켓 send 요청 시 ********* */
		// 	case "boardLike":

		// 		// 알림 내용 가공
		// 		content = String.format("<b>%s</b>님이 <b>[%s]</b> 게시글을 좋아합니다",
		// 				sendMember.getMemberNickname(), board.getBoardTitle());

		// 		// 알림 내용 세팅
		// 		notification.setNotificationContent(content);

		// 		// 알림 받을 회원 번호 세팅
		// 		notification.setReceiveMemberNo(board.getMemberNo());
		// 		break;

		// 	/* ********* 댓글 등록 웹소켓 send 요청 시 ********* */
		// 	case "insertComment":

		// 		// 알림 내용 가공
		// 		content = String.format("<b>%s</b>님이 <b>[%s]</b> 게시글에 댓글을 남겼습니다",
		// 				sendMember.getMemberNickname(), board.getBoardTitle());
		// 		// 알림 내용 세팅
		// 		notification.setNotificationContent(content);

		// 		// 알림 받을 회원 번호 세팅
		// 		notification.setReceiveMemberNo(board.getMemberNo());

		// 		break;
		// }
	}

    @Override
    public int insertNotification(Notification notification) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'insertNotification'");
    }
}
