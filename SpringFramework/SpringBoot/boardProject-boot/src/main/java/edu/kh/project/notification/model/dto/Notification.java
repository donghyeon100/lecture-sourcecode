package edu.kh.project.notification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Notification {

	private int notificationNo;
	private String notificationContent;
	private String notificationCheck;
	private String notificationDate;
	private String notificationUrl;
	private String sendMemberProfileImg;
	private int sendMemberNo;
	private int receiveMemberNo;
	
	private String notificationType;
	private String title;
	private int pkNo;
}
