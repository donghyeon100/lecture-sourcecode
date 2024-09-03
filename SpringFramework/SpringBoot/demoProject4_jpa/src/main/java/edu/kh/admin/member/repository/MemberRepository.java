package edu.kh.admin.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.kh.admin.member.domain.MemberEntity;

// JpaRepository<사용한엔티티, id 타입>
// -> 자식 중 SimpleJpaRepository 사용 예정

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	
}
