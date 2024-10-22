package edu.kh.project.sse.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import edu.kh.project.sse.dto.Notification;
import edu.kh.project.sse.mapper.SseMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {

	private final SseMapper mapper;

	// 알림 삽입
	@Override
    public Map<String, Object> insertNotification(Notification notification) {

			// 댓글 작성, 좋아요 -> 게시글 작성자 번호 조회
			// 답글 작성 -> 부모 댓글 작성자 번호 조회
			// 채팅 -> 상대방 번호 조회
			// int receiveMemberNo = 0;
			Map<String, Object> map = null;
			int result = 0;
			switch(notification.getNotificationType()) {
				case "insertComment", "boardLike", "insertChildComment":
					// notification.setReceiveMemberNo(receiveMemberNo);
					result = mapper.insertNotification(notification);

					break;
				// case "insertChildComment":
					// result = mapper.insertNotificationChildComment(notification);
					// break;
				case "insertChat":
					break;
			}

			// Notification noti = mapper.selectOne(notification.getNotificationNo());
			if(result > 0) {
				map = mapper.selectReceiveMemberNo(notification.getNotificationNo());
			}

			return map;
    }


		// 읽지 않은 알림 개수 체크
		@Override
		public int notReadCheck(int memberNo) {
			return mapper.notReadCheck(memberNo);
		}

		// 알림 목록 조회
		@Override
		public List<Notification> selectNotificationList(int memberNo) {
			return mapper.selectNotificationList(memberNo);
		}

		// 알림 읽음으로 변경
		@Override
		public void updateNotification(int notificationNo) {
			mapper.updateNotification(notificationNo);
		}

		// 알림 삭제
		@Override
		public void deleteNotification(int notificationNo) {
			mapper.deleteNotification(notificationNo);
		}

		
}
