package edu.kh.admin.member.contoller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.admin.member.domain.MemberEntity;
import edu.kh.admin.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberContoller {

	private final MemberService memberService;
	
	@GetMapping("findAll")
	public List<MemberEntity> findAll(){
		return memberService.findAll();
	}
	
//	@GetMapping("findCondition")
//	public List<Member>
}
