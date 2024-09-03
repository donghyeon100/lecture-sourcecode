package edu.kh.admin.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.kh.admin.member.domain.MemberEntity;


public interface MainRepository extends JpaRepository<MemberEntity, Integer>  {

	public MemberEntity findByMemberEmail(String memberEmail);
}
