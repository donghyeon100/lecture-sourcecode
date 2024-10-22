package edu.kh.project.sse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.sse.dto.Notification;

@Mapper
public interface SseMapper {

  /**
   * 게시글 작성자 번호 조회
   * @param pkNo
   * @return receiveMemberNo
   */
  int selectBoardMemberNo(int pkNo);

  /**
   * 알림 삽입
   * @param notification
   * @return notificationNo
   */
  int insertNotification(Notification notification);

  /**
   * 알림 번호로 받는 회원 번호, 해당 회원의 읽지 않은알림 개수 조회
   * @param notificationNo
   * @return receiveMemberNo
   */
  Map<String, Object> selectReceiveMemberNo(int notificationNo);
  
  /**
   * 읽지 않은 알림 개수 체크
   * @param memberNo
   * @return notReadCount
   */
  int notReadCheck(int memberNo);

  /**
   * 알림 조회
   * @param memberNo
   */
  List<Notification> selectNotificationList(int memberNo);

  /**
   * 알림 읽음으로 변경
   * @param notificationNo
   */
  void updateNotification(int notificationNo);

  /**
   * 알림 삭제
   * @param notificationNo
   */
  void deleteNotification(int notificationNo);

}
