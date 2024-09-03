package me.bdh.main.service;

/* UserDetailsService
 * - 사용자 인증을 처리하는 핵심 인터페이스
 */
//@Service
//@RequiredArgsConstructor
public class MainService /* implements UserDetailsService */{

	/*
	 * private final BCryptPasswordEncoder encoder;
	 * 
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { MemberEntity member =
	 * mainRepository.findByMemberEmail(username);
	 * 
	 * if(member == null) { throw new
	 * UsernameNotFoundException("User not found with username: " + username); }
	 * 
	 * return org.springframework.security.core.userdetails.User
	 * .withUsername(member.getMemberEmail()) .password(member.getMemberPw())
	 * .roles(String.valueOf(member.getAuthority())) .build();
	 * 
	 * }
	 */


}
