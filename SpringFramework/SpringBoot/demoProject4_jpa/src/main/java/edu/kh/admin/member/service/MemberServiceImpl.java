package edu.kh.admin.member.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.kh.admin.member.domain.MemberEntity;
import edu.kh.admin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;

	@Override
	public List<MemberEntity> findAll() {
		
		// memberNo 역순으로 Member 모두 조회
		List<MemberEntity> selectList = memberRepository.findAll(Sort.by( Sort.Direction.DESC, "memberNo")); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		for(MemberEntity entity : selectList) {
			String contverEnrollDate = sdf.format(entity.getEnrollDate());
			entity.setConvertEnrollDate(contverEnrollDate);
		}
		
		
		return selectList;
	}
	
	
	public static final void method1() {}
	public final static void method2() {}
	
	
}
