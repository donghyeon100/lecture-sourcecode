package edu.kh.project.sse.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.sse.dto.Notification;

public interface SseService {

  /**
   * 알림 삽입
   * @param notification
   * @return map
   */
  Map<String, Object> insertNotification(Notification notification);

  /**
   * 읽지 않은 알림 개수 체크
   * @param memberNo
   * @return notReadCount
   */
  int notReadCheck(int memberNo);

  /**
   * 알림 목록 조회
   * @param memberNo
   * @return notificationList
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
