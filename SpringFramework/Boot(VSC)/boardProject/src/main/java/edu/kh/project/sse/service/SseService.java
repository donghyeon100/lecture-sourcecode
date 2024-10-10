package edu.kh.project.sse.service;

import edu.kh.project.websocket.model.dto.Notification;

public interface SseService {

  /**
   * 알림 삽입
   * 
   * @param notification
   * @return result
   */
  int insertNotification(Notification notification);

}
