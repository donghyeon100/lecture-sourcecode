package edu.kh.project.sms.contoller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@RestController
@Slf4j
@RequestMapping("sms")
@RequiredArgsConstructor
public class SMSController {
	

    final DefaultMessageService messageService;

    @PostMapping("/send-one")
    public SingleMessageSentResponse sendOne(@RequestBody Map<String, String> map) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01090311339");
        message.setTo(map.get("toNumber"));
        message.setText(map.get("msg"));

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
}
