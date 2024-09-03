package edu.kh.admin.main.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.admin.main.repository.MainRepository;
import edu.kh.admin.member.domain.MemberEntity;
import edu.kh.admin.member.dto.MemberDetails;
import lombok.RequiredArgsConstructor;


/* UserDetailsService
 * - 사용자 인증을 처리하는 핵심 인터페이스
 */

@Service
@RequiredArgsConstructor
public class MainService implements UserDetailsService {
	
	private final BCryptPasswordEncoder encoder;
	private final MainRepository mainRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberEntity member = mainRepository.findByMemberEmail(username);
		
        if(member == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
        return org.springframework.security.core.userdetails.User
                .withUsername(member.getMemberEmail())
                .password(member.getMemberPw())
                .roles(String.valueOf(member.getAuthority()))
                .build();

	}

}
